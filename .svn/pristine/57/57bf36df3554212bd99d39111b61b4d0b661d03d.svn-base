package com.tacademy.penthouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tacademy.penthouse.UserRoomView.OnUserImgClickListener;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;

public class MDRoomView extends FrameLayout {

	public MDRoomView(Context context) {
		super(context);
		init();
	}
	
	public interface OnImgClickListener{
		public void onImgClick(View v, RoomData data);
	}
	OnImgClickListener mListener;
	public void setOnImgClickListener(OnImgClickListener l){
		mListener = l;
	}
	
	RoomData rData;
	TextView md_room_name, copyright;
	ImageView md_room_img;
	ImageLoader loader;
	
	DisplayImageOptions options;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.main_md_header_view, this);
		md_room_name = (TextView)findViewById(R.id.roomName);
		copyright = (TextView)findViewById(R.id.copyright);
		copyright.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onImgClick(MDRoomView.this, rData);
				}
			}
		});
		md_room_img = (ImageView)findViewById(R.id.room_img);
		
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
	
	public void setData(RoomData data, UserData uData){
		rData = data;
		loader.displayImage(data.room_img_url,md_room_img,options);
		copyright.setText(data.source_name);
		md_room_name.setText(data.room_name);
	}

}
