package com.anhlt.maddiscover.services;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.anhlt.maddiscover.R;
import com.anhlt.maddiscover.fragments.event.ListEvent;
import com.anhlt.maddiscover.fragments.organizer.ListOrganizer;
import com.anhlt.maddiscover.fragments.venue.ListVenue;

/**
 * Created by anhlt on 2/26/16.
 */
public class BaseService {

    FragmentManager fm;
    Context context;
    private static int position =0;
    private Fragment fragment = null;

    public BaseService(FragmentManager fm, Context context) {
        this.fm = fm;
        this.context = context;
    }

    public void addFragment(Fragment fragment){
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.content_frame, fragment);
        ft.commit();
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    public void removeFragment(){
        Fragment fragment = fm.findFragmentById(R.id.content_frame);
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }

    public void selectItem(int position) {
        if(position==0)
            listEvent();
        if(position==1)
            listVenue();
        if(position==2)
            listOrganizer();
    }

    public boolean menuActions(MenuInflater inflater, Menu menu){
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
            default:
                return false;
        }
    }

    public void listEvent(){
        fragment = new ListEvent();
        replaceFragment(fragment);
        position = 0;
    }

    public void listVenue(){
        fragment = new ListVenue();
        replaceFragment(fragment);
        position = 1;
    }

    public void listOrganizer(){
        fragment = new ListOrganizer();
        replaceFragment(fragment);
        position = 2;
    }

    public boolean itemSelected(MenuItem item){
        EventService eventService = new EventService(context);
        VenueService venueService = new VenueService(context);
        OrganizerService organizerService = new OrganizerService(context);

        switch(item.getItemId()) {
            case R.id.search_event:
                eventService.searchEvent();
                return true;
            case R.id.create_event:
                eventService.createEventForm(fm,context);
                return true;
            case R.id.delete_event:
                eventService.deleteEvent(fm,fragment);
                return true;
            case R.id.edit_event:
                eventService.editEventFromListEvent(fm,fragment);
                return true;
            case R.id.search_venue:
                venueService.searchVenue();
                return true;
            case R.id.create_venue:
                venueService.createVenueForm(fm, context);
                return true;
            case R.id.edit_venue:
                venueService.editVenue();
                return true;
            case R.id.delete_venue:
                venueService.deleteVenue();
                return true;
            case R.id.search_organizer:
                organizerService.searchOrganizer();
                return true;
            case R.id.create_organizer:
                organizerService.createOrganizerForm(fm, context);
                return true;
            case R.id.edit_organizer:
//                organizerService.editOrganizer();
                return true;
            case R.id.delete_organizer:
                organizerService.deleteOrganizer();
                return true;
        }
        return false;
    }

}
