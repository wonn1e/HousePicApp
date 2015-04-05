package com.tacademy.penthouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.tacademy.penthouse.entity.WalkThroughData;
import com.tacademy.penthouse.manager.PropertyManager;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class WalkThroughActivity extends FragmentActivity {
	ViewPager mPager;
	PageIndicator mIndicator;
	WalkthroughFragmentAdapter mAdapter;
	Button start;
	int[] resId = {R.drawable.img_walkthroughs_1p,R.drawable.img_walkthroughs_2p,
						R.drawable.img_walkthroughs_3p,R.drawable.img_walkthroughs_4p,R.drawable.img_walkthroughs_5p};
	WalkThroughData wData = new WalkThroughData(resId);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_walkthrough);
		
		PropertyManager.getInstance().setAutoLogin(1);
		start = (Button)findViewById(R.id.btn_start);
		mPager = (ViewPager)findViewById(R.id.walkThroughPager);
		mAdapter = new WalkthroughFragmentAdapter(getSupportFragmentManager(), wData);
		mPager.setAdapter(mAdapter);
		mIndicator = (CirclePageIndicator)findViewById(R.id.walkThroughIndicator);
		mIndicator.setViewPager(mPager);
		
		
		
		start.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PropertyManager.getInstance().setWalkThroughRead(1);
				startActivity(new Intent(WalkThroughActivity.this, MainActivity.class));
				finish();
			}
		});
	}
}
