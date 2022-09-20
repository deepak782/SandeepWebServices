package com.deepak.retrofitapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MutipleObjModel {
    @SerializedName("albumId")
    @Expose
    private  int id;
    @SerializedName("id")
    @Expose
    private  int idvalue;



    @SerializedName("title")
    @Expose
    private  String  s1;

    @SerializedName("url")
    @Expose
    private String s2;

    @SerializedName("thumbnailUrl")
    @Expose
    private  String s3;

    public MutipleObjModel(int id, int idvalue, String s1, String s2, String s3, MoreDetails moreDetails) {
        this.id = id;
        this.idvalue = idvalue;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.moreDetails = moreDetails;
    }

    @SerializedName("Details")
    @Expose
    private MoreDetails moreDetails;


    public MutipleObjModel(int id, int idvalue, String s1, String s2, String s3) {
        this.id = id;
        this.idvalue = idvalue;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    public MutipleObjModel(int id, String s1, String s2) {
        this.id = id;
        this.s1 = s1;
        this.s2 = s2;
    }

    public MoreDetails getMoreDetails() {
        return moreDetails;
    }

    public void setMoreDetails(MoreDetails moreDetails) {
        this.moreDetails = moreDetails;
    }




    public int getIdvalue() {
        return idvalue;
    }

    public void setIdvalue(int idvalue) {
        this.idvalue = idvalue;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }






    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }
}
