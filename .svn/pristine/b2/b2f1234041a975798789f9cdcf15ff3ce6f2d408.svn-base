package com.tacademy.penthouse;

import java.util.ArrayList;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tacademy.penthouse.browser.BrowserActivity;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.MultiUserRoomItemsResult;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.item.ItemInfoActivity;
import com.tacademy.penthouse.itemlike.ItemLikeShowListDialog;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.manager.UserManager;
import com.tacademy.penthouse.member.LogInActivity;
import com.tacademy.penthouse.room.UserRoomInfoActivity;
import com.tonicartos.widget.stickygridheaders.OnScrollDirectionChangeListener;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView.OnHeaderClickListener;

public class Tab1MDFragment extends Fragment implements OnItemClickListener, OnHeaderClickListener{

	public static final int REQEUST_NEW_ROOM = 0;
	
	MultiUserRoomItemsResult roomsResult;
	Dialog dialog;
	//ProgressDialog dialog;
	UserData myData = UserManager.getInstance().getuData();
	UserData uData;
	RoomData roomData;
	ArrayList<ItemData> items = new ArrayList<ItemData>();
	ItemData iData;
	int mdRoomNum;
	int scrollPosition = 15;
	boolean isLoading = false;
	int totalPage = 0;
	int pageCnt = 0;

	public interface OnScrollColorChangeListener{
		public void onScrollColorChanged(String color);
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	ItemLikeShowListDialog itemLikeDialog;
	GridView mdGridView;
	MDRoomAdapter mdAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		((ActionBarActivity)getActivity()).getSupportActionBar().show();
		itemLikeDialog = new ItemLikeShowListDialog();
		View v = inflater.inflate(R.layout.tab1_md_layout, container, false);
		mdGridView = (GridView)v.findViewById(R.id.md_grid);
		((MainFragment)getParentFragment()).showTabWidget(false);
		((StickyGridHeadersGridView)mdGridView).setPadding(0, 0, 0, 0);
		((StickyGridHeadersGridView)mdGridView).setOnScrollDirectionChangeListener(new OnScrollDirectionChangeListener() {

			@Override
			public void onScrollDirectionChanged(int direction) {
				switch(direction) {
				case OnScrollDirectionChangeListener.DIRECTION_BOTTOM_UP :
					((MainFragment)getParentFragment()).showTabWidget(false);
					break;
				case OnScrollDirectionChangeListener.DIRECTION_TOP_DOWN :
					((MainFragment)getParentFragment()).showTabWidget(true);
					break;
				case OnScrollDirectionChangeListener.DIRECTION_NONE :
					((MainFragment)getParentFragment()).showTabWidget(false);
					break;
				}
			}
		});
		mdGridView.setOnItemClickListener(this);
		((StickyGridHeadersGridView)mdGridView).setOnHeaderClickListener(this);
		((StickyGridHeadersGridView)mdGridView).setAreHeadersSticky(false);
		mdGridView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if(firstVisibleItem % 8 == 0){
					ItemData id = (ItemData)mdGridView.getItemAtPosition(firstVisibleItem + 2);
					if(id != null){
						String color = id.parent.room.room_color;
						String tabColor = colorChanger(color);
						((MainActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(color)));
						((MainFragment)getParentFragment()).tabWidget.setBackgroundDrawable(new ColorDrawable(Color.parseColor(tabColor)));

					}
				}else if(firstVisibleItem % 2 == 0){
					ItemData id = (ItemData)mdGridView.getItemAtPosition(firstVisibleItem);
					if(id != null){
						String color = id.parent.room.room_color;
						String tabColor = colorChanger(color);
						((MainActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(color)));
						((MainFragment)getParentFragment()).tabWidget.setBackgroundDrawable(new ColorDrawable(Color.parseColor(tabColor)));
					}
				}

				if(firstVisibleItem + visibleItemCount >= totalItemCount && visibleItemCount != 0){
					if(pageCnt < totalPage / 5 + 1 ){
						if (!isLoading) {
							isLoading = true;
							if(dialog != null){
								dialog.dismiss();
							}
						//	dialog = new ProgressDialog(getActivity(), R.style.ProgDialog);
							
							//dialog = new ProgressDialog(getActivity());
//							dialog.setTitle("로딩중");
//							dialog.setMessage("잠시만 기다려 주세요.");
						//	dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
						//	dialog.show();
							dialog = new Dialog(getActivity(), R.style.ProgDialog);
							dialog.setCancelable(true);
							dialog.addContentView(new ProgressBar(getActivity()), new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
							dialog.show();

							if(pageCnt < totalPage / 5 + 1 ){
								NetworkManager.getInstance().getMDRoomData(getActivity(),pageCnt,new NetworkManager.OnResultListener<MultiUserRoomItemsResult>() {
									@Override
									public void onSuccess(
											MultiUserRoomItemsResult result) {
										if (result.result != null) {
											pageCnt++;
											roomsResult = result;
											mdRoomNum = result.result.users.get(0).room.room_num;
											mdAdapter.put(result.result);		
											items = mdAdapter.set();
										}
										isLoading = false;
										mHandler.postDelayed(loadItem, 500);
									}

									@Override
									public void onFail(int code) {
										isLoading = false;
										Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
										dialog.dismiss();
									}
								});
							}else{
								isLoading = false;
								dialog.dismiss();
							}
						}
					}
				}
			}
		});

		mdAdapter = new MDRoomAdapter(getActivity());
		
		mdAdapter.setOnUserAdapterImgClickListener(new MDRoomAdapter.OnAdapterImgClickListener() {
			
			@Override
			public void onImgAdapterClick(View v, RoomData data) {
				roomData = data;
				if(data != null){
					if(roomData.source_url != null && !roomData.source_url.equals("")){
						Intent i = new Intent(getActivity(), BrowserActivity.class);
						i.setData(Uri.parse(roomData.source_url));
						startActivity(i);
					}
				}
			}
		});
	
		mdAdapter.setOnAdapterItemLikeClickListener(new MDRoomAdapter.OnAdapterItemLikeClickListener() {

			@Override
			public void onItemLikeClick(View v, ItemData data) {				
				iData = data;
				if(myData != null){

					//now unlike!!
					if(data.islike == 1){
						NetworkManager.getInstance().postPickItem(getActivity(), myData.user_num, data.room_num, data.item_num,
								new NetworkManager.OnResultListener<ResultData>() {

							@Override
							public void onSuccess(ResultData result) {
								mdAdapter.updateData(iData,0,iData.likeCnt--);
							}

							@Override
							public void onFail(int code) {
								Toast.makeText(getActivity(), "아이템 Pick하기 실패했습니다.", Toast.LENGTH_SHORT).show();
							}
						});
					}
					//now like!!
					else{
						Bundle b = new Bundle();
						b.putInt("iData", data.item_num);
						b.putParcelable("uData", myData);

						itemLikeDialog.setArguments(b);
						itemLikeDialog.show(getFragmentManager(), "dialog");

						itemLikeDialog.setOnRoomSelectedListener(new ItemLikeShowListDialog.OnRoomSelectedListener() {

							@Override
							public void onRoomSelected(boolean roomSelected, int roomNum) {
								if(roomSelected){
									NetworkManager.getInstance().postPickItem(getActivity(), myData.user_num, roomNum, iData.item_num, 
											new NetworkManager.OnResultListener<ResultData>() {

										@Override
										public void onSuccess(ResultData result) {
											mdAdapter.updateData(iData, 1,iData.likeCnt++);
										}

										@Override
										public void onFail(int code) {
											Toast.makeText(getActivity(), "아이템 Pic하기 실패했습니다.", Toast.LENGTH_SHORT).show();
										}
									});
								}
							}
						});

					}
				}else{
					startActivity(new Intent(getActivity(), LogInActivity.class));
				}
			}
		});
		mdGridView.setAdapter(mdAdapter);
	
		return v;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQEUST_NEW_ROOM && resultCode == Activity.RESULT_OK){

		}
	}

	private void initData(){
		myData = UserManager.getInstance().getuData();
		NetworkManager.getInstance().getMDRoomData(getActivity(), pageCnt, new NetworkManager.OnResultListener<MultiUserRoomItemsResult>() {

			@Override
			public void onSuccess(MultiUserRoomItemsResult result) {
				if(result.result != null){
					if(result.result.users.size() != 0){
						totalPage = result.totalPage;
						roomsResult = result;
						mdRoomNum = result.result.users.get(0).room.room_num;
						mdAdapter.replace(result.result);		
						items = mdAdapter.set();
						pageCnt++;
						//mdGridView.setSelection(scrollPosition);
						
					}else{
						initData();
					}

				}
			}
			@Override
			public void onFail(int code) {
				Toast.makeText(getActivity(), "fail to get tab1", Toast.LENGTH_SHORT).show();
			}			

		});
	}

	@Override
	public void onResume() {
		super.onResume();
		myData = UserManager.getInstance().getuData();
		mdAdapter.clear();
		totalPage = 0;
		pageCnt = 0;
		isLoading = false;
		initData();
		mdGridView.smoothScrollToPosition(scrollPosition,0);
		scrollPosition = 0;
		
		
		
	}
	
	@Override
	public void onHeaderClick(AdapterView<?> parent, View view, long id) {
		scrollPosition = (int)id;
		int headerNum = roomsResult.result.users.get((int)id % 5).room.room_num;
		Intent i = new Intent(getActivity(), UserRoomInfoActivity.class);
		i.putExtra("rData", headerNum);

		//id는 각(헤더와 아이템)의 positon임!
		startActivity(i);
	}

	@Override
	public void onItemClick(AdapterView<?> gridView, View view, int position, long id) {
		scrollPosition = position;
		if(items.get(position).item_num != 0){
			Intent i = new Intent(getActivity(), ItemInfoActivity.class);
			i.putExtra("iData", items.get(position).item_num);
			startActivity(i);		
		}else{
			Intent i = new Intent(getActivity(), UserRoomInfoActivity.class);
			i.putExtra("rData", items.get(position-1).room_num);
			startActivity(i);
		}
	}
	public String colorChanger(String barColor){
		if(barColor.equals("#98826F") ){
			return "#605246";
		}else if(barColor.equals("#B28850") ){
			return "#806139";
		}else if(barColor.equals("#D9B779") ){
			return "#A68C5D";
		}else if(barColor.equals("#E0673D") ){
			return "#AD4E2F";
		}else if(barColor.equals("#D99982") ){
			return "#A67563";
		}else if(barColor.equals("#D9B79A") ){
			return "#A68C76";
		}else if(barColor.equals("#F8B847") ){
			return "#C49139";
		}else if(barColor.equals("#BCAE86") ){
			return "#8A7F62";
		}else if(barColor.equals("#BFBBA4") ){
			return "#8C8979";
		}else if(barColor.equals("#D4CFA5") ){
			return "#A19D7D";
		}else if(barColor.equals("#B5BF6B") ){
			return "#858C4F";
		}else if(barColor.equals("#80A665") ){
			return "#597346";
		}else if(barColor.equals("#3F8C8C") ){
			return "#285959";
		}else if(barColor.equals("#7FC7BC") ){
			return "#5F948C";
		}else if(barColor.equals("#789EB4") ){
			return "#577282";
		}else if(barColor.equals("#8E94AC") ){
			return "#636878";
		}else if(barColor.equals("#40639A") ){
			return "#2B4266";
		}else if(barColor.equals("#595173") ){
			return "#312D40";
		}else if(barColor.equals("#4E5A68") ){
			return "#282E36";
		}else if(barColor.equals("#6A6A6A") ){
			return "#383838";
		}else if(barColor.equals("#988F87") ){
			return "#66605B";
		}
		return null;
	}

	Runnable loadItem = new Runnable() {

		@Override
		public void run() {
			dialog.dismiss();
		}
	};
	
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

		}
	};
}
