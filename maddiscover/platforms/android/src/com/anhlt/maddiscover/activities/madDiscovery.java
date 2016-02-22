package com.anhlt.maddiscover.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.anhlt.maddiscover.R;
import com.anhlt.maddiscover.fragments.ListEvent;
import com.anhlt.maddiscover.fragments.ListOrganizer;
import com.anhlt.maddiscover.fragments.ListVenue;
import com.anhlt.maddiscover.services.EventService;
import com.anhlt.maddiscover.services.VenueService;

/**
 * Created by anhlt on 2/19/16.
 */
public class madDiscovery extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;

    private static int position =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mad_discovery);

        mTitle = mDrawerTitle = getTitle();
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,  R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        EventService eventService = new EventService(getApplicationContext());
        VenueService venueService = new VenueService();
        switch(item.getItemId()) {
//            case R.id.search:
//                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
//                intent.putExtra(SearchManager.QUERY, getSupportActionBar().getTitle());
//                if (intent.resolveActivity(getPackageManager()) != null)
//                    startActivity(intent);
//                return true;
            case R.id.search_event:
                eventService.searchEvent();
                return true;
            case R.id.create_event:
                eventService.createEvent();
                return true;
            case R.id.delete_event:
                eventService.deleteEvent();
                return true;
            case R.id.edit_event:
                eventService.editEvent();
                return true;
            case R.id.search_venue:
                venueService.searchVenue();
                return true;
            case R.id.create_venue:
                venueService.createVenue();
                return true;
            case R.id.edit_venue:
                venueService.editVenue();
                return true;
            case R.id.delete_venue:
                venueService.deleteVenue();
                return true;
            case R.id.search_organizer:
                venueService.searchVenue();
                return true;
            case R.id.create_organizer:
                venueService.createVenue();
                return true;
            case R.id.edit_organizer:
                venueService.editVenue();
                return true;
            case R.id.delete_organizer:
                venueService.deleteVenue();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        Fragment fragment = null;
        if(position==0)
            listEvent(fragment);
        if(position==1)
            listVenue(fragment);
        if(position==2)
            listOrganizer(fragment);

        setTitle(mPlanetTitles[position]);
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void listEvent(Fragment fragment){
        fragment = new ListEvent();
        commitFragment(fragment);
        position = 0;
    }

    private void listVenue(Fragment fragment){
        fragment = new ListVenue();
        commitFragment(fragment);
        position = 1;
    }

    private void listOrganizer(Fragment fragment){
        fragment = new ListOrganizer();
        commitFragment(fragment);
        position = 2;
    }

    protected void commitFragment(Fragment fragment){
       try {
           FragmentManager fragmentManager = getFragmentManager();
           fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
       }catch (Exception ex){
            System.out.print(ex.toString());
       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        switch (position){
            case 0:
                inflater.inflate(R.menu.menu_event, menu);
                return true;
            case 1:
                inflater.inflate(R.menu.menu_venue, menu);
                return true;
            case 2:
                inflater.inflate(R.menu.menu_organizer, menu);
                return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    protected void setSupView(View view){
        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.content_frame);
        frameLayout.addView(view);
    }

}
