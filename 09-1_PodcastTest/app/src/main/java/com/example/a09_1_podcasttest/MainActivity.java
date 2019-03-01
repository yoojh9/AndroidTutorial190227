package com.example.a09_1_podcasttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.a09_1_podcasttest.model.PodcastData;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    PodcastTask podcastTask;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        podcastTask = new PodcastTask();
        podcastTask.execute("http://pod.ssenhosting.com/rss/rrojia2/rrojia2.xml");
        recyclerView = findViewById(R.id.recyclerView);

        podcastTask.setPodcastCallback(new PodcastTask.PodcastCallback() {
            @Override
            public void onFinish() {
                List<PodcastData> list = podcastTask.getPodcastList();
                setRecyclerView(list);
            }
        });
    }

    private void setRecyclerView(List<PodcastData> list){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        PodcastRecyclerViewAdapter adapter = new PodcastRecyclerViewAdapter(list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
