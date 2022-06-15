package com.example.navbarapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawer_id);

        NavigationView navigationView = findViewById(R.id.nav_id);
        navigationView.setNavigationItemSelectedListener(this);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if(menuItem.getItemId()==R.id.acc_id)
        {
            Intent intent=new Intent(this,accelerometer.class);
            startActivity(intent);
        }

        else if(menuItem.getItemId()==R.id.gyro_id)
        {
            Intent intent=new Intent(this,gyroscope.class);
            startActivity(intent);
        }
        else if(menuItem.getItemId()==R.id.prox_id)
        {
            Intent intent=new Intent(this,proximity.class);
            startActivity(intent);
        }
        else if(menuItem.getItemId()==R.id.shake_id)
        {
            Intent intent=new Intent(this,shake.class);
            startActivity(intent);
        }
        else if(menuItem.getItemId()==R.id.map_id)
        {
            Intent intent=new Intent(this,map.class);
            startActivity(intent);
        }
        else if(menuItem.getItemId()==R.id.gyro_data_id)
        {
            Intent intent=new Intent(this,gyro_data.class);
            startActivity(intent);
        }


        return false;
    }
}
