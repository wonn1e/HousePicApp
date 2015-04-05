package com.tacademy.penthouse;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MDMoreView extends FrameLayout{
	TextView mdCnt, mdMore;
	int itemCnt;
	public MDMoreView(Context context) {
		super(context);
		init();
	}
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.main_md_more_view, this);
		mdCnt = (TextView)findViewById(R.id.md_cnt);
		mdMore = (TextView)findViewById(R.id.md_more);
	}
	
	public void setData(int cnt, String color){
		mdCnt.setText(cnt+"");
		mdCnt.setBackgroundDrawable(new ColorDrawable(Color.parseColor(color)));
	}

	
}
