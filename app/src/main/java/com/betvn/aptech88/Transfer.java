package com.betvn.aptech88;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Transfer extends AppCompatActivity {
    String id_acc="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        Intent intent= getIntent();
        id_acc= intent.getExtras().getString("id_account_home_transfer");
        ImageView imageView=findViewById(R.id.imageview_back_home_to_momo);
        imageView.setOnClickListener(v->{
            Intent intents=new Intent(getApplicationContext(),Home.class);
            intents.putExtra("id_account",id_acc);
            startActivity(intents);
        });
        LinearLayout click_momo=findViewById(R.id.momo);
        click_momo.setOnClickListener(v->{
            Intent myIntent = new Intent(this, Momo.class);
            startActivity(myIntent);
        });
        LinearLayout click_paypal=findViewById(R.id.paypal);
        click_paypal.setOnClickListener(v->{
            Intent intents=new Intent(getApplicationContext(),PayPal.class);
            intents.putExtra("id_acc_to_paypal",id_acc);
            startActivity(intents);
        });
    }
}