package com.example.a09_1_podcasttest.model;

public class PodcastData {
    private String title;
    private String subTitle;
    private String summary;
    private String fileUrl;
    private String pubDate;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getSummary() {
        return summary;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public String getPubDate() {
        return pubDate;
    }

    @Override
    public String toString() {
        return "PodcastData{" +
                "title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", summary='" + summary + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", pubDate='" + pubDate + '\'' +
                '}';
    }
}
