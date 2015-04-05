package com.tacademy.penthouse.neighbor;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.entity.UserData;

public class NeighborAdapter extends BaseAdapter implements Neighbor.OnFollowClickListener{
	
	ArrayList<UserData> items = new ArrayList<UserData>();
	Context mContext;
	
	public NeighborAdapter(Context c){
		mContext = c;
	}

	public void clear(){
		items.clear();
		notifyDataSetChanged();
	}
	public void add(UserData d){
		items.add(d);
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		return items.size();
	}
	
	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Neighbor following;
		if(convertView == null){
			following = new Neighbor(mContext);
			following.setOnFollowClickListener(this);
		}else{
			following = (Neighbor)convertView;
		}
		following.setFollowingData(items.get(position));
		return following;
	}
	
	public interface OnAdapterFollowClickListener{
		public void onFollowClick(View v ,UserData uData);

	}
	OnAdapterFollowClickListener mAdapterListener;
	public void setOnAdapterFollowClickListener(OnAdapterFollowClickListener listener){
		mAdapterListener = listener;
	}

	@Override
	public void onFollowClick(View v, UserData uData) {
		if(mAdapterListener != null){
			mAdapterListener.onFollowClick(v, uData);
		}	
	}

}
