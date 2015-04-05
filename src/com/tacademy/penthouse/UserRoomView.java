package com.tacademy.penthouse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;

public class UserRoomView extends FrameLayout {

	public UserRoomView(Context context) {
		super(context);
		init();
	}
	public interface OnUserImgClickListener{
		public void onUserImgClick(View v, UserData data);
	}
	OnUserImgClickListener mListener;
	public void setOnUserImgClickListener(OnUserImgClickListener l){
		mListener = l;
	}
	
	RoomData rData;
	UserData uData;
	TextView roomUpdate, roomName;
	ImageView roomImg, userImg;
	ImageLoader loader;
	DisplayImageOptions options, userOpt;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.main_user_header_view, this);
		roomUpdate = (TextView)findViewById(R.id.userTimeOfRoom);
		roomName = (TextView)findViewById(R.id.userRoomName);
		roomImg = (ImageView)findViewById(R.id.userRoomImg);
		userImg= (ImageView)findViewById(R.id.userImgInTab2);
		userImg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onUserImgClick(UserRoomView.this, uData);
				}
			}
		});
		loader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.placeholder)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.img_myhouse_default)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.build();
		userOpt = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.img_mypage_default)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.img_mypage_default)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.build();
		
	}
	
	public void setData(RoomData data, 	UserData uD) throws ParseException {
		rData = data;
		uData = uD;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		long delay = (long)(System.currentTimeMillis() - sdf.parse(data.room_date).getTime()) - 32400000;
		//32400000 : 9시간의 시차
		if(delay < 60 * 1000){
			roomUpdate.setText("방금");
			//1분 미만
		}else if(delay < 3600 * 1000){
			long minute;
			minute =  (long)(delay / 1000 / 60) ; 
			roomUpdate.setText(minute + "분전");
			  //1시간 미만
		  }else if(delay < 3600 * 1000 * 24){
			  long hours;
			  hours = (long) (delay / 3600 / 1000);
			  roomUpdate.setText(hours + "시간 전");
			  //하루미만
		  }else if(delay < 3600L * 1000L * 24L * 30L){
			  long days;
			  days = (long) delay / 3600 / 1000 / 24;
			  roomUpdate.setText(days + "일 전");
			  //한달 미만
		  }
		  else{
			  roomUpdate.setText("오래 전");
		  }
		if((data.room_img_url.substring(0, 4)).equals("http")){
			loader.displayImage(data.room_img_url, roomImg, options);
		}else{
			loader.displayImage("http://54.178.158.103/images/" + data.room_img_url, roomImg, options);
		}
	
		loader.displayImage("http://54.178.158.103/images/" + uD.user_img_url, userImg, userOpt);
		roomName.setText(data.room_name);	
		
		
	}

}
