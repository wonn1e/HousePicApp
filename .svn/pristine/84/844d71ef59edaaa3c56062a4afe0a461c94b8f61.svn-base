package com.tacademy.penthouse.slidingmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tacademy.penthouse.R;

public class ConfigInformView extends FrameLayout{
	
	public ConfigInformView(Context context) {
		super(context);
		init();
	}
	TextView tv;
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.config_inform_view,this);
		tv = (TextView)findViewById(R.id.config_inform_list);
		
		
	}
	public void setData(String str){
		tv.setText(str);
		tv.setBackgroundResource(R.drawable.list_line);
	}
}
