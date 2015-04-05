package com.tacademy.penthouse.house;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.RoomData;

public class UserRoomView extends FrameLayout {

	public UserRoomView(Context context) {
		super(context);
		init();
	}
	
	RoomData rData;
	ImageView roomImg;
	TextView roomName;
	ImageLoader loader;
	DisplayImageOptions options;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.user_room_in_house_view, this);
		roomImg = (ImageView)findViewById(R.id.userRoomImg);
		roomName = (TextView)findViewById(R.id.userRoomName);
		
		loader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.placeholder)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.build();
	}
	
	public void setUserHouseRoomData(RoomData d){
		rData = d;
		if(d.room_num == -1){
			roomImg.setVisibility(View.GONE);
			roomName.setVisibility(View.GONE);
		}
		loader.displayImage("http://54.178.158.103/images/"+d.room_img_url ,roomImg, options);
		roomName.setText(d.room_name);
	}
}
