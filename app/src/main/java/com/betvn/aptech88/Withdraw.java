package com.betvn.aptech88;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class Withdraw extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        Intent intent= getIntent();
        String id_acc= intent.getExtras().getString("id_account_home_withdraw");
        ImageView imageView=findViewById(R.id.imageview_back_home);
        imageView.setOnClickListener(v->{
            Intent intents=new Intent(getApplicationContext(),Home.class);
            intents.putExtra("id_account",id_acc);
            startActivity(intents);
        });
    }
}