package com.tacademy.penthouse.ranking;

import java.util.ArrayList;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tacademy.penthouse.MainActivity;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.ItemsResult;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UserRoomsResult;
import com.tacademy.penthouse.entity.UsersResult;
import com.tacademy.penthouse.house.HouseActivity;
import com.tacademy.penthouse.house.MyHouseFragment;
import com.tacademy.penthouse.item.ItemInfoActivity;
import com.tacademy.penthouse.itemlike.ItemLikeShowListDialog;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.manager.UserManager;
import com.tacademy.penthouse.member.LogInActivity;
import com.tacademy.penthouse.ranking.RankUserAdapter.OnAdapterFollowClickListener;

public class RankingFragment extends Fragment {
	public static int SEND_GCM = 0;
	public static int LIST_TYPE_FLAG = 0;
	public static final int REQUEST_NEW_ROOM_IN_RANKING = 0;
	public static final String SHOW_TYPE = "ranking";
	public static final boolean IS_FOLLOW = false;
	ListView rankingList;
	RankUserAdapter uAdapter;
	RankItemAdapter iAdapter;
	ImageView rank_bg;
	TextView rank_title, rank_title_name;
	ItemLikeShowListDialog itemLikeDialog;
	UserData myData = UserManager.getInstance().getuData();
	UserData uData = new UserData();
	ArrayList<UserData> users = new ArrayList<UserData>();
	ArrayList<Integer> followUsers = new ArrayList<Integer>();
	UsersResult usersResult;
	ItemsResult itemsResult;
	ItemData iData;
	TextView tab1, tab2;
	ImageView rank_home;
	Dialog dialog;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.ranking_layout, container, false);
		itemLikeDialog = new ItemLikeShowListDialog();
		rankingList = (ListView)v.findViewById(R.id.listView_rank);
		rank_bg = (ImageView)v.findViewById(R.id.rank_bg);
		rank_title = (TextView)v.findViewById(R.id.rank_title);
		rank_title_name = (TextView)v.findViewById(R.id.rank_title_name);
		rank_home = (ImageView)v.findViewById(R.id.menu_ranking);
		uAdapter = new RankUserAdapter(getActivity());
		iAdapter = new RankItemAdapter(getActivity());
		LIST_TYPE_FLAG = 0;
		rank_home.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity)getActivity()).getSlidingMenu().toggle();
			}
		});

		((ActionBarActivity)getActivity()).getSupportActionBar().hide();

		tab1= (TextView)v.findViewById(R.id.rankNeighborTab);
		tab2 = (TextView)v.findViewById(R.id.rankItemTab);

		tab1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				LIST_TYPE_FLAG = 0;
				rankingList.setAdapter(uAdapter);
				getNewFollowList();
			}
		});

		tab2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LIST_TYPE_FLAG = 1;
				rankingList.setAdapter(iAdapter);
				initItemData();
			}
		});

		rankingList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				if(LIST_TYPE_FLAG == 0){		//user
					UserData d = (UserData)rankingList.getItemAtPosition(position);
					if(myData == null){
						Intent i = new Intent(getActivity(), HouseActivity.class);
						i.putExtra("uData", d.user_num);
						startActivity(i);
					}else if(d.user_num == myData.user_num){
						Bundle b = new Bundle();
						b.putInt(SHOW_TYPE, 1);
						MyHouseFragment fMyHouse = new MyHouseFragment();
						fMyHouse.setArguments(b);
						FragmentTransaction ftMain = getFragmentManager()
								.beginTransaction();
						ftMain.replace(R.id.container,fMyHouse);
						ftMain.addToBackStack(null);
						ftMain.commit();
					}else{
						Intent i = new Intent(getActivity(), HouseActivity.class);

						i.putExtra("uData", d.user_num);
						startActivity(i);
					}


				}else if(LIST_TYPE_FLAG == 1){	//item
					Intent i = new Intent(getActivity(), ItemInfoActivity.class);

					ItemData d = (ItemData)rankingList.getItemAtPosition(position);
					i.putExtra("iData", d.item_num);

					startActivity(i);

				}
			}
		});

		iAdapter.setOnAdapterPopularItemLikeListener(new RankItemAdapter.OnAdapterPopularItemLikeListener() {

			@Override
			public void onPopularItemLike(View v, ItemData data) {
				if(myData == null){
					Intent i = new Intent(getActivity(), LogInActivity.class);
					startActivity(i);
				}else{
					iData = data;
					//now unlike!!
					if(data.islike == 1){
						NetworkManager.getInstance().postPickItem(getActivity(), myData.user_num, data.room_num, data.item_num,
								new NetworkManager.OnResultListener<ResultData>() {

							@Override
							public void onSuccess(ResultData result) {
								initItemData();
							}

							@Override
							public void onFail(int code) {
								Toast.makeText(getActivity(), "아이템 Pic하기 실패했습니다.", Toast.LENGTH_SHORT).show();
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
							public void onRoomSelected(boolean roomSelected,
									int roomNum) {
								if(roomSelected){
									NetworkManager.getInstance().postPickItem(getActivity(), myData.user_num, roomNum, iData.item_num, 
											new NetworkManager.OnResultListener<ResultData>() {

										@Override
										public void onSuccess(ResultData result) {
											iAdapter.updateData(iData, 1);//, iData.likeCnt++);
											initItemData();
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

				}

			}
		});

		uAdapter.setOnAdapterFollowClickListener(new OnAdapterFollowClickListener() {

			@Override
			public void onFollowClick(View v, UserData data) {
				uData = data;
				if(myData == null){
					Intent i = new Intent(getActivity(), LogInActivity.class);
					startActivity(i);
				}
				else{
					NetworkManager.getInstance().postFollowData(getActivity(), myData.user_num, uData.user_num, new NetworkManager.OnResultListener<ResultData>() {

						@Override
						public void onSuccess(ResultData result) {
							if(result.success == 1){
								setUserData(myData.user_num);
								getNewFollowList();
						
								//getNewFollowList안에서 initData해줌
							}
						}

						@Override
						public void onFail(int code) {
							Toast.makeText(getActivity(), "Fail to Follow!" + code, Toast.LENGTH_SHORT).show();
						}
					});

				}
			}

		});
		rankingList.setAdapter(uAdapter);
		return v;
	}


	private void initUsersData(){
		if(getActivity() != null){

				dialog = new Dialog(getActivity(), R.style.ProgDialog);
				dialog.setCancelable(true);
				dialog.addContentView(new ProgressBar(getActivity()), new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				dialog.show();
		}
		
		NetworkManager.getInstance().getUserRankingResultData(getActivity(), new NetworkManager.OnResultListener<UsersResult>() {

			@Override
			public void onSuccess(UsersResult result) {
				usersResult = result;
				uAdapter.replace(result.result);
				users = uAdapter.set();

				rank_bg.setImageResource(R.drawable.img_ranking_userbg);
				rank_title.setText("유저랭킹 1위");
				rank_title_name.setText(result.result.users.get(0).house_name);
				tab1.setTextColor(getResources().getColor(R.color.tabSelect_ranking));
				tab2.setTextColor(getResources().getColor(R.color.tabNotSelect_ranking));
				dialog.dismiss();
			}

			@Override
			public void onFail(int code) {
				dialog.dismiss();
			}
		});
	}


	public UserData setUserData(int userNum){
		NetworkManager.getInstance().getUserInfoData(getActivity(), userNum, new NetworkManager.OnResultListener<UserRoomsResult>() {

			@Override
			public void onSuccess(UserRoomsResult result) {
				uData = result.result.user;
				UserManager.getInstance().setuData(uData);
			}

			@Override
			public void onFail(int code) {
				Toast.makeText(getActivity(), "Fail to get UserData" + code, Toast.LENGTH_SHORT).show();

			}

		});
		return uData;
	}

	public void getNewFollowList(){
		
		if(myData != null){
			NetworkManager.getInstance().getFollowingResultData(getActivity(), myData.user_num, new NetworkManager.OnResultListener<UsersResult>() {

				@Override
				public void onSuccess(UsersResult result) {
					followUsers.clear();
					if(result.result != null){
						for(int i = 0; i < result.result.users.size(); i++){
							followUsers.add(result.result.users.get(i).user_num);
						}
						UserManager.getInstance().setFollowUsers(followUsers);
					}
					initUsersData();
				}

				@Override
				public void onFail(int code) {
					// TODO Auto-generated method stub
				}
			});

		}else{
			initUsersData();
		}

	}

	private void initItemData(){
	
		dialog = new Dialog(getActivity(), R.style.ProgDialog);
		dialog.setCancelable(true);
		dialog.addContentView(new ProgressBar(getActivity()), new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		dialog.show();
		NetworkManager.getInstance().getItemRankingResultData(getActivity(), new NetworkManager.OnResultListener<ItemsResult>() {

			@Override
			public void onSuccess(ItemsResult result) {
				iAdapter.clear();
				itemsResult = result;
				iAdapter.replace(result.result);
				rank_bg.setImageResource(R.drawable.img_ranking_productbg);
				rank_title.setText("상품랭킹 1위");
				rank_title_name.setText(result.result.items.get(0).item_name);
				tab1.setTextColor(getResources().getColor(R.color.tabNotSelect_ranking));
				tab2.setTextColor(getResources().getColor(R.color.tabSelect_ranking));
				dialog.dismiss();
			}

			@Override
			public void onFail(int code) {
				dialog.dismiss();
			}
		});

	}
	@Override
	public void onResume() {
		super.onResume();
		myData = UserManager.getInstance().getuData();
		if(LIST_TYPE_FLAG == 0){
			getNewFollowList();
		}else if(LIST_TYPE_FLAG == 1){
			initItemData();
		}

	}
}
