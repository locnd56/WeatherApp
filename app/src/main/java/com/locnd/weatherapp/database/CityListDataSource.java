//package com.locnd.weatherapp.database;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//import com.locnd.weatherapp.model.CityEntity;
//
//
//public class CityListDataSource extends BaseDataSource {
//	public CityListDataSource(Context context) {
//		super(context);
//		// TODO Auto-generated constructor stub
//	}
//
//	public void insertWeather(CityEntity entity) {
//		ContentValues values = cityToContentValues(entity);
//		int id = (int) database.insertWithOnConflict(DbOpenHelper.TABLE_CITY,
//				null, values, SQLiteDatabase.CONFLICT_IGNORE);
//		if (id == -1) {
//			database.update(DbOpenHelper.TABLE_WEATHER, values,
//					DbOpenHelper.COLUMN_CITY + "=?",
//					new String[] { entity.getCity() });
//		}
//	}
//	public void deleteCity(CityEntity entity){
//        ContentValues values = cityToContentValues(entity);
//		database.delete(DbOpenHelper.TABLE_CITY, DbOpenHelper.COLUMN_CITY + "=?", new String[] {entity.getCity()});
//
//	}
//	public CityEntity getWeather(String city) {
//		CityEntity entity = new CityEntity();
//		Cursor cursor = database.rawQuery(
//				"SELECT id,city,city_order FROM city_table WHERE city = ?; ",
//				new String[] { city });
//		if (cursor.moveToFirst()) {
//			entity = cursorToCity(cursor);
//			cursor.close();
//		} else {
//			cursor.close();
//		}
//		return entity;
//	}
//
//	private ContentValues cityToContentValues(CityEntity entity) {
//		ContentValues values = new ContentValues();
//		values.put(DbOpenHelper.COLUMN_CITY, entity.getCity());
//		values.put(DbOpenHelper.COLUMN_ORDER, entity.getOrder());
//		return values;
//	}
//
//	private CityEntity cursorToCity(Cursor cursor) {
//		CityEntity entity = new CityEntity();
//		entity.setCity(cursor.getString(cursor
//				.getColumnIndexOrThrow(DbOpenHelper.COLUMN_CITY)));
//		entity.setOrder(cursor.getInt(cursor
//				.getColumnIndexOrThrow(DbOpenHelper.COLUMN_ORDER)));
//		return entity;
//	}
//
//	public List<CityEntity> getAllCities() {
//		List<CityEntity> entities = new ArrayList<>();
//		CityEntity entity = new CityEntity();
//		Cursor cursor = database.rawQuery(
//				"SELECT id,city,city_order FROM city_table ORDER BY city_order asc;",
//				new String[] {});
//		if(cursor.moveToFirst())
//		do {
//			entity = cursorToCity(cursor);
//			entities.add(entity);
//		}while (cursor.moveToNext());
//		cursor.close();
//		return entities;
//	}
//	public List<String> getAllCitiesString() {
//		List<String> entities = new ArrayList<>();
//		Cursor cursor = database.rawQuery(
//				"SELECT id,city,city_order FROM city_table ORDER BY city_order asc;",
//				new String[] {});
//		if(cursor.moveToFirst())
//		do {
//			String city = cursor.getString(1);
//			entities.add(city);
//		}while (cursor.moveToNext());
//		cursor.close();
//		return entities;
//	}
//}
