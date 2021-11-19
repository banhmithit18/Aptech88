package com.betvn.aptech88;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.betvn.aptech88.Adapter.AdapterLeague;
import com.betvn.aptech88.Model.League;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Leauge extends AppCompatActivity {
    ListView listView;
    ImageView btn_back_home;
    List<League> leaugeList;
    AdapterLeague adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leauge);
        Intent intent= getIntent();
        String id_acc= intent.getExtras().getString("id_account_home_league");
        listView=findViewById(R.id.listview);
        leaugeList = new ArrayList<League>();
        get_league();
        adapter = new AdapterLeague(this,R.layout.list_tournaments,leaugeList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                League infor_league = leaugeList.get(position);
                String id_league=infor_league.getId();
                Intent intents=new Intent(getApplicationContext(),Match.class);
                intents.putExtra("id_league",id_league);
                intents.putExtra("id_account",id_acc);
                startActivity(intents);
            }
        });
        btn_back_home=findViewById(R.id.back_home);
        btn_back_home.setOnClickListener(v->{
            Intent intents=new Intent(getApplicationContext(),Home.class);
            intents.putExtra("id_account",id_acc);
            startActivity(intents);
        });
   }

    private void get_league() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://192.168.1.7:8080/LeagueTop";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONArray jsonArray = response;
                try {
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String logo = jsonObject.getString("logo");
                        leaugeList.add(new League(id,name,logo));
                    }
                    adapter.notifyDataSetChanged();//To prevent app from crashing when updating
                    //UI through background Thread
                }
                catch (Exception w)
                {
                    Toast.makeText(Leauge.this,w.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Leauge.this,"What Wrong",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

}