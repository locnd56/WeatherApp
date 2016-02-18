package com.locnd.weatherapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper{
	private static final String DATABASE_NAME="weather.db";
	private static final int DATABASE_VERSION = 1;
	public static final String TABLE_WEATHER = "weather_element";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_CITY = "city";
	public static final String COLUMN_LAST_UPDATE = "last_update";
	public static String COLUMN_WEATHER = "current_weather";
	public static String COLUMN_NEXT_WEATHER = "next_weather";
	public static final String TABLE_CITY = "city_table";
	public static final String COLUMN_ORDER = "city_order";
	
	public DbOpenHelper(Context context) {	
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
        //Tạo bảng weather_element chứa thông tin thời tiết: id, city, curWeather, nextWeather
		db.execSQL("CREATE TABLE " + TABLE_WEATHER
                + "("
                + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_CITY + " text unique, "
                + COLUMN_WEATHER + " BLOB, "
                + COLUMN_NEXT_WEATHER + " BLOB, "
                + COLUMN_ORDER + " integer"
                + ");");
         db.execSQL("CREATE TABLE "+ TABLE_CITY
                +" ("
                + COLUMN_ID +" integer primary key autoincrement, "
                + COLUMN_CITY + " text unique, "
                + COLUMN_ORDER + " integer);");

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
