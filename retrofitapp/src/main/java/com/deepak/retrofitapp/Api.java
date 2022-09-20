package com.deepak.retrofitapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    //https://run.mocky.io/v3/
    @GET("aa44443d-7ee3-43bc-9891-6dd0278fbcde")
    Call<SingleModel> getData();

    //https://run.mocky.io/v3/c4de48b8-5b93-4f07-94bc-a3e339b1de5d
    @GET("c4de48b8-5b93-4f07-94bc-a3e339b1de5d")
    Call<List<MutipleObjModel>> getAllData();

   //https://run.mocky.io/v3/1fcd6c7a-0d7d-45db-99c3-6673d46a707e
   // https://run.mocky.io/v3/cf72e7d7-58f6-4103-af5b-3c6242dc4333
    @GET("cf72e7d7-58f6-4103-af5b-3c6242dc4333")
    Call<MovieJsonResponse> getMovieDAta();


}
