package com.tacademy.penthouse.member;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Request.GraphUserCallback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.tacademy.penthouse.MainActivity;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.SplashActivity;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UserNumResult;
import com.tacademy.penthouse.entity.UserRoomsResult;
import com.tacademy.penthouse.entity.UsersResult;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.manager.PropertyManager;
import com.tacademy.penthouse.manager.UserManager;

public class LogInActivity extends ActionBarActivity {
	EditText emailInput, pwInput;
	String email, pw;
	ImageView auto_checkbox;
	TextView findPw, register;
	int userNum;
	int myUserNum;
	int loginOk = -1;
	int autoLogin;
	String regId;
	UserManager uManager= UserManager.getInstance();
	UserData uData;
	int autoTrue = PropertyManager.getInstance().getAutoLogin();
	ActionBar mActionBar;
	ArrayList<Integer> followUsers = new ArrayList<Integer>();
	/*
	 * 
	 * FACEBOOK LOGIN 써야함
	 * 
	 * 회원가입
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */

	AlertDialog dialog;
	AlertDialog.Builder builder;
	ImageView cancel;
	EditText emailEdit;
	String emailAdd;
	Dialog pDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_in);
		emailInput = (EditText)findViewById(R.id.input_email);
		pwInput= (EditText)findViewById(R.id.inpu_password);
		//자동 로그인 처리
		PropertyManager.getInstance().setAutoLogin(1);

		mActionBar = getSupportActionBar();
		mActionBar.show();
		mActionBar.setCustomView(R.layout.gnb_title);
		mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#40639a")));
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowHomeEnabled(false);
		
		
		auto_checkbox = (ImageView)findViewById(R.id.autoLogIn_checkbox);
		auto_checkbox.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				autoTrue = 1;
				setAutoLogin();
				if(autoTrue == 1){
					//now not-auto
					autoTrue = 0;
					PropertyManager.getInstance().setAutoLogin(0);
				}else{
					//now auto-login
					autoTrue = 1;
					PropertyManager.getInstance().setAutoLogin(1);
				}
			}
		});

		findPw = (TextView)findViewById(R.id.find_password_btn);
		findPw.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				builder = new AlertDialog.Builder(LogInActivity.this);
				LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
				View view = inflater.inflate(R.layout.find_password_view, (ViewGroup)findViewById(R.layout.activity_log_in));

				cancel = (ImageView)view.findViewById(R.id.cancel_findpw);
				emailEdit = (EditText)view.findViewById(R.id.emailForLostPW);
				
				cancel.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});

				Button btn = (Button)view.findViewById(R.id.edit_pw);
				btn.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						emailAdd = emailEdit.getText().toString();
						if(emailAdd != null && !emailAdd.equals("")){
							NetworkManager.getInstance().postLostPWData(LogInActivity.this, emailAdd, new NetworkManager.OnResultListener<ResultData>() {

								@Override
								public void onSuccess(ResultData result) {
									Toast.makeText(LogInActivity.this, emailAdd + "로 임시 비밀번호가 발송되었습니다.", Toast.LENGTH_SHORT).show();
									dialog.dismiss();
								}

								@Override
								public void onFail(int code) {
									Toast.makeText(LogInActivity.this, "이메일 정보가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
								}
							});
						}else{
							Toast.makeText(LogInActivity.this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
						}

					}
				});
				builder.setView(view);
				dialog = builder.create();
				dialog.show();
			}
		});

		register = (TextView)findViewById(R.id.register_Login);
		register.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(LogInActivity.this, SignUpActivity.class));
			}
		});

		Button btn = (Button)findViewById(R.id.log_in_btn_LogIn);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				email = emailInput.getText().toString();
				pw = pwInput.getText().toString();
				autoLogin = PropertyManager.getInstance().getAutoLogin();
				autoTrue = autoLogin;
				if(email != null && !email.equals("") && pw != null && !pw.equals("")){
					regId = PropertyManager.getInstance().getRegistrationId(); 
					NetworkManager.getInstance().postLoginData(LogInActivity.this, email, pw, regId,
							new NetworkManager.OnResultListener<UserNumResult>() {

						@Override
						public void onSuccess(UserNumResult result) {
							if(result.success == 1){
								SplashActivity.LOGIN_TYPE = 1;
								userNum = result.result.user_num;
								myUserNum = result.result.user_num;
								loginOk = 1;
								if(autoTrue == 1){
									PropertyManager.getInstance().setUserId(email);
									PropertyManager.getInstance().setPassword(pw);
								}else{
									PropertyManager.getInstance().setUserId(null);
									PropertyManager.getInstance().setPassword(null);
								}

								if(loginOk == 1){
									//setUserData(userNum);
									setFollowList(userNum);
									UserManager.getInstance().setPassword(pw);
									UserManager.getInstance().setUserNum(userNum);
								}

							}
						}
						@Override
						public void onFail(int code) {
							Toast.makeText(LogInActivity.this, "로그인 정보가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
						}
					});
				}


			}
		});

		btn = (Button)findViewById(R.id.facebookLogin);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				pDialog = new Dialog(LogInActivity.this, R.style.ProgDialog);
				pDialog.setCancelable(true);
				pDialog.addContentView(new ProgressBar(LogInActivity.this), new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				pDialog.show();
				Session.openActiveSession(LogInActivity.this, true, new StatusCallback() {

					@Override
					public void call(Session session, SessionState state, Exception exception) {
						if (session.isOpened()) {
							final String token = session.getAccessToken();
							Request.newMeRequest(session, new GraphUserCallback() {

								@Override
								public void onCompleted(GraphUser user, Response response) {
									if (user != null) {
										//String id = user.getId();
										// NetworkModel... id, token
										regId = PropertyManager.getInstance().getRegistrationId();
										NetworkManager.getInstance().postFacebookLogin(LogInActivity.this, token,regId, new NetworkManager.OnResultListener<UserNumResult>() {

											@Override
											public void onSuccess(UserNumResult result) {
												SplashActivity.LOGIN_TYPE = 2;
												if(result.success == 1){
													userNum = result.result.user_num;
													loginOk = 1;
													myUserNum = result.result.user_num;
													if(loginOk == 1){
//														setUserData(result.result.user_num);
														setFollowList(result.result.user_num);
														UserManager.getInstance().setUserNum(userNum);
														PropertyManager.getInstance().setToken(token);
													}
													pDialog.dismiss();
												}
												
											}

											@Override
											public void onFail(int code) {
												Toast.makeText(LogInActivity.this, "페이스북 로그인 정보가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
												pDialog.dismiss();
											}
										});
										
									}
								}
							}).executeAsync();
						}
					}
				});
			}
		});
	}

	private static int cnt = 0;

	private void setAutoLogin(){
		//auto login
		if(cnt % 2 == 0){
			auto_checkbox.setImageResource(R.drawable.btn_checkbox_on);
			cnt++;
		}
		else{
			auto_checkbox.setImageResource(R.drawable.btn_checkbox_off);
			cnt++;
		}
	}
@Override
protected void onResume() {
	super.onResume();
	if(pDialog != null){
		pDialog.dismiss();
	}
}
	public UserData setUserData(int userNum){
		NetworkManager.getInstance().getUserInfoData(LogInActivity.this, userNum, new NetworkManager.OnResultListener<UserRoomsResult>() {
			@Override
			public void onSuccess(UserRoomsResult result) {
				
				if(result.result != null){
					uData = result.result.user;
					uManager.setuData(uData);
					Intent i = new Intent(LogInActivity.this, MainActivity.class);
					i.putExtra(MainActivity.MENU_TYPE, 1);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
					finish();
				}else{
					Toast.makeText(LogInActivity.this, "Fail to setUserData. try again", Toast.LENGTH_SHORT).show();
				}
			
			}
			@Override
			public void onFail(int code) {
				Toast.makeText(LogInActivity.this, "FAIL setUserData", Toast.LENGTH_SHORT).show();
			}
		});
		return uData;
	}
	public void setFollowList(int userNum){
		NetworkManager.getInstance().getFollowingResultData(LogInActivity.this, userNum, new NetworkManager.OnResultListener<UsersResult>() {
			@Override
			public void onSuccess(UsersResult result) {
				if(result.result != null){
					for(int i = 0; i < result.result.users.size(); i++){
						followUsers.add(result.result.users.get(i).user_num);
					}
					uManager.setFollowUsers(followUsers);
				}
				setUserData(myUserNum);
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (Session.getActiveSession() != null) {
			Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
		}
	}
	
}
