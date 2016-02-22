package com.anhlt.maddiscover.repositories;

import android.content.Context;
import android.database.Cursor;

import com.anhlt.maddiscover.data.sqlStatement.SQLStatement;
import com.anhlt.maddiscover.data.tables.Organizers;
import com.anhlt.maddiscover.entities.Organizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anhlt on 2/21/16.
 */
public class OrganizerRepository extends BasicRepository{

    public OrganizerRepository(Context context) {
        super(context);
    }

    public List<Organizer> getAllOrganizer(){

        Cursor cursor = databaseHelper.select(SQLStatement.getAll(Organizers.TABLE_NAME), null, null);
        List<Organizer> organizers = new ArrayList<Organizer>();
        Organizer organizer = new Organizer();

        while (cursor.moveToNext()){
            getObjectFromCursor(cursor, organizer);
            organizers.add(organizer);
        }

        return organizers;
    }

    public Organizer findByName(String name){

        String[] orgName = {name};
        Cursor cursor = databaseHelper.select(SQLStatement.find(Organizers.TABLE_NAME, Organizers.name), orgName, null);
        Organizer org = new Organizer();

        while (cursor.moveToNext()){
            getObjectFromCursor(cursor, org);
        }

        return org;
    }

    public Organizer findById(long id){
        return (Organizer)findById(Organizers.TABLE_NAME,new Organizer(),id);
    }

    public void create(Organizer org) {
        create(Organizers.TABLE_NAME, org);
    }

    public void update(Organizer org){
        update(Organizers.TABLE_NAME, org);
    }

    public void delete(long id){
        delete(Organizers.TABLE_NAME, id);
    }

    public void deletes(String[] ids){
        deletes(Organizers.TABLE_NAME, ids);
    }

    @Override
    protected void removeRelationship(String[] orgs){
        //delete report
        //delete image
        //remove relationship whit organizer
    }
}
