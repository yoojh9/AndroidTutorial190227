package com.example.a08_xml;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a08_xml.model.WeatherData;

import java.util.List;

public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter<WeatherRecyclerViewAdapter.WeatherViewHolder> {

    List<WeatherData> weatherList;

    public WeatherRecyclerViewAdapter(List<WeatherData> weatherList) {
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inf = LayoutInflater.from(viewGroup.getContext());
        View v = inf.inflate(R.layout.item_weather, viewGroup, false);
        return new WeatherViewHolder(v);
    }

    // viewHolder에게 데이터 바인딩만 해주고 실제적인 처리는 ViewHolder에서 처리하게 bindData() 함수를 생성함.
    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder weatherViewHolder, int i) {
        weatherViewHolder.bindData(weatherList.get(i));
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder{
        TextView textViewWfKor;
        TextView textViewTemp;
        TextView textViewDate;
        ImageView imageViewIcon;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWfKor = itemView.findViewById(R.id.textViewWfKor);
            textViewTemp = itemView.findViewById(R.id.textViewTemp);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            imageViewIcon = itemView.findViewById(R.id.imageViewIcon);
        }

        public void bindData(WeatherData data){
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
        }
    }
}
