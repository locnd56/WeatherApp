package com.locnd.weatherapp;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.locnd.weatherapp.adapter.CityAdapter;
import com.locnd.weatherapp.asynctask.ParseURL;
//import com.locnd.weatherapp.database.CityListDataSource;
import com.locnd.weatherapp.database.WeatherDataSource;
//import com.locnd.weatherapp.model.CityEntity;
import com.locnd.weatherapp.model.CityEntity;
import com.locnd.weatherapp.model.WeatherEntity;
import com.locnd.weatherapp.singleton.CityList;
import com.marshalchen.ultimaterecyclerview.DragDropTouchListener;
import com.marshalchen.ultimaterecyclerview.ItemTouchListenerAdapter;
import com.marshalchen.ultimaterecyclerview.Logs;
import com.marshalchen.ultimaterecyclerview.SwipeToDismissTouchListener;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.List;


public class CityManagerAcivity extends ActionBarActivity {
	private UltimateRecyclerView mCitiesListView;
	private WeatherDataSource db;
    private CityAdapter adapter;
    boolean isDrag = true;
    private List<WeatherEntity> entityList;
    private List<CityEntity> cityList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_manager);
		mCitiesListView = (UltimateRecyclerView) findViewById(R.id.ultimate_recycler_view);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
		db= new WeatherDataSource(CityManagerAcivity.this);
		db.open();
        entityList = db.getWeather();
        cityList = db.getAllCities();
		adapter = new CityAdapter(entityList);
		mCitiesListView.setAdapter(adapter);
        mCitiesListView.setLayoutManager(layoutManager);
        mCitiesListView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            List<String> allcities = db.getCity();
            for (String mCity: allcities) {
                 new ParseURL(CityManagerAcivity.this, new ProgressDialog(CityManagerAcivity.this), mCity) {
                     @Override
                     protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        adapter.notifyDataSetChanged();
                     }
                 }.execute();
                 db.updateWeather(db.getWeather(mCity));
                }
            mCitiesListView.setRefreshing(false);
            }
        });
        mCitiesListView.setSwipeToDismissCallback(new SwipeToDismissTouchListener.DismissCallbacks() {
            @Override
            public SwipeToDismissTouchListener.SwipeDirection dismissDirection(int position) {
                return SwipeToDismissTouchListener.SwipeDirection.BOTH;
            }
            @Override
            public void onDismiss(RecyclerView view, List<SwipeToDismissTouchListener.PendingDismissData> dismissData) {
                for (SwipeToDismissTouchListener.PendingDismissData data : dismissData) {
                    db.deleteCity(db.getAllCities().get(data.position));
                    CityList.getInstance().setCity(db.getAllCities());
                    db.deleteWeather(entityList.get(data.position));
                    adapter.remove(data.position);
                    CityList.getInstance().setCities(db.getWeather());
                    /*
                    delete city in db in here with db.deleteWeather(WeatherEntity);
                    LIST WEATHER ENTITIES:entityList
                     */
                }
            }
            @Override
            public void onResetMotion() {
                isDrag = true;
            }

            @Override
            public void onTouchDown() {
                isDrag = false;
            }
        });
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		db.close();
	}
}
