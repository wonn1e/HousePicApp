package com.tacademy.penthouse.item;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tacademy.penthouse.entity.ItemData;

public class ItemFragmentAdapter extends RefreshPagerAdapter {
	ItemData item;

	public ItemFragmentAdapter(FragmentManager fm, ItemData item) {
		super(fm);
		this.item = item;
	}

	public void clear() {
		item = null;
		clearAdapter();

	}

	public void setItemData(ItemData item) {
		this.item = item;
		notifyDataSetChanged();
	}

	@Override
	public Fragment getItem(int position) {
		Fragment f = new ItemFragment();
		Bundle b = new Bundle();
		b.putString("img", item.item_img_url[position]);
		b.putInt("item_like", item.islike);
		f.setArguments(b);
		return f;
	}

	@Override
	public int getCount() {
		int cnt = 0;
		if (item != null) {
			for (int i = 0; i < item.item_img_url.length; i++) {
				if (item.item_img_url[i] != "") {
					cnt++;
				}
			}
		}
		
		return cnt;
	}

}
