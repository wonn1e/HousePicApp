package com.tacademy.penthouse.manager;


import android.content.Context;
import android.content.SharedPreferences;

import com.tacademy.penthouse.MyApplication;

public class PropertyManager {
	private static PropertyManager instance;
	
	public static PropertyManager getInstance() {
		if (instance == null) {
			instance = new PropertyManager();
		}
		return instance;
	}
	
	SharedPreferences mPrefs;
	private static final String PREF_NAME = "myprefs";
	private static final String FIELD_REG_ID = "regId";
	SharedPreferences.Editor mEditor;
	
	private PropertyManager() {
		mPrefs = MyApplication.getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		mEditor = mPrefs.edit();
	}
	
	private static final String USER_ID = "userid";
	private String mUserId;
	
	public void setUserId(String userId) {
		mUserId = userId;
		mEditor.putString(USER_ID, userId);
		mEditor.commit();
	}
	
	public String getUserId() {
		if (mUserId == null) {
			mUserId = mPrefs.getString(USER_ID, null);
		}
		return mUserId;
	}
	
	private static final String AutoLogin = "autoLogin";
	private int mAutoLogin;
	public void setAutoLogin(int autoLogin){
		mAutoLogin = autoLogin;
		mEditor.putInt(AutoLogin, autoLogin);
		mEditor.commit();
	}
	public int getAutoLogin(){
		if(mAutoLogin == 0){
			mAutoLogin = mPrefs.getInt(AutoLogin, 0);
		}
		return mAutoLogin;
	}
	
	private static final String Token = "token";
	private String mToken;
	public void setToken(String token){
		mToken = token;
		mEditor.putString(Token, token);
		mEditor.commit();
	}
	
	public String getToken(){
		if(mToken == null){
			mToken = mPrefs.getString(Token, null);
		}
		return mToken;
	}
	private static final String PASSWORD = "password";
	private String mPassword;
	
	public void setPassword(String password) {
		mPassword = password;
		mEditor.putString(PASSWORD, password);
		mEditor.commit();
	}
	
	public String getPassword() {
		if (mPassword == null) {
			mPassword = mPrefs.getString(PASSWORD, null);
		}
		return mPassword;
	}
	private static final String WALKTHROUGH = "walkthrough";
	private int mReadAll = 0;
	
	public void setWalkThroughRead(int read){
		mReadAll = read;
		mEditor.putInt(WALKTHROUGH, read);
		mEditor.commit();
	}
	
	public int getWalkThroughRead(){
		if(mReadAll == 0){
			mReadAll = mPrefs.getInt(WALKTHROUGH, 0);
		}
		return mReadAll;
	}
	
	private String regId;
	
	public void setRegistrationId(String regId) {
		this.regId = regId;
		mEditor.putString(FIELD_REG_ID, regId);
		mEditor.commit();
		
	}
	
	public String getRegistrationId() {
		if(regId == null){
			regId = mPrefs.getString(FIELD_REG_ID, "");
		}
		return regId;
	}
}
