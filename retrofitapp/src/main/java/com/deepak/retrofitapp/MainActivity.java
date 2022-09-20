package com.deepak.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Api api;
    SingleModel singleModel;
    ProgressDialog progressDialog;
    Retrofit retrofit;
    String baseUrl="https://run.mocky.io/v3/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.txt_resposne);
        retrofit=new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        api=retrofit.create(Api.class);
    }

    public void singleObj(View view) {
        progressDialog.show();
        progressDialog.setMessage("Loading Data");

        Call<SingleModel> call=api.getData();
        call.enqueue(new Callback<SingleModel>() {
            @Override
            public void onResponse(Call<SingleModel> call, Response<SingleModel> response) {
                progressDialog.dismiss();
                if(response.code()!=200)
                {
                    Toast.makeText(MainActivity.this, "No Response"+response, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SingleModel singleModel= response.body();
                    String value="";
                    value+="User ID:- "+singleModel.getValue1()+"\n";
                    value+="ID:- "+singleModel.getValue2()+"\n";
                    value+="Title:- "+singleModel.getTitle()+"\n";
                    value+="Body:- "+singleModel.getBody();

                    textView.append(value);

                }

            }

            @Override
            public void onFailure(Call<SingleModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Server"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}