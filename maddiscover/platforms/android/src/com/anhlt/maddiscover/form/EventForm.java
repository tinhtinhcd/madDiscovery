package com.anhlt.maddiscover.form;

import android.content.Context;
import android.content.Intent;

import com.anhlt.maddiscover.activities.CreateEvent;
import com.anhlt.maddiscover.repositories.EventRepository;

/**
 * Created by anhlt on 2/26/16.
 */
public class EventForm {

    Context context;
    EventRepository eventRepository;

    public EventForm(Context context) {
        this.context = context;
    }

    public void createEvent(){
        Intent intent = new Intent(context, CreateEvent.class);
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
