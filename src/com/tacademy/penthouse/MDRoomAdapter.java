package com.tacademy.penthouse;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.UsersRoomAdapter.OnUserAdapterImgClickListener;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.MultiUserRoomItemsData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleAdapter;

public class MDRoomAdapter extends BaseAdapter implements MDRoomView.OnImgClickListener, StickyGridHeadersSimpleAdapter ,MDItemView.OnItemDataClickListener, MDItemView.OnItemDataLikeClickListener{
	Context mContext;
	MultiUserRoomItemsData userRoomItems = new MultiUserRoomItemsData();
	
	ArrayList<ItemData> items = new ArrayList<ItemData>();

	public MDRoomAdapter(Context context){
		mContext = context;
	}
	
	public ArrayList<ItemData> set(){
		return items;
	}
	public void put(MultiUserRoomItemsData rr){
		userRoomItems = rr;
		for(int j = 0; j < userRoomItems.users.size(); j++){
				
			for(int i=0; i<userRoomItems.users.get(j).items.size(); i++){
				items.add(userRoomItems.users.get(j).items.get(i));
				if(i == 4){
					items.add(new ItemData(userRoomItems.users.get(j).items.get(0).room_num, 0, "", "", "", 0, "", null, 0, "", 0, null, "", 0));
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
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public ItemData getItem(int arg0) {	
		return items.get(arg0);
	}
	
	@Override
	public long getItemId(int arg0) {
		
		return arg0;
	}

	@Override
	public long getHeaderId(int position) {
		int headerId = getItem(position).room_num;
		return headerId;
	}
	
	@Override
	public int getViewTypeCount() {
		return 2;
	}
	@Override
	public int getItemViewType(int position) {
		if(items.get(position).item_num == 0){
			return 1;
		}else{
			return 0;
		}
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		switch(getItemViewType(position)){
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
		case 0:
			MDItemView iv;

			if(convertView == null){
				iv = new MDItemView(mContext);
				iv.setOnItemDataLikeClickListener(this);
			
			}else{
				iv = (MDItemView)convertView;
			}

			iv.setData(items.get(position));

			return iv;
		
		}
		return null;
	}
	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {


		MDRoomView v;

		ItemData d= items.get(position);
		RoomData rd = d.parent.room;
		UserData ud = d.parent.user;
		if(convertView == null){
			v = new MDRoomView(mContext);
			v.setOnImgClickListener(this);
		}else{
			v = (MDRoomView)convertView;
		}
		v.setData(rd, ud);
		return v;
	}

	public interface OnAdapterItemClickListener{
		public void onItemClick(View v, ItemData data);
	}
	OnAdapterItemClickListener mAdapterListener;
	public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener){
		mAdapterListener = listener;
	}

	@Override
	public void onItemClick(View v, ItemData data) {
		if(mAdapterListener != null){
			mAdapterListener.onItemClick(v, data);
		}
	}

	
	public interface OnAdapterItemLikeClickListener{
		public void onItemLikeClick(View v, ItemData data);
	}
	
	OnAdapterItemLikeClickListener lAdapterListener;
	
	public void setOnAdapterItemLikeClickListener(OnAdapterItemLikeClickListener listener){
		lAdapterListener = listener;
	}
	
	/////
	public interface OnAdapterImgClickListener{
		public void onImgAdapterClick(View v, RoomData data);
	}
	
	OnAdapterImgClickListener iAdapterListener;
	public void setOnUserAdapterImgClickListener(OnAdapterImgClickListener l){
		iAdapterListener = l;
	}
	
	public void onImgClick(View v, RoomData data){
		if(iAdapterListener != null){
			iAdapterListener.onImgAdapterClick(v, data);
		}
	}
	
	///////
	public void updateData(ItemData data,int isLike, int likeCnt){//, int likeCnt) {
		data.islike = isLike;
		data.likeCnt = likeCnt;
		notifyDataSetChanged();
	}
	
	@Override
	public void onItemDataLikeClick(View v, ItemData data) {
		if(lAdapterListener != null){
			lAdapterListener.onItemLikeClick(v, data);
		}
	}
	
	
}
