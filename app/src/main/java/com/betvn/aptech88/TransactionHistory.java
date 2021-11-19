package com.betvn.aptech88;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.betvn.aptech88.Adapter.AdapterLeague;
import com.betvn.aptech88.Adapter.AdapterPayment;
import com.betvn.aptech88.Model.BetHistories;
import com.betvn.aptech88.Model.Fixture;
import com.betvn.aptech88.Model.League;
import com.betvn.aptech88.Model.Payment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionHistory extends AppCompatActivity {
    ListView listView_payment;
    ImageView transaction_history_to_home;
    String id_acc="";
    List<Payment> paymentList;
    AdapterPayment adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        listView_payment=findViewById(R.id.listview_payment);
        Intent intent= getIntent();
        id_acc= intent.getExtras().getString("id_account_home_transaction_history");
        transaction_history_to_home=findViewById(R.id.transaction_history_to_home);
        paymentList = new ArrayList<Payment>();
        adapter = new AdapterPayment(this,R.layout.list_transaction_history,paymentList);
        listView_payment.setAdapter(adapter);
        getwalletId(id_acc);
        transaction_history_to_home.setOnClickListener(v -> {
            Intent intents=new Intent(getApplicationContext(),Home.class);
            intents.putExtra("id_account",id_acc);
            startActivity(intents);
        });
        listView_payment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    private void getwalletId(String id_acc) {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.7:8080/FindWallet";
        Map<String, String> params = new HashMap();
        params.put("id", id_acc);
        JSONObject ids = new JSONObject(params);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, ids, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TODO: handle success
                try {
                    String id_wallet = response.getString("id");
                    load_payment(id_wallet);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse errorRes = error.networkResponse;
                String stringData = "";
                if (errorRes != null && errorRes.data != null) {
                    try {
                        stringData = new String(errorRes.data, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(TransactionHistory.this, stringData, Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        MyRequestQueue.add(jsonRequest);
    }

    private void load_payment(String id_wallet) {
        int id=Integer.parseInt(id_wallet);
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.7:8080/findpayment?id="+id;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONArray jsonArray = response;
                try {
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String type = jsonObject.getString("paymentType");
                        String date = jsonObject.getString("paymentDate");
                        String status = jsonObject.getString("status");
                        String amount = jsonObject.getString("amount");
                        String change_status="true";
                        if (status.equals(change_status)){
                            String status_paid="Paid";
                            paymentList.add(new Payment(type,amount,date,status_paid));
                        }else {
                            String status_paid="Unpaid";
                            paymentList.add(new Payment(type,amount,date,status_paid));
                        }

                    }
                    adapter.notifyDataSetChanged();//To prevent app from crashing when updating
                    //UI through background Thread
                }
                catch (Exception w)
                {
                    Toast.makeText(TransactionHistory.this,w.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TransactionHistory.this,"What Wrong",Toast.LENGTH_LONG).show();
            }
        });
        MyRequestQueue.add(jsonArrayRequest);
    }
}