package com.example.not_in_my_back_yard;

import com.google.type.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PostReport {
    private int ReportSource;
    private int Status;
    private String Date;
    private int StopId;
    private int HazardType;
    private String Description;
    private String PicPath;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar c = Calendar.getInstance();

    public PostReport(int stopId, String description) {
        this.ReportSource = 1;
        this.Status = 0;
        this.StopId = stopId;
        this.HazardType = 4;
        this.Description = description;
        this.PicPath = "";
        this.Date = sdf.format(c.getTime());
    }

    public String getDate() {
        return Date;
    }

    public PostReport(int stopId, String description, String picPath) {
        this.ReportSource = 1;
        this.Status = 0;
        this.StopId = stopId;
        this.HazardType = 4;
        this.Description = description;
        this.PicPath = picPath;
        this.Date = sdf.format(c.getTime());
    }

    public int getReportSource() {
        return ReportSource;
    }

    public void setReportSource(int reportSource) {
        ReportSource = reportSource;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getStopId() {
        return StopId;
    }

    public void setStopId(int stopId) {
        StopId = stopId;
    }

    public int getHazardType() {
        return HazardType;
    }

    public void setHazardType(int hazardType) {
        HazardType = hazardType;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicPath() {
        return PicPath;
    }

    public void setPicPath(String picPath) {
        PicPath = picPath;
    }
}
