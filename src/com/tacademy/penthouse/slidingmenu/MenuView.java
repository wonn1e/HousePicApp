package com.tacademy.penthouse.slidingmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.penthouse.R;

public class MenuView extends FrameLayout{

	public MenuView(Context context) {
		super(context);
		init();
			
	}
	ImageView menu_ic;
	TextView menu_text;
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.menu_view, this);
		menu_ic = (ImageView)findViewById(R.id.menu_ic);
		menu_text = (TextView)findViewById(R.id.menu_text);
	}
	
	public void setData(int resId, String str){
		menu_ic.setImageResource(resId);
		menu_text.setText(str);
	}
	
}
