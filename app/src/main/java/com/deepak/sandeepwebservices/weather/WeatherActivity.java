package com.deepak.sandeepwebservices.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.deepak.sandeepwebservices.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherActivity extends AppCompatActivity {

    EditText cityname;
    RequestQueue queue;
    ProgressDialog progressDialog;
    String baseUrl,cityStr;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        cityname=findViewById(R.id.edt_cityname);
        textView=findViewById(R.id.txt_response);

        queue= Volley.newRequestQueue(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
    }

    public void search(View view) {

        progressDialog.show();
        progressDialog.setMessage("Loading Weather Report");

        cityStr=cityname.getText().toString();
        baseUrl="https://api.openweathermap.org/data/2.5/weather?q="+cityStr+"&appid=a847de79ed9e5152022f478c2675fdf2";

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, baseUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();

                try {
                    int code=response.getInt("cod");
                    if(code==200)
                    {
                        String name=response.getString("name");

                        JSONObject jsonObject=response.getJSONObject("coord");
                        double lat= jsonObject.getDouble("lat");
                        double lon= jsonObject.getDouble("lon");

                        JSONArray jsonArray=response.getJSONArray("weather");
                        JSONObject innerObj=jsonArray.getJSONObject(0);
                        String main=innerObj.getString("main");
                        String description=innerObj.getString("description");


                        textView.setText("City Name:- "+name+"\n"+"Latitude:- "+lat+"\n"+"Longitude:- "+lon+"\n"+
                                "Main Weather:- "+main+"\n"+"Weather Desc:- "+description);
                        //Toast.makeText(WeatherActivity.this, ""+name, Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(WeatherActivity.this, "InValid Request", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(WeatherActivity.this, "Field Error \n"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    Toast.makeText(getApplicationContext(), "Time out or no connection  error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    //TODO
                    Toast.makeText(WeatherActivity.this, "AuthFailureError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    //TODO
                    Toast.makeText(WeatherActivity.this, "ServerError ", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    //TODO
                    Toast.makeText(WeatherActivity.this, "NetworkError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    //TODO
                    Toast.makeText(WeatherActivity.this, "ParseError", Toast.LENGTH_SHORT).show();
                }
                else 
                {
                    Toast.makeText(WeatherActivity.this, ""+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        queue.add(jsonObjectRequest);
    }
}