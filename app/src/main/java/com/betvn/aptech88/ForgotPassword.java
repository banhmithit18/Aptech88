package com.betvn.aptech88;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

public class ForgotPassword extends AppCompatActivity {
    EditText your_number;
    ProgressBar progressBar;
    Button next_step1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        your_number=findViewById(R.id.your_number);
         next_step1=findViewById(R.id.next_step1);

        next_step1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (your_number.getText().toString().trim().isEmpty()){
                    Toast.makeText(ForgotPassword.this, "Enter mobile", Toast.LENGTH_SHORT).show();
                    return;
                }
                check_phone(your_number.getText().toString());
//                progressBar.setVisibility(View.VISIBLE);
//                next_step1.setVisibility(View.INVISIBLE);
            }

            private void check_phone(String phone) {
                RequestQueue MyRequestQueue = Volley.newRequestQueue(ForgotPassword.this);
                String url = "http://192.168.1.7:8080/AccountPhone"; // <----en
                Map<String, String> params = new HashMap();
                params.put("phonenumber", phone);
                JSONObject id = new JSONObject(params);
                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url,id, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //TODO: handle success
                        try {
                            String id = response.getString("id");
                            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                    "+84" + phone,
                                    60,
                                    TimeUnit.SECONDS,
                                    ForgotPassword.this,
                                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                                        @Override
                                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                                progressBar.setVisibility(View.GONE);
//                                next_step1.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onVerificationFailed(@NonNull FirebaseException e) {
//                                progressBar.setVisibility(View.GONE);
//                                next_step1.setVisibility(View.VISIBLE);
                                            Toast.makeText(ForgotPassword.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                progressBar.setVisibility(View.GONE);
//                                next_step1.setVisibility(View.VISIBLE);
                                            Intent myIntent = new Intent(getApplicationContext(), VerifyOTP.class);
                                            myIntent.putExtra("mobile",phone);
                                            myIntent.putExtra("id",id);
                                            myIntent.putExtra("verificationId",verificationId);
                                            startActivity(myIntent);
                                        }
                                    }
                            );
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
                        Toast.makeText(ForgotPassword.this, stringData, Toast.LENGTH_SHORT).show();
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
        });
        ImageView back_step1=findViewById(R.id.back_step1);
        back_step1.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, Login.class);
            startActivity(myIntent);
        });
    }
}