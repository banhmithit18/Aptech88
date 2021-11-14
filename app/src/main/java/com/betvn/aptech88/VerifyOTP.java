package com.betvn.aptech88;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.FileObserver;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerifyOTP extends AppCompatActivity {
    EditText number_1, number_2, number_3, number_4, number_5, number_6;
    TextView txt_mobile;
    private String verificationId,id_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        number_1=findViewById(R.id.number_1);
        number_2=findViewById(R.id.number_2);
        number_3=findViewById(R.id.number_3);
        number_4=findViewById(R.id.number_4);
        number_5=findViewById(R.id.number_5);
        number_6=findViewById(R.id.number_6);
        txt_mobile=findViewById(R.id.txt_mobile);
        txt_mobile.setText(String.format(
               "+84 -%s ", getIntent().getStringExtra("mobile")
        ));
        setupOTP();
        Button next_step2=findViewById(R.id.next_step2);
        ImageView back_step2=findViewById(R.id.back_step2);
        back_step2.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, Login.class);
            startActivity(myIntent);
        });
        
        verificationId=getIntent().getStringExtra("verificationId");
        id_account=getIntent().getStringExtra("id");
        next_step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number_1.getText().toString().trim().isEmpty()
                    ||number_2.getText().toString().trim().isEmpty()
                    ||number_3.getText().toString().trim().isEmpty()
                    ||number_4.getText().toString().trim().isEmpty()
                    ||number_5.getText().toString().trim().isEmpty()
                    ||number_6.getText().toString().trim().isEmpty()){
                    Toast.makeText(VerifyOTP.this, "Enter valid code", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code=
                        number_1.getText().toString()
                        +number_2.getText().toString()
                        +number_3.getText().toString()
                        +number_4.getText().toString()
                        +number_5.getText().toString()
                        +number_6.getText().toString();
                if (verificationId != null){
                    PhoneAuthCredential phoneAuthProvider=PhoneAuthProvider.getCredential(
                            verificationId,
                            code
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthProvider).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent myIntent = new Intent(getApplicationContext(), NewPassword.class);
                                myIntent.putExtra("id_acc",id_account);
                                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(myIntent);
                            }else {
                                Toast.makeText(VerifyOTP.this, "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
    private void setupOTP(){
        number_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    number_2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        number_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    number_3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        number_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    number_4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        number_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    number_5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        number_5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    number_6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}