package com.example.a08_xml;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a08_xml.model.WeatherData;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //ListView listView;
    RecyclerView recyclerView;
    List<WeatherData> weatherList = new ArrayList<>();

    enum DataType { none, hourType, dayType, tempType, wfKorType }
    DataType type = DataType.none;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MyParserTask().execute("https://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1162058500");

        recyclerView = findViewById(R.id.recyclerView);
    }

    // Param: http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1162058500
    // Result: 0일 12시 9.0도 구름 조금
    class MyParserTask extends AsyncTask<String, Void, List<WeatherData>> {

        @Override
        protected void onPostExecute(List<WeatherData> list) {
           // 리스트뷰 일 때 설정
           // WeatherListViewAdapter adapter = new WeatherListViewAdapter(list, MainActivity.this);
            WeatherRecyclerViewAdapter adapter = new WeatherRecyclerViewAdapter(list);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }

        @Override
        protected List<WeatherData> doInBackground(String... strings) {

            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();
                URL url = new URL(strings[0]);
                xpp.setInput(url.openStream(), "utf-8");
                int eventType = xpp.getEventType();
                WeatherData weatherData = null;

                while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            String tag = xpp.getName();
                            switch (tag){
                                case "data":
                                    weatherData = new WeatherData();
                                    weatherList.add(weatherData);
                                    break;
                                case "hour":
                                    type = DataType.hourType;
                                    break;
                                case "day":
                                    type = DataType.dayType;
                                    break;
                                case "temp":
                                    type = DataType.tempType;
                                    break;
                                case "wfKor":
                                    type = DataType.wfKorType;
                                    break;
                            }
                            break;
                        case XmlPullParser.TEXT:
                            switch (type){
                                case hourType:
                                    int hour = Integer.parseInt(xpp.getText());
                                    weatherData.setHour(hour);
                                    break;
                                case dayType:
                                    int day = Integer.parseInt(xpp.getText());
                                    weatherData.setDay(day);
                                    break;
                                case tempType:
                                    float temp = Float.parseFloat(xpp.getText());
                                    weatherData.setTemp(temp);
                                    break;
                                case wfKorType:
                                    weatherData.setWfKor(xpp.getText());
                                    break;
                            }
                            type = DataType.none;
                            break;
                    }
                    eventType = xpp.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("xml", "count : "+weatherList.size());
            return weatherList;
        }
    }
}
