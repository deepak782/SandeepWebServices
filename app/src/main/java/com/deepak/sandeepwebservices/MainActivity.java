package com.deepak.sandeepwebservices;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    RequestQueue queue;
    ProgressDialog progressDialog;
    String baseUrl="https://run.mocky.io/v3/aa44443d-7ee3-43bc-9891-6dd0278fbcde";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.txt_data);

        queue= Volley.newRequestQueue(this);

        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);

    }

    public void getData(View view) {
        progressDialog.show();
        progressDialog.setMessage("Loading Data");


       /* StringRequest stringRequest=new StringRequest(Request.Method.GET, baseUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                textView.setText("Response is: " + response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

                textView.setText("That didn't work!");

            }
        });*/


        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, baseUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();

                try {
                    int userId= response.getInt("userId");
                    int id= response.getInt("id");
                    String title=response.getString("title");
                    String body=response.getString("body");

                    textView.setText(""+userId+"\n"+title+"\n"+body+"\n"+id);

                } catch (JSONException e) {
                    e.printStackTrace();
                    textView.setText(""+e.getLocalizedMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, ""+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(jsonObjectRequest);

    }
}