package com.tacademy.penthouse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.manager.UserManager;

public class Tab3FriendsFragment extends Fragment {

	UserData myData = UserManager.getInstance().getuData();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v =inflater.inflate(R.layout.tab3_friends_layout, container, false);
		
		return v;
	}

	@Override
	public void onResume() {
		super.onResume();
		myData = UserManager.getInstance().getuData();
		if(myData == null){
			getChildFragmentManager().beginTransaction().replace(R.id.tab3Container, 
					new Tab3SubNoFriendFragment()).commit();
		}else{
			getChildFragmentManager().beginTransaction().replace(R.id.tab3Container,
					new Tab3SubFriendFragment()).commit();
		}
	}

}
