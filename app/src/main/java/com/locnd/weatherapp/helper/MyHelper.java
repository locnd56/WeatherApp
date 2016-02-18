package com.locnd.weatherapp.helper;

import android.content.Context;
public class MyHelper {
	public static int getID(String eImage, Context c){
		int id = 0;
		switch(eImage.split("/")[eImage.split("/").length-1]){
			case "260.gif":
				id = c.getResources().getIdentifier("drawable/nhieumaykomua" , "drawable", c.getPackageName());
				break;
			case "110.gif":
				id = c.getResources().getIdentifier("drawable/comuavuamuato" , "drawable", c.getPackageName());
				break;	
			case "40.gif":
				id = c.getResources().getIdentifier("drawable/comuaraovadong" , "drawable", c.getPackageName());
				break;
//			case "http://113.171.0.83/web/Upload/WeatherSymbol/2008/8/22/110.gif":
//				id = c.getResources().getIdentifier("drawable/comua" , "drawable", c.getPackageName());
//				break;
			case "90.gif":
				id = c.getResources().getIdentifier("drawable/muaraonhe" , "drawable", c.getPackageName());
				break;
			case "340.gif":
				id = c.getResources().getIdentifier("drawable/itmaytroinang" , "drawable", c.getPackageName());
				break;
			case "300.gif":
				id = c.getResources().getIdentifier("drawable/maythaydoitroinang" , "drawable", c.getPackageName());
				break;
			case "390.gif":
				id = c.getResources().getIdentifier("drawable/coluccomua" , "drawable", c.getPackageName());
				break;
//			case "http://113.171.0.83/web/Upload/WeatherSymbol/2008/8/22/110.gif":
//				id = c.getResources().getIdentifier("drawable/comuarao" , "drawable", c.getPackageName());
//				break;
			case "6_lge.gif":
				id = c.getResources().getIdentifier("drawable/cosuongmu" , "drawable", c.getPackageName());
				break;
//			case "http://113.171.0.83/web/Upload/WeatherSymbol/2008/8/22/90.gif":
//				id = c.getResources().getIdentifier("drawable/comuaphun" , "drawable", c.getPackageName());
//				break;
			case "31(5).gif":
				id = c.getResources().getIdentifier("drawable/troiret" , "drawable", c.getPackageName());
				break;
			case "320.gif":
				id = c.getResources().getIdentifier("drawable/troinang" , "drawable", c.getPackageName());
				break;
			case "310.gif":
				id = c.getResources().getIdentifier("drawable/demkhongmua" , "drawable", c.getPackageName());
				break;
			case "330.gif":
				id = c.getResources().getIdentifier("drawable/demcomay" , "drawable", c.getPackageName());
				break;
			case "270.gif":
				id = c.getResources().getIdentifier("drawable/demnhieumay" , "drawable", c.getPackageName());
				break;
			case "450.gif":
				id = c.getResources().getIdentifier("drawable/demcomuarao" , "drawable", c.getPackageName());
				break;
            default:
                id = -1;
                break;
		}
		return id;	
	}
	public static String subString(String s){
		return s.substring(0,s.length()-5);
	}


}
