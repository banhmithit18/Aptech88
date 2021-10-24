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

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button btn_sendmoney=findViewById(R.id.btn_sendmoney);
        btn_sendmoney.setOnClickListener(v->{
            Intent myIntent = new Intent(this, Transfer.class);
            startActivity(myIntent);
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
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void click_sign_out() {
        Intent myIntent = new Intent(this, Login.class);
        startActivity(myIntent);
    }

    private void click_transaction_history() {
        Intent myIntent = new Intent(this, TransactionHistory.class);
        startActivity(myIntent);
    }

    private void click_transfer() {
        Intent myIntent = new Intent(this, Transfer.class);
        startActivity(myIntent);
    }

    private void click_profile() {
        Intent myIntent = new Intent(this, Profile.class);
        startActivity(myIntent);
    }

    private void click_withdraw() {
        Intent myIntent = new Intent(this, Withdraw.class);
        startActivity(myIntent);
    }

    public void click_home(){
        Intent myIntent = new Intent(this, Home.class);
        startActivity(myIntent);
    }
    public void click_match(){
        Intent myIntent = new Intent(this, Leauge.class);
        startActivity(myIntent);
    }
}