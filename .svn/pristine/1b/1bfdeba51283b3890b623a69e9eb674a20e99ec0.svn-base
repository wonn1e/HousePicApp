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

public class RoomInHouseView extends FrameLayout {

	public RoomInHouseView(Context context) {
		super(context);
		init();
	}
	
	public interface OnRoomDeleteClickListener{
		public void onRoomDeleteClick(View v, RoomData room);
	}
	
	OnRoomDeleteClickListener mListener;
	public void setOnRoomDeleteClickListener(OnRoomDeleteClickListener l){
		mListener = l;
	}
	
	RoomData rData;
	ImageView room_img;
	TextView room_name, roomDelete;
	ImageLoader loader;
	DisplayImageOptions options;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.house_room_view, this);
		room_img =(ImageView)findViewById(R.id.room_img);
		room_name = (TextView)findViewById(R.id.room_name);
		roomDelete = (TextView)findViewById(R.id.deleteRoom);
		roomDelete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mListener != null) {
					mListener.onRoomDeleteClick(RoomInHouseView.this, rData);
				}
			}
		});
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
	
	public void setHouseRoomData(RoomData d){
		rData = d;
		loader.displayImage("http://54.178.158.103/images/"+ d.room_img_url ,room_img , options);
		room_name.setText(d.room_name);
	}
}
