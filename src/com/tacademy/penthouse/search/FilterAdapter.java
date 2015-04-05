package com.tacademy.penthouse.search;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class FilterAdapter extends BaseAdapter implements FilterView.OnItemDataClickListener{
	ArrayList<String> items = new ArrayList<String>();
	Context mContext;
	
	public FilterAdapter(Context c){
		mContext = c;
	}
	
	public void add(String s){
		items.add(s);
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
		FilterView v = new FilterView(mContext);
		
		if(convertView == null){
			v = new FilterView(mContext);
			v.setOnItemDataClickListener(this);
		}else{
			v = (FilterView)convertView;
		}
		
		v.setData(items.get(position));
		return v;
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
