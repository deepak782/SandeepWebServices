package com.deepak.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArraywithMutipleObjActivity extends AppCompatActivity {

    ListView listView;
    ProgressDialog progressDialog;
    Retrofit retrofit;
    String baseUrl="https://run.mocky.io/v3/";
    Api api;
    MovieJsonResponse movieJsonResponse;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arraywith_mutiple_obj);
        listView=findViewById(R.id.simpleList);
        retrofit=new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        api=retrofit.create(Api.class);

        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);

        arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

    }

    public void load1(View view) {
        progressDialog.show();
        progressDialog.setMessage("Loading Moviz.....");
        Call<MovieJsonResponse> call=api.getMovieDAta();
        call.enqueue(new Callback<MovieJsonResponse>() {
            @Override
            public void onResponse(Call<MovieJsonResponse> call, Response<MovieJsonResponse> response) {
                progressDialog.dismiss();
                if(response.code()!=200)
                {
                    Toast.makeText(ArraywithMutipleObjActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    List<MutipleObjModel> mutipleObjModelList=response.body().getMutipleObjModelList();

                    for(MutipleObjModel mutipleObjModel:mutipleObjModelList)
                    {
                        String url=mutipleObjModel.getS2();
                        String title=mutipleObjModel.getS1();
                        String thumbnailUrl=mutipleObjModel.getS3();
                        int albumid=mutipleObjModel.getId();
                        int id=mutipleObjModel.getIdvalue();
                        MoreDetails moreDetails= mutipleObjModel.getMoreDetails();
                        String course1=moreDetails.getCourse1();
                        String course2=moreDetails.getCourse2();
                        //arrayList.add(""+albumid+"\n"+id+"\n"+title+"\n"+url+"\n"+thumbnailUrl);
                        arrayList.add(""+albumid+"\n"+id+"\n"+title+"\n"+url+"\n"+thumbnailUrl+"\n"+course1+"\n"+course2);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieJsonResponse> call, Throwable t) {
                progressDialog.dismiss();


            }
        });
    }
}