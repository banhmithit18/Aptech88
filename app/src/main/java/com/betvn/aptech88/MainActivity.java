package com.betvn.aptech88;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.nio.channels.GatheringByteChannel;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        drawerLayout=findViewById(R.id.drawer_layout);
//        navigationView=findViewById(R.id.nav_view);
//        toolbar=findViewById(R.id.toolbar);
//        navigationView.bringToFront();
//        setSupportActionBar(toolbar);
//        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_dawer_open,R.string.navigation_dawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//        navigationView.setNavigationItemSelectedListener(this);
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }else {
//            super.onBackPressed();
//        }
//
//    }
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.nav_home2) {
//            click1();
//        } else if(id == R.id.nav_home3) {
//
//        } else if(id == R.id.nav_home4) {
//
//        }
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }
//    public void click1(){
//        setContentView(R.layout.activity_home);
//    }
    }
}