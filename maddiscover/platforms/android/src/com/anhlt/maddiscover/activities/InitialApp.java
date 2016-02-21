package com.anhlt.maddiscover.activities;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.anhlt.maddiscover.R;
import com.anhlt.maddiscover.data.DatabaseHelper;

import java.util.concurrent.TimeUnit;
import android.os.Handler;

public class InitialApp extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial);
        setupApp();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(InitialApp.this, madDiscovery.class));
                finish();
            }
        }, 1500);

    }

    private void setupApp(){
        databaseHelper = new DatabaseHelper(this);
    }
}
