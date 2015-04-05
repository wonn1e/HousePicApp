package com.tacademy.penthouse.house;

import java.util.ArrayList;

import android.content.Context;
import android.text.Html;
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

public class HouseView extends FrameLayout{

	public HouseView(Context context) {
		super(context);
		init();
	}
	UserData uData;
	TextView user_nickname,house_intro;
	ImageView user_img, house_img, edit_btn;
	Button following_btn, follower_btn, follow_btn;
	boolean isClicked;
	ImageLoader loader;
	DisplayImageOptions userOptions, houseOptions;
	ArrayList<Integer> followUsers = new ArrayList<Integer>();;

	NoRoomInHouseView noRoomView;
	
	public interface OnFollowClickListener{
		public void onFollowerClick(View v, UserData uData);
		public void onFollowingClick(View v, UserData uData);
		public void onFollowClick(View v, UserData uData);
	}
	OnFollowClickListener mListener;
	public void setOnFollowClickListener(OnFollowClickListener listener){
		mListener = listener;
	}
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.header_view_house_layout, this);
		noRoomView = (NoRoomInHouseView)findViewById(R.id.noRoomInHouseView);
		user_nickname = (TextView)findViewById(R.id.userNicknameHouse);
		house_intro = (TextView)findViewById(R.id.houseInfoHouse);
		user_img = (ImageView)findViewById(R.id.userImgHouse);
		house_img = (ImageView)findViewById(R.id.houseImgHouse);
		following_btn = (Button)findViewById(R.id.followingBtnHouse);
		follower_btn = (Button)findViewById(R.id.followerBtnHouse);
		follow_btn = (Button)findViewById(R.id.followBtnHouse);
		loader = ImageLoader.getInstance();
		houseOptions = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.placeholder)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.img_myhouse_default)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.build();
		userOptions = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.img_mypage_default)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.build();
		
		following_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onFollowerClick(HouseView.this, uData);
				}
			}
		});

		
		follower_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onFollowingClick(HouseView.this, uData);
				}
			}
		});
		follow_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onFollowClick(HouseView.this, uData);
				}
			}
		});
	}
	
	public void setData(UserData data){
		uData = data;
		user_nickname.setText(uData.user_nickname);
		house_intro.setText(uData.house_intro);
		loader.displayImage("http://54.178.158.103/images/"+ uData.user_img_url, user_img, userOptions);
		loader.displayImage("http://54.178.158.103/images/" +uData.house_img_url , house_img, houseOptions);
		String followerCnt = "" + uData.follower_cnt;
		follower_btn.setText(Html.fromHtml( "<b>"+followerCnt+"</b>  ÆÈ·Î¿ö" ));
		following_btn.setText(Html.fromHtml("<b>"+ uData.following_cnt + "</b>  ÆÈ·ÎÀ×"));
		follow_btn.setBackgroundResource(R.drawable.unfollow);
		follow_btn.setTextColor(getResources().getColor(R.color.brown));
		followUsers = com.tacademy.penthouse.manager.UserManager.getInstance().getFollowUsers();
		if(followUsers != null){
			for(int i = 0; i < followUsers.size(); i++){
				if(uData.user_num == followUsers.get(i)){
					follow_btn.setBackgroundResource(R.drawable.follow);
					follow_btn.setTextColor(getResources().getColor(R.color.white));
					
					break;
				}
			}
		}

	}
	
	public void setHeaderGone(boolean isHeaderGone) {
		if(isHeaderGone == true){
			noRoomView.setVisibility(View.GONE);
		}else{
			noRoomView.setVisibility(View.VISIBLE);
		}
		
	}
	
	public void setFollower(boolean noFollower) {
		if(noFollower){
			follower_btn.setEnabled(false);
		}else{
			follower_btn.setEnabled(true);
		}
	}
	
	public void setFollowing(boolean noFollowing) {
		if(noFollowing){
			following_btn.setEnabled(false);
		}else{
			following_btn.setEnabled(true);
		}
	}
}
