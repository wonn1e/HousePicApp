package com.tacademy.penthouse.slidingmenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.tacademy.penthouse.MainActivity;
import com.tacademy.penthouse.R;

public class MenuFragment extends Fragment {
	
	public static final int ID_MYHOUSE = 0;
	public static final int ID_HOME = 1;
	public static final int ID_RANK = 3;
	public static final int ID_SEARCH = 2;
	public static final int ID_INVITE = 4;
	public static final int ID_SETTING = 5;
	ListView lv;
	MenuAdapter mAdapter;
	String[] menu = { "홈", "검색", "마이페이지", "랭킹", "친구초대", "설정"};
	int[] resId = {R.drawable.ic_slidebar_home,R.drawable.ic_slidebar_search,R.drawable.ic_slidebar_mypage,
			R.drawable.ic_slidebar_ranking,R.drawable.ic_slidebar_invite,R.drawable.ic_slidebar_setting};
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.menu_fragment, container, false);
		
		//0
		TextView my_page = (TextView)v.findViewById(R.id.my_page);
		my_page.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((MainActivity)getActivity()).actionMenu(ID_MYHOUSE);
				//Toast.makeText(getActivity(), "to my page", Toast.LENGTH_SHORT).show();
			}
		});
		//1
		TextView home = (TextView)v.findViewById(R.id.show_recommendation); //to home page
		home.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((MainActivity)getActivity()).actionMenu(ID_HOME);
			}
		});
		//3
		TextView show_ranking = (TextView)v.findViewById(R.id.show_ranking);
		show_ranking.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((MainActivity)getActivity()).actionMenu(ID_RANK);
			}
		});
		//2
		TextView show_search = (TextView)v.findViewById(R.id.show_search);
		show_search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((MainActivity)getActivity()).actionMenu(ID_SEARCH);
			}
		});
		//4
		TextView show_invite = (TextView)v.findViewById(R.id.show_invite);
		show_invite.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((MainActivity)getActivity()).actionMenu(ID_INVITE);
			}
		});
		//5
		TextView show_setting = (TextView)v.findViewById(R.id.show_setting);
		show_setting.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((MainActivity)getActivity()).actionMenu(ID_SETTING);
			}
		});
		return v;
	}
}
