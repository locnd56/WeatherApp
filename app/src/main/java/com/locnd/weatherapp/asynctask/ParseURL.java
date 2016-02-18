package com.locnd.weatherapp.asynctask;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;
import com.locnd.weatherapp.database.WeatherDataSource;
import com.locnd.weatherapp.helper.MyHelper;
import com.locnd.weatherapp.model.WeatherEntity;
import com.locnd.weatherapp.model.WeatherDailyEntity;
import com.locnd.weatherapp.singleton.CityList;
import com.locnd.weatherapp.singleton.ElementList;

public class ParseURL extends AsyncTask<String, Void, String> {
	private ProgressDialog dialog;
	private Context context;
	private String city;
	private WeatherDailyEntity curDate;
	private List<WeatherDailyEntity> items = new ArrayList<WeatherDailyEntity>();
	public ParseURL(Context context, ProgressDialog dialog, String city) {
		this.context = context;
		this.dialog = dialog;
		this.city = city;
	}
    private String getURL(String city) {
        String url;
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
            default:
                url="http://www.nchmf.gov.vn/Web/vi-VN/62/19/58/map/Default.aspx";
                break;
        }
        return url;
    }
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
        dialog.setMessage("Loading...");
		dialog.show();
	}
	@Override
	protected String doInBackground(String... url) {
		StringBuffer buffer = new StringBuffer();
		try {
			String[] infotmt= new String[100];
			String[] infotMt = new String[100];
			String tmt = null;
			String tMt = null;
			String dayString = null;
			String[] day = new String[100];
			Document htmlFile = Jsoup.connect(getURL(city)).get();
			String city = null;
			String curTime = null;
            //Lay ve ten thanh pho
			city = htmlFile.select("select#_ctl1__ctl0__ctl0_cboProvince option[selected]").text();
			//Gio/Ngay update
            curTime = htmlFile.select("span.forecast_detail_update").text();
			// Lay thong tin thoi tiet hien tai
			Elements currentInfo = htmlFile.select("table#_ctl1__ctl0__ctl0_pnl_infor tbody tr td table tbody tr td ");
			String[] info = new String[100];
			String td = null;
			int i = 0;
			for (Element curInfo : currentInfo) {
				td = curInfo.getElementsByTag("strong").text();
				info[i] = td;
				i++;
			}
			// Set Image
			Elements img = htmlFile.select("table#_ctl1__ctl0__ctl0_pnl_img img[src$=.gif]");
			String curImg = img.attr("abs:src");
			curDate = new WeatherDailyEntity();
            //CurIma
			curDate.setImag(MyHelper.getID(curImg, context));
			// Province/City
			curDate.setCity(this.city);
			// CurTime
			curDate.setCurDate(curTime);
			// Temperature hien tai
			curDate.setCurTemp(info[2]);
			// Description hien tai
			curDate.setCurDesc(info[5]);
			curDate.setHumi(info[8]);
			// Gio hien tai
			//Xet truong hop lang gio
			if(info[11].length() > 9){
				curDate.setWind(info[11].substring(info[11].length()-5,info[11].length()).toString());
				curDate.setWindDirection(info[11].substring(0,info[11].length()-16).toString());
			}else{				
				curDate.setWind(info[11]);
				curDate.setWindDirection(info[11]);
			}
			// Hien thi ngay
			Elements Day = htmlFile.select("table#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho>tbody>tr>td>table>tbody>tr:nth-child(1)>td");
			int d = 0;
			for (Element dayEle : Day) {
				dayString = dayEle.getElementsByTag("td").text();
				day[d] = dayString;
				d++;
			}
			// -----------------------------------------------------------------------------------------------------------------------------------------------------

			List<ArrayList<String>> elementList = ElementList.getInstance().getElements();
			int dayArrays=1;
			for(ArrayList<String> item: elementList) {
				String Desc = htmlFile.select(item.get(2)).text();
				// Nhiet do thap nhat
				Elements minTemp = htmlFile.select(item.get(0));
				int countMin = 0;
				for (Element tomminTemp : minTemp) {
					tmt = tomminTemp.getElementsByTag("strong").text();
					infotmt[countMin] = tmt;
                    countMin++;
				}
				// Nhiet do cao nhat
				Elements maxTemp = htmlFile.select(item.get(1));
				int countMax = 0;
				for (Element tommaxTemp : maxTemp) {
					tMt = tommaxTemp.getElementsByTag("strong").text();
					infotMt[countMax] = tMt;
                    countMax++;
				}
				Elements imgDaily= htmlFile.select(item.get(3));
				// Get the source of the image
				String imgSrc = imgDaily.attr("abs:src");
				WeatherDailyEntity weather = new WeatherDailyEntity(Color.parseColor(item.get(4)),
						(day[dayArrays]), MyHelper.getID(imgSrc,context),
						infotmt[dayArrays+1], infotMt[dayArrays], Desc);
                if(weather.getImag()!=-1)
				items.add(weather);
				dayArrays++;
			}
			//
			WeatherDataSource db = new WeatherDataSource(context);
			db.open();
			WeatherEntity entity = new WeatherEntity();
			entity.setCity(this.city);
			entity.setWeathers(items);
			entity.setCurrentDay(curDate);
			Gson gson = new Gson();
			Log.e("today:", gson.toJson(curDate));
			db.insertWeather(entity);
            CityList.getInstance().setCities(db.getWeather());
            Log.e(getClass().getSimpleName(),""+db.getWeather().size());
			db.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}

		return buffer.toString();
	}
	@Override
	protected void onPostExecute(String s) {
		super.onPostExecute(s);
		dialog.dismiss();
	}

}
