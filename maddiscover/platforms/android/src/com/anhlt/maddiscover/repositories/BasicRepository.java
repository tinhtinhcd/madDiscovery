package com.anhlt.maddiscover.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.anhlt.maddiscover.data.DatabaseHelper;
import com.anhlt.maddiscover.data.sqlStatement.SQLStatement;
import com.anhlt.maddiscover.data.tables.BaseTable;
import com.anhlt.maddiscover.data.tables.Venues;
import com.anhlt.maddiscover.entities.Venue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anhlt on 2/21/16.
 */
public class BasicRepository {

    DatabaseHelper databaseHelper;
    String columnId = BaseTable.COLUMN_NAME_ID;
    Context context = null;
    private Object object;

    public BasicRepository(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    protected Object findById(String tableName,Object o, long oId){

        String[] id = {String.valueOf(oId)};
        Cursor cursor = databaseHelper.select(SQLStatement.findById(tableName), id, null);
        while (cursor.moveToNext()){
            getObjectFromCursor(cursor,o);
        }
        return o;
    }

    protected List<Object> getAll(String tableName){
        Cursor cursor = databaseHelper.select(SQLStatement.getAll(tableName), null, null);
        List<Object> objects = new ArrayList<Object>();
        while (cursor.moveToNext()){
            Object o = new Object();
            getObjectFromCursor(cursor, o);
            objects.add(o);
        }
        return objects;
    }

    protected void create(String tableName, Object object){

        ContentValues values = new ContentValues();

        for (Field obj : object.getClass().getFields()) {
            values.put(obj.getName(), obj.toString());
        }

        databaseHelper.insert(tableName, values);
    }

    protected void delete(String tableName, long objectId){
        String[] id = {String.valueOf(objectId)};
        deletes(tableName, id);
    }

    protected void deletes(String tableName, String[] ids){
        removeRelationship(ids);
        databaseHelper.delete(tableName, columnId + " IN (?)", ids);
    }

    protected void removeRelationship(String[] id){

    }

    protected void update(String tableName, Object o){

        String[] id = {String.valueOf(o.getClass().getFields()[0])};
        ContentValues values = new ContentValues();

        for (Field obj : o.getClass().getFields()) {
            if(o instanceof Long && Long.parseLong(o.toString()) >0)
                values.put(obj.getName(), obj.toString());
            else if(o!=null)
                values.put(obj.getName(), obj.toString());
        }

        databaseHelper.update(tableName, values, columnId + " = ?", id);
    }

    protected void getObjectFromCursor(Cursor cursor, Object object){
        try {
            for (Field obj : object.getClass().getFields()) {
                object.getClass().getField(obj.getName()).set(obj,cursor.getColumnIndex(obj.getName()));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
