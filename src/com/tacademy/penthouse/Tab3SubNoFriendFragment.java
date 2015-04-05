package com.tacademy.penthouse;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tab3SubNoFriendFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.tab3_no_friend_sublayout, container, false);
		((MainActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#40639A")));
		((MainFragment)getParentFragment().getParentFragment()).showTabWidget(true);
		((MainFragment)getParentFragment().getParentFragment()).tabWidget.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2B4266")));
		return v;
	}
}
