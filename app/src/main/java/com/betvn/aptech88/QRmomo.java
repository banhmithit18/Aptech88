package com.betvn.aptech88;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class QRmomo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrmomo);
        ImageView imageView=findViewById(R.id.imageview_back_home_to_momo);
        imageView.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, Momo.class);
            startActivity(myIntent);
        });
    }
}