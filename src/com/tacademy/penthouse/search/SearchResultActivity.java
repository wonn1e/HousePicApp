package com.tacademy.penthouse.search;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.tacademy.penthouse.MainActivity;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.ItemsResult;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.house.HouseActivity;
import com.tacademy.penthouse.item.ItemInfoActivity;
import com.tacademy.penthouse.itemlike.ItemLikeShowListDialog;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.manager.UserManager;
import com.tacademy.penthouse.member.LogInActivity;
import com.tacademy.penthouse.room.ItemAdapter;

public class SearchResultActivity extends ActionBarActivity {
	public static final int REQUEST_NEW_ROOM_IN_SEARCH = 0;
	public static String SAVED_SORT_STRING = "최신 순";
	public static String SAVED_FILTER_STRING = "모두 보기";
	private PopupWindow sortPopup, filterPopup;
	UserData myData = UserManager.getInstance().getuData();
	ItemData iData;
	ArrayList<ItemData> items = new ArrayList<ItemData>();
	ItemLikeShowListDialog itemLikeDialog;
	TextView sort, filter;
	GridView item_gridview;
	SortAdapter sAdapter;
	FilterAdapter fAdapter;
	View sortView, filterView;
	ImageView sortClick, filterClick, sortSpinner, filterSpinner;
	ListView sortList, filterList;
	ItemAdapter iAdapter;
	String keyword = "";
	int cateNum = -1;
	int themeNum = 0;
	String option = "latest";
	ActionBar mActionBar;
	TextView gnbTitle;
	ImageView gnbHome;
	PopupWindow selectPopup;
	ImageView dimView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		dimView = (ImageView)findViewById(R.id.dim_view);
		dimView.setVisibility(View.GONE);
		dimView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
			}
		});
		mActionBar = getSupportActionBar();
		mActionBar.show();
		mActionBar.setCustomView(R.layout.gnb_menu_title);
		mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#40639a")));
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowHomeEnabled(false);
		gnbTitle = (TextView)findViewById(R.id.title);
		gnbTitle.setText("검색결과");
		gnbHome = (ImageView)findViewById(R.id.gnb_menu);
		gnbHome.setImageResource(R.drawable.ic_gnb_home);
		gnbHome.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(SearchResultActivity.this, MainActivity.class);
				i.putExtra(MainActivity.MENU_TYPE, 1);
				startActivity(i);
			}
		});

		itemLikeDialog = new ItemLikeShowListDialog();
		Intent i = getIntent();
		keyword = i.getStringExtra("keyword");
		cateNum = i.getIntExtra("cateNum", 0);

		iAdapter = new ItemAdapter(this);
		//검색어로 쿼리를 날려서 Item을 받아야한다!!!

		sort = (TextView)findViewById(R.id.sortSpinner);
		filter = (TextView)findViewById(R.id.filterSpinner);	
		sortSpinner = (ImageView)findViewById(R.id.sortSpinnerImg);
		filterSpinner = (ImageView)findViewById(R.id.filterSpinnerImg);
		sAdapter = new SortAdapter(this);
		sAdapter.add("최신 순");
		sAdapter.add("인기 순");
		sAdapter.add("저 가격 순");
		sAdapter.add("고 가격 순");
		fAdapter = new FilterAdapter(this);
		fAdapter.add("모두 보기");
		fAdapter.add("심플함");	//themeNum = 1
		fAdapter.add("화려함");	//themeNum = 2
		fAdapter.add("빈티지");	//themeNum = 3
		fAdapter.add("클래식");	//themeNum = 4
		sortView = getLayoutInflater().inflate(R.layout.sort_popup, null);
		sortList  = (ListView)sortView.findViewById(R.id.sortList);
		sortList.setAdapter(sAdapter);
		filterView = getLayoutInflater().inflate(R.layout.filter_popup, null);
		filterList = (ListView)filterView.findViewById(R.id.filterList);
		filterList.setAdapter(fAdapter);

		sortClick = (ImageView)findViewById(R.id.sortClickView);
		filterClick =(ImageView)findViewById(R.id.filterClickView);

		item_gridview = (GridView)findViewById(R.id.item_gridview);
		item_gridview.setAdapter(iAdapter);

		item_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(SearchResultActivity.this, ItemInfoActivity.class);
				i.putExtra("iData", items.get(position).item_num);
				startActivity(i);
			}
		});		

		sAdapter.setOnAdapterItemClickListener(new SortAdapter.OnAdapterItemClickListener() {

			@Override
			public void onAdapterItemClick(View v, String s) {
				sort.setText(s);
				SAVED_SORT_STRING = s;
				if(s.equals("최신 순")){
					option = "latest";
				}else if(s.equals("인기 순")){
					option = "popularity";
				}else if(s.equals("저 가격 순")){
					option = "lowprice";
				}else if(s.equals("고 가격 순")){
					option = "highprice";
				}

				if(sortPopup != null && sortPopup.isShowing()){
					iAdapter.clear();
					initData(cateNum, themeNum, option,  keyword);

					sortSpinner.setImageResource(R.drawable.ic_seach_dropdown_unselect);
					sortPopup.dismiss();
					dimView.setVisibility(View.GONE);
				}
			}
		});

		fAdapter.setOnAdapterItemClickListener(new FilterAdapter.OnAdapterItemClickListener() {
			@Override
			public void onAdapterItemClick(View v, String s) {
				filter.setText(s);
				SAVED_FILTER_STRING = s;

				if(s.equals("모두 보기")){
					themeNum = 0;
				}else if(s.equals("심플함")){
					themeNum = 1;
				}else if(s.equals("화려함")){
					themeNum = 2;
				}else if(s.equals("빈티지")){
					themeNum = 3;
				}else if(s.equals("클래식")){
					themeNum = 4;
				}

				if(filterPopup != null && filterPopup.isShowing()){
					iAdapter.clear();
					initData(cateNum, themeNum, option,  keyword);

					filterSpinner.setImageResource(R.drawable.ic_seach_dropdown_unselect);
					filterPopup.dismiss();
					dimView.setVisibility(View.GONE);
				}
			}
		});
		sortPopup = new PopupWindow(sortView, RelativeLayout.LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);

		filterPopup = new PopupWindow(filterView, RelativeLayout.LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		sortClick.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				sortPopup.setAnimationStyle(-1);
				sortPopup.setTouchable(true);
				sortPopup.setOutsideTouchable(false);
				sortPopup.setBackgroundDrawable(new BitmapDrawable());
				if (selectPopup != null && selectPopup != sortPopup) {
					selectPopup.dismiss();
				}
				sortPopup.showAsDropDown(sort, 0, 0);
				sortPopup.showAtLocation(sortView, Gravity.NO_GRAVITY, 0, 0);
				selectPopup = sortPopup;
				dimView.setVisibility(View.VISIBLE);
				/////////////////////////////////////////////////////////////////////////////////
				sortSpinner.setImageResource(R.drawable.ic_search_dropdown_seleted);
			}
		});

		filterClick.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				filterPopup.setAnimationStyle(-1);
				filterPopup.setTouchable(true);
				filterPopup.setOutsideTouchable(false);
				filterPopup.setBackgroundDrawable(new BitmapDrawable());
				if (selectPopup != null && selectPopup != filterPopup) {
					selectPopup.dismiss();
				}
				filterPopup.showAsDropDown(sort, 0, 0);
				filterPopup.showAtLocation(sortView, Gravity.NO_GRAVITY, 0, 0);
				filterPopup.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70000000")));
				selectPopup = filterPopup;
				dimView.setVisibility(View.VISIBLE);
				//////////////////////////////////////////////////////////////////////////////
				filterSpinner.setImageResource(R.drawable.ic_search_dropdown_seleted);
			}
		});

		iAdapter.setOnAdapterItemClickListener(new ItemAdapter.OnAdapterItemClickListener() {

			@Override
			public void onItemLikeClick(View v, ItemData data) {
				if(myData != null){
					iData = data;
					//now unlike!!
					if(data.islike == 1){
						NetworkManager.getInstance().postPickItem(SearchResultActivity.this, myData.user_num, data.room_num, data.item_num,
								new NetworkManager.OnResultListener<ResultData>() {

							@Override
							public void onSuccess(ResultData result) {
								iAdapter.updateData(iData, 0, iData.likeCnt--);
							}

							@Override
							public void onFail(int code) {
								Toast.makeText(SearchResultActivity.this, "아이템 Pick하기 실패했습니다.", Toast.LENGTH_SHORT).show();
							}
						});		
					}

					//now like!!
					else{
						Bundle b = new Bundle();
						b.putInt("iData", data.item_num);
						b.putParcelable("uData", myData);
						itemLikeDialog.setArguments(b);
						itemLikeDialog.show(getSupportFragmentManager(), "dialog");

						itemLikeDialog.setOnRoomSelectedListener(new ItemLikeShowListDialog.OnRoomSelectedListener() {

							@Override
							public void onRoomSelected(boolean roomSelected, int roomNum) {
								if(roomSelected){
									NetworkManager.getInstance().postPickItem(SearchResultActivity.this, myData.user_num, roomNum, iData.item_num, 
											new NetworkManager.OnResultListener<ResultData>() {

										@Override
										public void onSuccess(ResultData result) {
											iAdapter.updateData(iData, 1, iData.likeCnt++);
										}

										@Override
										public void onFail(int code) {
											Toast.makeText(SearchResultActivity.this, "아이템 Pick하기 실패했습니다.", Toast.LENGTH_SHORT).show();
										}
									});
								}
							}
						});
					}
				}else{
					startActivity(new Intent(SearchResultActivity.this, LogInActivity.class));
				}
			}
		});	 
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	private void initData(int cateNum, int themeNum, String option, String keyword){
		if(cateNum != 0 && keyword == null){
			NetworkManager.getInstance().getSearchFilterData(SearchResultActivity.this, cateNum, themeNum, option,
					new NetworkManager.OnResultListener<ItemsResult>() {

				@Override
				public void onSuccess(ItemsResult result) {
					if(result.result != null){
						items = result.result.items;
						for(int i = 0; i < items.size(); i++){
							iAdapter.add(items.get(i));
						}
					}else{
						Toast.makeText(SearchResultActivity.this, "검색결과가 없습니다.", Toast.LENGTH_SHORT).show();
					}
				}
				@Override
				public void onFail(int code) {
					Toast.makeText(SearchResultActivity.this, "필터링 실패", Toast.LENGTH_SHORT).show();
				}
			});
		} else if(keyword != null && cateNum == 0){
			NetworkManager.getInstance().getTextSearchResultData(SearchResultActivity.this, keyword, themeNum, option, new NetworkManager.OnResultListener<ItemsResult>() {

				@Override
				public void onSuccess(ItemsResult result) {
					if(result.result != null){
						items = result.result.items;
						for(int i = 0; i < items.size(); i++){
							iAdapter.add(items.get(i));
						}
					}else{
						Toast.makeText(SearchResultActivity.this, "검색결과가 없습니다.", Toast.LENGTH_SHORT).show();
					}
				}

				@Override
				public void onFail(int code) {
				}

			});
		}else{
			Toast.makeText(SearchResultActivity.this, "검색결과가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUEST_NEW_ROOM_IN_SEARCH && resultCode == Activity.RESULT_OK){
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		myData = UserManager.getInstance().getuData();
		iAdapter.clear();
		initData(cateNum, themeNum, option, keyword);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case android.R.id.home:
			startActivity(new Intent(SearchResultActivity.this, MainActivity.class));
		}
		return super.onOptionsItemSelected(item);
	}

}
