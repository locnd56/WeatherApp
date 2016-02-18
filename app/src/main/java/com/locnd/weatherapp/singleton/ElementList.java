package com.locnd.weatherapp.singleton;

import java.util.ArrayList;
import java.util.List;

public class ElementList {
	private static final ElementList INSTANCE = new ElementList();
	private List<ArrayList<String>> elements;
	public static ElementList getInstance() {
		return INSTANCE;
	}
	public List<ArrayList<String>> getElements() {
		return elements;
	}
	private ElementList() {
		elements = new ArrayList<ArrayList<String>>();
		for(int i =0; i<10 ; i++){	
		ArrayList<String> nodes = new ArrayList<>();
		nodes.add("table#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho>tbody>tr>td>table>tbody>tr:nth-child(3)>td");
		nodes.add("table#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho>tbody>tr>td>table>tbody>tr:nth-child(4)>td ");
		switch(i){
			case 0:
				nodes.add("#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho__ctl0_pnlText_24");			
				nodes.add("table#_ctl1__ctl0__ctl0_pnl_img img[src$=.gif]");
				nodes.add("#d9a9ce");
				break;
			case 1:
				nodes.add("#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho__ctl0_pnlText_48");
				nodes.add("table#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho__ctl0_Panel1 img[src$=.gif]");
				nodes.add("#d29cc7");
				break;
			case 2:
				nodes.add("#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho__ctl0_pnlText_72");
				nodes.add("table#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho__ctl0_pl72 img[src$=.gif]");
				nodes.add("#c586ba");
				break;
			case 3:
				nodes.add("#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho__ctl0_pnlText_4ngay");
				nodes.add("table#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho__ctl0_pl4ngay img[src$=.gif]");
				nodes.add("#b980b1");
				break;
			case 4:
				nodes.add("#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho__ctl0_pnlText_5ngay");
				nodes.add("table#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho__ctl0_pl5ngay img[src$=.gif]");
				nodes.add("#b074a8");
				break;
			case 5:
				nodes.add("#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho__ctl0_pnlText_6ngay");
				nodes.add("table#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho__ctl0_pl6ngay img[src$=.gif]");
				nodes.add("#a96da1");
				break;
			case 6:
				nodes.add("#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho__ctl0_pnlText_7ngay");
				nodes.add("table#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho__ctl0_pl7ngay img[src$=.gif]");
				nodes.add("#98578f");
				break;
			case 7:
				nodes.add("#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho__ctl0_pnlText_8ngay");
				nodes.add("table#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho__ctl0_pl8ngay img[src$=.gif]");
				nodes.add("#8c4d84");
				break;
			case 8:
				nodes.add("#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho__ctl0_pnlText_9ngay");
				nodes.add("table#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho__ctl0_pl9ngay img[src$=.gif]");
				nodes.add("#7e4276");
				break;
			case 9:
				nodes.add("#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho__ctl0_pnlText_10ngay");
				nodes.add("table#_ctl1__ctl0__ctl0_dl_Thoitietthanhpho__ctl0_pl10ngay img[src$=.gif]");
				nodes.add("#7e3999");
				break;
			
		}
		elements.add(nodes);
		}
	}
}
