package com.betvn.aptech88;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.betvn.aptech88.Model.Fixture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Betticket extends AppCompatActivity {
    String id_fixture="";
    String id_wallet="";
    String bettypeId="";
    String id_account="";
    String date="";
//    String current_amount="";
    Button accept_bets;
    EditText editTextNumbers_amount;
    TextView bet_value,bet_values,bet_odd,bet_name_home,bet_name_away,amount_current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_betticket);
        Intent intent= getIntent();
        id_account= intent.getExtras().getString("id_account");
        date= intent.getExtras().getString("date");
        String name_home= intent.getExtras().getString("name_home");
        String name_away= intent.getExtras().getString("name_away");
        String time= intent.getExtras().getString("time");
        String id_league= intent.getExtras().getString("id_league");
        String value= intent.getExtras().getString("value");
        String odd= intent.getExtras().getString("odd");
        String name_bet= intent.getExtras().getString("name_bet");
        id_fixture= intent.getExtras().getString("id_fixture");
        bettypeId= intent.getExtras().getString("bettypeId");
        get_amount_current(id_account);
        Get_Id_Wallet(id_account);
        load_bet_detail(name_bet,name_home,name_away,value,odd);

        ImageView imageView_back_match=findViewById(R.id.imageview_back_bet);
        imageView_back_match.setOnClickListener(v->{
            Intent intents=new Intent(getApplicationContext(),Matchdetails.class);
            intents.putExtra("id_league",id_league);
            intents.putExtra("id_to_match_detail",id_account);
            intents.putExtra("name_home",name_home);
            intents.putExtra("name_away",name_away);
            intents.putExtra("date",date);
            intents.putExtra("time",time);
            intents.putExtra("id_fixture",id_fixture);
            startActivity(intents);
        });
        Button bet_out=findViewById(R.id.betout);
        bet_out.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, Match.class);
            startActivity(myIntent);
        });
        accept_bets=findViewById(R.id.accept_bets);
        accept_bets.setOnClickListener(v -> {
            editTextNumbers_amount=findViewById(R.id.editTextNumbers_amount);
            String amount_bets=editTextNumbers_amount.getText().toString();
            if(amount_bets.equals(""))
            {
                editTextNumbers_amount.requestFocus();
                editTextNumbers_amount.setError("Please enter amount");
                return;
            }else {
                amount_current=findViewById(R.id.amount_current);
                String amount=amount_current.getText().toString();
                double check_amount=Double.parseDouble(amount);
                double check_wallet=Double.parseDouble(amount_bets);
                if (check_wallet>check_amount){
                    editTextNumbers_amount.requestFocus();
                    editTextNumbers_amount.setError("Please recharge");
                    return;
                }else {
                    create_bets(id_wallet,amount_bets,date,bettypeId,value,odd,id_fixture);
                }
            }
        });
    }

    private void get_amount_current(String id_account) {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.7:8080/amount_current";
        Map<String, String> params = new HashMap();
        params.put("id", id_account);
        JSONObject id = new JSONObject(params);
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, url, id, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String amount=response.getString("amount");
                    amount_current=findViewById(R.id.amount_current);
                    amount_current.setText(amount);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse errorRes = error.networkResponse;
                String stringData = "";
                if(errorRes != null && errorRes.data != null){
                    try {
                        stringData = new String(errorRes.data,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
//                Toast.makeText(Betticket.this, stringData, Toast.LENGTH_SHORT).show();
                Toast.makeText(Betticket.this,"Betted",Toast.LENGTH_LONG).show();
                editTextNumbers_amount.setText(null);
            }
        });
        MyRequestQueue.add(jsonObject);
    }

    private void create_bets(String id_wallet, String amount_bets, String date, String bettypeId, String value, String odd, String id_fixture) {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.7:8080/BetCreate";
        int walletId=Integer.parseInt(id_wallet);
        int id_fixtures=Integer.parseInt(id_fixture);
        int bettypeIds=Integer.parseInt(bettypeId);
        double betAmount=Double.parseDouble(amount_bets);
        double odds=Double.parseDouble(odd);
        //Create Main jSon object
        JSONObject jsonParams = new JSONObject();
        //Create json objects for two filter Ids
        JSONObject jsonParam2 =new JSONObject();

        try {
            jsonParam2.put("date",date);
            jsonParam2.put("status",false);
            jsonParam2.put("bettypeId",bettypeIds);
            jsonParam2.put("oddId",0);
            jsonParam2.put("value",value);
            jsonParam2.put("oddValue",odds);
            jsonParam2.put("fixtureId",id_fixtures);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Add the filter Id object to array
        //Create json array for filter
        JSONArray array=new JSONArray();
        array.put(jsonParam2);
        //Add object & array to main json object
        try {
            jsonParams.put("walletId",walletId);
            jsonParams.put("betAmount",betAmount);
            jsonParams.put("betdetail_odds",array);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, url, jsonParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    Toast.makeText(Betticket.this,"Betted sadadad",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse errorRes = error.networkResponse;
                String stringData = "";
                if(errorRes != null && errorRes.data != null){
                    try {
                        stringData = new String(errorRes.data,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
//                Toast.makeText(Betticket.this, stringData, Toast.LENGTH_SHORT).show();
                Toast.makeText(Betticket.this,"Betted",Toast.LENGTH_LONG).show();
                editTextNumbers_amount.setText(null);
                get_amount_current(id_account);
            }
        });
        MyRequestQueue.add(jsonObject);
    }

    private void Get_Id_Wallet(String id_account) {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.7:8080/walletid?id="+id_account;
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                        id_wallet = response.getString("id");
//                        Toast.makeText(Betticket.this,"Id la"+id_wallet,Toast.LENGTH_LONG).show();
                }
                catch (Exception w)
                {
                    Toast.makeText(Betticket.this,w.getMessage(),Toast.LENGTH_LONG).show();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Betticket.this,"Match not found",Toast.LENGTH_LONG).show();
            }
        });
        MyRequestQueue.add(jsonObject);
    }

    private void load_bet_detail(String name_bet, String name_home, String name_away, String value, String odd) {
        bet_values=findViewById(R.id.bet_values);
        bet_value=findViewById(R.id.bet_value);
        bet_odd=findViewById(R.id.bet_odd);
        bet_name_away=findViewById(R.id.bet_name_away);
        bet_name_home=findViewById(R.id.bet_name_home);
        bet_value.setText(value);
        bet_values.setText(name_bet);
        bet_odd.setText(odd);
        bet_name_away.setText(name_away);
        bet_name_home.setText(name_home);
    }
}