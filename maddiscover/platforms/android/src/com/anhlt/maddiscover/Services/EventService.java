package com.anhlt.maddiscover.services;

import android.app.FragmentManager;
import android.content.Context;

import com.anhlt.maddiscover.activities.CreateEvent;
import com.anhlt.maddiscover.data.sqlStatement.SQLStatement;
import com.anhlt.maddiscover.entities.Event;
import com.anhlt.maddiscover.repositories.EventRepository;

import java.util.List;

/**
 * Created by anhlt on 2/19/16.
 */
public class EventService {

    Context context;
    EventRepository eventRepository;
    BaseService baseService;

    public EventService(Context context) {
        this.context = context;
        eventRepository = new EventRepository(context);
    }

    public Event findById(long eventId){
        return eventRepository.findById(eventId);
    }

    public List<Event> getEventList(){
        return eventRepository.getAllEvent();
    }

    public void createEventForm(FragmentManager fm, Context context){
        baseService = new BaseService(fm,context);
        baseService.replaceFragment(CreateEvent.getInstance());
    }

    public void saveNewEvent(Event event){
        eventRepository.create(event);
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

    public boolean validEvent(String eventName, String venueName, String organizerName){
        return eventRepository.validEvent(eventName,venueName,organizerName);
    }
}
