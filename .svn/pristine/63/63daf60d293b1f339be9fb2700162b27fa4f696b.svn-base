package com.tacademy.penthouse.ranking;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.ItemsData;

public class RankItemAdapter extends BaseAdapter implements RankItemView.OnPopularItemLikeListener{
	ArrayList<ItemData> list = new ArrayList<ItemData>();
	Context mContext;
	ItemsData iData;
	public RankItemAdapter(Context context){
		mContext = context;
	}

	/*public void add(ItemData d){
		list.add(d);
		notifyDataSetChanged();
	}*/
	public void put(ItemsData iD){
		iData = iD;
		for(int i = 0; i<iD.items.size(); i++){
			list.add(iD.items.get(i));
		}
		notifyDataSetChanged();
	}
	public void replace(ItemsData iD){
		list.clear();
		put(iD);
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
		RankItemView v;
		if(convertView == null){
			v = new RankItemView(mContext);
			v.setOnPopularItemLikeListener(this);
		}else{
			v = (RankItemView)convertView;
		}
		v.setRankItemData(list.get(position), position);
		return v;
	}

	public interface OnAdapterPopularItemLikeListener{
		public void onPopularItemLike(View v, ItemData iData);
	}
	OnAdapterPopularItemLikeListener pAdapterListener;
	public void setOnAdapterPopularItemLikeListener(OnAdapterPopularItemLikeListener l){
		pAdapterListener = l;
	}

	public void updateData(ItemData data, int isLike){	//, int likeCnt){
		data.islike = isLike;
		//data.likeCnt = likeCnt;
		notifyDataSetChanged();
	}

	@Override
	public void onPopularItemLikeListener(View v, ItemData iData) {
		if(pAdapterListener != null){
			pAdapterListener.onPopularItemLike(v, iData);
		}
	}

}
