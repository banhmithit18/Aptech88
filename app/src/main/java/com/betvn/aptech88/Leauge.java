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

import java.util.Collections;

public class Leauge extends AppCompatActivity {
    ListView listView;
    ImageView btn_back_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leauge);
        btn_back_home=findViewById(R.id.back_home);
        btn_back_home.setOnClickListener(v->{
            Intent myIntent = new Intent(this, Home.class);
            startActivity(myIntent);
        });

        listView=findViewById(R.id.listview);
        String name[]={"Premier League","Bundesliga","International","International","International"};
        int id[]={R.drawable.nha,R.drawable.bundesliga,R.drawable.international,R.drawable.international,R.drawable.international};
//        Integer []image={R.drawable.nha,R.drawable.bundesliga,R.drawable.international};
//        MyAdapter adapter=new MyAdapter(getApplicationContext(),name,image);
 //       listView.setAdapter(adapter);
        MyAdapter adapter=new MyAdapter(this,name,id);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    Toast.makeText(Leauge.this, "Premier League", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(Leauge.this, Match.class);
                    startActivity(myIntent);
                }
                if (position==1){
                    Toast.makeText(Leauge.this, "Bundesliga", Toast.LENGTH_SHORT).show();
                }
                if (position==2){
                    Toast.makeText(Leauge.this, "International", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String name[];
        int id[];
        MyAdapter(Context c,String name[],int id[]){
            super(c,R.layout.list_tournaments,R.id.Name_T,name);
            this.context=c;
            this.name=name;
            this.id=id;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.list_tournaments,parent,false);
            ImageView imageView=row.findViewById(R.id.imageview);
            TextView myname=row.findViewById(R.id.Name_T);
            imageView.setImageResource(id[position]);
            myname.setText(name[position]);
            return row;
        }

    }
//    public class MyAdapter extends ArrayAdapter<String>{
//        private  final Context context;
//        private  final String[] name;
//        private  final Integer[] image;
//        public MyAdapter(Context context,String[]name,Integer[] image){
//            super(context,R.layout.list_tournaments,name);
//            this.context=context;
//            this.name=name;
//            this.image=image;
//        }
//        public View getView(int i, @Nullable View convertView, @NonNull ViewGroup parent) {
//           View view=getLayoutInflater().inflate(R.layout.list_tournaments,parent,false);
//            ImageView imageView=view.findViewById(R.id.imageview);
//            TextView myname=view.findViewById(R.id.Name_T);
//            imageView.setImageResource(image[i]);
//            myname.setText(name[i]);
//            return view;
//        }
//
//    }

}