package com.anhlt.maddiscover.services;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.anhlt.maddiscover.activities.CreateEvent;
import com.anhlt.maddiscover.activities.MainActivity;
import com.anhlt.maddiscover.entities.Event;
import com.anhlt.maddiscover.repositories.EventRepository;

/**
 * Created by anhlt on 2/19/16.
 */
public class EventService {

    Context context;
    EventRepository eventRepository;

    public EventService(Context context) {
        this.context = context;
        eventRepository = new EventRepository(context);
    }

    public Event findById(long eventId){
        return eventRepository.findById(eventId);
    }

    public void createEvent(){
        Intent intent = new Intent(context, CreateEvent.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void editEvent(){
//        eventRepository.updateEvent(event);
    }

    public void deleteEvent(){
//        eventRepository.deleteEvent(eventId);
    }

    public void searchEvent(){
//        eventRepository.findByName(eventTitle);
    }
}
