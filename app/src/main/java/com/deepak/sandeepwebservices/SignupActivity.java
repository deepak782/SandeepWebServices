package com.deepak.sandeepwebservices;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    EditText name,mail,mobile,password;
    String baseUrl="https://www.androiddada.com/Clostitch/Code/Clo/Register";
    RequestQueue queue;
    ProgressDialog progressDialog;

    String nameStr,mailStr,mobileStr,passwordStr;
    TextView response_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=findViewById(R.id.edt_name);
        mail=findViewById(R.id.edt_mail);
        mobile=findViewById(R.id.edt_mobile);
        password=findViewById(R.id.edt_password);
        response_txt=findViewById(R.id.response);

        queue= Volley.newRequestQueue(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
    }

    public void signup(View view) {
        progressDialog.show();
        progressDialog.setMessage("Creating User");

        nameStr=name.getText().toString();
        mailStr=mail.getText().toString();
        mobileStr=mobile.getText().toString();
        passwordStr=password.getText().toString();

        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("Name",nameStr);
        hashMap.put("Email",mailStr);
        hashMap.put("Mobile",mobileStr);
        hashMap.put("Password",passwordStr);



        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, baseUrl, new JSONObject(hashMap), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();

                try {
                    String code=response.getString("code");
                    if(code.equals("200"))
                    {
                        String message=response.getString("message");
                        String description=response.getString("description");
                        response_txt.setText(""+message+"\n"+description);
                    }
                    else  if(code.equals("400"))
                    {
                        String message=response.getString("message");
                        String description=response.getString("description");
                        response_txt.setText(""+message+"\n"+description);
                    }
                    else
                    {
                        response_txt.setText("no response");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

            }
        })/*{
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,Object> hashMap=new HashMap<>();
                hashMap.put("",mailStr);
                return super.getParams();
            }
        }*/;
        queue.add(jsonObjectRequest);
    }
}