package com.example.a09_1_podcasttest;

import android.os.AsyncTask;
import android.util.Log;

import com.example.a09_1_podcasttest.model.PodcastData;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PodcastTask extends AsyncTask<String, Void, List<PodcastData>> {

    private ArrayList<PodcastData> podcastList = new ArrayList<>();

    private enum DataType { none, titleType, subTitleType, summaryType, fileUrlType, pubDateType }
    DataType type = DataType.none;

    public interface PodcastCallback {
        void onFinish();
    }

    PodcastCallback podcastCallback;

    public void setPodcastCallback(PodcastCallback podcastCallback) {
        this.podcastCallback = podcastCallback;
    }

    @Override
    protected void onPostExecute(List<PodcastData> podcastData) {
        if(podcastCallback!=null){
            podcastCallback.onFinish();
        }
    }

    public ArrayList<PodcastData> getPodcastList() {
        return podcastList;
    }

    @Override
    protected List<PodcastData> doInBackground(String... strings) {

        PodcastData podcastData = null;

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            URL url = new URL(strings[0]);
            xpp.setInput(url.openStream(), "utf-8");
            int eventType = xpp.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT){
                switch(eventType) {
                    case XmlPullParser.START_TAG:
                        String tag = xpp.getName();

                        switch(tag){
                            case "item" :
                                podcastData = new PodcastData();
                                podcastList.add(podcastData);

                                switch(xpp.getName()) {
                                    case "title":
                                        type = DataType.titleType;
                                        break;
                                    case "itunes:subtitle":
                                        type = DataType.subTitleType;
                                        break;
                                    case "itunes:summary":
                                        type = DataType.summaryType;
                                        break;
                                    case "guid":
                                        type = DataType.fileUrlType;
                                        break;
                                    case "pubDate":
                                        type = DataType.pubDateType;
                                        break;
                                }
                                break;
                            default:
                                break;
                        }
                        break;

                    case XmlPullParser.TEXT:
                        switch(type) {
                            case titleType:
                                String title = xpp.getText();
                                podcastData.setTitle(title);
                                break;
                            case subTitleType:
                                podcastData.setSubTitle(xpp.getText());
                                break;
                            case summaryType:
                                podcastData.setSummary(xpp.getText());
                                break;
                            case fileUrlType:
                                podcastData.setFileUrl(xpp.getText());
                                break;
                            case pubDateType:
                                podcastData.setPubDate(xpp.getText());
                                break;
                        }
                        type = DataType.none;
                        break;
                }
                eventType = xpp.next();
            }

        } catch(Exception e){
            Log.e("Parser Error", e.getMessage());
            e.printStackTrace();
        }
        Log.d("podCastList " , podcastList.size()+" 개");
        return podcastList;
    }


}
