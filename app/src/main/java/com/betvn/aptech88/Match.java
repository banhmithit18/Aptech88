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

public class Match extends AppCompatActivity {
    ListView listView;
    ImageView btn_back_leauge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        btn_back_leauge=findViewById(R.id.back_leauge);
        btn_back_leauge.setOnClickListener(v->{
            Intent myIntent = new Intent(this, Leauge.class);
            startActivity(myIntent);
        });
        listView=findViewById(R.id.listview_match);
        String name_left[]={"Premier League","Bundesliga","International","International","International"};
        int image_left[]={R.drawable.nha,R.drawable.bundesliga,R.drawable.international,R.drawable.international,R.drawable.international};
        String name_right[]={"Premier League","Bundesliga","International","International","International"};
        int image_right[]={R.drawable.nha,R.drawable.bundesliga,R.drawable.international,R.drawable.international,R.drawable.international};
        Match.MatchAdapter adapter=new Match.MatchAdapter(this,name_left,image_left,name_right,image_right);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    Toast.makeText(Match.this, "Premier League", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(Match.this, Matchdetails.class);
                    startActivity(myIntent);
                }
                if (position==1){
                    Toast.makeText(Match.this, "Bundesliga", Toast.LENGTH_SHORT).show();
                }
                if (position==2){
                    Toast.makeText(Match.this, "International", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    class MatchAdapter extends ArrayAdapter<String> {
        Context context;
        String name_left[];
        int image_left[];
        String name_right[];
        int image_right[];
        public MatchAdapter(Context c, String[] name_left, int[] image_left, String[] name_right, int[] image_right) {
            super(c,R.layout.list_match,R.id.Name_Left,name_left);
            this.context=c;
            this.name_left=name_left;
            this.name_right=name_right;
            this.image_left=image_left;
            this.image_right=image_right;
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.list_match,parent,false);
            ImageView imageView_left=row.findViewById(R.id.imageview_left);
            ImageView imageView_right=row.findViewById(R.id.imageview_right);
            TextView name_lefts=row.findViewById(R.id.Name_Left);
            TextView name_rights=row.findViewById(R.id.Name_Right);
            imageView_left.setImageResource(image_left[position]);
            imageView_right.setImageResource(image_right[position]);
            name_lefts.setText(name_left[position]);
            name_rights.setText(name_right[position]);
            return row;
        }

    }
}