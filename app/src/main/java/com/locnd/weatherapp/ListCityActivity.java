package com.locnd.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.locnd.weatherapp.database.WeatherDataSource;
import com.locnd.weatherapp.model.CityEntity;
import com.locnd.weatherapp.model.WeatherEntity;
import com.locnd.weatherapp.singleton.CityList;

import java.util.ArrayList;
import java.util.List;
public class ListCityActivity extends ActionBarActivity{
    public String city;
    private WeatherDataSource db;
    private List<String> listcity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_city);
        WeatherEntity entity = new WeatherEntity();
        db = new WeatherDataSource(getApplicationContext());
        db.open();
        listcity = new ArrayList<String>();
        listcity.add("Hà Nội");
        listcity.add("TP.Hồ Chí Minh");
        listcity.add("Đà Nẵng");
        listcity.add("Hải Phòng");
        listcity.add("Vinh");
        listcity.add("Pleiku");
        listcity.add("Sơn La");
        listcity.add("Nha Trang");
        listcity.removeAll(db.getCity());
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listcity);
        final ListView listitems = (ListView) findViewById(R.id.listcity1);
        listitems.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position,
                                    long id) {
                // TODO Auto-generated method stub
                city = listcity.get(position);
                CityEntity centity = new CityEntity(city,db.getAllCities().size());
//                centity.setCity(city);
                db.insertCity(centity);
                CityList.getInstance().setCity(db.getAllCities());
                db.close();
                Intent intent = new Intent(getApplicationContext(), CityActivity.class);
                intent.putExtra("city", city);
                setResult(RESULT_OK, intent);
                finish();
            }

        });
        listitems.setAdapter(adapter);
    }

}

//public class ListCityActivity extends ActionBarActivity{
//    public String city;
//    private WeatherDataSource db;
//    private List<String> listcity;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        // TODO Auto-generated method stub
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.list_city);
//        WeatherEntity entity = new WeatherEntity();
//        db = new WeatherDataSource(getApplicationContext());
//        db.open();
//        listcity = new ArrayList<String>();
//        listcity.add("Hà Nội");
//        listcity.add("TP.Hồ Chí Minh");
//        listcity.add("Đà Nẵng");
//        listcity.add("Hải Phòng");
//        listcity.add("Vinh");
//        listcity.add("Pleiku");
//        listcity.add("Sơn La");
//        listcity.add("Nha Trang");
//        listcity.removeAll(db.getCity());
//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listcity);
//        final ListView listitems = (ListView) findViewById(R.id.listcity1);
//        listitems.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View viewClicked, int position,
//                                    long id) {
//                // TODO Auto-generated method stub
//                city = listcity.get(position);
//                CityEntity centity = new CityEntity(city,db.getAllCities().size());
////                centity.setCity(city);
//                db.insertCity(centity);
//                CityList.getInstance().setCity(db.getAllCities());
//                db.close();
//                Intent intent = new Intent(getApplicationContext(), CityActivity.class);
//                intent.putExtra("city", city);
//                setResult(RESULT_OK, intent);
//                finish();
//            }
//
//        });
//        listitems.setAdapter(adapter);
//    }
//
//}
