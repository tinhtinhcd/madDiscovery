package com.anhlt.maddiscover.data.tables;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.anhlt.maddiscover.data.sqlStatement.SQLStatement;

/**
 * Created by anhlt on 2/19/16.
 */
public class Venues extends BaseTable{

    public static void onCreate(Context pContext, SQLiteDatabase pSQLiteDatabase) {
        pSQLiteDatabase.execSQL(SQLStatement.create_table_venues);
    }

    public static void onUpgrade(Context pContext, SQLiteDatabase pSQLiteDatabase, int pOldVersion, int pNewVersion) {

    }
}
