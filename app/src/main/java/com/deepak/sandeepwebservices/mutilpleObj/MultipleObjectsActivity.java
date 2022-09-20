package com.deepak.sandeepwebservices.mutilpleObj;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.deepak.sandeepwebservices.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MultipleObjectsActivity extends AppCompatActivity {

    Spinner spinner;
    MyModel myModel;
    MyAdapter myAdapter;
    List<MyModel> myModelList=new ArrayList<>();
    RequestQueue queue;
    ProgressDialog progressDialog;
    String baseUrl="https://run.mocky.io/v3/4ee99c12-04bf-4707-9fec-30bba437a631";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_objects);

        spinner=findViewById(R.id.countrySpinner);
        queue= Volley.newRequestQueue(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);

        myAdapter=new MyAdapter(this,myModelList);
        spinner.setAdapter(myAdapter);

        loadSpinner();
    }

    private void loadSpinner() {
        progressDialog.show();
        progressDialog.setMessage("Loading...");

        myModel=new MyModel("-- Select Country --","","");
        myModelList.add(myModel);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, baseUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.dismiss();

                for(int i=0;i< response.length();i++)
                {
                    try {
                        JSONObject jsonObject= response.getJSONObject(i);
                        String cityname=jsonObject.getString("cityname");
                        String country=jsonObject.getString("country");
                        String code=jsonObject.getString("code");
                        myModel=new MyModel(country,cityname,code);
                        myModelList.add(myModel);
                        myAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MultipleObjectsActivity.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                progressDialog.dismiss();
                Toast.makeText(MultipleObjectsActivity.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();



            }
        });

        queue.add(jsonArrayRequest);
    }
}