package com.betvn.aptech88;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(v->{
            Intent myIntent = new Intent(this, Register.class);
            startActivity(myIntent);
        });

        Button btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(v->{
            Intent myIntent = new Intent(this, Home.class);
            startActivity(myIntent);
        });
        TextView forgot_password=findViewById(R.id.forgot_password);
        forgot_password.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, ForgotPassword.class);
            startActivity(myIntent);
        });
    }
}