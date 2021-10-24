package com.betvn.aptech88;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btn_login=findViewById(R.id.login);
        btn_login.setOnClickListener(v->{
            Intent myIntent = new Intent(this, Login.class);
            startActivity(myIntent);
        });
    }
}