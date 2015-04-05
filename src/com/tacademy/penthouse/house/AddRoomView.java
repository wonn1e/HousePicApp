package com.tacademy.penthouse.house;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.tacademy.penthouse.R;

public class AddRoomView extends FrameLayout{

	ImageView addRoomImg;
	public AddRoomView(Context context) {
		super(context);
		init();
	}
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.add_room_view, this);
		addRoomImg = (ImageView)findViewById(R.id.add_room_img);
	}
	

}
