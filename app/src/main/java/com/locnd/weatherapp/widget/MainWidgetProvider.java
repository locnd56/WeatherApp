package com.locnd.weatherapp.widget;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.locnd.weatherapp.R;
import com.locnd.weatherapp.database.WeatherDataSource;
import com.locnd.weatherapp.model.WeatherEntity;

import java.util.Random;

public class MainWidgetProvider extends AppWidgetProvider {
    private WeatherDataSource db;
    private WeatherEntity entity;
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
        db = new WeatherDataSource(context);
//        entity = db.getWeather(db.getCity().get(1));
		// TODO Auto-generated method stub
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_main);
        //remoteViews.setTextViewText(R.id.tv_city,String.valueOf(number));
//        remoteViews.setTextViewText(R.id.tv_city,entity.getCity());
//        remoteViews.setTextViewText(R.id.tv_curDesc,entity.getCurrentDay().getDesc());
//        remoteViews.setOnClickPendingIntent(R.id.tv_city, buildPendingIntent(context));
		updateWidget(context, remoteViews);
	}
	public static PendingIntent buildPendingIntent(Context context) {
		Intent intent = new Intent();
		intent.setAction("com.example.weatherapp.intent.action.RELOAD_WEATHER");
		return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	}
	
	public static void updateWidget(Context context, RemoteViews remoteViews) {
		ComponentName widget = new ComponentName(context, MainWidgetProvider.class);
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		manager.updateAppWidget(widget, remoteViews);
	}
}
