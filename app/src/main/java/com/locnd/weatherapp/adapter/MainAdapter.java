package com.locnd.weatherapp.adapter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.locnd.weatherapp.database.WeatherDataSource;
import com.locnd.weatherapp.fragment.CityFragment;
import com.locnd.weatherapp.singleton.CityList;

public class MainAdapter extends FragmentPagerAdapter{
//	private WeatherDataSource db;
	public MainAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Fragment getItem(int index) {
		Fragment fragment = new CityFragment();
		Bundle mBundle = new Bundle();
		mBundle.putString("city", CityList.getInstance().getCities().get(index).getCity());
		fragment.setArguments(mBundle);
		return fragment;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return CityList.getInstance().getCities().size();
	}

}
