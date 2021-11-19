package com.betvn.aptech88;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.betvn.aptech88.Adapter.AdapterBets;
import com.betvn.aptech88.Model.Bets;
import com.betvn.aptech88.Model.Fixture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Matchdetails extends AppCompatActivity {
    TextView team_home,team_away,date_match_detail,time_match_detail;
    String id_acc="";
    String id_fixture="";
    String id_league="";
    String date="";
    ImageView kt;
    ListView listview_name_bets;
    ListView listview_ratio;
    List<Bets> bestList;
    AdapterBets adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchdetails);
        bestList = new ArrayList<Bets>();
        listview_name_bets=findViewById(R.id.listview_name_bets);
        kt=findViewById(R.id.imageview_back_match);
        Intent intent= getIntent();
        id_acc= intent.getExtras().getString("id_to_match_detail");
        id_fixture= intent.getExtras().getString("id_fixture");
        String name_home= intent.getExtras().getString("name_home");
        String name_away= intent.getExtras().getString("name_away");
        date= intent.getExtras().getString("date");
        String time= intent.getExtras().getString("time");
        id_league= intent.getExtras().getString("id_league");
        load_match_detail(name_home,name_away,date,time);
        load_odd(id_fixture);
        adapter = new AdapterBets(this,R.layout.list_bets,bestList);
        listview_name_bets.setAdapter(adapter);
        listview_name_bets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bets infor_bets = bestList.get(position);
                String bettypeId=infor_bets.getId();
                String name_bet=infor_bets.getName_bet();
                String value=infor_bets.getValue();
                String odd=infor_bets.getOdd();

                Intent intents=new Intent(getApplicationContext(),Betticket.class);
                intents.putExtra("name_bet",name_bet);
                intents.putExtra("bettypeId",bettypeId);
                intents.putExtra("value",value);
                intents.putExtra("odd",odd);
                intents.putExtra("id_fixture",id_fixture);
                intents.putExtra("name_home",name_home);
                intents.putExtra("name_away",name_away);
                intents.putExtra("id_account",id_acc);
                intents.putExtra("date",date);
                intents.putExtra("time",time);
                intents.putExtra("id_league",id_league);
                startActivity(intents);

            }
        });
        kt.setOnClickListener(v->{
            Intent myIntent = new Intent(getApplicationContext(), Match.class);
            myIntent.putExtra("id_account",id_acc);
            myIntent.putExtra("id_league",id_league);
            startActivity(myIntent);
        });
    }

    private void load_odd(String id_fixture) {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "https://api-football-v1.p.rapidapi.com/v3/odds?fixture="+id_fixture+"&bookmaker=6";
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray feedArray1 = response.getJSONArray("response");
                    for (int i = 0; i < feedArray1.length(); i++) {
                        try {
                            JSONObject responseObj = feedArray1.getJSONObject(i);
                            String amount = responseObj.getString("league");
                            JSONArray feedArray2 = responseObj.getJSONArray("bookmakers");
                            for (int i1 = 0; i1 < feedArray2.length(); i1++) {
                                try {
                                    JSONObject responseObj2 = feedArray2.getJSONObject(i1);
                                    String Bwin = responseObj2.getString("name");
                                    String id = responseObj2.getString("id");
                                    JSONArray feedArray3 = responseObj2.getJSONArray("bets");
                                    for (int i3 = 0; i3 < feedArray3.length(); i3++) {
                                        try {
                                            JSONObject responseObj3 = feedArray3.getJSONObject(i3);
                                            String name_bets = responseObj3.getString("name");
                                            String id_bets = responseObj3.getString("id");
                                            JSONArray feedArray4 = responseObj3.getJSONArray("values");
                                            for (int i4 = 0; i4 < feedArray4.length(); i4++) {
                                                try {
                                                    JSONObject responseObj4 = feedArray4.getJSONObject(i4);
                                                    String value_home = responseObj4.getString("value");
                                                    String odd_home = responseObj4.getString("odd");
                                                    bestList.add(new Bets(id_bets,name_bets,value_home,odd_home));
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
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
//                    String bookmakers=jsonObject.getString("bookmakers");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Matchdetails.this, "Match not found", Toast.LENGTH_LONG).show();
            }
        }){

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("x-rapidapi-host", "api-football-v1.p.rapidapi.com");
                headers.put("x-rapidapi-key", "c20af5d45emsh3116f32c09643fcp1848d8jsn2559bfc29c69");
                return headers;
            }
        };
        MyRequestQueue.add(jsonArrayRequest);
    }

    private void load_match_detail(String name_home, String name_away, String date, String time) {
        team_home=findViewById(R.id.team_home);
        team_away=findViewById(R.id.team_away);
        date_match_detail=findViewById(R.id.date_match_detail);
        time_match_detail=findViewById(R.id.time_match_detail);
        team_home.setText(name_home);
        team_away.setText(name_away);
        date_match_detail.setText(date);
        time_match_detail.setText(time);
    }
}