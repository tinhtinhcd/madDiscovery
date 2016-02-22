package com.anhlt.maddiscover.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;

import com.anhlt.maddiscover.R;

public class CreateEvent extends madDiscovery {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mad_discovery);
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        setSupView(inflater.inflate(R.layout.event_form, null, false));
        getSupportActionBar().setTitle("Create Event");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_create_event, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }
}
