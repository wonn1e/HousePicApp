package com.tacademy.penthouse;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class Tab3NoFriendView extends FrameLayout {

	public Tab3NoFriendView(Context context) {
		super(context);
		initData();
	}
	
	public Tab3NoFriendView(Context context, AttributeSet attrs) {
	    this(context, attrs,0);
	    initData();
	}

	public Tab3NoFriendView(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	    initData();
	}
	
	private void initData(){
		LayoutInflater.from(getContext()).inflate(R.layout.tab3_no_friend_sublayout, this);
	}

}
