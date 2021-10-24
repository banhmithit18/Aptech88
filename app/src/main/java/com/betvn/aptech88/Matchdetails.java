package com.betvn.aptech88;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Matchdetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchdetails);
        ImageView kt=findViewById(R.id.imageview_back_match);
        kt.setOnClickListener(v->{
            Intent myIntent = new Intent(this, Match.class);
            startActivity(myIntent);
        });
        LinearLayout linearLayout=findViewById(R.id.in_bet);
        linearLayout.setOnClickListener(v->{
            Intent myIntent = new Intent(this, Betticket.class);
            startActivity(myIntent);
        });
    }
}