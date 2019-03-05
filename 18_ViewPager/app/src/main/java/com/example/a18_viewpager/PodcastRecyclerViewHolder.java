package com.example.a09_1_podcasttest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.a09_1_podcasttest.model.PodcastData;

public class PodcastRecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView subTitle;
    TextView summary;
   // TextView fileUrl;
    TextView pubDate;


    public PodcastRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.textViewtitle);
        subTitle = itemView.findViewById(R.id.textViewsubTitle);
        summary = itemView.findViewById(R.id.textViewSummary);
       // fileUrl = itemView.findViewById(R.id.fileUrl);
        pubDate = itemView.findViewById(R.id.textViewPubDate);
    }

    public void bindData(PodcastData podcastData){
        title.setText(podcastData.getTitle());
        subTitle.setText(podcastData.getSubTitle());
        summary.setText(podcastData.getSummary());
     //   fileUrl.setText(podcastData.getFileUrl());
        pubDate.setText(podcastData.getPubDate());
    }
}
