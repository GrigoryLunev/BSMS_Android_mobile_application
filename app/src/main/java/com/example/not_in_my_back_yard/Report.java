package com.example.not_in_my_back_yard;

import android.net.Uri;

import java.net.URL;

public class Report {
    private double lat = 0;
    private double lon = 0;
    private String url = "";
    private String comment = "";
    private int isActive = 1;


    public Report() { }

    public double getLat() {
        return lat;
    }

    public Report setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLon() {
        return lon;
    }

    public Report setLon(double lon) {
        this.lon = lon;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Report setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Report setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public int getIsActive() {
        return isActive;
    }

    public Report setIsActive(int isActive) {
        this.isActive = isActive;
        return this;
    }
}
