package com.anhlt.maddiscover.repositories;

import android.content.Context;
import android.database.Cursor;

import com.anhlt.maddiscover.data.sqlStatement.SQLStatement;
import com.anhlt.maddiscover.data.tables.Venues;
import com.anhlt.maddiscover.entities.Venue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anhlt on 2/19/16.
 */
public class VenueRepository extends BasicRepository{

    public VenueRepository(Context context) {
        super(context);
    }

    public List<Venue> getAllVenues(){
        Cursor cursor = databaseHelper.select(SQLStatement.getAll(Venues.TABLE_NAME), null, null);
        List<Venue> venues = new ArrayList<Venue>();
        while (cursor.moveToNext()){
            Venue venue = new Venue();
            getObjectFromCursor(cursor, venue);
            venues.add(venue);
        }
        return venues;
    }

    public Venue findByName(String name){
        String[] venueName = {name};
        Cursor cursor = databaseHelper.select(SQLStatement.find(Venues.TABLE_NAME, Venues.name), venueName, null);
        Venue venue = new Venue();
        while (cursor.moveToNext()){
            getObjectFromCursor(cursor, venue);
        }
        return venue;
    }

    public Venue findById(long venueId){
        return (Venue)findById(Venues.TABLE_NAME,new Venue(),venueId);
    }

    public void create(Venue venue){
        create(Venues.TABLE_NAME, venue);
    }

    public void updateVenue(Venue venue){
       update(Venues.TABLE_NAME, venue);
    }

    public void delete(long venueId){
        delete(Venues.TABLE_NAME, venueId);
    }

    public void deletes(String[] ids){
        deletes(Venues.TABLE_NAME, ids);
    }

    @Override
    protected void removeRelationship(String[] eventIds) {

    }

}
