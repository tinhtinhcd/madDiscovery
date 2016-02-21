package com.anhlt.maddiscover.services;

import android.content.Context;

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
//        eventRepository.create(event);
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
