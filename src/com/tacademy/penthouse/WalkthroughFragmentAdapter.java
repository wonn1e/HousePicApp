package com.tacademy.penthouse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.WalkThroughData;
import com.tacademy.penthouse.item.RefreshPagerAdapter;

public class WalkthroughFragmentAdapter extends RefreshPagerAdapter{
	WalkThroughData wData;
	public WalkthroughFragmentAdapter(FragmentManager fm, WalkThroughData wData) {
		super(fm);
		this.wData = wData;
	}
	
	@Override
	public Fragment getItem(int position) {
		Fragment f = new WalkThroughFragment();
		Bundle b = new Bundle();
		b.putInt("img", wData.resid[position]);
		f.setArguments(b);
		return f;	
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 5;
	}
	

}
