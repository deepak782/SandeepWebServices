package com.deepak.retrofitapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoreDetails {

    @SerializedName("name1")
    @Expose
    private  String course1;
    @SerializedName("name2")
    @Expose
    private  String course2;

    public MoreDetails(String course1, String course2) {
        this.course1 = course1;
        this.course2 = course2;
    }

    public String getCourse1() {
        return course1;
    }

    public void setCourse1(String course1) {
        this.course1 = course1;
    }

    public String getCourse2() {
        return course2;
    }

    public void setCourse2(String course2) {
        this.course2 = course2;
    }
}
