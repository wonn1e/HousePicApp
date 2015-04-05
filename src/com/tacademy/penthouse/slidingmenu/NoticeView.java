package com.tacademy.penthouse.slidingmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.NoticeData;

public class NoticeView extends FrameLayout{

	public NoticeView(Context context) {
		super(context);
		init();
	}
	
	TextView title;
	TextView date;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.notice_view, this);
		title = (TextView)findViewById(R.id.notice_title);
		date = (TextView)findViewById(R.id.notice_date);
		
	}
	
	public void setData(NoticeData d){
		title.setText(d.title);
		date.setText("2014/10/02");
	}
	
}
