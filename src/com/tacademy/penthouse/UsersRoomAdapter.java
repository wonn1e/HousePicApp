package com.tacademy.penthouse;

import java.text.ParseException;
import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.MultiUserRoomItemsData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleAdapter;

public class UsersRoomAdapter extends BaseAdapter implements StickyGridHeadersSimpleAdapter ,MDItemView.OnItemDataLikeClickListener ,UserRoomView.OnUserImgClickListener{

	Context mContext;
	MultiUserRoomItemsData userRoomD = new MultiUserRoomItemsData();
	ArrayList<ItemData> items = new ArrayList<ItemData>();
	
	public UsersRoomAdapter(Context c){
		mContext= c;
	}
	
	public ArrayList<ItemData> set(){
		return items;
	}
	
	public void put(MultiUserRoomItemsData multiUsers){
		userRoomD = multiUsers;
		if(multiUsers != null)
		for(int i=0; i < multiUsers.users.size(); i++){
			for(int j=0; j < multiUsers.users.get(i).items.size(); j++){
				items.add(multiUsers.users.get(i).items.get(j));
				if(j==4){
					items.add(new ItemData(multiUsers.users.get(i).items.get(0).room_num, 0, "", "", "", 0, "", null, 0, "", 0, null, "", 0));
					break;
				}
			}
		}
		
		notifyDataSetChanged();
	}
	public void replace(MultiUserRoomItemsData rr){
		items.clear();
		put(rr);
	}
	public void clear(){
		items.clear();
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public ItemData getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	
	@Override
	public int getViewTypeCount() {
		return 2;
	}
	
	@Override
	public int getItemViewType(int position) {
		if(items.get(position).item_num == 0){
			return 1;
		}
		else{
			return 0;
		}
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		switch(getItemViewType(position)){	
		case 0:
			MDItemView v;

			if(convertView == null){
				v = new MDItemView(mContext);
				v.setOnItemDataLikeClickListener(this);
			}else{
				v = (MDItemView)convertView;
			}
			v.setData(items.get(position));

			return v;
		case 1 :
			MDMoreView mv;

			ItemData d = items.get(position-1);
			int cnt = d.parent.items.size();
			if(convertView == null){
				mv = new MDMoreView(mContext);
			}else{
				mv = (MDMoreView)convertView;
			}
			mv.setData(cnt, d.parent.room.room_color);
			return mv;
		}
		return null;
	}

	

	@Override
	public long getHeaderId(int position) {
		int headerId =getItem(position).room_num;
		return headerId;
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		UserRoomView v;
		ItemData d = items.get(position);
		RoomData rd = d.parent.room;
		UserData ud = d.parent.user;
		
		if(convertView == null){
			v = new UserRoomView(mContext);
			v.setOnUserImgClickListener(this);
		}else{
			v = (UserRoomView)convertView;
		}
		
			try {
				v.setData(rd, ud);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		return v;
	}
	public interface OnAdapterItemLikeClickListener{
		public void OnItemLikeClickListener(View v, ItemData data);
	}
	OnAdapterItemLikeClickListener mAdapterListener2;
	public void setOnAdapterItemLikeClickListener(OnAdapterItemLikeClickListener l){
		mAdapterListener2 = l;
	}
	/////
	public interface OnUserAdapterImgClickListener{
		public void onUserImgAdapterClick(View v, UserData data);
	}
	
	OnUserAdapterImgClickListener mAdapterListener;
	public void setOnUserAdapterImgClickListener(OnUserAdapterImgClickListener l){
		mAdapterListener = l;
	}
	
	public void onUserImgClick(View v, UserData data){
		if(mAdapterListener != null){
			mAdapterListener.onUserImgAdapterClick(v, data);
		}
	}
	///////
	@Override
	public void onItemDataLikeClick(View v, ItemData data) {
		if(mAdapterListener2 != null){
			mAdapterListener2.OnItemLikeClickListener(v, data);
		}
	}

	public void updateData(ItemData data, int isLike, int likeCnt) {
		data.islike = isLike;
		data.likeCnt = likeCnt;
		notifyDataSetChanged();
	}
}
