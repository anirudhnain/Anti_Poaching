package com.example.anirudh_pc.anti_poaching;

import android.content.Intent;


import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;

import android.view.View;


import android.widget.TabHost;
import android.widget.TextView;




/**
 * Created by anand on 3/5/15.
 */
public class UserHome extends ActionBarActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhome);


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("animal");
        tabSpec.setContent(R.id.tabanimal);
        tabSpec.setIndicator("Animals");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("userdetail");
        tabSpec.setContent(R.id.tabuserdetail);
        tabSpec.setIndicator("TrackDetails");
        tabHost.addTab(tabSpec);







    }







    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UserHome.this, MainActivity.class));
        finish();

    }

    public void create(View view) {
        Intent intent = new Intent(this, Create.class);
        startActivity(intent);

    }

    public void update(View view) {
        Intent intent = new Intent(this, Update.class);
        startActivity(intent);

    }

    public void delete(View view) {
        Intent intent = new Intent(this, Delete.class);
        startActivity(intent);

    }


    public void track(View view) {
        Intent intent = new Intent(this, track_Map.class);
        startActivity(intent);
    }



}


