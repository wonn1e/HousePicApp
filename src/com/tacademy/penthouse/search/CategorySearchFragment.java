package com.tacademy.penthouse.search;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.tacademy.penthouse.MainActivity;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.CategoryData;
import com.tacademy.penthouse.room.UserRoomInfoActivity;

public class CategorySearchFragment extends Fragment {
	String keyword;
	EditText searchQuery;
	Button submit;
	ImageView searchBtn;
	ActionBar mActionBar;
	ExpandableListView category_list;
	CategoryAdapter mAdapter;
	int cate_num = 0;
	TextView gnbTitle;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mActionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
		mActionBar.show();
		mActionBar.setCustomView(R.layout.gnb_menu_title);
		mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#40639a")));
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowHomeEnabled(false);
		gnbTitle = (TextView)getActivity().findViewById(R.id.title);
		gnbTitle.setText("�˻� �� ī�װ�");
		ImageView gnb_menu = (ImageView)getActivity().findViewById(R.id.gnb_menu);
		gnb_menu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((MainActivity)getActivity()).getSlidingMenu().toggle();
			}
		});
		
	};
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_category_search, container, false);

		searchBtn = (ImageView)v.findViewById(R.id.search_btn);
		searchBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				keyword = searchQuery.getText().toString();
				if(keyword.length() < 2){
					Toast.makeText(getActivity(), "�˻�� 2���� �̻��̾�� �մϴ�.", Toast.LENGTH_SHORT).show();
				}else{
					Intent i = new Intent(getActivity(), SearchResultActivity.class);
					i.putExtra("keyword", keyword);
					startActivity(i);
				}
			}
		});
		
		
		searchQuery = (EditText)v.findViewById(R.id.searchquery);
		searchQuery.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(actionId == EditorInfo.IME_ACTION_SEARCH){
					InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(searchQuery.getWindowToken(), 0);
					keyword = searchQuery.getText().toString();
					if(keyword.length() < 2){
						Toast.makeText(getActivity(), "�˻�� 2���� �̻��̾�� �մϴ�.", Toast.LENGTH_SHORT).show();
					}else{
						Intent i = new Intent(getActivity(), SearchResultActivity.class);
						i.putExtra("keyword", keyword);
						startActivity(i);
					}
				}
				return false;
			}
		});

		category_list = (ExpandableListView)v.findViewById(R.id.category_list);
		mAdapter = new CategoryAdapter(getActivity());
		category_list.setAdapter(mAdapter);
		initData();
		for(int i = 0; i < mAdapter.getGroupCount(); i++){
			category_list.expandGroup(i);
		}
		category_list.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				category_list.expandGroup(groupPosition);
			}
		});
		category_list.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Intent i = new Intent(getActivity(), SearchResultActivity.class);
				
				int category = 1;
				//CategoryNum�� Adapter�� �־������!
				if(groupPosition == 0){
					category = childPosition +1;
				}else if(groupPosition == 1){
					category = childPosition +7;
				}else if(groupPosition == 2){
					category = childPosition + 11;
				}
				i.putExtra("cateNum", category);
				
				startActivity(i);
				return true;
			}
		});

		return v;
	}

	private void initData(){
		String[] category = {"����","�к긯","����"};
		CategoryData[] cd1 = {new CategoryData("å�� / ���̺�", 1), new CategoryData("ȭ���", 2), new CategoryData("������", 3),
				new CategoryData("å�� / ����", 4), new CategoryData("����", 5), new CategoryData("����", 6)};
		CategoryData[] cd2 = {new CategoryData("Ŀư / ����ε�", 7), new CategoryData("��� / �漮", 8), new CategoryData("ħ��", 9),
				new CategoryData("���� / ī��Ʈ", 10)};
		CategoryData[] cd3 = {new CategoryData("������ǰ", 11), new CategoryData("����", 12), new CategoryData("���ǰ", 13),
				new CategoryData("�����̾���", 14), new CategoryData("Ź������", 15)};
		
		for(int j = 0; j < 6; j++){
			mAdapter.put(category[0], cd1[j]);
		}
		for(int i=0; i<4; i++){
			mAdapter.put(category[1], cd2[i]);
		}
		for(int i=0; i<5; i++){
			mAdapter.put(category[2], cd3[i]);
		}

	}
}
