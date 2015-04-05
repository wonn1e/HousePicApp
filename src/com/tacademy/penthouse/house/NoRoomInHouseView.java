package com.tacademy.penthouse.house;

import com.tacademy.penthouse.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class NoRoomInHouseView extends FrameLayout {

	public NoRoomInHouseView(Context context) {
		super(context);
		init();
	}
	
	public NoRoomInHouseView(Context context, AttributeSet attrs) {
	    this(context, attrs,0);
	    init();
	}
	
	public NoRoomInHouseView(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	    init();
	}
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.no_room_in_house, this);
	}

}
