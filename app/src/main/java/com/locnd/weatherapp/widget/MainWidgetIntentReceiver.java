package com.locnd.weatherapp.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.locnd.weatherapp.R;
import com.locnd.weatherapp.database.WeatherDataSource;


public class MainWidgetIntentReceiver extends BroadcastReceiver {
    private WeatherDataSource db;
	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals("com.locnd.weatherapp.intent.action.RELOAD_WEATHER")) {
			updateWidget(context);
		}
		
	}
	private void updateWidget(Context context) {
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_main);
//		remoteViews.setOnClickPendingIntent(R.id.widget_button, MainWidgetProvider.buildPendingIntent(context));
//		remoteViews.setTextViewText(R.id.tv_widget, System.currentTimeMillis()+"");
		MainWidgetProvider.updateWidget(context.getApplicationContext(), remoteViews);
	}
}
