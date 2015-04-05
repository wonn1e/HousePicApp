package com.tacademy.penthouse;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.MultiUserRoomItemsResult;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.house.HouseActivity;
import com.tacademy.penthouse.house.MyHouseFragment;
import com.tacademy.penthouse.item.ItemInfoActivity;
import com.tacademy.penthouse.itemlike.ItemLikeShowListDialog;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.manager.UserManager;
import com.tacademy.penthouse.member.LogInActivity;
import com.tacademy.penthouse.ranking.RankingFragment;
import com.tacademy.penthouse.room.MyRoomInfoActivity;
import com.tacademy.penthouse.room.UserRoomInfoActivity;
import com.tonicartos.widget.stickygridheaders.OnScrollDirectionChangeListener;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView.OnHeaderClickListener;

public class Tab2EveryoneFragment extends Fragment implements
OnItemClickListener, OnHeaderClickListener {

	public static final int REQEUST_NEW_ROOM = 0;
	MultiUserRoomItemsResult userRoomsResult;
	UserData uData;
	UserData myData = UserManager.getInstance().getuData();
	RoomData roomData;
	ArrayList<ItemData> items = new ArrayList<ItemData>();
	ItemData iData;
	ItemLikeShowListDialog itemLikeDialog;
	GridView mdGridView;
	UsersRoomAdapter everyoneAdapter;
	Dialog dialog;
	int totalPage = 0;
	int pageCnt = 0;
	boolean isLoading = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		itemLikeDialog = new ItemLikeShowListDialog();
		View v = inflater.inflate(R.layout.tab2_everyone_layout, container,
				false);
		mdGridView = (GridView) v.findViewById(R.id.every_grid);
		mdGridView.setOnItemClickListener(this);
		((StickyGridHeadersGridView) mdGridView)
		.setOnScrollDirectionChangeListener(new OnScrollDirectionChangeListener() {

			@Override
			public void onScrollDirectionChanged(int direction) {
				switch (direction) {
				case OnScrollDirectionChangeListener.DIRECTION_BOTTOM_UP:
					((MainFragment) getParentFragment())
					.showTabWidget(false);
					break;
				case OnScrollDirectionChangeListener.DIRECTION_TOP_DOWN:
					((MainFragment) getParentFragment())
					.showTabWidget(true);
					break;
				case OnScrollDirectionChangeListener.DIRECTION_NONE:
					((MainFragment) getParentFragment())
					.showTabWidget(false);
					break;
				}
			}
		});
		((StickyGridHeadersGridView) mdGridView).setOnHeaderClickListener(this);
		((StickyGridHeadersGridView) mdGridView).setAreHeadersSticky(false);
		mdGridView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if (firstVisibleItem % 8 == 0) {
					ItemData id = (ItemData) mdGridView
							.getItemAtPosition(firstVisibleItem + 2);
					if (id != null) {
						String color = id.parent.room.room_color;
						String tabColor = colorChanger(color);
						((MainActivity) getActivity()).getSupportActionBar()
						.setBackgroundDrawable(
								new ColorDrawable(Color
										.parseColor(color)));
						((MainFragment) getParentFragment()).tabWidget
						.setBackgroundDrawable(new ColorDrawable(Color.parseColor(tabColor)));
					}
				} else if (firstVisibleItem % 2 == 0) {
					ItemData id = (ItemData) mdGridView
							.getItemAtPosition(firstVisibleItem);
					if (id != null) {
						String color = id.parent.room.room_color;
						String tabColor = colorChanger(color);
						((MainActivity) getActivity()).getSupportActionBar()
						.setBackgroundDrawable(
								new ColorDrawable(Color
										.parseColor(color)));
						((MainFragment) getParentFragment()).tabWidget.setBackgroundDrawable(new ColorDrawable(Color.parseColor(tabColor)));
					}
				}
				if (firstVisibleItem + visibleItemCount == totalItemCount && visibleItemCount != 0) {
					if (pageCnt < totalPage / 5 + 1) {
						if (!isLoading) {
							isLoading = true;
							if(dialog != null){
								dialog.dismiss();
							}
							

							dialog = new Dialog(getActivity(), R.style.ProgDialog);
							dialog.setCancelable(true);
							dialog.addContentView(new ProgressBar(getActivity()), new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
							dialog.show();
							dialog.show();
							if (pageCnt < totalPage / 5 + 1) {
								NetworkManager.getInstance().getEveryoneRoomData(getActivity(),pageCnt,new NetworkManager.OnResultListener<MultiUserRoomItemsResult>() {

									@Override
									public void onSuccess(
											MultiUserRoomItemsResult result) {
										if (result.result != null) {
											userRoomsResult = result;
											everyoneAdapter.put(result.result);
											items = everyoneAdapter.set();
											pageCnt++;
										}
										isLoading = false;
										mHandler.postDelayed(loadItem, 500);
									}

									@Override
									public void onFail(int code) {
										isLoading = false;
										mHandler.postDelayed(loadItem, 500);
									}
								});
							} else {
								isLoading = false;
								mHandler.postDelayed(loadItem, 500);
							}
						}
					}
				}
			}
		});

		everyoneAdapter = new UsersRoomAdapter(getActivity());
		everyoneAdapter
		.setOnUserAdapterImgClickListener(new UsersRoomAdapter.OnUserAdapterImgClickListener() {

			@Override
			public void onUserImgAdapterClick(View v, UserData data) {
				if (myData != null) {
					if (data.user_num == myData.user_num) {
						Bundle b = new Bundle();
						b.putInt(RankingFragment.SHOW_TYPE, 1);
						MyHouseFragment fMyHouse = new MyHouseFragment();
						fMyHouse.setArguments(b);
						FragmentTransaction ftMain = getParentFragment()
								.getFragmentManager()
								.beginTransaction();
						ftMain.replace(R.id.container, fMyHouse);
						ftMain.addToBackStack(null);
						ftMain.commit();
					} else {
						Intent i = new Intent(getActivity(),
								HouseActivity.class);
						i.putExtra("uData", data.user_num);
						startActivity(i);
					}
				} else {
					Intent i = new Intent(getActivity(),
							HouseActivity.class);
					i.putExtra("uData", data.user_num);
					startActivity(i);
				}
			}
		});

		everyoneAdapter
		.setOnAdapterItemLikeClickListener(new UsersRoomAdapter.OnAdapterItemLikeClickListener() {

			@Override
			public void OnItemLikeClickListener(View v, ItemData data) {
				if (myData != null) {
					iData = data;
					// now unlike!!
					if (data.islike == 1) {
						NetworkManager.getInstance().postPickItem(getActivity(),myData.user_num, data.room_num, data.item_num,
								new NetworkManager.OnResultListener<ResultData>() {

							@Override
							public void onSuccess(ResultData result) {
								everyoneAdapter.updateData(iData, 0, iData.likeCnt--);
							}

							@Override
							public void onFail(int code) {
								Toast.makeText(
										getActivity(),
										"아이템 Pic하기 실패했습니다.",
										Toast.LENGTH_SHORT)
										.show();
							}
						});

					}
					// now like!!
					else {

						Bundle b = new Bundle();
						b.putInt("iData", data.item_num);
						b.putParcelable("uData", myData);

						itemLikeDialog.setArguments(b);
						itemLikeDialog.show(getFragmentManager(), "dialog");

						itemLikeDialog.setOnRoomSelectedListener(new ItemLikeShowListDialog.OnRoomSelectedListener() {

							@Override
							public void onRoomSelected(boolean roomSelected,int roomNum) {
								if (roomSelected) {
									NetworkManager.getInstance().postPickItem(getActivity(), myData.user_num, roomNum, iData.item_num,
											new NetworkManager.OnResultListener<ResultData>() {

										@Override
										public void onSuccess(ResultData result) {
											everyoneAdapter.updateData(iData,1,iData.likeCnt++);
										}

										@Override
										public void onFail(int code) {
											Toast.makeText(getActivity(),"아이템 Pic하기 실패했습니다.",Toast.LENGTH_SHORT).show();
										}
									});
								}
							}
						});

					}
				} else {
					startActivity(new Intent(getActivity(), LogInActivity.class));
				}
			}
		});

		mdGridView.setAdapter(everyoneAdapter);
		return v;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQEUST_NEW_ROOM && resultCode == Activity.RESULT_OK) {

		}
	}

	private void initData() {
		myData = UserManager.getInstance().getuData();
		NetworkManager.getInstance().getEveryoneRoomData(getActivity(),pageCnt,new NetworkManager.OnResultListener<MultiUserRoomItemsResult>() {

			@Override
			public void onSuccess(
					MultiUserRoomItemsResult result) {
				if(result.result != null){
					if(result.result.users.size() != 0){
						totalPage = result.totalPage;
						userRoomsResult = result;
						everyoneAdapter.replace(result.result);
						items = everyoneAdapter.set();
						pageCnt++;
					}
				}else{
					initData();
				}

			}

			@Override
			public void onFail(int code) {
				Toast.makeText(getActivity(),"fail to get tab2", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		myData = UserManager.getInstance().getuData();
		everyoneAdapter.clear();
		totalPage = 0;
		pageCnt = 0;
		isLoading = false;
		initData();
	}

	@Override
	public void onHeaderClick(AdapterView<?> parent, View view, long id) {
		if (myData != null) {
			if (userRoomsResult.result.users.get((int) id % 5).user.user_num == myData.user_num) {
				Intent i = new Intent(getActivity(), MyRoomInfoActivity.class);
				int headerNum = userRoomsResult.result.users.get((int)id % 5).room.room_num;
				i.putExtra("rData", headerNum);
				startActivity(i);
			} else {
				Intent i = new Intent(getActivity(), UserRoomInfoActivity.class);
				int headerNum = userRoomsResult.result.users.get((int)id % 5).room.room_num;
				i.putExtra("rData", headerNum);
				startActivity(i);
			}
		} else {
			Intent i = new Intent(getActivity(), UserRoomInfoActivity.class);
			int headerNum = userRoomsResult.result.users.get((int)id % 5).room.room_num;
			i.putExtra("rData", headerNum);
			startActivity(i);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> gridView, View view, int position,
			long id) {
		//
		// if(getActivity() != null){
		// Toast.makeText(getActivity(), "positon" + position + "RoomNum" +
		// items.get(position).room_num, Toast.LENGTH_SHORT).show();
		// }
		if (items.get(position).item_num != 0) {
			Intent i = new Intent(getActivity(), ItemInfoActivity.class);
			i.putExtra("iData", items.get(position).item_num);
			startActivity(i);
		} else {
			if (myData != null) {
				if (items.get(position-1).parent.user.user_num == myData.user_num) {
					Intent i = new Intent(getActivity(), MyRoomInfoActivity.class);
					i.putExtra("rData",
							items.get(position - 1).room_num);
					startActivity(i);
				} else {
					Intent i = new Intent(getActivity(), UserRoomInfoActivity.class);
					i.putExtra(
							"rData",
							items.get(position - 1).room_num);
					startActivity(i);
				}
			} else {
				Intent i = new Intent(getActivity(), UserRoomInfoActivity.class);
				i.putExtra("rData",
						items.get(position - 1).room_num);
				startActivity(i);
			}
			//			Intent i = new Intent(getActivity(), UserRoomInfoActivity.class);
			//			i.putExtra("rData", items.get(position - 1).room_num);
			//			startActivity(i);
		}
	}

	public String colorChanger(String barColor) {
		if (barColor.equals("#98826F")) {
			return "#605246";
		} else if (barColor.equals("#B28850")) {
			return "#806139";
		} else if (barColor.equals("#D9B779")) {
			return "#A68C5D";
		} else if (barColor.equals("#E0673D")) {
			return "#AD4E2F";
		} else if (barColor.equals("#D99982")) {
			return "#A67563";
		} else if (barColor.equals("#D9B79A")) {
			return "#A68C76";
		} else if (barColor.equals("#F8B847")) {
			return "#C49139";
		} else if (barColor.equals("#BCAE86")) {
			return "#8A7F62";
		} else if (barColor.equals("#BFBBA4")) {
			return "#8C8979";
		} else if (barColor.equals("#D4CFA5")) {
			return "#A19D7D";
		} else if (barColor.equals("#B5BF6B")) {
			return "#858C4F";
		} else if (barColor.equals("#80A665")) {
			return "#597346";
		} else if (barColor.equals("#3F8C8C")) {
			return "#285959";
		} else if (barColor.equals("#7FC7BC")) {
			return "#5F948C";
		} else if (barColor.equals("#789EB4")) {
			return "#577282";
		} else if (barColor.equals("#8E94AC")) {
			return "#636878";
		} else if (barColor.equals("#40639A")) {
			return "#2B4266";
		} else if (barColor.equals("#595173")) {
			return "#312D40";
		} else if (barColor.equals("#4E5A68")) {
			return "#282E36";
		} else if (barColor.equals("#6A6A6A")) {
			return "#383838";
		} else if (barColor.equals("#988F87")) {
			return "#66605B";
		}
		return null;
	}
	Runnable loadItem = new Runnable() {

		@Override
		public void run() {
			//			dialog = new ProgressDialog(getActivity());
			//			dialog.setTitle("로딩중");
			//			dialog.setMessage("잠시만 기다려 주세요.");
			//			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			//			dialog.show();
			dialog.dismiss();
		}
	};

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

		}
	};
}
