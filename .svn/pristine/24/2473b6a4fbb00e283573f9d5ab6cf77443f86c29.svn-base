package com.tacademy.penthouse.slidingmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tacademy.penthouse.R;

public class ConfigTitleView extends FrameLayout{

	public ConfigTitleView(Context context) {
		super(context);
		init();
	}
	TextView title;
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.config_title_view, this);
		title = (TextView)findViewById(R.id.config_title);
		
	}
	
	public void setData(String data){
		title.setText(data);
	}

}
