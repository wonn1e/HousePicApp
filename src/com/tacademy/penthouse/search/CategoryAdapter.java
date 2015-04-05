package com.tacademy.penthouse.search;

import java.util.ArrayList;

import android.app.Notification.Style;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.CategoryData;
import com.tacademy.penthouse.entity.CategoryResult;

public class CategoryAdapter extends BaseExpandableListAdapter {
	Context mContext;
	ArrayList<CategoryResult> mItems = new ArrayList<CategoryResult>();
	
	public CategoryAdapter(Context context){
		mContext = context;
	}
	
	public void put(String headerCategory, CategoryData data) {
		CategoryResult item = null;
		for (CategoryResult id : mItems) {
			if (id.category.equals(headerCategory)) {
				item = id;
				break;
			}
		}
		if (item == null) {
			item = new CategoryResult();
			item.category = headerCategory;
			mItems.add(item);
		}
		item.categories.add(data);
		notifyDataSetChanged();
	}
	
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return mItems.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
			CategoryResult item = mItems.get(groupPosition);
		return item.categories.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
			CategoryResult item = mItems.get(groupPosition);
		return item.category;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
			CategoryResult item = mItems.get(groupPosition);
		return item.categories.get(childPosition);
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
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView tv;
		
		if (convertView == null) {
			tv = (TextView)LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, null);
		} else {
			tv = (TextView)convertView;
		}
		tv.setText(mItems.get(groupPosition).category);
		tv.setTextSize(18);
		tv.setBackgroundResource(R.drawable.title_line);
		tv.setTypeface(Typeface.SANS_SERIF);
		tv.setTextColor(Color.BLACK);
		tv.setPadding(-2, 67, 40, 18);
		
		return tv;
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
		tv.setText(mItems.get(groupPosition).categories.get(childPosition).categoryName);
		tv.setTextSize(15);
		tv.setTypeface(Typeface.SANS_SERIF);
		tv.setHeight(96);
		tv.setPadding(-2, 0, 0, 0);
		tv.setBackgroundResource(R.drawable.list_line);
		tv.setTextColor(R.color.grey);
		return tv;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	@Override
	public int getGroupTypeCount() {
		return super.getGroupTypeCount();
	}
	
	@Override
	public int getGroupType(int groupPosition) {
		return super.getGroupType(groupPosition);
	}
	
	@Override
	public int getChildTypeCount() {
		return super.getChildTypeCount();
	}
	
	@Override
	public int getChildType(int groupPosition, int childPosition) {
		return super.getChildType(groupPosition, childPosition);
	}
	
}
