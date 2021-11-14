package com.betvn.aptech88;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
        Button btn_login, btn_register;
        EditText username,password;
        String amount="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(v->{
            Intent myIntent = new Intent(this, Register.class);
            startActivity(myIntent);
        });

        Button btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(v->{
//            Intent myIntent = new Intent(this, Home.class);
//            startActivity(myIntent);
            login();
        });
        TextView forgot_password=findViewById(R.id.forgot_password);
        forgot_password.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, ForgotPassword.class);
            startActivity(myIntent);
        });
    }

    private void login() {
        username = findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);
        String usernameL = username.getText().toString();
        String passwordL = password.getText().toString();
        if(usernameL.equals(""))
        {
            username.requestFocus();
            username.setError("Username cannot be empty");
            return;
        }else if (passwordL.equals("")){
            password.requestFocus();
            password.setError("Password cannot be empty");
            return;
        }
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.7:8080/AccountLogin";
        Map<String, String> params = new HashMap();
        params.put("username", usernameL);
        params.put("password", passwordL);

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TODO: handle success
                try {
                        String id = response.getString("id");
                        String name = response.getString("name");
                        Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),Home.class);
                        intent.putExtra("id_account",id);
                        intent.putExtra("amount_account",amount);
                        intent.putExtra("name_account",name);
                        startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                //TODO: handle failure
//                Toast.makeText(Login.this, "Login Fail" + error, Toast.LENGTH_SHORT).show();
                NetworkResponse errorRes = error.networkResponse;
                String stringData = "";
                if(errorRes != null && errorRes.data != null){
                    try {
                        stringData = new String(errorRes.data,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(Login.this, stringData, Toast.LENGTH_SHORT).show();
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
}