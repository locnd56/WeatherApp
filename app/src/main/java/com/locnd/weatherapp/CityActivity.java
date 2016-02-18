package com.locnd.weatherapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.locnd.weatherapp.adapter.MainAdapter;
import com.locnd.weatherapp.asynctask.ParseURL;
import com.locnd.weatherapp.database.WeatherDataSource;
import com.locnd.weatherapp.singleton.CityList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CityActivity extends FragmentActivity{
	private ImageView mAddImageView;
	private ImageView mSettingImageView;
	private ImageView mManagerImageView;
	private TextView mNoCityTextView;
	private WeatherDataSource db;
	private MainAdapter adapter;
	private ViewPager pager;
	public static final int CHANGE_CITY_REQUEST_CODE = 1;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city);
		pager = (ViewPager) findViewById(R.id.pager);
		mNoCityTextView = (TextView) findViewById(R.id.tv_no_city);
        db = new WeatherDataSource(getApplicationContext());
        db.open();
        CityList.getInstance().setCity(db.getAllCities());
        adapter = new MainAdapter(getSupportFragmentManager());
		pager.setAdapter(adapter);
		mAddImageView = (ImageView) findViewById(R.id.iv_add);
		mSettingImageView = (ImageView) findViewById(R.id.iv_setting);
        mManagerImageView = (ImageView) findViewById(R.id.iv_menu);
		mAddImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CityActivity.this, ListCityActivity.class);
				startActivityForResult(intent, CHANGE_CITY_REQUEST_CODE);
			}
		});
        mManagerImageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CityActivity.this, CityManagerAcivity.class);
				startActivity(intent);

			}
		});
		mSettingImageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
                saveBitmap(takeScreenshot());
				Intent intent = new Intent(CityActivity.this, SettingActivity.class);
				startActivity(intent);

			}
		});
	}
	


	@Override
	protected void onDestroy() {
		super.onDestroy();
        db.close();
	}
	
	@Override
	protected void onResume() {
        super.onResume();
        Log.e("Data",""+CityList.getInstance().getCity().size());
		if(CityList.getInstance().getCity().size() == 0) {
			mNoCityTextView.setVisibility(View.VISIBLE);
			pager.setVisibility(View.INVISIBLE);
		} else {
			pager.setVisibility(View.VISIBLE);
			mNoCityTextView.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == CHANGE_CITY_REQUEST_CODE) {
			if(resultCode == RESULT_OK) {
                new ParseURL(CityActivity.this, new ProgressDialog(CityActivity.this),data.getStringExtra("city")){
                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        adapter.notifyDataSetChanged();
                    }
                }.execute();

			}
		}

	}
    public Bitmap takeScreenshot(){
        View rootView = null;
        rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }
    public void saveBitmap(Bitmap bitmap){
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

}
