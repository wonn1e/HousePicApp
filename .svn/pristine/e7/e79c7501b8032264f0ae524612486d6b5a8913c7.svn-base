package com.tacademy.penthouse.item;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.entity.ItemData;

public class ItemRecommandAdapter extends BaseAdapter{
	ArrayList<String> items = new ArrayList<String>();
	ArrayList<Integer> numbers = new ArrayList<Integer>();
	Context mContext;
	public ItemRecommandAdapter(Context context){
		mContext = context;
	}
	
	public void add(String imageURL, Integer iNum){
		items.add(imageURL);
		numbers.add(iNum);
		notifyDataSetChanged();
	}
	public void clear(){
		items.clear();
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int cnt = items.size();
		if(cnt == numbers.size())
			return cnt;
		return items.size();
	}
	public Integer getNum(int position){
		return numbers.get(position);
	}
	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ItemRecommandView v;
		if(convertView == null){
			v = new ItemRecommandView(mContext);
		}else{
			v = (ItemRecommandView)convertView;
		}
		//ItemData id = getItem(position);
		v.setItemRecommandData(items.get(position));
		
		return v;
	}
	public void updateData(ItemData data, int isLike){ //, int likeCnt) {
		data.islike = isLike;
	//	data.likeCnt = likeCnt;
		notifyDataSetChanged();
	}
}
