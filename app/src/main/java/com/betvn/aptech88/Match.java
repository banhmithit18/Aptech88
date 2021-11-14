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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.betvn.aptech88.Adapter.AdapterFixture;
import com.betvn.aptech88.Adapter.AdapterLeague;
import com.betvn.aptech88.Model.Fixture;
import com.betvn.aptech88.Model.League;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        fixtureList = new ArrayList<Fixture>();
        listView=findViewById(R.id.listview_match);
        Intent intent= getIntent();
        String id= intent.getExtras().getString("id_account");
        String id_league= intent.getExtras().getString("id_league");
        getmatch(id_league);
        String name= intent.getExtras().getString("name_league_match");
        adapter = new AdapterFixture(this,R.layout.list_match,fixtureList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fixture infor_fixture = fixtureList.get(position);
                int id_league=infor_fixture.getId();
                Intent intents=new Intent(getApplicationContext(),Betticket.class);
                intents.putExtra("id_league",id_league);
                intents.putExtra("id_account",id);
                startActivity(intents);
            }
        });

        btn_back_leauge=findViewById(R.id.back_leauge);
        btn_back_leauge.setOnClickListener(v->{
            Intent intents=new Intent(getApplicationContext(),Home.class);
            intents.putExtra("id_account",id);
            startActivity(intents);
        });
//        String name_left[]={"Premier League","Bundesliga","International","International","International"};
//        int image_left[]={R.drawable.nha,R.drawable.bundesliga,R.drawable.international,R.drawable.international,R.drawable.international};
//        String name_right[]={"Premier League","Bundesliga","International","International","International"};
//        int image_right[]={R.drawable.nha,R.drawable.bundesliga,R.drawable.international,R.drawable.international,R.drawable.international};
//        Match.MatchAdapter adapter=new Match.MatchAdapter(this,name_left,image_left,name_right,image_right);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position==0){
//                    Toast.makeText(Match.this, "Premier League", Toast.LENGTH_SHORT).show();
//                    Intent myIntent = new Intent(Match.this, Matchdetails.class);
//                    startActivity(myIntent);
//                }
//                if (position==1){
//                    Toast.makeText(Match.this, "Bundesliga", Toast.LENGTH_SHORT).show();
//                }
//                if (position==2){
//                    Toast.makeText(Match.this, "International", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    private void getmatch(String id_league) {
//        //get match
//    }
//
//    class MatchAdapter extends ArrayAdapter<String> {
//        Context context;
//        String name_left[];
//        int image_left[];
//        String name_right[];
//        int image_right[];
//        public MatchAdapter(Context c, String[] name_left, int[] image_left, String[] name_right, int[] image_right) {
//            super(c,R.layout.list_match,R.id.Name_Left,name_left);
//            this.context=c;
//            this.name_left=name_left;
//            this.name_right=name_right;
//            this.image_left=image_left;
//            this.image_right=image_right;
//        }
//
//
//        @NonNull
//        @Override
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            LayoutInflater layoutInflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View row=layoutInflater.inflate(R.layout.list_match,parent,false);
//            ImageView imageView_left=row.findViewById(R.id.imageview_left);
//            ImageView imageView_right=row.findViewById(R.id.imageview_right);
//            TextView name_lefts=row.findViewById(R.id.Name_Left);
//            TextView name_rights=row.findViewById(R.id.Name_Right);
//            imageView_left.setImageResource(image_left[position]);
//            imageView_right.setImageResource(image_right[position]);
//            name_lefts.setText(name_left[position]);
//            name_rights.setText(name_right[position]);
//            return row;
//        }

    }

    private void getmatch(String id_league) {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.7:8080/AccountLogin";
        Map<String, String> params = new HashMap();
        params.put("leagueId", id_league);
        JSONObject f = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, f, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TODO: handle success
                try {
                    for(int i=0;i<response.length();i++)
                    {
                        JSONObject jsonObject = response.getJSONObject(String.valueOf(i));
                        int home = jsonObject.getInt("home");
                        int away= jsonObject.getInt("away");
                        fixtureList.add(new Fixture(home,away));
                    }
                    adapter.notifyDataSetChanged();//To prevent app from crashing when updating
                    //UI through background Thread
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                //TODO: handle failure
//                Toast.makeText(Login.this, "Login Fail" + error, Toast.LENGTH_SHORT).show();
                NetworkResponse errorRes = error.networkResponse;
                String stringData = "";
                if(errorRes != null && errorRes.data != null){
                    try {
                        stringData = new String(errorRes.data,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(Match.this, stringData, Toast.LENGTH_SHORT).show();
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