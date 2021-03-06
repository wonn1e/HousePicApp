package com.tacademy.penthouse.house;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UserRoomsData;
import com.tacademy.penthouse.house.MyHouseView.OnItemClickListener;
import com.tacademy.penthouse.manager.UserManager;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleAdapter;

public class MyRoomAdapter extends BaseAdapter implements StickyGridHeadersSimpleAdapter ,OnItemClickListener, RoomInHouseView.OnRoomDeleteClickListener{
	Context mContext;
	
	UserRoomsData urData = new UserRoomsData();	
	ArrayList<RoomData> rooms = new ArrayList<RoomData>();

	public MyRoomAdapter(Context context){
		mContext = context;
	}
	public ArrayList<RoomData> set(){
		return rooms;
	}
	
	public void put(UserRoomsData rD){
		urData = rD;
		int temp_u_num = 0;
		for(int i= 0; i<rD.rooms.size()+1; i++){
			if(rD.rooms.size() == 0){
				rooms.add(new RoomData(temp_u_num,0,"","","",0,""));
				break;
			}
			if(i < rD.rooms.size()){
				rooms.add(rD.rooms.get(i));
				temp_u_num = rD.rooms.get(i).user_num;
				
			} else if(i == rD.rooms.size()){
				rooms.add(new RoomData(temp_u_num,0,"","","",0,""));
			}
		}
		
		notifyDataSetChanged();
	}
	public void replace(UserRoomsData rd){
		rooms.clear();
		put(rd);
	}
	public void clear(){
		rooms.clear();
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
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
	public int getViewTypeCount() {
		return 2;
	}
	
	@Override
	public int getItemViewType(int position) {
		if(rooms.get(position).room_num == 0){
			return 1;
		}else{
			return 0;
		}
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		switch(getItemViewType(position)){
		case 0:
			RoomInHouseView v;
			if(convertView == null){
				v = new RoomInHouseView(mContext);
				v.setOnRoomDeleteClickListener(this);
			}else{
				v = (RoomInHouseView)convertView;
			}
			v.setHouseRoomData(rooms.get(position));
			return v;
	
		
		case 1:
			AddRoomView av;
			if(convertView == null){
				av = new AddRoomView(mContext);
			}else{
				av = (AddRoomView)convertView;
			}
			return av;
		}	
		return null;
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		MyHouseView v;
		UserData ud = UserManager.getInstance().getuData();
		if(convertView == null){
			v = new MyHouseView(mContext);
			v.setOnItemClickListener(this);
		
		}else{
			v = (MyHouseView)convertView;
		}
		v.setData(ud);

		return v;
	}
	
	public interface OnAdapterEditClickListener{
//		public void onNickNameEditClick(View v, UserData uData);
//		public void onHouseIntroEditClick(View v, UserData uData);
//		public void onUserImgEditClick(View v, UserData uData);
//		public void onHouseImgEditClick(View v, UserData uData);
		public void onEditClick(View v, UserData uData);
		public void onFollowClick(View v, UserData uData);
		public void onFollowingClick(View v, UserData uData);

	}
	OnAdapterEditClickListener mAdapterListener;
	public void setOnAdapterEditClickListener(OnAdapterEditClickListener listener){
		mAdapterListener = listener;
	}


//	@Override
//	public void onNickNameEditClick(View v, UserData uData) {
//		if (mAdapterListener != null) {
//			mAdapterListener.onNickNameEditClick(v, uData);
//		}		
//	}
//
//	@Override
//	public void onHouseIntroEditClick(View v, UserData uData) {
//		if (mAdapterListener != null) {
//			mAdapterListener.onHouseIntroEditClick(v, uData);
//		}				
//	}
//
//
//	@Override
//	public void onUserImgEditClick(View v, UserData uData) {
//		if (mAdapterListener != null) {
//			mAdapterListener.onUserImgEditClick(v, uData);
//		}				
//	}
//
//
//	@Override
//	public void onHouseImgEditClick(View v, UserData uData) {
//		if (mAdapterListener != null) {
//			mAdapterListener.onHouseImgEditClick(v, uData);
//		}		
//	}


	@Override
	public void onEditClick(View v, UserData uData) {
		if (mAdapterListener != null) {
			mAdapterListener.onEditClick(v, uData);
		}			
	}


	@Override
	public void onFollowClick(View v, UserData uData) {
		if (mAdapterListener != null) {
			mAdapterListener.onFollowClick(v, uData);
		}					
	}


	@Override
	public void onFollowingClick(View v, UserData uData) {
		if (mAdapterListener != null) {
			mAdapterListener.onFollowingClick(v, uData);
		}			
	}

//	public void updateNickName(UserData uData, String newName){
//		uData.user_nickname = newName;
//		notifyDataSetChanged();
//	}
//	public void updateHouseName(UserData uData, String newName){
//		uData.house_name = newName;
//		notifyDataSetChanged();
//	}
//	public void updateHouseIntro(UserData uData, String newIntro){
//		uData.house_intro = newIntro;
//		notifyDataSetChanged();
//	}
//	public void updateUserImg(UserData uData, String newURL){
//		uData.user_img_url = newURL;
//		notifyDataSetChanged();
//	}
//	public void updateHouseImg(UserData uData, String newURL){
//		uData.house_img_url = newURL;
//		notifyDataSetChanged();
//	}
	public void updateFollowCnt(UserData uData){
		this.urData.user = uData;
		notifyDataSetChanged();
	}
	
	 public interface OnAdapterDeleteClickListener{
	      public void onDeleteClick(View v, RoomData room);
	   }
	   OnAdapterDeleteClickListener mAdapterDeleteListener;
	   public void setOnAdapterDeleteClickListener(OnAdapterDeleteClickListener listener){
	      mAdapterDeleteListener = listener;
	   }
	   @Override
	   public void onRoomDeleteClick(View v, RoomData room) {
	      if(mAdapterDeleteListener != null){
	         mAdapterDeleteListener.onDeleteClick(v, room);
	      }
	   }

	
}
