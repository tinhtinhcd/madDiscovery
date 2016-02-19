package com.anhlt.maddiscover.data.tables;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by anhlt on 2/19/16.
 */
public class BaseTable {
    public static final String COLUMN_NAME__ID = "_id";
    public static final int COLUMN_INDEX__ID = 0;

    private static String createStatement = "";

    public static String getCreateStatement() {
        return createStatement;
    }

    public static void setCreateStatement(String createStatement) {
        BaseTable.createStatement = createStatement;
    }
}
