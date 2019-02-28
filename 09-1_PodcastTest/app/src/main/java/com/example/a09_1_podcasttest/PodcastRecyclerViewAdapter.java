package com.example.a09_1_podcasttest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a09_1_podcasttest.model.PodcastData;

import java.util.List;

public class PodcastRecyclerViewAdapter extends RecyclerView.Adapter<PodcastRecyclerViewHolder> {

    List<PodcastData> podcastDataList;

    public PodcastRecyclerViewAdapter(List<PodcastData> podcastDataList) {
        this.podcastDataList = podcastDataList;
    }

    @NonNull
    @Override
    public PodcastRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inf = LayoutInflater.from(viewGroup.getContext());
        View v = inf.inflate(R.layout.podcast_item, viewGroup, false);
        return new PodcastRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PodcastRecyclerViewHolder podcastRecyclerViewHolder, int i) {
        podcastRecyclerViewHolder.bindData(podcastDataList.get(i));
    }

    @Override
    public int getItemCount() {
        return podcastDataList.size();
    }
}
