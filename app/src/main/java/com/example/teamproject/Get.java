package com.example.teamproject;

import com.google.gson.annotations.SerializedName;

public class Get {
    private String gClass;
    private String id;
    private String unit;
    private int p;


    @SerializedName("body")
    private String text;

    public String getgClass() {
        return gClass;
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
}
