package com.tacademy.penthouse.search;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class SortAdapter extends BaseAdapter implements SortView.OnItemDataClickListener{
	ArrayList<String> categories = new ArrayList<String>();
	Context mContext;

	public SortAdapter(Context c){
		mContext = c;
	}

	public void add(String s){
		categories.add(s);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return categories.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return categories.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SortView cv = new SortView(mContext);
		
		if(convertView == null){
			cv = new SortView(mContext);
			cv.setOnItemDataClickListener(this);
		}else{
			cv = (SortView)convertView;
		}
		
		String str = categories.get(position);
		cv.setData(str);
		return cv;

	}
	
	public interface OnAdapterItemClickListener{
		public void onAdapterItemClick(View v, String s);
	}
	
	OnAdapterItemClickListener mAdapterListener;

	public void setOnAdapterItemClickListener(OnAdapterItemClickListener l){
		mAdapterListener = l;
	}
	
	@Override
	public void onItemClick(View v, String filter) {
		if(mAdapterListener != null)
			mAdapterListener.onAdapterItemClick(v, filter);
	}
}
