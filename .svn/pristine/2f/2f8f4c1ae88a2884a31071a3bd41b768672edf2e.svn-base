package com.tacademy.penthouse.neighbor;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.entity.UserData;

public class FollowerAdapter extends BaseAdapter {

	ArrayList<UserData> items = new ArrayList<UserData>();
	Context mContext;
	
	public FollowerAdapter(Context c){
		mContext = c;
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
		Neighbor follower;
		if(convertView == null){
			follower = new Neighbor(mContext);
		}else{
			follower = (Neighbor)convertView;
		}
		follower.setFollowingData(items.get(position));
		return follower;
	}
}
