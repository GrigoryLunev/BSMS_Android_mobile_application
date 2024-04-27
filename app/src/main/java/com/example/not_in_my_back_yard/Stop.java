package com.example.not_in_my_back_yard;

public class Stop {

    private int StopId;

    private int StopCode;

    private String StopName;

    private String StopDesc;

    private double StopLat;

    private double StopLon;

    private int LocationType;

    private int ParentStation;

    private int ZoneId;

    public Stop(int stopId, int stopCode, String stopName, String stopDesc, double stopLat, double stopLon) {
        StopId = stopId;
        StopCode = stopCode;
        StopName = stopName;
        StopDesc = stopDesc;
        StopLat = stopLat;
        StopLon = stopLon;
        LocationType = 0;
        ParentStation = 0;
        ZoneId = 0;
    }

    public Stop(int stopId, int stopCode, String stopName, String stopDesc, double stopLat, double stopLon, int locationType, int parentStation, int zoneId) {
        StopId = stopId;
        StopCode = stopCode;
        StopName = stopName;
        StopDesc = stopDesc;
        StopLat = stopLat;
        StopLon = stopLon;
        LocationType = locationType;
        ParentStation = parentStation;
        ZoneId = zoneId;
    }

    public int getStopId() {
        return StopId;
    }

    public Stop setStopId(int stopId) {
        StopId = stopId;
        return this;
    }

    public int getStopCode() {
        return StopCode;
    }

    public Stop setStopCode(int stopCode) {
        StopCode = stopCode;
        return this;
    }

    public String getStopName() {
        return StopName;
    }

    public Stop setStopName(String stopName) {
        StopName = stopName;
        return this;
    }

    public String getStopDesc() {
        return StopDesc;
    }

    public Stop setStopDesc(String stopDesc) {
        StopDesc = stopDesc;
        return this;
    }

    public double getStopLat() {
        return StopLat;
    }

    public Stop setStopLat(double stopLat) {
        StopLat = stopLat;
        return this;
    }

    public double getStopLon() {
        return StopLon;
    }

    public Stop setStopLon(double stopLon) {
        StopLon = stopLon;
        return this;
    }

    public int getLocationType() {
        return LocationType;
    }

    public Stop setLocationType(int locationType) {
        LocationType = locationType;
        return this;
    }

    public int getParentStation() {
        return ParentStation;
    }

    public Stop setParentStation(int parentStation) {
        ParentStation = parentStation;
        return this;
    }

    public int getZoneId() {
        return ZoneId;
    }

    public Stop setZoneId(int zoneId) {
        ZoneId = zoneId;
        return this;
    }
}
