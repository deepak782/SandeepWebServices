package com.deepak.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    MutipleObjModel mutipleObjModel;
    List<MutipleObjModel> mutipleObjModelList=new ArrayList<>();
    MutliAdapter mutliAdapter;
    ProgressDialog progressDialog;
    Retrofit retrofit;
    String baseUrl="https://run.mocky.io/v3/";
    Api api;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView=findViewById(R.id.List);
        retrofit=new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        api=retrofit.create(Api.class);
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);

        mutliAdapter=new MutliAdapter(this,mutipleObjModelList);
        listView.setAdapter(mutliAdapter);



    }

    public void load(View view) {
        mutipleObjModelList.clear();
        progressDialog.show();
        progressDialog.setMessage("Loading List....");

        Call<List<MutipleObjModel>> call=api.getAllData();

        call.enqueue(new Callback<List<MutipleObjModel>>() {
            @Override
            public void onResponse(Call<List<MutipleObjModel>> call, Response<List<MutipleObjModel>> response) {
                progressDialog.dismiss();

                if(response.code()!=200)
                {
                    Toast.makeText(ListActivity.this, "Error\n"+response, Toast.LENGTH_SHORT).show();
                }
                else
                {

                    List<MutipleObjModel> modelList=response.body();

                    for(MutipleObjModel mutipleObjModel:modelList)
                    {
                        String url=mutipleObjModel.getS2();
                        String title=mutipleObjModel.getS1();
                        int id=mutipleObjModel.getId();
                        mutipleObjModel=new MutipleObjModel(id,title,url);
                        mutipleObjModelList.add(mutipleObjModel);
                        mutliAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<MutipleObjModel>> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}