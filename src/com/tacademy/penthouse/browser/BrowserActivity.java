package com.tacademy.penthouse.browser;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.tacademy.penthouse.MainActivity;
import com.tacademy.penthouse.R;

public class BrowserActivity extends Activity {
	private static final String TAG = "BrowserActivity";

	WebView webView;
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_browser);
		webView = (WebView)findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);	//zoom

		webView.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if(url.startsWith("http://www.google.com")){
					//----

					return true;
				}
				return super.shouldOverrideUrlLoading(view, url);
			}
		});

		Intent i = getIntent();
		Uri uri = i.getData();
		webView.loadUrl(uri.toString());
		
		
		Button btn = (Button)findViewById(R.id.btn_prev);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(webView.canGoBack()){
					webView.goBack();
				}else{
					Toast.makeText(BrowserActivity.this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
				}
			}
		});

		btn = (Button)findViewById(R.id.btn_next);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(webView.canGoForward()){
					webView.goForward();
				}else{
					Toast.makeText(BrowserActivity.this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		btn = (Button)findViewById(R.id.btn_home);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(BrowserActivity.this, MainActivity.class);
				startActivity(i);
			}
		});
		
		btn = (Button)findViewById(R.id.btn_refresh);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				webView.reload();
				
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		webView.resumeTimers();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		webView.pauseTimers();
	}
}