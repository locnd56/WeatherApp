package com.locnd.weatherapp.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.locnd.weatherapp.R;
import com.locnd.weatherapp.adapter.DailyWeatherAdapter;
import com.locnd.weatherapp.asynctask.ParseURL;
import com.locnd.weatherapp.config.UrlConfig;
import com.locnd.weatherapp.database.WeatherDataSource;
import com.locnd.weatherapp.model.WeatherDailyEntity;
import com.locnd.weatherapp.model.WeatherEntity;
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
    private String url;

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
        View view = inflater.inflate(R.layout.fragment_city, container,false);
        initView(view);
        initData(view);
        return view;
    }

    private void initData(View view) {
        db = new WeatherDataSource(getActivity());
        db.open();
        items = new ArrayList<>();
        adapter = new DailyWeatherAdapter(items);
        recyclerView = (UltimateRecyclerView) view.findViewById(R.id.ultimate_recycler_view);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        entity = db.getWeather(city);
        if (entity == null) {
            url = getUrlWeatherCity();
            new ParseURL(getActivity(), new ProgressDialog(getActivity()), city) {

                @Override
                protected void onPostExecute(String s) {
                    // TODO Auto-generated method stub
                    super.onPostExecute(s);
                    entity = db.getWeather(city);
                    if (entity == null) {
                        return;
                    }
                    setDataWeatherCity();
                }
            }.execute(url);
        } else {
            setDataWeatherCity();
        }


    }

    private void initView(View view) {
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
    }

    private void setDataWeatherCity() {
        items.clear();
        items.addAll(entity.getWeathers());
        final WeatherDailyEntity today = entity.getCurrentDay();
        adapter.notifyDataSetChanged();
        imgWind.setImageResource(R.drawable.wind28);
        imgHumi.setImageResource(R.drawable.humi);
        curImag.setImageResource(today.getImag());
        curDesc.setText(today.getCurDesc());
        txtWind.setText("	-	" + today.getWindDirection());
        mCityTextView.setText(today.getCity() + "\n");
        curTemp.setText(today.getCurTemp() + (char) 0x00B0);
        curHumi.setText(today.getHumi() + "\n");
        curWind.setText(today.getWind() + "\n");
        curTime.setText(today.getCurDate());
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        db.close();
    }

    public String getUrlWeatherCity() {
        String url = "";
        if (city.equals("Hà Nội")) {
            url = UrlConfig.urlHanoiWeather;
        } else if (city.equals("TP.Hồ Chí Minh")) {
            url = UrlConfig.urlHcmWeather;
        } else if (city.equals("Đà Nẵng")) {
            url = UrlConfig.urlDanangWeather;
        } else if (city.equals("Hải Phòng")) {
            url = UrlConfig.urlHaiphongWeather;
        } else if (city.equals("Nha Trang")) {
            url = UrlConfig.urlNhatrangWeather;
        } else if (city.equals("Vinh")) {
            url = UrlConfig.urlVinhWeather;
        } else if (city.equals("Sơn La")) {
            url = UrlConfig.urlSonlaWeather;
        } else if (city.equals("Pleiku")) {
            url = UrlConfig.urlPleikuWeather;
        }
        return url;
    }
}
