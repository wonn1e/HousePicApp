package com.tacademy.penthouse.itemlike;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tacademy.penthouse.entity.ColorData;

public class ColorAdapter extends BaseAdapter {

	ArrayList<ColorData> colorList = new ArrayList<ColorData>();
	Context c;
	
	public ColorAdapter(Context context){
		c = context;
	}
	public void add(ColorData cData){
		colorList.add(cData);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return colorList.size();
	}

	@Override
	public Object getItem(int position) {
		return colorList.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}
	public void clear(){
		colorList.clear();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ColorView v;
		if(convertView == null){
			v = new ColorView(c);
		}else{
			v = (ColorView)convertView;
		}
		v.setColorData(colorList.get(position));
		return v;
	}

}
