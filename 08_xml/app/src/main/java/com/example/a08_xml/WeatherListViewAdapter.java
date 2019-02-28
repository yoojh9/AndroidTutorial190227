package com.example.a08_xml;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a08_xml.model.WeatherData;

import java.util.List;

class WeatherListViewAdapter extends BaseAdapter {

    List<WeatherData> weatherList;
    Context context;

    public WeatherListViewAdapter(List<WeatherData> weatherList, Context context) {
        this.weatherList = weatherList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return weatherList.size();
    }

    @Override
    public Object getItem(int position) {
        return weatherList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // 1) 인플레이션 시키고
    // 2) 해당 데이터를 찾아서 세팅해 줌
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1) 최초의 convertView는 null이므로 처음에는 인플리케이션 시켜줘야함
        if(convertView == null){
            LayoutInflater inf = LayoutInflater.from(context);
            convertView = inf.inflate(R.layout.item_weather, parent, false);
        }

        // 2) weather 데이터를 찾아서 세팅해준다
        WeatherData data = weatherList.get(position);

        TextView textViewWfKor = convertView.findViewById(R.id.textViewWfKor);
        TextView textViewTemp = convertView.findViewById(R.id.textViewTemp);
        TextView textViewDate = convertView.findViewById(R.id.textViewDate);
        ImageView imageViewIcon = convertView.findViewById(R.id.imageViewIcon);

        textViewWfKor.setText(data.getWfKor());
        textViewTemp.setText(data.getTemp()+" 'c");
        textViewDate.setText(data.getDay() +" 일 " + data.getHour() + "시");

        int res = R.mipmap.ic_launcher;
        if(data.getWfKor().contains("구름")){
            res = R.drawable.ic_cloud;
        } else if(data.getWfKor().contains("맑음")){
            res = R.drawable.ic_wb_sunny;
        }
        imageViewIcon.setImageResource(res);

        return convertView;
    }
}