package com.tacademy.penthouse.slidingmenu;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.tacademy.penthouse.MainActivity;
import com.tacademy.penthouse.R;

public class NoticeActivity extends ActionBarActivity {
	ExpandableListView lv;
	NoticeAdapter mAdapter;
	ActionBar mActionBar;
	TextView gnbTitle;
	String notice1 = "하우스픽은 현재 오픈 베타중입니다.";
	String content1 = "하우스픽은 현재 오픈 베타 기간입니다. 물론 정식 출시 이전에도 앱을 이용하시는 데에는 아무런 지장이 없습니다. 하우스픽을 다운로드 해주셔서 감사합니다.\n 하우스픽은 1인 가구를 위한 인테리어 상품 추천 서비스입니다.\n 그동안 원하는 인테리어 찾느라 힘드셨죠!? 인테리어 스타일을 찾아도 어떻게 해야 할지 난감하진 않았나요!? 이제는 하우스픽이 있습니다.\n\n - 하우스픽은 다양한 테마의 인테리어에 어울리는 상품을 보여줍니다. 원하는 스타일과 그에 필요한 상품을 쉽게 찾을 수 있어요~ \n - 내가 원하는 인테리어 테마가 있다면 [나의 방]을 만들고, 상품들을 PICK해서 방에 모아볼 수 있어요.\n - [나의 방]을 공개해서 다른 사용자들과 공유하세요. 다른 사용자들이 많이 팔로잉하면 인기 유저가 될 수 있어요! :)\n\n 하우스픽과 함께 예쁘고, 멋지게 내 집을 꾸며보세요!\n\n그럼 곧 정식 버전과 함께 인사드리겠습니다.\n Coming soon!\n\n감사합니다.\n\n - HousePic Team";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mActionBar = getSupportActionBar();
		mActionBar.show();
		mActionBar.setCustomView(R.layout.gnb_title);
		mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#40639a")));
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowHomeEnabled(false);
		gnbTitle = (TextView)findViewById(R.id.title);
		gnbTitle.setText("공지사항");
		
		
		setContentView(R.layout.activity_notice);
		lv = (ExpandableListView)findViewById(R.id.notice_list);
		mAdapter = new NoticeAdapter(this);
		lv.setAdapter(mAdapter);
		init();
		for(int i = 0; i < mAdapter.getGroupCount(); i++){
			lv.expandGroup(i);
		}
	}

	private void init() {
//		for (int i = 0; i < 5; i++) {
//			for (int j = 0; j < 1; j++) {
//				mAdapter.put("공지사항"+i, " 내용 내용 내용"+j);
//			}
//		}
		mAdapter.put(notice1, content1);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case android.R.id.home:
			startActivity(new Intent(NoticeActivity.this, MainActivity.class));
		}
		return super.onOptionsItemSelected(item);
	}

}
