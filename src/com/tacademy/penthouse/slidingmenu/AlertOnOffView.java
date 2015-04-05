package com.tacademy.penthouse.slidingmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UserRoomsResult;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.manager.UserManager;

public class AlertOnOffView extends FrameLayout{

	ImageView btn;
	TextView title;
	UserData myData;
	boolean isChecked;
	
	public AlertOnOffView(Context context){
	      super(context);
	      init();
	   }
	
	
	   private void drawIsCheck(){
	      if(isChecked){
	         myData.alert = 0;
	         updateUserSetting(0);
	      }else{
	         myData.alert = 1;
	         updateUserSetting(1);
	      }
	   }
	   
	   private void init(){
	      LayoutInflater.from(getContext()).inflate(R.layout.alert_on_off_view, this);
	      title = (TextView)findViewById(R.id.follow_alert);
	      btn = (ImageView)findViewById(R.id.alertswitch);
	      myData = UserManager.getInstance().getuData();
	      if(myData!= null){
	         if(myData.alert == 1){
	            btn.setImageResource(R.drawable.btn_checkbox_on);
	         }else{
	            btn.setImageResource(R.drawable.btn_checkbox_off);
	         }
	      }else{
	         title.setTextColor(getResources().getColor(R.color.unable)); 
	         btn.setImageResource(R.drawable.btn_checkbox_off);
	      }
	      
	      btn.setOnClickListener(new View.OnClickListener() {

	         @Override
	         public void onClick(View v) {
	            myData = UserManager.getInstance().getuData();
	            if(myData!=null){
	               if(myData.alert == 1){
	                  isChecked = true;
	               }else{
	                  isChecked = false;
	               }
	               drawIsCheck();
	            }else{
	               Toast.makeText(getContext(), "�α��� ���ּ���!", Toast.LENGTH_SHORT).show();
	            }
	         }

	      });

	   }

	   public void updateUserSetting(int alert){
//	      NetworkManager.getInstance().postEditMyData(getContext(), myData.user_num, myData.user_nickname, null, myData.house_name, null,
//	            myData.house_intro, alert, new NetworkManager.OnResultListener<ResultData>() {
//
//	                  @Override
//	                  public void onSuccess(ResultData result) {
//	                     if(myData.alert == 1){
//	                        btn.setImageResource(R.drawable.btn_checkbox_on);
//	                  }else{
//	                        btn.setImageResource(R.drawable.btn_checkbox_off);
//	                     }
//	                     getNewUserData();
//	                  }
//
//	                  @Override
//	                  public void onFail(int code) {
//	                     Toast.makeText(getContext(), "�������濡 �����Ͽ����ϴ�. error code : " + code , Toast.LENGTH_SHORT).show();
//	                  }
//	               });
		   NetworkManager.getInstance().postEditMyData(getContext(), myData.user_num, "�г���", null, "���̸�", null, "���Ұ�", alert, new NetworkManager.OnResultListener<ResultData>() {

			@Override
			public void onSuccess(ResultData result) {
				Toast.makeText(getContext(), "����", Toast.LENGTH_SHORT).show();
				
			}

			@Override
			public void onFail(int code) {
				Toast.makeText(getContext(), "���� ", Toast.LENGTH_SHORT).show();				
			}
		});
	   }
	   
	   
		public void getNewUserData(){
		NetworkManager.getInstance().getUserInfoData(getContext(), myData.user_num, new NetworkManager.OnResultListener<UserRoomsResult>() {

			@Override
			public void onSuccess(UserRoomsResult result) {
				UserData ud = result.result.user;
				UserManager.getInstance().setuData(ud);
				myData = ud;
				//init();
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	}


//	
//	{
//		super(context);
//		init();
//	}
//	
//	private void init(){
//		LayoutInflater.from(getContext()).inflate(R.layout.alert_on_off_view, this);
//		title = (TextView)findViewById(R.id.follow_alert);
//		btn = (ImageView)findViewById(R.id.alertswitch);
//		myData = UserManager.getInstance().getuData();
//		if(myData!= null){
//			if(myData.alert == 1){
//				btn.setImageResource(R.drawable.btn_checkbox_on);
//			}else{
//				btn.setImageResource(R.drawable.btn_checkbox_off);
//			}
//		}else{
//			title.setTextColor(getResources().getColor(R.color.unable));
//			btn.setImageResource(R.drawable.btn_checkbox_off);
//		}
//		btn = (ImageView)findViewById(R.id.alertswitch);
//		btn.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				myData = UserManager.getInstance().getuData();
//				if(myData!=null){
//					if(myData.alert == 1){
//						//isChecked = true;
//						updateUserSetting(0);
//					}else{
//						//isChecked = false;
//						updateUserSetting(1);
//					}
//				}else{
//					Toast.makeText(getContext(), "�α��� ���ּ���!", Toast.LENGTH_SHORT).show();
//				}
//			}
//
//		});
//	}
//
//	public void updateUserSetting(int alert){
//		NetworkManager.getInstance().postEditMyData(getContext(), myData.user_num, myData.user_nickname, null, myData.house_name, null,
//				myData.house_intro, alert, new NetworkManager.OnResultListener<ResultData>() {
//
//						@Override
//						public void onSuccess(ResultData result) {
//							if(myData.alert == 1){
//								Toast.makeText(getContext(), "�˶� On", Toast.LENGTH_SHORT).show();
//								btn.setImageResource(R.drawable.btn_checkbox_on);
//						}else{
//								btn.setImageResource(R.drawable.btn_checkbox_off);
//								Toast.makeText(getContext(), "�˶� Off", Toast.LENGTH_SHORT).show();
//							}
//							getNewUserData();
//						}
//
//						@Override
//						public void onFail(int code) {
//							
//						}
//					});
//	}
//	
//	public void getNewUserData(){
//		NetworkManager.getInstance().getUserInfoData(getContext(), myData.user_num, new NetworkManager.OnResultListener<UserRoomsResult>() {
//
//			@Override
//			public void onSuccess(UserRoomsResult result) {
//				UserData ud = result.result.user;
//				UserManager.getInstance().setuData(ud);
//				myData = ud;
//				init();
//			}
//
//			@Override
//			public void onFail(int code) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//	}
//	
//}
//
