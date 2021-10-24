package com.betvn.aptech88;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class Betticket extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_betticket);
        ImageView imageView_back_match=findViewById(R.id.imageview_back_bet);
        imageView_back_match.setOnClickListener(v->{
            Intent myIntent = new Intent(this, Matchdetails.class);
            startActivity(myIntent);
        });
        Button bet_out=findViewById(R.id.betout);
        bet_out.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, Match.class);
            startActivity(myIntent);
        });
    }
}