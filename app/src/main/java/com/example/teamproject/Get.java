package com.example.teamproject;

import com.google.gson.annotations.SerializedName;

public class Get {
    private String category;
    private String id;
    private String unit;
    private int p;


    @SerializedName("body")
    private String text;

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
}
