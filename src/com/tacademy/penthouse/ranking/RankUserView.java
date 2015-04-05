package com.tacademy.penthouse.ranking;


import java.util.ArrayList;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.manager.NetworkManager;

public class RankUserView extends FrameLayout {

	public RankUserView(Context context) {
		super(context);
		init();
	}
	UserData myData;
	UserData uData;
	ImageView user_img;
	TextView user_nickname, follower_num, houseName;
	Button btn;
	ArrayList<Integer> followUsers = new ArrayList<Integer>();
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
		myData = com.tacademy.penthouse.manager.UserManager.getInstance().getuData();
		LayoutInflater.from(getContext()).inflate(R.layout.rank_user_view, this);
		user_img = (ImageView)findViewById(R.id.userImgRank);
		user_nickname = (TextView)findViewById(R.id.userNicknameInRank);
		follower_num = (TextView)findViewById(R.id.userFollowerNum);
		houseName = (TextView)findViewById(R.id.houseName_ranking);
		loader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.img_mypage_default)
		.showImageForEmptyUri(R.drawable.img_mypage_default)
		.showImageOnFail(R.drawable.img_mypage_default)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
	//	.displayer(new RoundedBitmapDisplayer(100))
		.build();
		
		btn = (Button)findViewById(R.id.following_ranking);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onFollowClick(RankUserView.this, uData);
				}
			}
		});
	}

	public void setRankUserData(UserData data, int position){
		uData = data;
		if(myData == null){
			loader.displayImage(data.user_img_url, user_img, options);

			user_nickname.setText(data.user_nickname);
			follower_num.setText(data.follower_cnt+"");
			houseName.setText((position+1) + ". " + data.house_name);

		} else if(myData.user_num == uData.user_num){
			btn.setVisibility(View.INVISIBLE);
			
		} else {
			btn.setVisibility(View.VISIBLE);
		}
		loader.displayImage("http://54.178.158.103/images/"+ data.user_img_url, user_img, options);

		user_nickname.setText(data.user_nickname);
		follower_num.setText(data.follower_cnt+"");
		houseName.setText((position+1) + ". " + data.house_name);
		btn.setBackgroundResource(R.drawable.unfollow);
		btn.setTextColor(getResources().getColor(R.color.brown));
		followUsers = com.tacademy.penthouse.manager.UserManager.getInstance().getFollowUsers();
		if(followUsers != null){
			for(int i = 0; i < followUsers.size(); i++){
				if(uData.user_num == followUsers.get(i)){
					btn.setBackgroundResource(R.drawable.follow);
					btn.setTextColor(getResources().getColor(R.color.white));
					
					break;
				}
			}
		}
	}
	

	
}
