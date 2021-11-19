package com.betvn.aptech88;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.betvn.aptech88.Adapter.AdapterBetHistory;
import com.betvn.aptech88.Adapter.AdapterPayment;
import com.betvn.aptech88.Model.BetHistories;
import com.betvn.aptech88.Model.Payment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BetHistory extends AppCompatActivity {
    ListView listview_bet_hístory;
    List<BetHistories> bethistoryList;
    AdapterBetHistory adapter;
    String id_acc="";
    ImageView bet_history_to_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_history);
        listview_bet_hístory=findViewById(R.id.listview_bet_hístory);
        bethistoryList = new ArrayList<BetHistories>();
        adapter = new AdapterBetHistory(this,R.layout.list_bet_history,bethistoryList);
        listview_bet_hístory.setAdapter(adapter);
        Intent intent= getIntent();
        id_acc= intent.getExtras().getString("id_account");
        load_bet_history(id_acc);
        bet_history_to_home=findViewById(R.id.bet_history_to_home);
        bet_history_to_home.setOnClickListener(v -> {
            Intent intents=new Intent(getApplicationContext(),Home.class);
            intents.putExtra("id_account",id_acc);
            startActivity(intents);
        });
    }

    private void load_bet_history(String id_acc) {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.7:8080/FindAccount"; // <----en
        Map<String, String> params = new HashMap();
        params.put("id", id_acc);
        JSONObject id = new JSONObject(params);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url,id, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TODO: handle success
                try {
                    String name= response.getString("name");
                    JSONArray feedArray1 = response.getJSONArray("wallet");
                    for (int i = 0; i < feedArray1.length(); i++) {
                        try {
                            JSONObject responseObj = feedArray1.getJSONObject(i);
                            String amount = responseObj.getString("amount");
                            JSONArray feedArray2 = responseObj.getJSONArray("bet");
                            for (int i1 = 0; i1 < feedArray2.length(); i1++) {
                                try {
                                    JSONObject responseObj1 = feedArray2.getJSONObject(i1);
                                    String bet_amount = responseObj1.getString("betAmount");
                                    String bet_odd = responseObj1.getString("odd");
                                    String result = responseObj1.getString("win");
                                    String win="0.0";
                                    if (result.equals(win)){
                                        String status_bet="Waiting...";
                                        bethistoryList.add(new BetHistories(bet_odd,bet_amount,status_bet));
                                    }else {
                                        String status_bet="Paid / "+result;
                                        bethistoryList.add(new BetHistories(bet_odd,bet_amount,status_bet));
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //TODO: handle failure
                Toast.makeText(BetHistory.this, "Login Fail" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                return params;
            }
        };
        MyRequestQueue.add(jsonRequest);
    }
}