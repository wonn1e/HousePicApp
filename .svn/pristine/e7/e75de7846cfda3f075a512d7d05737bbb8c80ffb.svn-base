package com.tacademy.penthouse.slidingmenu;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.tacademy.penthouse.entity.NoticeData;

public class NoticeAdapter extends BaseExpandableListAdapter{

	Context mContext;
	ArrayList<NoticeData> items = new ArrayList<NoticeData>();
	
	public NoticeAdapter(Context context){
		mContext = context;
	}
	
	public void put(String title, String detail){
		NoticeData item = null;
		for(NoticeData nd : items){
			if(nd.title.equals(title)){
				item = nd;
				break;
			}
		}
		if(item == null){
			item = new NoticeData();
			item.title = title;
			items.add(item);
		}
		item.detail.add(detail);
		notifyDataSetChanged();
	}
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		NoticeData item = items.get(groupPosition);
		
		return item.detail.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		NoticeData item = items.get(groupPosition);
		return item.title;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		NoticeData item = items.get(groupPosition);
		return item.detail.get(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		
		return (((long)groupPosition) << 32 | ((long)childPosition));
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		NoticeView v;
		if(convertView == null){
				v = new NoticeView(mContext);
			}else{
				v = (NoticeView)convertView;
			}
		v.setData(items.get(groupPosition));
		
		return v;
		
//		TextView tv;
//		if (convertView == null) {
//			tv = (TextView)LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, null);
//		} else {
//			tv = (TextView)convertView;
//		}
//		tv.setText(items.get(groupPosition).title);
//		
//		return tv;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		TextView tv;
		if (convertView == null) {
			tv = (TextView)LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, null);
		} else {
			tv = (TextView)convertView;
		}
		tv.setText(items.get(groupPosition).detail.get(childPosition));
		tv.setPadding(16, 16, 16, 21);
		tv.setTextSize(15);
		return tv;
	}

	

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
