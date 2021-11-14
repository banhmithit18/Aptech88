package com.betvn.aptech88;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NewPassword extends AppCompatActivity {
    String id_acc;
    EditText new_pass, old_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        id_acc=getIntent().getStringExtra("id_acc");
//        id_acc="1";
        Button next_step3=findViewById(R.id.next_step3);
        next_step3.setOnClickListener(v -> {
            new_pass=findViewById(R.id.new_pass);
            old_pass=findViewById(R.id.old_pass);
            String pass_new=new_pass.getText().toString();
            String pass_old=old_pass.getText().toString();
            if (pass_old.equals("")){
                old_pass.requestFocus();
                old_pass.setError("Please enter old password");
                return;
            }else if(pass_new.equals("")){
                new_pass.requestFocus();
                new_pass.setError("Please enter new password");
                return;
            }
            Change_Pass(id_acc, pass_old, pass_new);
        });
        ImageView back_step3=findViewById(R.id.back_step3);
        back_step3.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, Login.class);
            startActivity(myIntent);
        });


    }

    private void Change_Pass(String id_acc, String old_pass, String new_pass) {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(NewPassword.this);
        String url = "http://192.168.1.7:8080/AccountPassword"; // <----en
        Map<String, String> params = new HashMap();
        params.put("id", id_acc);
        params.put("new_password", new_pass);
        params.put("old_password", old_pass);
        JSONObject cp = new JSONObject(params);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url,cp, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TODO: handle success
                try {
                    String id = response.getString("id");
                    Intent myIntent = new Intent(getApplicationContext(), Success.class);
                    startActivity(myIntent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //TODO: handle failure
                NetworkResponse errorRes = error.networkResponse;
                String stringData = "";
                if(errorRes != null && errorRes.data != null){
                    try {
                        stringData = new String(errorRes.data,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(NewPassword.this, stringData, Toast.LENGTH_SHORT).show();
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