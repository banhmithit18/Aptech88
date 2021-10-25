package com.betvn.aptech88;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Button next_step1=findViewById(R.id.next_step1);
        next_step1.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, VerifyOTP.class);
            startActivity(myIntent);
        });
        ImageView back_step1=findViewById(R.id.back_step1);
        back_step1.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, Login.class);
            startActivity(myIntent);
        });
    }
}