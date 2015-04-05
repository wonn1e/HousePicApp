package com.tacademy.penthouse.slidingmenu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.tacademy.penthouse.MainActivity;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.manager.NetworkManager;

public class ContactActivity extends ActionBarActivity {
	ImageView spinnerImg, contactTypeClickArea, dimView;
	TextView contactType;
	EditText getEmail, getTitle,getContact;
	PopupWindow contactPopup;
	View contactView;
	ListView contactList;
	ContactAdapter cAdapter;
	String type;
	Button send;
	int userNum;
	String title, content;
	ActionBar mActionBar;
	TextView gnbTitle;
	public static String SAVE_CONTACT_TYPE = "문의유형 선택";
	
	private static int cnt = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		dimView = (ImageView)findViewById(R.id.dim_contact);
		dimView.setVisibility(View.GONE);
		dimView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			}
		});
		userNum = com.tacademy.penthouse.manager.UserManager.getInstance().getUserNum();

		mActionBar = getSupportActionBar();
		mActionBar.show();
		mActionBar.setCustomView(R.layout.gnb_title);
		mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#40639a")));
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowHomeEnabled(false);
		gnbTitle = (TextView)findViewById(R.id.title);
		gnbTitle.setText("문의하기");
		
		
		cAdapter = new ContactAdapter(this);
		cAdapter.add("문의유형 선택");
		cAdapter.add("기능 추가 문의");
		cAdapter.add("오류 / 버그 신고하기");
		cAdapter.add("불편사항");
		cAdapter.add("회원 탈퇴하기");
		cAdapter.add("기타");
		contactView = getLayoutInflater().inflate(R.layout.contact_popup, null);
		contactList = (ListView)contactView.findViewById(R.id.contactList);
		contactPopup = new PopupWindow(contactView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

		contactList.setAdapter(cAdapter);

		spinnerImg = (ImageView)findViewById(R.id.contactSpinnerImg);
		contactTypeClickArea = (ImageView)findViewById(R.id.clickContactChoice);
		contactType = (TextView)findViewById(R.id.currentContactChoice);
		getEmail = (EditText)findViewById(R.id.emailInfo);
		getTitle = (EditText)findViewById(R.id.titleInfo);
		getContact = (EditText)findViewById(R.id.contactInfo);
		send = (Button)findViewById(R.id.sendContactBtn);
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				title = getTitle.getText().toString();
				content = getContact.getText().toString();
				NetworkManager.getInstance().postContact(ContactActivity.this, userNum, title, content, new NetworkManager.OnResultListener<ResultData>() {

					@Override
					public void onSuccess(ResultData result) {
						if(result.success == 1)
							Toast.makeText(ContactActivity.this, "문의사항을 보냈습니다.", Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFail(int code) {
						Toast.makeText(ContactActivity.this, "문의사항을 보내기에 실패하였습니다.", Toast.LENGTH_SHORT).show();
					}
					
				});
				
			}
		});
		setSpinnerImg();

		contactTypeClickArea.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(cnt %2 == 0){
					cnt++;
					setSpinnerImg();
					contactPopup.setFocusable(true);
					contactPopup.setAnimationStyle(-1);
					contactPopup.setTouchable(true);
					//	contactPopup.setOutsideTouchable(true);
					//	contactPopup.setBackgroundDrawable(new BitmapDrawable());
					contactPopup.showAsDropDown(contactTypeClickArea, 0, 0);
					contactPopup.showAtLocation(contactView, Gravity.NO_GRAVITY, 0, 0);
					dimView.setVisibility(View.VISIBLE);
				}else{
					if(contactPopup != null && contactPopup.isShowing()){
						contactPopup.dismiss();
						dimView.setVisibility(View.GONE);
						cnt++;
						setSpinnerImg();
					}
				}
			}
		});

		cAdapter.setOnAdapterItemClickListener(new ContactAdapter.OnAdapterItemClickListener() {

			@Override
			public void onAdapterItemClick(View v, String s) {
				contactType.setText(s);
				SAVE_CONTACT_TYPE = s;
				if(contactPopup != null && contactPopup.isShowing()){
					if(s.equals("회원 탈퇴하기")){
						getContact.setHint("탈퇴 사유를 입력해주세요.");
					}else{
						getContact.setHint("문의사항을 입력해주세요.");
					}
					contactPopup.dismiss();
					dimView.setVisibility(View.GONE);
					cnt++;
					setSpinnerImg();
				}
			}
		});

		getContact.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(actionId == EditorInfo.IME_ACTION_DONE){
					InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(getContact.getWindowToken(), 0);
				}
				return false;
			}
		});

	}

	private void setSpinnerImg(){
		//default
		if(cnt %2 == 0){
			spinnerImg.setImageResource(R.drawable.ic_seach_dropdown_unselect);
		}
		//when popup is opened
		else{
			spinnerImg.setImageResource(R.drawable.ic_search_dropdown_seleted);
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case android.R.id.home:
			startActivity(new Intent(ContactActivity.this, MainActivity.class));
		}
		return super.onOptionsItemSelected(item);
	}


}
