package com.betvn.aptech88;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class Withdraw extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        ImageView imageView=findViewById(R.id.imageview_back_home);
        imageView.setOnClickListener(v->{
            Intent myIntent = new Intent(this, Home.class);
            startActivity(myIntent);
        });
    }
}