package com.tacademy.penthouse.neighbor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.tacademy.penthouse.MainActivity;
import com.tacademy.penthouse.R;

public class NeighborListActivity extends ActionBarActivity {
	ViewPager pager;
	TabHost tabHost;
	TabsAdapter mAdapter;
	int uDataNum;
	ActionBar mActionBar;
	TextView gnbTitle;
	public static final String PARAM_CURRENT_TAB = "currentTab";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mActionBar = getSupportActionBar();
		mActionBar.show();
		mActionBar.setCustomView(R.layout.gnb_title);
		mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#40639a")));
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowHomeEnabled(false);
		gnbTitle = (TextView)findViewById(R.id.title);
		gnbTitle.setText("ÀÌ¿ô¸®½ºÆ®");		
		
		Intent i = getIntent();
		uDataNum = i.getIntExtra("uData", 0);
		
		setContentView(R.layout.activity_neighbor_list);
		tabHost = (TabHost)findViewById(R.id.tabhost);
		pager = (ViewPager)findViewById(R.id.pager);
		tabHost.setup();
		mAdapter = new TabsAdapter(this, getSupportFragmentManager(), tabHost, pager);
		
		FollowerTabView tab1 = new FollowerTabView(NeighborListActivity.this);
		FollowingTabView tab2 = new FollowingTabView(NeighborListActivity.this);
		
		Bundle b = new Bundle();
		b.putInt("uNum", uDataNum);
		
		mAdapter.addTab(tabHost.newTabSpec("tab2").setIndicator(tab1), FollowerList.class, b);
		mAdapter.addTab(tabHost.newTabSpec("tab1").setIndicator(tab2), FollowingList.class, b);
		
		if(savedInstanceState != null){
			mAdapter.onRestoreInstanceState(savedInstanceState);
			tabHost.setCurrentTabByTag(savedInstanceState.getString(PARAM_CURRENT_TAB));
		}
		
		int tab =i.getIntExtra(PARAM_CURRENT_TAB, 0);
		
		if(tab == 0)
			tabHost.setCurrentTab(0);
		else if(tab == 1)
			tabHost.setCurrentTab(1);
		
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		
	}
	
	@Override
	public View onCreateView(String name, @NonNull Context context,
			@NonNull AttributeSet attrs) {
		return super.onCreateView(name, context, attrs);
	}
	/*
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 0){
			tabHost.setCurrentTab(FollowingList.REQUEST_CODE_FOLLOWING);
		}else if(requestCode == 1){
			tabHost.setCurrentTab(FollowerList.REQUEST_CODE_FOLLOWER);
		}
	}
	*/
	@Override
	protected void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);
		mAdapter.onSaveInstanceState(outState);
		outState.putString(PARAM_CURRENT_TAB, tabHost.getCurrentTabTag());
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case android.R.id.home:
			startActivity(new Intent(NeighborListActivity.this, MainActivity.class));
		}
		return super.onOptionsItemSelected(item);
	}

}
