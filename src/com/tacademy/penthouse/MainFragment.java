package com.tacademy.penthouse;

import com.tacademy.penthouse.search.CategorySearchFragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;

public class MainFragment extends Fragment {
	public static final String TAG_SEARCH = "fSearch";
	FragmentTabHost tabHost;
	TabWidget tabWidget;
	ActionBar mActionBar;
	CategorySearchFragment fSearch;
	
	@Override
	public void onResume() {
		super.onResume();
		mActionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
		mActionBar.show();
		mActionBar.setCustomView(R.layout.gnb_menu_logo_search);
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowHomeEnabled(false);
		
		ImageView gnb_menu = (ImageView)getActivity().findViewById(R.id.gnb_menu);
		gnb_menu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((MainActivity)getActivity()).getSlidingMenu().toggle();				
			}
		});
		
		ImageView gnb_search = (ImageView)getActivity().findViewById(R.id.gnb_search);
		gnb_search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Fragment f = getActivity().getSupportFragmentManager().findFragmentByTag(TAG_SEARCH);
				if (f == null) {

					while (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
						getActivity().getSupportFragmentManager().popBackStackImmediate();
					}

					fSearch = new CategorySearchFragment();
					FragmentTransaction ftSearch = getActivity().getSupportFragmentManager()
							.beginTransaction();
					ftSearch.replace(R.id.container, fSearch, TAG_SEARCH);
					ftSearch.addToBackStack(null);
					ftSearch.commit();
				}
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_main_fragment, container, false);
		MDTabView mdTab = new MDTabView(getActivity());
		EveryoneTabView everyTab = new EveryoneTabView(getActivity());
		FriendsTabView friendsTab = new FriendsTabView(getActivity());
		
		tabHost = (FragmentTabHost)v.findViewById(R.id.tabhost);
		tabWidget = (TabWidget)v.findViewById(android.R.id.tabs);
		tabWidget.setDividerDrawable(null);
		//tabWidget.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#40639A")));
		tabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(mdTab), Tab1MDFragment.class, null);
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(everyTab), Tab2EveryoneFragment.class, null);
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(friendsTab), Tab3FriendsFragment.class, null);
		tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				if (tabId.equals("tab1")) {
				} else if (tabId.equals("tab2")) {
				} else if (tabId.equals("tab3")) {
			
				}
			}
		});
		return v;
	}
	
	
	public void showTabWidget(boolean isShow) {
		if (isShow) {
			tabWidget.setVisibility(View.VISIBLE);
		} else {
			tabWidget.setVisibility(View.GONE);
		}
	}
	
}
