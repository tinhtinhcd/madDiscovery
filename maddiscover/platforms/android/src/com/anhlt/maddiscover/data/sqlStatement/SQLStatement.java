package com.anhlt.maddiscover.data.sqlStatement;

import com.anhlt.maddiscover.data.tables.Events;
import com.anhlt.maddiscover.data.tables.Organizers;
import com.anhlt.maddiscover.data.tables.Venues;
import com.anhlt.maddiscover.entities.Venue;

/**
 * Created by anhlt on 2/19/16.
 */
public class SQLStatement {

    private static final String GET_ALL = "SELECT * FROM ";

    public static String getAll(String tableName){
        return GET_ALL+ tableName;
    }

    public static String findById(String table){
        return getAll(table) + " WHERE id = ?";
    }

    public static String findByName(String table){
        return getAll(table) + " WHERE name = ?";
    }

    public static String find(String table, String columnCondition){
        return getAll(table) + " WHERE "+columnCondition+" = ?";
    }

    public static String getColumns(String table, String column,String condition){
        StringBuilder query = new StringBuilder();
        if(table!=null && column!=null && !table.isEmpty() && !column.isEmpty()){
            query.append("SELECT ");
            query.append(column);
            query.append(" FROM ");
            query.append(table);
            if (condition!=null && !condition.isEmpty()){
                query.append(" WHERE ");
                query.append(condition);
            }
        }
        return query.toString();
    }

    public static String validEvent(String eventName, String venueName, String organizerName){
        StringBuilder builder = new StringBuilder();

        builder.append("SELECT * FROM ");
        builder.append(Events.TABLE_NAME);
        builder.append(" as e inner join ");
        builder.append(Venues.TABLE_NAME);
        builder.append(" as v on e.venueId = v.id inner join ");
        builder.append(Organizers.TABLE_NAME);
        builder.append(" as o on o.id = e.organizer");
        builder.append(" where e.eventName = '");
        builder.append(eventName);
        builder.append("' and v.name = '");
        builder.append(venueName);
        builder.append("' and o.name = '");
        builder.append(organizerName);
        builder.append("'");

        return builder.toString();
    }
}
