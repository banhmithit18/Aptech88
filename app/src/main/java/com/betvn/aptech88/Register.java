package com.betvn.aptech88;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText name, age, email, address, phone, user, password;
    Button btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_login = findViewById(R.id.login);
        btn_login.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, Login.class);
            startActivity(myIntent);
        });
        btn_register = findViewById(R.id.register);
        btn_register.setOnClickListener(v -> {
            save_register();
        });
    }

    private void CheckEmail(String emails) {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(Register.this);
        String url = "http://192.168.1.7:8080/AccountEmail"; // <----en
        Map<String, String> params = new HashMap();
        params.put("email", emails);
        JSONObject email = new JSONObject(params);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, email, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TODO: handle success
                Toast.makeText(Register.this, "Email Available", Toast.LENGTH_SHORT).show();
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //TODO: handle failure
                NetworkResponse errorRes = error.networkResponse;
                String stringData = "";
                if (errorRes != null && errorRes.data != null) {
                    try {
                        stringData = new String(errorRes.data, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
//                Toast.makeText(Register.this, stringData, Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        MyRequestQueue.add(jsonRequest);
    }

    private void save_register() {
        name=findViewById(R.id.name_register);
        age=findViewById(R.id.age_register);
        email=findViewById(R.id.email_register);
        address=findViewById(R.id.address_register);
        phone=findViewById(R.id.phonenumber_register);
        user=findViewById(R.id.username_register);
        password=findViewById(R.id.password_register);
        String nameR = name.getText().toString();
        String ageR = age.getText().toString();
        String emailR = email.getText().toString();
        String addressR = address.getText().toString();
        String phoneR = phone.getText().toString();
        String userR = user.getText().toString();
        String passwordR = password.getText().toString();
        if(nameR.equals(""))
        {
            name.requestFocus();
            name.setError("Name cannot be empty");
            return;
        }else if (ageR.equals("")){
            age.requestFocus();
            age.setError("Age cannot be empty");
            return;
        }else if (emailR.equals("")){
            email.requestFocus();
            email.setError("Enter valid Email address !");
            return;
        }else if (!(Patterns.EMAIL_ADDRESS.matcher(emailR).matches())){
            email.requestFocus();
            email.setError("Indian Email address !");
            return;
        }else if (addressR.equals("")){
            address.requestFocus();
            address.setError("Address cannot be empty");
            return;
        }else if (phoneR.equals("")){
            phone.requestFocus();
            phone.setError("Phone cannot be empty");
            return;
        }else if (userR.equals("")){
            user.requestFocus();
            user.setError("Username cannot be empty");
            return;
        }else if (passwordR.equals("")){
            password.requestFocus();
            password.setError("Password cannot be empty");
            return;
        }
//        CheckEmail(emailR);
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        String URL = "http://192.168.1.7:8080/AccountCreate";
//        Map<String, String> params = new HashMap();
//        params.put("name", nameR);
//        params.put("age", ageR);
//        params.put("address", addressR);
//        params.put("phonenumber", phoneR);
//        params.put("email", emailR);
//        params.put("username", userR);
//        params.put("password", passwordR);
//
//        JSONObject c = new JSONObject(params);
//
//        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URL, c, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                //TODO: handle success
//                    Toast.makeText(Register.this, "Success (Please check mail !!!)", Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                NetworkResponse errorRes = error.networkResponse;
//                String stringData = "";
//                if(errorRes != null && errorRes.data != null){
//                    try {
//                        stringData = new String(errorRes.data,"UTF-8");
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
//                }
//                Toast.makeText(Register.this, stringData, Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            @NonNull
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("Content-Type","application/json");
//                return params;
//            }
//        };
//        requestQueue.add(jsonRequest);


        //----------------------------------------------------------------------------
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = "http://192.168.1.7:8080/AccountCreate";
            JSONObject c= new JSONObject();
            c.put("name", nameR);
            c.put("age", ageR);
            c.put("address", addressR);
            c.put("phonenumber", phoneR);
            c.put("email", emailR);
            c.put("username", userR);
            c.put("password", passwordR);
            final String requestBody = c.toString();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(Register.this, "Success", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Register.this, stringData, Toast.LENGTH_SHORT).show();
                    }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }
                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}