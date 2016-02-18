package com.locnd.weatherapp.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.locnd.weatherapp.model.CityEntity;
import com.locnd.weatherapp.model.WeatherEntity;
import com.locnd.weatherapp.model.WeatherDailyEntity;


public class WeatherDataSource extends BaseDataSource{
	private Gson gson;
	public WeatherDataSource(Context context) {
		super(context);
		gson = new Gson();
	}
    //Con tro toi weather
	private WeatherEntity cursorToWeather(Cursor cursor) {
		WeatherEntity entity = new WeatherEntity();
		Gson gson = new Gson();
		entity.setCity(cursor.getString(cursor.getColumnIndexOrThrow(DbOpenHelper.COLUMN_CITY)));
		byte[] blobCurrentDay = cursor.getBlob(cursor.getColumnIndexOrThrow(DbOpenHelper.COLUMN_WEATHER));
		byte[] blobNextDays = cursor.getBlob(cursor.getColumnIndexOrThrow(DbOpenHelper.COLUMN_NEXT_WEATHER));
		String json = new String(blobCurrentDay);
		WeatherDailyEntity current = gson.fromJson(json, WeatherDailyEntity.class);
		json = new String(blobNextDays);
		List<WeatherDailyEntity> days = gson.fromJson(json, new TypeToken<ArrayList<WeatherDailyEntity>>(){}.getType());
		entity.setCurrentDay(current);
		entity.setWeathers(days);
        entity.setOrder(cursor.getInt(cursor.getColumnIndexOrThrow(DbOpenHelper.COLUMN_ORDER)));
		return entity;
	}
	private ContentValues weatherToContentValues(WeatherEntity entity) {
		ContentValues values = new ContentValues();
		Gson gson = new Gson();
		values.put(DbOpenHelper.COLUMN_CITY, entity.getCity());
//		values.put(DbOpenHelper.COLUMN_LAST_UPDATE,	entity.getLastUpdate());
		values.put(DbOpenHelper.COLUMN_WEATHER, gson.toJson(entity.getCurrentDay()).getBytes());
		values.put(DbOpenHelper.COLUMN_NEXT_WEATHER, gson.toJson(entity.getWeathers()).getBytes());
        values.put(DbOpenHelper.COLUMN_ORDER, entity.getOrder());
		return values;
	}

	public void insertWeather(WeatherEntity entity) {
		Log.d("[INSERT ENTITY]", gson.toJson(entity));
        ContentValues values = weatherToContentValues(entity);
        int id = (int)database.insertWithOnConflict(DbOpenHelper.TABLE_WEATHER, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if(id==-1){
        	database.update(DbOpenHelper.TABLE_WEATHER, values, DbOpenHelper.COLUMN_CITY+"=?", new String[] {entity.getCity()});
        }
	}

	public void deleteWeather(WeatherEntity entity){
        ContentValues values = weatherToContentValues(entity);
		int id = (int)database.delete(DbOpenHelper.TABLE_WEATHER, DbOpenHelper.COLUMN_CITY+"=?", new String[] {entity.getCity()});
        if(id==-1){
            database.update(DbOpenHelper.TABLE_WEATHER, values, DbOpenHelper.COLUMN_CITY+"=?", new String[] {entity.getCity()});
        }
	}
	public void updateWeather(WeatherEntity entity){
		Log.v("[UPDATE ENTITY",gson.toJson(entity));
		ContentValues values = weatherToContentValues(entity);
		database.update(DbOpenHelper.TABLE_WEATHER, values, DbOpenHelper.COLUMN_CITY+"=?", new String[] {entity.getCity()});
	}
	public WeatherEntity getWeather(String city) {
		Log.d("[GET ENTITY]", city);
		WeatherEntity entity = new WeatherEntity();
		Cursor cursor = database.rawQuery("SELECT id,city,current_weather,next_weather,city_order  FROM weather_element WHERE city = ?", new String[] {city});
        if(cursor.moveToFirst()) {
        	entity = cursorToWeather(cursor);
        	cursor.close();
        	return entity;
        } else {
        	cursor.close();
        	return null;
        }
        
	}
    public List<WeatherEntity> getWeather() {
        WeatherEntity entity = new WeatherEntity();
        List<WeatherEntity> entities = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT id,city,current_weather,next_weather,city_order  FROM weather_element WHERE 1", new String[] {});
        if(cursor.moveToFirst()) {
            do {
                entity = cursorToWeather(cursor);
                entities.add(entity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return entities;
    }
    public List<String> getCity() {
        List<String> entities = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT id,city,current_weather,next_weather,city_order  FROM weather_element WHERE 1", new String[] {});
        if(cursor.moveToFirst()) {
            do {
                entities.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return entities;
    }

    // Database City
    private CityEntity cursorToCity(Cursor cursor) {
        CityEntity centity = new CityEntity();
        centity.setCity(cursor.getString(cursor
                .getColumnIndexOrThrow(DbOpenHelper.COLUMN_CITY)));
        centity.setOrder(cursor.getInt(cursor
                .getColumnIndexOrThrow(DbOpenHelper.COLUMN_ORDER)));
        return centity;
    }

    private ContentValues cityToContentValues(CityEntity centity) {
        ContentValues cvalues = new ContentValues();
        cvalues.put(DbOpenHelper.COLUMN_CITY, centity.getCity());
        cvalues.put(DbOpenHelper.COLUMN_ORDER, centity.getOrder());
        return cvalues;
    }
    public void insertCity(CityEntity centity) {
        ContentValues cvalues = cityToContentValues(centity);
        int id = (int)database.insertWithOnConflict(DbOpenHelper.TABLE_CITY,null, cvalues, SQLiteDatabase.CONFLICT_IGNORE);
        if(id==-1){
            database.update(DbOpenHelper.TABLE_CITY, cvalues, DbOpenHelper.COLUMN_CITY+"=?", new String[] {centity.getCity()});
        }
    }

    public void deleteCity(CityEntity centity){
        ContentValues values = cityToContentValues(centity);
        int id = (int)database.delete(DbOpenHelper.TABLE_CITY, DbOpenHelper.COLUMN_CITY+"=?", new String[] {centity.getCity()});
        if(id==-1){
            database.update(DbOpenHelper.TABLE_CITY, values, DbOpenHelper.COLUMN_CITY+"=?", new String[] {centity.getCity()});
        }
    }
    public List<CityEntity> getAllCities() {
        List<CityEntity> centities = new ArrayList<>();
        CityEntity centity = new CityEntity();
        Cursor cursor = database.rawQuery(
                "SELECT id,city,city_order FROM city_table WHERE 1 ORDER BY id ASC",
                new String[] {});
        if(cursor.moveToFirst())
            do {
                centity = cursorToCity(cursor);
                centities.add(centity);
            }while (cursor.moveToNext());
        cursor.close();
        return centities;
    }

}

