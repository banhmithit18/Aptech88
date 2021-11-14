package com.betvn.aptech88;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {
    TextView forgot_password;
    ImageView profile_back_home;
    Button btn_update;
    EditText profile_name, profile_phone, profile_mail, profile_age,profile_address,profile_pass;
    String id_account="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent= getIntent();
        id_account= intent.getExtras().getString("id_account_home_profile");
        profile_back_home=findViewById(R.id.profile_back_home);
        forgot_password=findViewById(R.id.forgot_password);
        forgot_password.setOnClickListener(v -> {
            Intent intents=new Intent(getApplicationContext(),NewPassword.class);
            intents.putExtra("id_acc",id_account);
            startActivity(intents);
        });
        btn_update=findViewById(R.id.update_profile);
        profile_back_home.setOnClickListener(v->{
            Intent intents=new Intent(getApplicationContext(),Home.class);
            intents.putExtra("id_account",id_account);
            startActivity(intents);
        });
        load_client(id_account);
        btn_update.setOnClickListener(v->{
            update_account();
        });
    }

    private void update_account() {
        profile_phone=findViewById(R.id.profile_phone);
        profile_address=findViewById(R.id.profile_address);
        profile_age=findViewById(R.id.profile_age);
        profile_name=findViewById(R.id.profile_name);
        String nameR=profile_name.getText().toString();
        String ageR=profile_age.getText().toString();
        String addressR=profile_address.getText().toString();
        String phoneR=profile_phone.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://192.168.1.7:8080/AccountEdit";
        Map<String, String> params = new HashMap();
        params.put("id", id_account);
        params.put("name", nameR);
        params.put("age", ageR);
        params.put("address", addressR);
        params.put("phonenumber", phoneR);

        JSONObject c = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URL, c, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TODO: handle success
                    Toast.makeText(Profile.this, "Update Success", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse errorRes = error.networkResponse;
                String stringData = "";
                if(errorRes != null && errorRes.data != null){
                    try {
                        stringData = new String(errorRes.data,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(Profile.this, stringData, Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                return params;
            }
        };
        requestQueue.add(jsonRequest);
    }

    private void load_client(String id_account) {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.7:8080/FindAccount"; // <----en
        Map<String, String> params = new HashMap();
        params.put("id", id_account);
        JSONObject id = new JSONObject(params);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url,id, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TODO: handle success
                try {
                    String pass= response.getString("password");
                    String age= response.getString("age");
                    String name= response.getString("name");
                    String email = response.getString("email");
                    String address = response.getString("address");
                    String phonenumber = response.getString("phonenumber");
                    load_infor_account(name,email,phonenumber,address,age,pass);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //TODO: handle failure
                Toast.makeText(Profile.this, "Login Fail" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                return params;
            }
        };
        MyRequestQueue.add(jsonRequest);
    }
    private void load_infor_account(String name, String email, String phonenumber, String address,String age,String pass) {
        profile_phone=findViewById(R.id.profile_phone);
        profile_address=findViewById(R.id.profile_address);
        profile_age=findViewById(R.id.profile_age);
        profile_mail=findViewById(R.id.profile_email);
        profile_pass=findViewById(R.id.profile_pass);
        profile_name=findViewById(R.id.profile_name);
        profile_name.setText(name);
        profile_phone.setText(phonenumber);
        profile_age.setText(age);
        profile_mail.setText(email);
        profile_address.setText(address);
        profile_pass.setText(pass);
    }
}