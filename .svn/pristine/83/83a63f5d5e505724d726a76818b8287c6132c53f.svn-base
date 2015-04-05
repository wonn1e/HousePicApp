package com.tacademy.penthouse.house;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UserRoomsData;
import com.tacademy.penthouse.house.HouseView.OnFollowClickListener;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleAdapter;

public class RoomAdapter extends BaseAdapter implements StickyGridHeadersSimpleAdapter ,OnFollowClickListener{
	Context mContext;
	
	UserData uData = new UserData();
	ArrayList<RoomData> rooms = new ArrayList<RoomData>();

	public RoomAdapter(Context context){
		mContext = context;
	}
	
	public void replace(UserRoomsData rd){
		rooms.clear();
		put(rd);
	}
	
	public void clear(){
		rooms.clear();
		notifyDataSetChanged();
	}
	
	boolean isHeaderGone = false;
	boolean noFollower = false;
	boolean noFollowing = false;
	
	public void put(UserRoomsData rD){
		uData = rD.user;
		
		if(rD.user.follower_cnt == 0){
			noFollower = true;
		}else if(rD.user.following_cnt == 0){
			noFollowing = true;
		}
		
		if(rD.rooms.size() == 0){
			isHeaderGone = false;
			rooms.add(new RoomData(0,-1,"","","",0,""));
		}else{
			int roomCnt = 0;
			for(int i= 0; i<rD.rooms.size(); i++){
				if(rD.rooms.get(i).room_ispublic == 1){
					rooms.add(rD.rooms.get(i));
					roomCnt++;
				}
			}
			
			if(roomCnt != 0){
				isHeaderGone = true;
			}else{
				isHeaderGone = false;
			}
			
			if(rooms.size() == 0){
				rooms.add(new RoomData(0,-1,"","","",0,""));
			}
		}
		notifyDataSetChanged();
	}
	public ArrayList<RoomData> set(){
		return rooms;
	}

	@Override
	public int getCount() {
		return rooms.size();
	}

	@Override
	public RoomData getItem(int arg0) {	
		return rooms.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		
		return arg0;
	}

	@Override
	public long getHeaderId(int position) {
		
		return getItem(position).user_num;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		UserRoomView v;
		if(convertView == null){
			v = new UserRoomView(mContext);
		}else{
			v = (UserRoomView)convertView;
		}
		v.setUserHouseRoomData(rooms.get(position));
		return v;
	
	}
	
	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		HouseView v;
		UserData ud = uData;
		if(convertView == null){
			v = new HouseView(mContext);
			v.setOnFollowClickListener(this);
		}else{
			v = (HouseView)convertView;
		}
		v.setData(ud);
		v.setHeaderGone(isHeaderGone);

		v.setFollower(noFollower);
		v.setFollowing(noFollowing);
		
		return v;
	}

	public interface OnAdapterFollowClickListener{
		public void onFollowerClick(View v, UserData uData);
		public void onFollowingClick(View v, UserData uData);
		public void onFollowClick(View v ,UserData uData);

	}
	OnAdapterFollowClickListener mAdapterListener;
	public void setOnAdapterFollowClickListener(OnAdapterFollowClickListener listener){
		mAdapterListener = listener;
	}
	



	@Override
	public void onFollowerClick(View v, UserData uData) {
		if (mAdapterListener != null) {
			mAdapterListener.onFollowerClick(v, uData);
		}					
	}


	@Override
	public void onFollowingClick(View v, UserData uData) {
		if (mAdapterListener != null) {
			mAdapterListener.onFollowingClick(v, uData);
		}			
	}


	@Override
	public void onFollowClick(View v, UserData uData) {
		if(mAdapterListener != null){
			mAdapterListener.onFollowClick(v, uData);
		}
	}
	
	
}
