package com.betvn.aptech88;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class Momo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_momo);
        ImageView imageView=findViewById(R.id.imageview_back_home_to_momo);
        imageView.setOnClickListener(v->{
            Intent myIntent = new Intent(this, Transfer.class);
            startActivity(myIntent);
        });
        Button create_QR=findViewById(R.id.create_QR);
        create_QR.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, QRmomo.class);
            startActivity(myIntent);
        });
    }
}