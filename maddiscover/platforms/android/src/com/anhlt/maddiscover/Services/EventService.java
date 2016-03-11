package com.anhlt.maddiscover.services;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.ListView;

import com.anhlt.maddiscover.R;
import com.anhlt.maddiscover.activities.CreateEvent;
import com.anhlt.maddiscover.adapter.EventListAdapter;
import com.anhlt.maddiscover.data.sqlStatement.SQLStatement;
import com.anhlt.maddiscover.entities.Event;
import com.anhlt.maddiscover.fragments.ListEvent;
import com.anhlt.maddiscover.repositories.EventRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

    public void deleteEvent(FragmentManager fm, Fragment lf){
        ListEvent listEvent = (ListEvent)lf;
        EventListAdapter list = (EventListAdapter)listEvent.getListAdapter();
        deleteEventId(list.checkedEvent);
        list.removeDeletedItem();
        baseService = new BaseService(fm,context);
        baseService.replaceFragment(new ListEvent());
    }

    private void deleteEventId(List<Long> events){
        for (Long event : events) {
            eventRepository.delete(event);
        }
    }

    public void searchEvent(){
//        eventRepository.findByName(eventTitle);
    }

    public boolean validEvent(String eventName, String venueName, String organizerName){
        return eventRepository.validEvent(eventName,venueName,organizerName);
    }
}
