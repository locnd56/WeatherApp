package com.locnd.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class SettingActivity extends ActionBarActivity{
	private ListView ListSetting;
    private ArrayList<String> items;
    private TextView tv_feedback;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        items = new ArrayList<>();
        items.add("Thông tin    v1.0");
        items.add("Chia sẻ thời tiết");
        items.add("Gửi thông tin phản hồi");
        final ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(), R.layout.item_listview, items);
        ListSetting = (ListView) findViewById(R.id.lv_setting);
        ListSetting.setAdapter(adapter);
        ListSetting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                if (position == 1) {
                    startActivity(new Intent(SettingActivity.this,ShareActivity.class));
                }
                if(position == 2){
                    startActivity(new Intent(SettingActivity.this,FeedBackActivity.class));
                }
            }
        });

    }



}
