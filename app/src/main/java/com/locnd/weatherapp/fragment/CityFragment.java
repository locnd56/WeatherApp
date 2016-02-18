package com.locnd.weatherapp.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.locnd.weatherapp.R;
import com.locnd.weatherapp.adapter.DailyWeatherAdapter;
import com.locnd.weatherapp.asynctask.ParseURL;
import com.locnd.weatherapp.database.WeatherDataSource;
import com.locnd.weatherapp.model.WeatherEntity;
import com.locnd.weatherapp.model.WeatherDailyEntity;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class CityFragment extends Fragment {
	private String city;
	private WeatherDataSource db;
	private TextView mCityTextView;
	private TextView curTemp;
	private TextView curDesc;
	private TextView curHumi;
	private TextView curWind;
	private TextView txtWind;
	private ImageView curImag;
	private TextView curTime;
	private ImageView imgWind;
	private ImageView imgHumi;
    private UltimateRecyclerView recyclerView;
	private List<WeatherDailyEntity> items;
    private List<String> strings;
	private WeatherEntity entity;
	private DailyWeatherAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle mBundle = getArguments();
		city = mBundle.getString("city");
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_city, null);
		db = new WeatherDataSource(getActivity());
		curImag = (ImageView) view.findViewById(R.id.img);
		mCityTextView = (TextView) view.findViewById(R.id.txtCity);
		curTime = (TextView) view.findViewById(R.id.txtDate);
		curTemp = (TextView) view.findViewById(R.id.curTemp);
		curDesc = (TextView) view.findViewById(R.id.curDesc);
		curHumi = (TextView) view.findViewById(R.id.curHumi);
		curWind = (TextView) view.findViewById(R.id.curWind);
		txtWind = (TextView) view.findViewById(R.id.txtcurWind);
		imgWind = (ImageView) view.findViewById(R.id.imgWind);
		imgHumi = (ImageView) view.findViewById(R.id.imgHumi);
		items = new ArrayList<>();
        adapter = new DailyWeatherAdapter(items);
        recyclerView = (UltimateRecyclerView) view.findViewById(R.id.ultimate_recycler_view);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
		db = new WeatherDataSource(getActivity());
		db.open();
		entity = db.getWeather(city);
		if (entity == null) {
			String url="";
			switch(city) {
				case "Hà Nội":
					url="http://www.nchmf.gov.vn/Web/vi-VN/62/19/58/map/Default.aspx";
					break;
				case "TP.Hồ Chí Minh":
					url="http://www.nchmf.gov.vn/Web/vi-VN/62/23/44/map/Default.aspx";
					break;
				case "Đà Nẵng":
					url="http://www.nchmf.gov.vn/Web/vi-VN/62/21/92/map/Default.aspx";
					break;
				case "Hải Phòng":
					url="http://www.nchmf.gov.vn/Web/vi-VN/62/19/88/map/Default.aspx";
					break;
				case "Nha Trang":
					url="http://www.nchmf.gov.vn/Web/vi-VN/62/21/37/map/Default.aspx";
					break;
				case "Vinh":
					url="http://www.nchmf.gov.vn/Web/vi-VN/62/20/111/map/Default.aspx";
					break;
				case "Sơn La":
					url="http://www.nchmf.gov.vn/Web/vi-VN/62/18/78/map/Default.aspx";
					break;
				case "Pleiku":
					url="http://www.nchmf.gov.vn/Web/vi-VN/62/22/41/map/Default.aspx";
					break;
//				default:

			}
			new ParseURL(getActivity(), new ProgressDialog(getActivity()), city) {

				@Override
				protected void onPostExecute(String s) {
					// TODO Auto-generated method stub
					super.onPostExecute(s);
					entity = db.getWeather(city);
					if(entity == null) {
						return;
					}
					items.clear();
					items.addAll(entity.getWeathers());
					final WeatherDailyEntity today = entity.getCurrentDay();
					adapter.notifyDataSetChanged();
					imgWind.setImageResource(R.drawable.wind28);
					imgHumi.setImageResource(R.drawable.humi);
					curImag.setImageResource(today.getImag());
					curDesc.setText(today.getCurDesc());
					txtWind.setText("	-	"+today.getWindDirection());
					mCityTextView.setText(today.getCity() + "\n");
//					mCityTextView.setText(today.getCity() + "\n");
					curTemp.setText(today.getCurTemp() + (char) 0x00B0);
					curHumi.setText(today.getHumi() + "\n");
					curWind.setText(today.getWind() + "\n");
					curTime.setText(today.getCurDate());
				}

			}
			.execute(url);
		} else {
			items.clear();
			items.addAll(entity.getWeathers());
			final WeatherDailyEntity today = entity.getCurrentDay();
			adapter.notifyDataSetChanged();
			imgWind.setImageResource(R.drawable.wind28);
			imgHumi.setImageResource(R.drawable.humi);
			curImag.setImageResource(today.getImag());
			curDesc.setText(today.getCurDesc());
			txtWind.setText("	-	"+today.getWindDirection());
			mCityTextView.setText(today.getCity() + "\n");
			curTemp.setText(today.getCurTemp() + (char) 0x00B0);
			curHumi.setText(today.getHumi() + "\n");
			curWind.setText(today.getWind() + "\n");
			curTime.setText(today.getCurDate());
			Log.d("WEATHER SIZE:", items + "");
		}
		
		return view;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		db.close();
	}
	
}
