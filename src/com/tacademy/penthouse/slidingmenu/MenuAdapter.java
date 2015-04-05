package com.tacademy.penthouse.slidingmenu;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MenuAdapter extends BaseAdapter {

	ArrayList<String> menu = new ArrayList<String>();
	ArrayList<Integer> ic = new ArrayList<Integer>();
	Context mContext;
	
	public MenuAdapter(Context context){
		mContext = context;
	}
	
	public void put(int resId, String str){
		menu.add(str);
		ic.add(resId);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MenuView mv;
		if(convertView == null){
			mv = new MenuView(mContext);
		}else{
			mv = (MenuView)convertView;
		}
			
		mv.setData(ic.get(position), menu.get(position));
		
		
		return mv;
	}

}
