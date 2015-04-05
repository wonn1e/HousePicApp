package com.tacademy.penthouse;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.house.MyHouseFragment;
import com.tacademy.penthouse.manager.UserManager;
import com.tacademy.penthouse.member.LogInActivity;
import com.tacademy.penthouse.ranking.RankingFragment;
import com.tacademy.penthouse.search.CategorySearchFragment;
import com.tacademy.penthouse.slidingmenu.ConfigInformFragment;
import com.tacademy.penthouse.slidingmenu.InviteFragment;
import com.tacademy.penthouse.slidingmenu.MenuFragment;

public class MainActivity extends SlidingFragmentActivity {
	public static final String TAG_SEARCH = "fSearch";
	public static final String TAG_RANKING = "fRanking";
	public static final String TAG_MYHOUSE = "fMyHouse";
	public static final String TAG_MAIN = "fMain";
	public static final String TAG_CONFIGINFORM = "fConfig";
	public static final String TAG_INVITE = "fInvite";
	CategorySearchFragment fSearch;
	RankingFragment fRanking;
	MyHouseFragment fMyHouse;
	MainFragment fMain;
	ConfigInformFragment fConfig;
	InviteFragment fInvite;
	String mColor;
	UserData uData;
	UserData myData = UserManager.getInstance().getuData();
	ActionBar mActionBar;
	
	
	// ///////////////////////////////////////////////////////////////
	private static final int MESSAGE_FINISH_TIMEOUT = 0;
	private static final int BACK_PRESS_TIMEOUT = 2000;
	
	boolean isBackPressed = false;
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MESSAGE_FINISH_TIMEOUT:
				isBackPressed = false;
				break;
			}
		}
	};

	protected void onResume() {
		super.onResume();
		myData = UserManager.getInstance().getuData();
	};
	@Override
	public void onBackPressed() {
		if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
			if (!isBackPressed) {
				isBackPressed = true;
				Toast.makeText(this, "한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT)
				.show();
				mHandler.sendMessageDelayed(
						mHandler.obtainMessage(MESSAGE_FINISH_TIMEOUT),
						BACK_PRESS_TIMEOUT);
			} else {
				mHandler.removeMessages(MESSAGE_FINISH_TIMEOUT);
				UserManager.getInstance().setuData(null);
				UserManager.getInstance().setFollowUsers(null);
				UserManager.getInstance().setUserNum(-1);
				finish();
			}
		} else {
			super.onBackPressed();
		}
	}
	
	// Implement Back
	// key///////////////////////////////////////////////////////////
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.menu_frame);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
			.add(R.id.container, new MainFragment()).commit();
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.menu_container, new MenuFragment()).commit();
		}

		
		mActionBar = getSupportActionBar();
		mActionBar.show();
		mActionBar.setCustomView(R.layout.gnb_menu_logo_search);
		//mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#40639a")));
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowHomeEnabled(false);
		
		
		
		ImageView gnb_menu = (ImageView)findViewById(R.id.gnb_menu);
		gnb_menu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getSlidingMenu().toggle();				
			}
		});
		
		ImageView gnb_search = (ImageView)findViewById(R.id.gnb_search);
		gnb_search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Fragment f = getSupportFragmentManager().findFragmentByTag(TAG_SEARCH);
				if (f == null) {

					while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
						getSupportFragmentManager().popBackStackImmediate();
					}

					fSearch = new CategorySearchFragment();
					FragmentTransaction ftSearch = getSupportFragmentManager()
							.beginTransaction();
					ftSearch.replace(R.id.container, fSearch, TAG_SEARCH);
					ftSearch.addToBackStack(null);
					ftSearch.commit();
				}
			}
		});
		
		
		SlidingMenu sm = getSlidingMenu();
		sm.setMode(SlidingMenu.LEFT);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		setSlidingActionBarEnabled(true);
		
	}

	public static final String MENU_TYPE = "menuType";
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		int menu = intent.getIntExtra(MENU_TYPE, -1);
		if (menu == MenuFragment.ID_HOME) {
			actionMenu(MenuFragment.ID_HOME);
		}
	}
	// for fragment tab
	@Override
	public void onAttachFragment(Fragment fragment) {
		super.onAttachFragment(fragment);
		
		if (fragment instanceof Tab1MDFragment) {
			Tab1MDFragment f = (Tab1MDFragment) fragment;
			// register listener;

		} else if (fragment instanceof Tab2EveryoneFragment) {
			Tab2EveryoneFragment f = (Tab2EveryoneFragment) fragment;

		} else if (fragment instanceof Tab3FriendsFragment) {

			Tab3FriendsFragment f = (Tab3FriendsFragment) fragment;
		}		

	}

	// for sliding menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	// ActionBar actionBar
	String keyword;
	Button submit;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	public void actionMenu(int menuId) {
		switch (menuId) {
		case MenuFragment.ID_MYHOUSE:
			if (myData == null) {
				Intent i = new Intent(MainActivity.this, LogInActivity.class);
				startActivity(i);
			} else {
				Fragment f = getSupportFragmentManager().findFragmentByTag(TAG_MYHOUSE);
				if (f == null) {
					while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
						getSupportFragmentManager().popBackStackImmediate();
					}

					fMyHouse = new MyHouseFragment();
					FragmentTransaction ftMyHouse = getSupportFragmentManager()
							.beginTransaction();
					ftMyHouse.replace(R.id.container, fMyHouse, TAG_MYHOUSE);
					ftMyHouse.addToBackStack(null);
					ftMyHouse.commit();
				}
			}
			//getSlidingMenu().showContent();
			mHandler.postDelayed(closeMenu, 500);
			break;
		case MenuFragment.ID_HOME:
			while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
				getSupportFragmentManager().popBackStackImmediate();
			}
			mHandler.postDelayed(closeMenu, 500);
			break;
		case MenuFragment.ID_SEARCH:
		{
			Fragment f = getSupportFragmentManager().findFragmentByTag(TAG_SEARCH);
			if (f == null) {

				while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
					getSupportFragmentManager().popBackStackImmediate();
				}

				fSearch = new CategorySearchFragment();
				FragmentTransaction ftSearch = getSupportFragmentManager()
						.beginTransaction();
				ftSearch.replace(R.id.container, fSearch, TAG_SEARCH);
				ftSearch.addToBackStack(null);
				ftSearch.commit();
			}

			mHandler.postDelayed(closeMenu, 500);
			break;
		}
		case MenuFragment.ID_RANK: {
			Fragment fRank = getSupportFragmentManager().findFragmentByTag(TAG_RANKING);
			if (fRank == null) {
				while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
					getSupportFragmentManager().popBackStackImmediate();
				}
				fRanking = new RankingFragment();
				FragmentTransaction ftRanking = getSupportFragmentManager()
						.beginTransaction();
				ftRanking.replace(R.id.container, fRanking, TAG_RANKING);
				ftRanking.addToBackStack(null);
				ftRanking.commit();
			}
			mHandler.postDelayed(closeMenu, 500);
			break;
		}
		case MenuFragment.ID_INVITE:
			if (myData == null) {
				Intent i = new Intent(MainActivity.this, LogInActivity.class);
				startActivity(i);
			} else {
				Fragment f = getSupportFragmentManager().findFragmentByTag(TAG_INVITE);
				if (f == null) {
					while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
						getSupportFragmentManager().popBackStackImmediate();
					}

					fInvite = new InviteFragment();
					FragmentTransaction ftInvite = getSupportFragmentManager()
							.beginTransaction();
					ftInvite.replace(R.id.container, fInvite, TAG_INVITE);
					ftInvite.addToBackStack(null);
					ftInvite.commit();
				}
			}
			mHandler.postDelayed(closeMenu, 500);
			break;
		case MenuFragment.ID_SETTING: {
			Fragment f = getSupportFragmentManager().findFragmentByTag(TAG_CONFIGINFORM);
			if (f == null) {
				while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
					getSupportFragmentManager().popBackStackImmediate();
				}
				fConfig = new ConfigInformFragment();
				FragmentTransaction ftConfig = getSupportFragmentManager()
						.beginTransaction();
				ftConfig.replace(R.id.container, fConfig, TAG_CONFIGINFORM);
				ftConfig.addToBackStack(null);
				ftConfig.commit();
			}
			mHandler.postDelayed(closeMenu, 500);
			break;
		}
		}

	}


	Runnable closeMenu = new Runnable() {

		@Override
		public void run() {
			getSlidingMenu().showContent();
		}
	};

}
