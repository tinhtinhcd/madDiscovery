package com.anhlt.maddiscover.data.tables;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.anhlt.maddiscover.data.sqlStatement.SQLStatement;

/**
 * Created by anhlt on 2/19/16.
 */
public class Reports extends BaseTable {

    public static final String id = COLUMN_NAME_ID;
    public static final String eventId = "event_id";
    public static final String createDate = "create_date";
    public static final String title = "title";
    public static final String details = "details";

    public static void onCreate(Context pContext, SQLiteDatabase pSQLiteDatabase) {
        pSQLiteDatabase.execSQL(createStatement());
    }

    public static void onUpgrade(Context pContext, SQLiteDatabase pSQLiteDatabase, int pOldVersion, int pNewVersion) {

    }

    protected static String createStatement(){
        StringBuilder createTable = new StringBuilder();
        createTable.append("Create Table `reports` (`");
        createTable.append(COLUMN_NAME_ID);
        createTable.append("` BIGINT NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        createTable.append("`event_id` BIGINT NOT NULL, ");
        createTable.append("`create_date` DATETIME, ");
        createTable.append("`title` VARCHAR(255), ");
        createTable.append("`details` VARCHAR(255) ");
        createTable.append(");");
        return createTable.toString();
    }
}
