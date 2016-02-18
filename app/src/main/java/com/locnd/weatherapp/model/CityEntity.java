package com.locnd.weatherapp.model;

import java.io.Serializable;

public class CityEntity implements Serializable {
	private String city;
	private int order;

	public CityEntity() {
        super();
	}
	public CityEntity(String city, int order) {
		super();
		this.city = city;
		this.order = order;
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
