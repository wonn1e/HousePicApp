package com.tacademy.penthouse.room;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tacademy.penthouse.MainActivity;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UserRoomItemsResult;
import com.tacademy.penthouse.item.ItemInfoActivity;
import com.tacademy.penthouse.itemlike.CreateNewRoomActivity;
import com.tacademy.penthouse.itemlike.ItemLikeShowListDialog;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.manager.UserManager;

public class MyRoomInfoActivity extends ActionBarActivity {
	public static final int REQUEST_NEW_ROOM_IN_MYROOM = 0;
	public static final int REQUEST_EDIT_MYROOM = 3;

	ItemLikeShowListDialog itemLikeDialog;
	MyItemAdapter iAdapter;

	ImageView room_img, edit_room;
	TextView room_name;
	TextView room_public;
	StaggeredGridView myroom_item_gridview;
	View v;
	NoItemView deleteView;
	ImageLoader loader;
	DisplayImageOptions options, userImgOptions;
	UserData myData = UserManager.getInstance().getuData();
	ActionBar mActionBar;
	ArrayList<ItemData> items = new ArrayList<ItemData>();
	ArrayList<RoomData> rooms = new ArrayList<RoomData>();
	ItemData itemData;
	RoomData rData;
	int roomNum = 0;
	int userNum = 0;
	int itemNum;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_room_info);

		mActionBar = getSupportActionBar();
		mActionBar.show();
		mActionBar.setCustomView(R.layout.gnb_menu_title);
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowHomeEnabled(false);

		ImageView gnb_menu = (ImageView)findViewById(R.id.gnb_menu);
		gnb_menu.setImageResource(R.drawable.ic_gnb_home);
		gnb_menu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MyRoomInfoActivity.this, MainActivity.class);
				i.putExtra(MainActivity.MENU_TYPE, 1);
				startActivity(i);			
			}
		});

		Intent i = getIntent();
		roomNum = i.getIntExtra("rData", roomNum);
		userNum = UserManager.getInstance().getUserNum();
		itemLikeDialog = new ItemLikeShowListDialog();

		loader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.placeholder)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.build();
		userImgOptions = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.btn_color_normal_16)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.build();

		v = getLayoutInflater().inflate(R.layout.header_view_myroom_layout, null);
		deleteView = (NoItemView)v.findViewById(R.id.noItemView1);
		room_img = (ImageView)v.findViewById(R.id.room_img);
		room_name = (TextView)v.findViewById(R.id.room_name);
		room_public = (TextView)v.findViewById(R.id.room_public); 
		edit_room = (ImageView)v.findViewById(R.id.edit_room);
		edit_room.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MyRoomInfoActivity.this, CreateNewRoomActivity.class);
				i.putExtra("rData", rData);
				i.putExtra("from", 1);
				startActivityForResult(i, REQUEST_EDIT_MYROOM);
			}
		});
		myroom_item_gridview = (StaggeredGridView)findViewById(R.id.gridView_myroom);
		myroom_item_gridview.addHeaderView(v);

		//Adapter
		iAdapter = new MyItemAdapter(this);
		myroom_item_gridview.setAdapter(iAdapter);

		iAdapter.setOnAdapterItemClickListener(new MyItemAdapter.OnAdapterItemClickListener() {

			@Override
			public void onItemLikeClick(View v, ItemData data) {
				itemData = data;
				//now unlike!!
				if(data.islike == 1){
					NetworkManager.getInstance().postPickItem(MyRoomInfoActivity.this, myData.user_num, roomNum, data.item_num,
							new NetworkManager.OnResultListener<ResultData>() {

						@Override
						public void onSuccess(ResultData result) {
							initData();

						}

						@Override
						public void onFail(int code) {
							Toast.makeText(MyRoomInfoActivity.this, "아이템 Pic하기 실패했습니다.", Toast.LENGTH_SHORT).show();
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
								NetworkManager.getInstance().postPickItem(MyRoomInfoActivity.this, myData.user_num, roomNum, itemData.item_num, 
										new NetworkManager.OnResultListener<ResultData>() {

									@Override
									public void onSuccess(ResultData result) {
										initData();
									}

									@Override
									public void onFail(int code) {
										Toast.makeText(MyRoomInfoActivity.this, "아이템 Pic하기 실패했습니다.", Toast.LENGTH_SHORT).show();
									}
								});
							}
						}
					});
				}

			}

			@Override
			public void onItemMoveClick(View v, final ItemData data) {
				itemNum = data.item_num;
				Bundle b = new Bundle();
				b.putInt("iData", data.item_num);
				b.putInt("rData", roomNum);
				b.putParcelable("uData", myData);
				itemLikeDialog.setArguments(b);
				itemLikeDialog.show(getSupportFragmentManager(), "dialog");

				itemLikeDialog.setOnRoomSelectedListener(new ItemLikeShowListDialog.OnRoomSelectedListener() {

					@Override
					public void onRoomSelected(boolean roomSelected, int newRoomNum) {

						NetworkManager.getInstance().postMoveItem(MyRoomInfoActivity.this, userNum, roomNum, newRoomNum, itemNum,
								new NetworkManager.OnResultListener<ResultData>() {

							@Override
							public void onSuccess(ResultData result) {
								Toast.makeText(MyRoomInfoActivity.this, "아이템이 이동되었습니다.", Toast.LENGTH_SHORT).show();
								initData();
							}

							@Override
							public void onFail(int code) {
								Toast.makeText(MyRoomInfoActivity.this, "fail to put the item into new room", Toast.LENGTH_SHORT).show();
							}
						});
					}
				});
			}
		});

		myroom_item_gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Item의 상세정보 Activity로 이동!
				if(position != 0){
					Intent i = new Intent(MyRoomInfoActivity.this, ItemInfoActivity.class);
					ItemData d = (ItemData)myroom_item_gridview.getItemAtPosition(position);
					i.putExtra("iData", d.item_num);
					startActivityForResult(i, 0);
				}
			}
		});
		initData();

	}

	private void initData(){
		iAdapter.clear();

		NetworkManager.getInstance().getRoomInfo(this, userNum, roomNum, new NetworkManager.OnResultListener<UserRoomItemsResult>() {

			@Override
			public void onSuccess(UserRoomItemsResult result) {
				iAdapter.clear();
				deleteView.setVisibility(View.VISIBLE);
				if(result.result != null){
					rData = result.result.room;
					mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(rData.room_color)));
					items = result.result.items;
					room_name.setText(rData.room_name);
					if(rData.room_ispublic == 1){
						room_public.setText("공개");
					}else{
						room_public.setText("비공개");
					}
					loader.displayImage("http://54.178.158.103/images/"+ rData.room_img_url, room_img ,options);

					if(items.size() != 0){

						deleteView.setVisibility(View.GONE);
						myroom_item_gridview.requestLayout();
						iAdapter.replace(items);
					}
				}
			}

			@Override
			public void onFail(int code) {
				Toast.makeText(MyRoomInfoActivity.this, "fail to get data in UserRoomInfo", Toast.LENGTH_SHORT).show();
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUEST_NEW_ROOM_IN_MYROOM && resultCode == Activity.RESULT_OK){

		}else if(requestCode == REQUEST_EDIT_MYROOM && resultCode == Activity.RESULT_OK){
			initData();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case android.R.id.home:
			startActivity(new Intent(MyRoomInfoActivity.this, MainActivity.class));
		}
		return super.onOptionsItemSelected(item);
	}

	public void getNewData(){
		NetworkManager.getInstance().getRoomInfo(this, userNum, roomNum, new NetworkManager.OnResultListener<UserRoomItemsResult>() {

			@Override
			public void onSuccess(UserRoomItemsResult result) {
				if(result.result != null){
					items = result.result.items;
					if(items.size() != 0){
						iAdapter.replace(items);
					}
				}else{
					deleteView.setVisibility(View.VISIBLE);
					iAdapter.clear();
				}
			}

			@Override
			public void onFail(int code) {
				Toast.makeText(MyRoomInfoActivity.this, "fail to get data in UserRoomInfo", Toast.LENGTH_SHORT).show();
			}
		});
	}
	@Override
	protected void onResume() {
		super.onResume();
		initData();
	}
}
