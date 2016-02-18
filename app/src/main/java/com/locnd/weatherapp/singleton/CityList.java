package com.locnd.weatherapp.singleton;

//import com.locnd.weatherapp.model.CityEntity;

import com.locnd.weatherapp.model.CityEntity;
import com.locnd.weatherapp.model.WeatherEntity;

import java.util.ArrayList;
import java.util.List;


public class CityList {
    //Tao Instance cho lop CityList
	private static final CityList INSTANCE = new CityList();
	private List<WeatherEntity> cities;
    private List<CityEntity> city;
	private CityList() {
		cities = new ArrayList<>();
        city = new ArrayList<>();
	}
	public static CityList getInstance() {
		return INSTANCE;
	}
	public List<WeatherEntity> getCities() {
		return cities;
	}
    public void setCity(List<CityEntity> city){
        this.city = city;
    }
    public List<CityEntity> getCity(){
        return city;
    }
	public void setCities(List<WeatherEntity> cities) {
		this.cities = cities;
	}
}
