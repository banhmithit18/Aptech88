package com.betvn.aptech88;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Transfer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        ImageView imageView=findViewById(R.id.imageview_back_home_to_momo);
        imageView.setOnClickListener(v->{
            Intent myIntent = new Intent(this, Home.class);
            startActivity(myIntent);
        });
        LinearLayout click_momo=findViewById(R.id.momo);
        click_momo.setOnClickListener(v->{
            Intent myIntent = new Intent(this, Momo.class);
            startActivity(myIntent);
        });
    }
}