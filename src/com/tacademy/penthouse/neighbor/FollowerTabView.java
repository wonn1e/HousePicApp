package com.tacademy.penthouse.neighbor;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.tacademy.penthouse.R;

public class FollowerTabView extends FrameLayout {

	public FollowerTabView(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.neighborlist_follower_tab, this);
	}

}
