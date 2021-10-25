package com.betvn.aptech88;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class VerifyOTP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        Button next_step2=findViewById(R.id.next_step2);
        next_step2.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, NewPassword.class);
            startActivity(myIntent);
        });
        ImageView back_step2=findViewById(R.id.back_step2);
        back_step2.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, Login.class);
            startActivity(myIntent);
        });
    }
}