package com.tacademy.penthouse.slidingmenu;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.tacademy.penthouse.MainActivity;
import com.tacademy.penthouse.R;

public class PrivacyActivity extends ActionBarActivity{
	TextView privacy;
	ActionBar mActionBar;
	TextView gnbTitle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_privacy);
		mActionBar = getSupportActionBar();
		mActionBar.show();
		mActionBar.setCustomView(R.layout.gnb_title);
		mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#40639a")));
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowHomeEnabled(false);
		gnbTitle = (TextView)findViewById(R.id.title);
		gnbTitle.setText("개인정보 취급방침");

		privacy = (TextView)findViewById(R.id.privacy);
		privacy.setText("[개인정보의 수집 및 이용목적]\n\n“회사”는 회원가입을 통한 서비스 신청과 제공 등을 위해 다음과 같은 개인정보를 수집하고 있습니다.\n\n1. 개인정보 수집 항목\n1) 회원가입시 : 이메일주소, 비밀번호, 닉네임, 프로필 사진\n2) 서비스 이용시 : 서비스 이용기록, 접속 로그, 쿠키, 접속 IP 정보\n\n2. 개인정보 수집 방법\n1) 회원여부를 판단하기 위한 목적으로 입력하는 고객의 이메일 주소 및 비밀번호\n2) 서비스 이용과정에서 고객의 상담 및 오류개선 등의 목적으로 이용되는 서비스 이용기록, 접속 로그, 쿠키, 접속 IP 정보 등의 생성과 수집\n\n[수집하는 개인 정보의 항목]\n\n1. “회사”는 최초 회원가입 시 서비스 제공을 원활하게 하기 위해 필요한 최소한의 정보만을 받고 있으며 “회사”가 제공하는 서비스 이용에 따른 대금결제, 물품배송 및 환불에 필요한 정보를 추가로 수집할 수 있습니다.\n2. “회사”는 개인정보의 기본 수집 이용목적 이외에 다른 용도로 이를 이용하거나 회원의 동의 없이 제3자에게 이를 제공하지 않습니다.\n3. “회사”는 수집된 고객의 개인정보를 다음의 목적을 위해 이용하고 있습니다.\n1) 서비스 제공에 관한 계약 이행 및 서비스 제공\n   (1) 하우스픽 컨텐츠 제공\n   (2) 회원 맞춤형 서비스 제공\n2) 하우스픽 회원관리\n   (1) 부정 이용자 발생 시 접속제한 등의 제재조치를 위한 기록 보존\n   (2) 고객과의 분쟁발생 시 조정을 위한 기록 보존\n   (3) 불만접수 및 개선사항 요구 등 민원처리를 위한 기록 보존\n3) 신규 컨텐츠 홍보 및 이벤트/광고 등 마케팅 정보의 전달\n   (1) 이메일 뉴스레터 수신을 허가한 고객을 위한 뉴스레터 발송\n   (2) 신규 컨텐츠 및 서비스 개발에 따른 홍보 및 정보전달\n   (3) 이벤트, 프로모션 등 광고성 정보의 전달\n   (4) 접속빈도 파악 및 고객 서비스 이용의 통계 등을 통한 기록 보존\n4. “회사”는 회원의 개인정보를 수집할 경우 반드시 회원의 동의를 얻어 수집하며, 회원의 기본적 인권을 침해할 우려가 있는 인종, 출신지, 본적지, 사상, 정치적 성향, 범죄기록, 건강상태 등의 정보는 회원의 동의 또는 법령의 규정에 의한 경우 이외에는 수집하지 않습니다.\n\n[개인정보의 보유 및 이용기간]\n\n“회사”는 원칙적으로 개인정보 수집 및 이용목적이 달성된 후, 또 <회원탈퇴> 기능을 통해 해당 이메일과 비밀번호의 삭제를 희망할 경우에는 해당 개인정보를 지체 없이 파기합니다. 단, 관계법령의 규정에 의하여 보존할 필요가 있는 경우 회사는 아래와 같이 관계법령에서 정한 일정한 기간 동안 이용자 개인정보를 보관합니다.\n\n1. 상법 등 법령에 따라 보존할 필요성이 있는 경우\n1) 광고에 관한 기록\n보존근거 : 전자상거래 등에서의 소비자보호에 관한 법률\n보존기간 : 6개월\n2) 소비자의 불만 또는 분쟁처리에 관한 기록\n보존근거 : 전자상거래등에서의 소비자보호에 관한 법률\n보존기간 : 3년\n3) 접속에 관한 기록보존\n보존근거 : 통신비밀보호법 제15조의2 및 시행령 제41조\n보존기간 : 3개월\n2. 기타(분쟁조정 및 불만접수, 또는 그에 상응하는 외부요청 등)로 고객과의 개별적인 동의가 있는 경우에는 개별 동의에 따른 기간까지 보관합니다.\n");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case android.R.id.home:
			startActivity(new Intent(PrivacyActivity.this, MainActivity.class));
		}
		return super.onOptionsItemSelected(item);
	}


}
