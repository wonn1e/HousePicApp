package com.tacademy.penthouse.slidingmenu;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
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

import com.facebook.Session;
import com.tacademy.penthouse.MainActivity;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.SplashActivity;
import com.tacademy.penthouse.WalkThroughActivity;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.manager.PropertyManager;
import com.tacademy.penthouse.manager.UserManager;

public class ConfigInformFragment extends Fragment{
	ListView lv;
	ConfigInformAdapter mAdapter;
	UserData myData = UserManager.getInstance().getuData();
	TextView gnbTitle;
	ActionBar mActionBar;
	Dialog dialog;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mActionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
		mActionBar.show();
		mActionBar.setCustomView(R.layout.gnb_menu_title);
		mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#40639a")));
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowHomeEnabled(false);
		gnbTitle = (TextView)getActivity().findViewById(R.id.title);
		gnbTitle.setText("설정");
		ImageView gnb_menu = (ImageView)getActivity().findViewById(R.id.gnb_menu);
		gnb_menu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((MainActivity)getActivity()).getSlidingMenu().toggle();
			}
		});
		
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		mAdapter.clear();
		initData();
		
	}
	
	public void initData(){
		mAdapter.add("Title1");
		mAdapter.add("Alert");
		mAdapter.add("비밀번호 변경");
		mAdapter.add("Title2");
		mAdapter.add("공지사항");
		mAdapter.add("이용약관");
		mAdapter.add("개인정보 취급방침");
		mAdapter.add("문의하기");
		mAdapter.add("이용가이드");
		if(myData != null)
			mAdapter.add("로그아웃");
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_config_inform, container,false);

		
		lv = (ListView)v.findViewById(R.id.listView1);
		mAdapter = new ConfigInformAdapter(getActivity());
		mAdapter.clear();
		initData();
		lv.setAdapter(mAdapter);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(position == 2){	//비밀번호 변경
					if(SplashActivity.LOGIN_TYPE == 2){
						Toast.makeText(getActivity(), "페이스북 로그인시 비밀번호 변경이 되지 않습니다.", Toast.LENGTH_SHORT).show();
					}else{
						if(myData != null){
							Intent i = new Intent(getActivity(), ChangePWDialog.class);
							startActivity(i);
						}else{
							Toast.makeText(getActivity(), "마이페이지에서 로그인을 해주세요.", Toast.LENGTH_SHORT).show();
						}
					}
				}
				if(position == 4){
					Intent i = new Intent(getActivity(), NoticeActivity.class);
					startActivity(i);
				}
				else if(position == 5){
					Intent i = new Intent(getActivity(), RulesActivity.class);
					startActivity(i);
				}else if(position == 6){
					Intent i = new Intent(getActivity(), PrivacyActivity.class);
					startActivity(i);
				}else if(position == 7){
					Intent i = new Intent(getActivity(), ContactActivity.class);
					startActivity(i);
				}else if(position == 8){
					Intent i = new Intent(getActivity(), WalkThroughActivity.class);
					startActivity(i);
				}else if(position == 9){
				
					dialog = new Dialog(getActivity(), R.style.ProgDialog);
					dialog.setCancelable(true);
					dialog.addContentView(new ProgressBar(getActivity()), new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
					dialog.show();

					if(SplashActivity.LOGIN_TYPE == 2){//FaceBook Log out
						
						Session session = Session.getActiveSession();
						if (session == null) {
							session = Session.openActiveSessionFromCache(getActivity());
						}
						if (session != null && session.isOpened()) {
							session.closeAndClearTokenInformation();
						}
						NetworkManager.getInstance().postFacebookLogout(getActivity(), new NetworkManager.OnResultListener<ResultData>() {
							
							@Override
							public void onSuccess(ResultData result) {
								
								dialog.dismiss();
								Toast.makeText(getActivity(), "정상적으로 로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
								UserManager.getInstance().setuData(null);
								UserManager.getInstance().setFollowUsers(null);
								UserManager.getInstance().setUserNum(-1);
								Intent i = new Intent(getActivity(), MainActivity.class);
								i.putExtra(MainActivity.MENU_TYPE, 1);
								PropertyManager.getInstance().setToken(null);
								PropertyManager.getInstance().setAutoLogin(0);
								startActivity(i);
							}

							@Override
							public void onFail(int code) {
								Toast.makeText(getActivity(), "Log Out Fail" + code, Toast.LENGTH_SHORT).show();										
							}
						});
					}else if(SplashActivity.LOGIN_TYPE == 1){ //Log out
						int userNum = UserManager.getInstance().getUserNum();
						NetworkManager.getInstance().postLogoutData(getActivity(), userNum, new NetworkManager.OnResultListener<ResultData>() {
							@Override
							public void onSuccess(ResultData result) {
								if(result.success == 1){
									dialog.dismiss();
									Toast.makeText(getActivity(), "정상적으로 로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
									UserManager.getInstance().setuData(null);
									UserManager.getInstance().setFollowUsers(null);
									UserManager.getInstance().setUserNum(-1);
									PropertyManager.getInstance().setUserId(null);
									PropertyManager.getInstance().setPassword(null);
									PropertyManager.getInstance().setAutoLogin(0);
									Intent i = new Intent(getActivity(), MainActivity.class);
									i.putExtra(MainActivity.MENU_TYPE, 1);
									startActivity(i);
								}
							}

							@Override
							public void onFail(int code) {
								Toast.makeText(getActivity(), "Log Out Fail", Toast.LENGTH_SHORT).show();							
							}

						});
					}else{
						dialog.dismiss();
						Toast.makeText(getActivity(), "로그인 하지 않으셨습니다.", Toast.LENGTH_SHORT).show();
					}
				}

			}
		});

		return v;
	}
}
