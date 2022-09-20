package com.deepak.retrofitapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleModel {

    @SerializedName("userId")
    @Expose
    private int value1;
    @SerializedName("id")
    @Expose
    private int value2;
    private String title;
    private String body;

    public int getValue1() {
        return value1;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public int getValue2() {
        return value2;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
