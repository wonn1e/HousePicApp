package com.tacademy.penthouse.neighbor;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UserRoomsResult;
import com.tacademy.penthouse.entity.UsersResult;
import com.tacademy.penthouse.house.HouseActivity;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.manager.UserManager;
import com.tacademy.penthouse.member.LogInActivity;
import com.tacademy.penthouse.neighbor.NeighborAdapter.OnAdapterFollowClickListener;

public class FollowingList extends PagerFragment {
	public static final int REQUEST_CODE_FOLLOWING= 0;
	ListView listview_following;
	NeighborAdapter nAdapter;
	String name, message;
	int resId;
	int uNum;
	ArrayList<UserData> users = new ArrayList<UserData>();
	ArrayList<Integer> followUsers = new ArrayList<Integer>();
	UserData uData = new UserData();
	UserData myData = UserManager.getInstance().getuData();
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.neighbor_list_layout, container, false);
		Bundle iB = getArguments();
		if(iB != null){
			uNum = iB.getInt("uNum");
		}
		listview_following = (ListView)v.findViewById(R.id.listview_neighbor);
		nAdapter = new NeighborAdapter(getActivity());
		listview_following.setAdapter(nAdapter);
		
		listview_following.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				//
				uNum = users.get(position).user_num;
				Intent i = new Intent(getActivity(), HouseActivity.class);
				i.putExtra("uData", uNum);
				startActivity(i);
				
			}
		});
		
	nAdapter.setOnAdapterFollowClickListener(new OnAdapterFollowClickListener() {
			
			@Override
			public void onFollowClick(View v, UserData data) {
				
				uData = data;
				if(myData == null){
					Intent i = new Intent(getActivity(), LogInActivity.class);
					startActivity(i);
				}
				else{
					NetworkManager.getInstance().postFollowData(getActivity(), myData.user_num, data.user_num, new NetworkManager.OnResultListener<ResultData>() {

						@Override
						public void onSuccess(ResultData result) {
							if(result.success == 1){
								if(getActivity() != null)
								setUserData(myData.user_num);
								getNewFollowList();
							}
						}

						@Override
						public void onFail(int code) {
							if(getActivity() != null)
							Toast.makeText(getActivity(), "팔로우하기에 실패하였습니다." + code, Toast.LENGTH_SHORT).show();
						}
					});
					
				}
				
			}
		});
		initData();
		return v;
	}
	
	private void initData(){
		NetworkManager.getInstance().getFollowingResultData(getActivity(), uNum, new NetworkManager.OnResultListener<UsersResult>() {
			
			@Override
			public void onSuccess(UsersResult result) {
				nAdapter.clear();
				if(result.result == null){
					//nAdapter.add(new UserData(0, "", "", "", 0, 0, "", "", "", ""));
				}else{
					users = result.result.users;
					for(int i = 0; i < users.size(); i++){
						nAdapter.add(users.get(i));
					}
				}
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		
		
		});
		
		
	}
	@Override
	public void onPageCurrent() {
		super.onPageCurrent();
		nAdapter.clear();
		initData();
	}
	public void getNewFollowList(){
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
				initData();
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
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
				// TODO Auto-generated method stub
				
			}
    		
		});
    	return uData;
    }
}
