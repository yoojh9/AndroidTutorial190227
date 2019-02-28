package com.example.a08_xml;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        new MyParserTask().execute("https://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1162058500");
    }

    // Param: http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1162058500
    // Result: 0일 12시 9.0도 구름 조금
    class MyParserTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            String res = "";

            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();
                URL url = new URL(strings[0]);
                xpp.setInput(url.openStream(), "utf-8");
                int eventType = xpp.getEventType();
                boolean bRead = false;

                while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            String tag = xpp.getName();
                            if(tag.equals("hour") || tag.equals("day")
                                    || tag.equals("temp") || tag.equals("wfKor")){
                                bRead = true;
                            }
                            break;
                        case XmlPullParser.TEXT:
                            if(bRead) {
                                res += xpp.getText() + " ";
                                bRead = false;
                            }
                            break;
                    }
                    eventType = xpp.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return res;
        }
    }
}
