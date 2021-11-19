package com.betvn.aptech88;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar2;
    String id_account="",name="";
    TextView name_account,money_account;
    Button btn_sendmoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent= getIntent();
        id_account= intent.getExtras().getString("id_account");
        name= intent.getExtras().getString("name_account");
        load_client(id_account);
        btn_sendmoney=findViewById(R.id.btn_sendmoney);
        name_account=findViewById(R.id.name_account);
        money_account=findViewById(R.id.money_account);
        btn_sendmoney.setOnClickListener(v->{
//            Intent myIntent = new Intent(this, Transfer.class);
//            startActivity(myIntent);
        });
        drawerLayout=findViewById(R.id.drawer_layout_home);
        navigationView=findViewById(R.id.nav_view);
        toolbar2=findViewById(R.id.toolbar2);
        navigationView.bringToFront();
        setSupportActionBar(toolbar2);
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawerLayout,toolbar2,R.string.navigation_dawer_open,R.string.navigation_dawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void load_client(String id_account) {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.7:8080/FindAccount"; // <----en
        Map<String, String> params = new HashMap();
        params.put("id", id_account);
        JSONObject id = new JSONObject(params);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url,id, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TODO: handle success
                try {
                    String name= response.getString("name");
                    name_account.setText(name);
                    JSONArray feedArray1 = response.getJSONArray("wallet");
                        for (int i = 0; i < feedArray1.length(); i++) {
                            try {
                                JSONObject responseObj = feedArray1.getJSONObject(i);
                                String amount = responseObj.getString("amount");
                                money_account.setText(amount);
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
                Toast.makeText(Home.this, "Login Fail" + error, Toast.LENGTH_SHORT).show();
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
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home2) {
            click_home();
        } else if(id == R.id.nav_home3) {
            click_match();
        } else if(id == R.id.nav_profile) {
            click_profile();
        }else if(id == R.id.nav_transfer) {
            click_transfer();
        }else if(id == R.id.nav_withdraw) {
            click_withdraw();
        }else if(id == R.id.nav_transaction_history) {
            click_transaction_history();
        }else if(id == R.id.nav_sign_out) {
            click_sign_out();
        }else if(id == R.id.nav_bet_history) {
            click_bet_history();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void click_bet_history() {
        Intent myIntent = new Intent(getApplicationContext(), BetHistory.class);
        myIntent.putExtra("id_account",id_account);
        startActivity(myIntent);
    }


    private void click_sign_out() {
        Intent myIntent = new Intent(this, Login.class);
        startActivity(myIntent);
    }
    private void click_profile() {
            Intent myIntent = new Intent(getApplicationContext(), Profile.class);
            myIntent.putExtra("id_account_home_profile",id_account);
            startActivity(myIntent);

    }

    private void click_transaction_history() {
        Intent myIntent = new Intent(getApplicationContext(), TransactionHistory.class);
        myIntent.putExtra("id_account_home_transaction_history",id_account);
        startActivity(myIntent);
    }

    private void click_transfer() {
        Intent myIntent = new Intent(getApplicationContext(), Transfer.class);
        myIntent.putExtra("id_account_home_transfer",id_account);
        startActivity(myIntent);
    }


    private void click_withdraw() {
        Intent myIntent = new Intent(getApplicationContext(), Withdraw.class);
        myIntent.putExtra("id_account_home_withdraw",id_account);
        startActivity(myIntent);
    }

    public void click_home(){
        Intent myIntent = new Intent(getApplicationContext(), Home.class);
        myIntent.putExtra("id_account",id_account);
        startActivity(myIntent);
    }
    public void click_match(){
        Intent myIntent = new Intent(getApplicationContext(), Leauge.class);
        myIntent.putExtra("id_account_home_league",id_account);
        startActivity(myIntent);
    }
}