package com.tacademy.penthouse.slidingmenu;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ContactAdapter extends BaseAdapter implements ContactTypeView.OnItemDataClickListener{
	ArrayList<String> types = new ArrayList<String>();
	Context mContext;
	public ContactAdapter(Context c) {
		mContext = c;
	}
	
	public void add(String s){
		types.add(s);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return types.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return types.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ContactTypeView cv = new ContactTypeView(mContext);
		
		if(convertView == null){
			cv = new ContactTypeView(mContext);
			cv.setOnItemDataClickListener(this);
		}else{
			cv = (ContactTypeView)convertView;
		}
		
		String str = types.get(position);
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
