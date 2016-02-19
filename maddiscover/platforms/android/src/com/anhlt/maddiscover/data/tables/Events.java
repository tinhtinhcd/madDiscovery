package com.anhlt.maddiscover.data.tables;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by anhlt on 2/19/16.
 */
public class Events extends BaseTable{

    public static void onCreate(Context pContext, SQLiteDatabase pSQLiteDatabase) {
        pSQLiteDatabase.execSQL(createStatement());
    }

    public static void onUpgrade(Context pContext, SQLiteDatabase pSQLiteDatabase, int pOldVersion, int pNewVersion) {

    }

    protected static String createStatement(){
        StringBuilder createTable = new StringBuilder();
        createTable.append("Create Table `events` (`");
        createTable.append(COLUMN_NAME_ID);
        createTable.append("` BIGINT NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        createTable.append("`venue_id` BIGINT NOT NULL, ");
        createTable.append("`name` VARCHAR(255), ");
        createTable.append("`create_date` DATETIME, ");
        createTable.append("`start_date` DATETIME, ");
        createTable.append("`organizer_id` BIGINT");
        createTable.append(");");
        return createTable.toString();
    }
}
