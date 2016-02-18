package com.locnd.weatherapp.model;


public class WeatherDailyEntity extends Object{
	public int backgroundColor;
	public String city;
	public String curdate;
	public String date;
	public int imag;
	public String mintemp;
	public String maxtemp;
	public String desc;
	public String humi;
	public String wind;
	public String curtemp;
	public String windDirection;
	public String curdesc;
	public WeatherDailyEntity() {
		
	}
	public WeatherDailyEntity(int backgroundColor, String date, int imag, String mintemp, String maxtemp, String desc){
		this.backgroundColor = backgroundColor;
		this.date = date;
		this.imag = imag;
		this.mintemp = mintemp;
		this.maxtemp = maxtemp;
		this.desc = desc;
	}
	public WeatherDailyEntity(String city, String curdate, int curimag, String curtemp, String curdesc, String humi, String wind){
		this.city = city;
		this.curdate = curdate;
		this.imag = curimag;
		this.curtemp = curtemp;
		this.curdesc = curdesc;
		this.humi = humi;
		this.wind = wind;
	}
	public int getbackgroundColor(){
		return backgroundColor;
	}
	public void setCity(String city){
		this.city = city;
	}
	public String getCity(){
		return city;
	}
	public void setDate(String date){
            int count = date.length()-5;
            String mdate = date.substring(0, count);
            this.date = mdate;
	}
	public String getDate(){
		return date;
	}
	public void setCurDate(String curdate){
        int count = curdate.length();
        String mcurdate = curdate.substring(count - 19, count).toString();
        this.curdate = mcurdate;

	}
	public String getCurDate(){
		return curdate;
	}
	public void setImag(int imag){
		this.imag = imag;
	}
	public int getImag(){
		return imag;
	}
	public String getMin(){
		return mintemp;
	}
	public String getMax(){
		return maxtemp;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return desc;
	}
	public void setCurTemp(String curtemp){
        String mcurtemp = curtemp.substring(0, 2);
		this.curtemp = mcurtemp;
	}
	public String getCurTemp(){
		return curtemp;
	}
	public void setHumi(String humi){
		this.humi = humi;
	}
	public String getHumi(){
		return humi;
	}
	public void setWind(String wind){
		this.wind = wind;
	}
	public String getWind(){
		return wind;
	}
	public void setWindDirection(String wind){
		windDirection = wind;		
	}
	public String getWindDirection(){
		return windDirection;
	}
	public void setCurDesc(String curdesc){
		this.curdesc = curdesc;
	}
	public String getCurDesc(){
		return curdesc;
	}
}