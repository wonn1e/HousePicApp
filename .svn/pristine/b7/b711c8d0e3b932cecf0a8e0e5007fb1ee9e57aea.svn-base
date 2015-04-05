package com.tacademy.penthouse.slidingmenu;

import java.util.ArrayList;

import com.tacademy.penthouse.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ConfigInformAdapter extends BaseAdapter{
	ArrayList<String> items = new ArrayList<String>();
	Context mContext;
	public ConfigInformAdapter(Context context){
		mContext = context;
	}
	
	public void clear(){
		items.clear();
	}
	public void add(String str){
		items.add(str);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
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
	
		
		if(items.get(position).equals("Alert")){
			AlertOnOffView av;
				av = new AlertOnOffView(mContext);
			return av;
		}else if(items.get(position).equals("Title1")){
			ConfigTitleView tv1;
				tv1 = new ConfigTitleView(mContext);
				tv1.setData("설정");
				tv1.setBackgroundResource(R.drawable.title_line);
			return tv1;
		}else if(items.get(position).equals("Title2")){
			ConfigTitleView tv2;
				tv2 = new ConfigTitleView(mContext);
				tv2.setData("안내");
				tv2.setBackgroundResource(R.drawable.title_line);
			return tv2;
		}else{
			ConfigInformView cv;
			cv = new ConfigInformView(mContext);
		String str = items.get(position);
		cv.setData(str);
		return cv;
		}
	
	}
	
	

}
