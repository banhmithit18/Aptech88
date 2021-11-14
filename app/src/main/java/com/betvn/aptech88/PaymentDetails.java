package com.betvn.aptech88;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDetails extends AppCompatActivity {
    TextView txt_id,txt_amount,txt_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail);
        txt_id=findViewById(R.id.txt_id);
        txt_amount=findViewById(R.id.txt_amount);
        txt_status=findViewById(R.id.txt_status);
        Intent intent=getIntent();
        try {
            JSONObject jsonObject=new JSONObject(intent.getStringExtra("PaymentDetails"));
            showDetails(jsonObject.getJSONObject("response"),intent.getStringExtra("PaymentAmount"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showDetails(JSONObject response, String paymentAmount) {
        try {
            txt_id.setText(response.getString("id"));
            txt_amount.setText(response.getString("state"));
            txt_status.setText(response.getString(String.format("$"+paymentAmount)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}