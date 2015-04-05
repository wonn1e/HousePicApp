package com.tacademy.penthouse.member;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Session;
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
import com.tacademy.penthouse.slidingmenu.PrivacyActivity;
import com.tacademy.penthouse.slidingmenu.RulesActivity;

public class SignUpActivity extends ActionBarActivity {
	UserData uData;
	ArrayList<Integer> followUsers = new ArrayList<Integer>();
	UserManager uManager= UserManager.getInstance();
	EditText emailInput, pwInput, nicknameInput;
	String email, pw, nickname;
	Button signUp, fbSignUp;
	TextView rules, privacy;
	int userNum;
	ActionBar mActionBar;
	TextView gnbTitle;
	String regId;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		mActionBar = getSupportActionBar();
		mActionBar.show();
		mActionBar.setCustomView(R.layout.gnb_title);
		mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#40639a")));
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowHomeEnabled(false);
		gnbTitle = (TextView)findViewById(R.id.title);
		gnbTitle.setText("회원가입");


		emailInput = (EditText)findViewById(R.id.sign_up_email);
		pwInput = (EditText)findViewById(R.id.sign_up_password);
		nicknameInput = (EditText)findViewById(R.id.sign_up_nickname);
		InputFilter[] FilterArray = new InputFilter[1];
		FilterArray[0] = new InputFilter.LengthFilter(10);
		nicknameInput.setFilters(FilterArray);

		signUp= (Button)findViewById(R.id.sign_up_btn);
		signUp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				email = emailInput.getText().toString();
				pw = pwInput.getText().toString();
				nickname = nicknameInput.getText().toString();
				boolean emailForm = false;
				if(email !=null && !email.equals("")){
					emailForm = checkEmail(email);
				}
				
				if(emailForm && pw !=null && !pw.equals("") && nickname != null && nickname != ""){
					signUp.setEnabled(true);
					regId = PropertyManager.getInstance().getRegistrationId();
					NetworkManager.getInstance().postRegister(SignUpActivity.this, email, pw, nickname, nickname+"님의 집", regId,new NetworkManager.OnResultListener<ResultData>() {

						@Override
						public void onSuccess(ResultData result) {
							if(result == null){

							}
							if(result.success == 1){
								Toast.makeText(SignUpActivity.this, "성공적으로 회원가입이 되었습니다", Toast.LENGTH_SHORT).show();
								SplashActivity.LOGIN_TYPE = 1;
								autoLogIn();
							}
						}

						@Override
						public void onFail(int code) {
							if(code == 401){
								Toast.makeText(SignUpActivity.this, "이미 등록된 이메일 입니다", Toast.LENGTH_SHORT).show();
							}
														
						}
					});
					Intent i = new Intent(SignUpActivity.this, SignUpActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(i);
				}else if(!emailForm){
					Toast.makeText(SignUpActivity.this, "이메일 형식에 맞는 아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
				}
			}
		});

		fbSignUp = (Button)findViewById(R.id.sign_up_facebook);
		fbSignUp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}

		});
		privacy = (TextView)findViewById(R.id.privacy_tv);
		rules = (TextView)findViewById(R.id.rules_tv);
		privacy.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(SignUpActivity.this, PrivacyActivity.class));
			}
		});

		rules.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(SignUpActivity.this, RulesActivity.class));
			}
		});
	}

	private boolean checkEmail(String email){
		String mail = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
		Pattern p = Pattern.compile(mail);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	private void autoLogIn(){
		regId = PropertyManager.getInstance().getRegistrationId();
		NetworkManager.getInstance().postLoginData(SignUpActivity.this , email, pw, regId, 
				new NetworkManager.OnResultListener<UserNumResult>() {

			@Override
			public void onSuccess(UserNumResult result) {
				if(result.success == 1){
					userNum = result.result.user_num;
					PropertyManager.getInstance().setUserId(email);
					PropertyManager.getInstance().setPassword(pw);
					setUserData(userNum);
					setFollowList(userNum);
					UserManager.getInstance().setPassword(pw);
					UserManager.getInstance().setUserNum(userNum);
				}
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub

			}
		});
	}

	public UserData setUserData(int userNum){
		NetworkManager.getInstance().getUserInfoData(SignUpActivity.this, userNum, new NetworkManager.OnResultListener<UserRoomsResult>() {
			@Override
			public void onSuccess(UserRoomsResult result) {

				if(result.result != null){
					uData = result.result.user;
					uManager.setuData(uData);
					Intent i = new Intent(SignUpActivity.this, MainActivity.class);
					i.putExtra(MainActivity.MENU_TYPE, 1);
					startActivity(i);
				}else{
					Toast.makeText(SignUpActivity.this, "Fail to setUserData. try again", Toast.LENGTH_SHORT).show();
				}

			}
			@Override
			public void onFail(int code) {
				Toast.makeText(SignUpActivity.this, "FAIL setUserData", Toast.LENGTH_SHORT).show();
			}
		});
		return uData;
	}
	public void setFollowList(int userNum){
		NetworkManager.getInstance().getFollowingResultData(SignUpActivity.this, userNum, new NetworkManager.OnResultListener<UsersResult>() {

			@Override
			public void onSuccess(UsersResult result) {
				if(result.result != null){
					for(int i = 0; i < result.result.users.size(); i++){
						followUsers.add(result.result.users.get(i).user_num);
					}
					uManager.setFollowUsers(followUsers);
				}
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
