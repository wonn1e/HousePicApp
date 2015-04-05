package com.tacademy.penthouse.search;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tacademy.penthouse.R;

public class FilterView extends FrameLayout {
	String filter;
	public interface OnItemDataClickListener{
		public void onItemClick(View v, String filter);
	}
	OnItemDataClickListener mListener;
	public void setOnItemDataClickListener(OnItemDataClickListener listener){
		mListener = listener;
	}

	public FilterView(Context context) {
		super(context);
		init();
	}
	TextView sortCategory;
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.search_item, this);
		sortCategory = (TextView)findViewById(R.id.textView1);
		sortCategory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onItemClick(v, filter);
				}
			}
		});
	}
	
	public void setData(String s){
		filter = s;
		sortCategory.setText(s);
		sortCategory.setTextColor(getResources().getColor(com.tacademy.penthouse.R.color.search_category));
		if(s == SearchResultActivity.SAVED_FILTER_STRING){
			sortCategory.setTextColor(Color.BLACK);
		}
	}
}
