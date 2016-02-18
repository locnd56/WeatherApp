package com.locnd.weatherapp.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BaseDataSource {
    protected SQLiteDatabase database;
    protected DbOpenHelper dbHelper;

//    public BaseDataSource(){
//        dbHelper = new DbOpenHelper();
//    }
    public BaseDataSource(Context context) {
        dbHelper = new DbOpenHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
}