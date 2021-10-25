package com.betvn.aptech88;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class NewPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        Button next_step3=findViewById(R.id.next_step3);
        next_step3.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, Success.class);
            startActivity(myIntent);
        });
        ImageView back_step3=findViewById(R.id.back_step3);
        back_step3.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, Login.class);
            startActivity(myIntent);
        });
    }
}