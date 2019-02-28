package com.example.a08_xml.model;

public class WeatherData {
    private int day;
    private int hour;
    private float temp;
    private String wfKor;

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public void setWfKor(String wfKor) {
        this.wfKor = wfKor;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public float getTemp() {
        return temp;
    }

    public String getWfKor() {
        return wfKor;
    }

    public WeatherData(){}

    public WeatherData(int day, int hour, float temp, String wfKor) {
        this.day = day;
        this.hour = hour;
        this.temp = temp;
        this.wfKor = wfKor;
    }
}
