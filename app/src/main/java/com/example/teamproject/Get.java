package com.example.teamproject;

import com.google.gson.annotations.SerializedName;

public class Get implements Comparable<Get>{
    private String category;
    private String id;
    private String unit;
    public String market_name;
    public String tag;
    private double distance;
    private int p;

    @SerializedName("body")
    private String text;

    public void setDistance(double d) { this.distance = d; }
    public double getDistance() { return distance; }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public String getUnit() {
        return unit;
    }

    public int getP() {
        return p;
    }

    public String getText() {
        return text;
    }

    @Override
    public int compareTo(Get get) {
        if(this.p > get.p)
            return 1;
        else if(this.p < get.p)
            return -1;
        else
            return 0;
    }
}
