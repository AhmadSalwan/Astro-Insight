package com.example.finallabmobile2024.Models;

public class APOD {
    String date;
    String explanation;
    String media_type;
    String title;
    String url;
    String serviceVersion;

    public APOD(String date, String explanation, String media_type,String serviceVersion, String title, String url) {
        this.date = date;
        this.explanation = explanation;
        this.media_type = media_type;
        this.serviceVersion=serviceVersion;
        this.title = title;
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        url = url;
    }
}
