package com.tacademy.penthouse.neighbor;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.manager.UserManager;

public class Neighbor extends FrameLayout {
	public Neighbor(Context context) {
		super(context);
		init();
	}	
	ImageView user_img;
	Button isFollowing;
	TextView user_name;
	ArrayList<Integer> followUsers = new ArrayList<Integer>();
	UserData uData;
	UserData myData;
	ImageLoader loader;
	DisplayImageOptions options;
	
	public interface OnFollowClickListener{
		public void onFollowClick(View v, UserData uData);
	}
	
	OnFollowClickListener mListener;
	
	public void setOnFollowClickListener(OnFollowClickListener listener){
		mListener = listener;
	}
	
	private void init(){
		myData = UserManager.getInstance().getuData();
		LayoutInflater.from(getContext()).inflate(R.layout.neighbor_layout, this);
		user_img = (ImageView)findViewById(R.id.user_img);
		isFollowing = (Button)findViewById(R.id.isNeighbor_btn);
		user_name = (TextView)findViewById(R.id.user_nickname);
		loader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.img_mypage_default)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.img_mypage_default)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		//.displayer(new RoundedBitmapDisplayer(100))
		.build();

		isFollowing.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onFollowClick(Neighbor.this, uData);
				}
			}
		});
	}
	public void setFollowingData(UserData data){
		uData = data;
		if(myData != null){
			if(data.user_num == 0){
				user_name.setVisibility(View.GONE);
				user_img.setVisibility(View.GONE);
				isFollowing.setVisibility(View.GONE);
			}else if(myData.user_num == uData.user_num){
				isFollowing.setVisibility(View.INVISIBLE);
			}else{
				isFollowing.setVisibility(View.VISIBLE);
			}
		}else{
			if(data.user_num == 0){
				user_name.setVisibility(View.GONE);
				user_img.setVisibility(View.GONE);
				isFollowing.setVisibility(View.GONE);
			}else{
				isFollowing.setVisibility(View.VISIBLE);
			}
		}
		loader.displayImage("http://54.178.158.103/images/"+ data.user_img_url, user_img,options);
		user_name.setText(data.user_nickname);
		isFollowing.setBackgroundResource(R.drawable.unfollow);
		isFollowing.setTextColor(getResources().getColor(R.color.brown));
		followUsers = UserManager.getInstance().getFollowUsers();
		if(followUsers != null){
			for(int i = 0; i < followUsers.size(); i++){
				if(uData.user_num == followUsers.get(i)){
					isFollowing.setBackgroundResource(R.drawable.follow);
					isFollowing.setTextColor(getResources().getColor(R.color.white));
					break;
				}
			}
		}
	}

}
