package com.example.windows10.App_APR;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenhelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME="aguadb.db";
    private static final int DATABASE_VERSION=1;

    public DatabaseOpenhelper (Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
