package com.deepak.sandeepwebservices.oneObjmulObj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.deepak.sandeepwebservices.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyModel2 myModel2;
    MyAdapter2 myAdapter2;
    List<MyModel2> myModel2List=new ArrayList<>();

    RequestQueue queue;
    ProgressDialog progressDialog;
    String baseUrl="https://run.mocky.io/v3/d23a54b8-280f-4bd9-bdd5-a9ed48b298ae";
    String baseurl2="https://run.mocky.io/v3/cf72e7d7-58f6-4103-af5b-3c6242dc4333";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView=findViewById(R.id.myRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        queue= Volley.newRequestQueue(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);


        myAdapter2=new MyAdapter2(this,myModel2List);
        recyclerView.setAdapter(myAdapter2);

        loadMoviz();
    }

    private void loadMoviz() {
        progressDialog.show();
        progressDialog.setMessage("Loading Data.....");

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, baseurl2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();
                try {
                    JSONArray movizArray= response.getJSONArray("moviz");
                    for(int i=0;i<movizArray.length();i++)
                    {
                        JSONObject jsonObject= movizArray.getJSONObject(i);
                        int ID=jsonObject.getInt("id");
                        String title=jsonObject.getString("title");
                        String url=jsonObject.getString("url");
                      /*  myModel2=new MyModel2(ID,title,url);
                        myModel2List.add(myModel2);
                        myAdapter2.notifyDataSetChanged();*/

                        JSONObject detailObj= movizArray.getJSONObject(i);
                        JSONObject object=detailObj.getJSONObject("Details");

                        String name1=object.getString("name1");
                        String name2=object.getString("name2");

                        myModel2=new MyModel2(ID,title,url,name1,name2);
                        myModel2List.add(myModel2);
                        myAdapter2.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Field name "+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Server exe name "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();


            }
        });
        queue.add(jsonObjectRequest);
    }
}