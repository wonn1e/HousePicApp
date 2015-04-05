package com.tacademy.penthouse.ranking;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UsersData;
import com.tacademy.penthouse.manager.UserManager;
import com.tacademy.penthouse.ranking.RankUserView.OnFollowClickListener;

public class RankUserAdapter extends BaseAdapter implements OnFollowClickListener {

	public final static int VISIBLE_FLAG = 1;
	public final static int GONE_FLAG = 0;
	ArrayList<UserData> list = new ArrayList<UserData>();
	Context mContext;
	UsersData uData;
	UserData myData = UserManager.getInstance().getuData();
	
	public RankUserAdapter(Context context){
		mContext = context;
	}

	public ArrayList<UserData> set(){
		return list;
	}
	
	public void replace(UsersData uD) {
		list.clear();
		put(uD);
	}
	public void put(UsersData uD){
		uData = uD;
		for(int i = 0; i<uD.users.size(); i++){
			list.add(uD.users.get(i));
		}
		notifyDataSetChanged();
	}
	public void clear() {
		list.clear();
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RankUserView v;
		
		if(convertView == null){
			v = new RankUserView(mContext);
			v.setOnFollowClickListener(this);
		}else{
			v = (RankUserView)convertView;
		}
		v.setRankUserData(list.get(position), position);
		return v;
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
