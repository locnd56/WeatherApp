package com.locnd.weatherapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeatherEntity implements Serializable{
	private String city;
	private long lastUpdate;
	private WeatherDailyEntity currentDay;
	private List<WeatherDailyEntity> weathers;
    private int order;
	public WeatherEntity() {
		currentDay = new WeatherDailyEntity();
		weathers = new ArrayList<>();
		city = "";
		lastUpdate = System.currentTimeMillis();
        order = 0;
	}
	public WeatherDailyEntity getCurrentDay() {
		return currentDay;
	}
	public void setCurrentDay(WeatherDailyEntity currentDay) {
		this.currentDay = currentDay;
	}
	public List<WeatherDailyEntity> getWeathers() {
		return weathers;
	}
	public void setWeathers(List<WeatherDailyEntity> weathers) {
		this.weathers = weathers;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }
}
