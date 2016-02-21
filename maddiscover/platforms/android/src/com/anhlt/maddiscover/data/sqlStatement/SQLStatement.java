package com.anhlt.maddiscover.data.sqlStatement;

import com.anhlt.maddiscover.data.tables.Events;

/**
 * Created by anhlt on 2/19/16.
 */
public class SQLStatement {

    private static final String GET_ALL = "SELECT * FROM ";

    public static String getAll(String tableName){
        return GET_ALL+ tableName;
    }

    public static String findById(String table){
        return getAll(table) + " WHERE id = ?1";
    }

    public static String findEventByName(String name){
        return getAll(Events.TABLE_NAME) + " WHERE "+Events.name+" = '?1'";
    }
}
