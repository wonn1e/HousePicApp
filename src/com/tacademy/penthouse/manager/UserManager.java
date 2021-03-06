package com.tacademy.penthouse.manager;

import java.util.ArrayList;

import com.tacademy.penthouse.entity.UserData;

public class UserManager {
	private static UserManager instance;
	public static UserManager getInstance(){
		if(instance == null){
			instance = new UserManager();
		}
		return instance;
	}
	
	private UserManager() {
		
	}

	private int userNum;
	private String userId;
	private String password;
	private UserData uData;
	public ArrayList<Integer> followUsers = new ArrayList<Integer>();
	
	public UserData getuData() {
		return uData;
	}
	public void setuData(UserData uData) {
		this.uData = uData;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Integer> getFollowUsers() {
		return followUsers;
	}

	public void setFollowUsers(ArrayList<Integer> followUsers) {
		this.followUsers = followUsers;
	}
	
}
