package com.tacademy.penthouse.item;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.meetme.android.horizontallistview.HorizontalListView;
import com.tacademy.penthouse.MainActivity;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.browser.BrowserActivity;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.ItemItemsResult;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.itemlike.ItemLikeShowListDialog;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.manager.UserManager;
import com.tacademy.penthouse.member.LogInActivity;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class ItemInfoActivity extends FragmentActivity {

	public static final int REQUEST_NEW_ROOM_IN_ITEMINFO = 0;

	ItemData iData;
	ArrayList<ItemData> itemsData = new ArrayList<ItemData>();
	UserData myData = UserManager.getInstance().getuData();
	ViewPager mPager;
	HorizontalListView hlv_s_item;
	ItemFragmentAdapter mAdapter;
	ItemRecommandAdapter iAdapter;
	CirclePageIndicator mIndicator;
	ItemLikeShowListDialog itemLikeDialog;

	TextView item_name, item_price, item_size, item_brand, pickText;
	ImageView show_item_like, home, pickClick;

	int i_num;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_info);

		home = (ImageView)findViewById(R.id.home_iteminfo);
		home.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ItemInfoActivity.this, MainActivity.class);
				i.putExtra(MainActivity.MENU_TYPE, 1);
				startActivity(i);
			}
		});
		itemLikeDialog = new ItemLikeShowListDialog();
		Button item_share_btn;
		Button item_buy_btn;
		iData = new ItemData();
		Intent i = getIntent();
		i_num = i.getIntExtra("iData", 0);

		//data that is passed to other Fragments

		pickText = (TextView)findViewById(R.id.pickText);
		mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
		item_size = (TextView)findViewById(R.id.item_size);
		item_name = (TextView) findViewById(R.id.item_name);
		item_price = (TextView)findViewById(R.id.item_price);
		item_brand = (TextView)findViewById(R.id.item_brand);
		mPager = (ViewPager)findViewById(R.id.pager);
		pickClick = (ImageView)findViewById(R.id.pickClick);
		hlv_s_item = (HorizontalListView) findViewById(R.id.horizontalListView2);
		iAdapter = new ItemRecommandAdapter(this);
		
		
	

		show_item_like = (ImageView)findViewById(R.id.itemLike_itemInfo);
		pickText.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(myData != null){

					//now unlike!!
					if(iData.islike == 1){
						NetworkManager.getInstance().postPickItem(ItemInfoActivity.this, myData.user_num, iData.room_num, iData.item_num,
								new NetworkManager.OnResultListener<ResultData>() {

							@Override
							public void onSuccess(ResultData result) {
								initData();
							}

							@Override
							public void onFail(int code) {
								Toast.makeText(ItemInfoActivity.this, "아이템 Pic하기 실패했습니다.", Toast.LENGTH_SHORT).show();
							}
						});
					}
					//now like!!
					else{

						//idata update! (ex. likeCnt, etc)
						itemLikeDialog = new ItemLikeShowListDialog ();

						Bundle b = new Bundle();
						b.putInt("iData", iData.item_num);
						b.putParcelable("uData", myData);

						itemLikeDialog.setArguments(b);
						itemLikeDialog.show(getSupportFragmentManager(), "dialog");

						itemLikeDialog.setOnRoomSelectedListener(new ItemLikeShowListDialog.OnRoomSelectedListener() {

							@Override
							public void onRoomSelected(boolean roomSelected,
									int roomNum) {
								if(roomSelected){
									NetworkManager.getInstance().postPickItem(ItemInfoActivity.this, myData.user_num, roomNum, iData.item_num, 
											new NetworkManager.OnResultListener<ResultData>() {

										@Override
										public void onSuccess(ResultData result) {
											initData();
										}

										@Override
										public void onFail(int code) {
											Toast.makeText(ItemInfoActivity.this, "아이템 Pic하기 실패했습니다.", Toast.LENGTH_SHORT).show();
										}
									});
								}	
							}
						});
					}

				}
				else{
					Intent i = new Intent(ItemInfoActivity.this, LogInActivity.class);
					startActivity(i);
				}
			}
		});

		item_share_btn = (Button)findViewById(R.id.item_share_btn);
		item_share_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ItemShareDialog d = new ItemShareDialog();

				Bundle b = new Bundle();
				b.putParcelable("iData", iData);
				d.setArguments(b);
				d.show(getSupportFragmentManager(), "item_share");

			}
		});

		item_buy_btn = (Button)findViewById(R.id.item_buy_btn);
		item_buy_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(iData.link != null && !iData.link.equals("")){
					Intent i = new Intent(ItemInfoActivity.this, BrowserActivity.class);
					i.setData(Uri.parse(iData.link));
					startActivity(i);
				}
			}
		});
		hlv_s_item.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				int iNum = iAdapter.getNum(position);
				if(iNum != 0){
					iAdapter.clear();
					initActivity(iNum);
				}
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		myData = UserManager.getInstance().getuData();
		initData();
	}

	private void initData(){

		NetworkManager.getInstance().getItemInfoResultData(this, i_num, new NetworkManager.OnResultListener<ItemItemsResult>() {

			@Override
			public void onSuccess(ItemItemsResult result) {
				iData = result.result.item;
				itemsData = result.result.items;
				show_item_like.setImageResource(R.drawable.ic_info_pick_off);
				if(iData.islike == 1)
					show_item_like.setImageResource(R.drawable.ic_info_pick_on);

				item_name.setText(iData.item_name);
				item_brand.setText("브랜드: " + iData.brand);
				item_size.setText("사이즈: " + iData.item_size);
				DecimalFormat df = new DecimalFormat("#,##0");
				item_price.setText(""+ df.format(iData.price) + "원");

				mAdapter = new ItemFragmentAdapter(getSupportFragmentManager(), iData);
				mPager.setAdapter(mAdapter);
				mIndicator.setViewPager(mPager);
				mIndicator.setVisibility(View.GONE);
				if(mAdapter.getCount() > 1){
					mIndicator.setVisibility(View.VISIBLE);	
				}
				
				iAdapter.add("http://54.178.158.103/recommand.png", 0);
				for(int i = 0; i < itemsData.size(); i++){
					iAdapter.add(itemsData.get(i).item_img_url[0], itemsData.get(i).item_num);
				}
				hlv_s_item.setAdapter(iAdapter);
			}

			@Override
			public void onFail(int code) {
				Toast.makeText(ItemInfoActivity.this, "fail in ItemInfo", Toast.LENGTH_SHORT).show();
			}
		});


	}

	private void initActivity(int si_num){

		NetworkManager.getInstance().getItemInfoResultData(this, si_num, new NetworkManager.OnResultListener<ItemItemsResult>() {

			@Override
			public void onSuccess(ItemItemsResult result) {
				mAdapter.clear();

				iData = result.result.item;
				show_item_like.setImageResource(R.drawable.ic_info_pick_off);
				if(iData.islike == 1){
					show_item_like.setImageResource(R.drawable.ic_info_pick_on);
				} 

				itemsData = result.result.items;
				item_name.setText(iData.item_name);
				item_brand.setText(iData.brand);
				item_size.setText(iData.item_size);
				DecimalFormat df = new DecimalFormat("#,##0");
				item_price.setText(""+ df.format(iData.price) + "원");
				mAdapter.setItemData(iData);
				mPager.setAdapter(mAdapter);
				mIndicator.setViewPager(mPager);
				mIndicator.setVisibility(View.GONE);
				if(mAdapter.getCount() > 1){
					mIndicator.setVisibility(View.VISIBLE);	
				}	
				iAdapter = new ItemRecommandAdapter(ItemInfoActivity.this);
				iAdapter.add("http://54.178.158.103/recommand.png", 0);
				for(int i = 0; i < itemsData.size(); i++){
					iAdapter.add(itemsData.get(i).item_img_url[0], itemsData.get(i).item_num);
				}
				hlv_s_item.setAdapter(iAdapter);
			}

			@Override
			public void onFail(int code) {
				Toast.makeText(ItemInfoActivity.this, "ItemInfoActivity fail to connect", Toast.LENGTH_SHORT).show();
			}
		});


	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUEST_NEW_ROOM_IN_ITEMINFO && resultCode == Activity.RESULT_OK){
		}
	}
}