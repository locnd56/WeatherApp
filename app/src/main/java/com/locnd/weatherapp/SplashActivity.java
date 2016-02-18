package com.locnd.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.locnd.weatherapp.database.WeatherDataSource;
import com.locnd.weatherapp.singleton.CityList;


public class SplashActivity extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_activity);
		Thread logoTimer = new Thread(){
			public void run(){
				try{
					sleep(2000);
                    WeatherDataSource db = new WeatherDataSource(getApplicationContext());
                    db.open();
                    CityList.getInstance().setCity(db.getAllCities());
                    CityList.getInstance().setCities(db.getWeather());
                    db.close();
					Intent maincontent = new Intent(SplashActivity.this, CityActivity.class);
					startActivity(maincontent);
					finish();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally{
					
				}
			}
		};
		logoTimer.start();
	}
	
	}
