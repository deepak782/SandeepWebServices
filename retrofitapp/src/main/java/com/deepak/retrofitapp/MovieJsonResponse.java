package com.deepak.retrofitapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieJsonResponse {

    @SerializedName("moviz")
    @Expose
    private List<MutipleObjModel> mutipleObjModelList;

    public MovieJsonResponse(List<MutipleObjModel> mutipleObjModelList) {
        this.mutipleObjModelList = mutipleObjModelList;
    }

    public List<MutipleObjModel> getMutipleObjModelList() {
        return mutipleObjModelList;
    }

    public void setMutipleObjModelList(List<MutipleObjModel> mutipleObjModelList) {
        this.mutipleObjModelList = mutipleObjModelList;
    }
}
