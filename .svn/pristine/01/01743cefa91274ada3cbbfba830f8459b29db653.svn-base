package com.tacademy.penthouse.slidingmenu;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tacademy.penthouse.R;


public class ContactTypeView extends FrameLayout{
	String type;
	public interface OnItemDataClickListener{
		public void onItemClick(View v, String type);
	}
	OnItemDataClickListener mListener;
	public void setOnItemDataClickListener(OnItemDataClickListener listener){
		mListener = listener;
	}
	
	public ContactTypeView(Context context) {
		super(context);
		init();
	}
	TextView sortCategory;
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.contact_type, this);
		sortCategory = (TextView)findViewById(R.id.contact_type_view);
		sortCategory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onItemClick(v, type);
				}
			}
		});
	}
	
	public void setData(String s){
		type = s;
		sortCategory.setText(s);
		sortCategory.setTextColor(getResources().getColor(com.tacademy.penthouse.R.color.search_category));
		if(s == ContactActivity.SAVE_CONTACT_TYPE){
			sortCategory.setTextColor(Color.BLACK);
		}
	}
}
