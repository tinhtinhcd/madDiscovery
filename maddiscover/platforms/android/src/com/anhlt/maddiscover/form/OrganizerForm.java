package com.anhlt.maddiscover.form;

import android.content.Context;
import android.content.Intent;

import com.anhlt.maddiscover.activities.CreateEvent;
import com.anhlt.maddiscover.activities.CreateOrganizer;
import com.anhlt.maddiscover.repositories.EventRepository;
import com.anhlt.maddiscover.repositories.OrganizerRepository;

/**
 * Created by anhlt on 2/26/16.
 */
public class OrganizerForm {

    Context context;
    OrganizerRepository repository;

    public OrganizerForm(Context context) {
        this.context = context;
    }

    public void createOrginazer(){
        Intent intent = new Intent(context, CreateOrganizer.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void editEvent(){
    }

    public void deleteEvent(){
    }

    public void searchEvent(){
    }

}
