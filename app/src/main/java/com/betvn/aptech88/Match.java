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
import com.betvn.aptech88.Adapter.AdapterFixture;
import com.betvn.aptech88.Adapter.AdapterLeague;
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

public class Match extends AppCompatActivity {
    ListView listView;
    ImageView btn_back_leauge;
    List<Fixture> fixtureList;
    AdapterFixture adapter;
    String ids="";
    String id_leagues="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        fixtureList = new ArrayList<Fixture>();
        listView=findViewById(R.id.listview_match);
        Intent intent= getIntent();
        ids= intent.getExtras().getString("id_account");
        id_leagues= intent.getExtras().getString("id_league");
        getmatch(id_leagues);
        adapter = new AdapterFixture(this,R.layout.list_match,fixtureList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fixture infor_fixture = fixtureList.get(position);
                String id_league=infor_fixture.getId();
                String id_fixture=infor_fixture.getId();
                String name_home=infor_fixture.getName_team_home();
                String name_away=infor_fixture.getName_team_away();
                String date=infor_fixture.getDate();
                String time=infor_fixture.getTime();

                Intent intents=new Intent(getApplicationContext(),Matchdetails.class);
                intents.putExtra("id_league",id_leagues);
                intents.putExtra("id_to_match_detail",ids);
                intents.putExtra("name_home",name_home);
                intents.putExtra("name_away",name_away);
                intents.putExtra("date",date);
                intents.putExtra("time",time);
                intents.putExtra("id_fixture",id_fixture);
                startActivity(intents);
            }
        });

        btn_back_leauge=findViewById(R.id.back_leauge);
        btn_back_leauge.setOnClickListener(v->{
            Intent intents=new Intent(getApplicationContext(),Leauge.class);
            intents.putExtra("id_account_home_league",ids);
            startActivity(intents);
        });

    }

    private void getmatch(String id_league) {
        int id=Integer.parseInt(id_league);
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.7:8080/getfixture?id="+id;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONArray jsonArray = response;
                try {
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id_fixture = jsonObject.getString("id");
                        String home = jsonObject.getString("home");
                        String away = jsonObject.getString("away");
                        String date = jsonObject.getString("date");
                        String time = jsonObject.getString("time");
                        String homeTeam = jsonObject.getString("homeTeam");
                        String awayTeam = jsonObject.getString("awayTeam");
                        JSONObject Js_home = new JSONObject(homeTeam);
                        String name_home= Js_home.getString("name");
                        String logo_home= Js_home.getString("logo");
                        JSONObject Js_away = new JSONObject(awayTeam);
                        String name_away= Js_away.getString("name");
                        String logo_away= Js_away.getString("logo");
                        fixtureList.add(new Fixture(id_fixture,home,away,date,time,logo_home,logo_away,name_home,name_away));

                    }
                    adapter.notifyDataSetChanged();//To prevent app from crashing when updating
                }
                catch (Exception w)
                {
                    Toast.makeText(Match.this,w.getMessage(),Toast.LENGTH_LONG).show();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Match.this,"Match not found",Toast.LENGTH_LONG).show();
            }
        });
        MyRequestQueue.add(jsonArrayRequest);
    }
}