package com.tacademy.penthouse.itemlike;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.ItemItemsResult;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UserRoomsData;
import com.tacademy.penthouse.entity.UserRoomsResult;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.manager.UserManager;

public class ItemLikeShowListDialog extends DialogFragment{
	public static final String PARAM_ITEM_DATA = "iData";
	public static final String PARAM_ROOM_DATA = "rData";
	public static final String PARAM_USER_DATA = "uData";

	ListView room_list;
	UserRoomsData urData;
	ArrayList<RoomData> rooms;
	RoomData rData;
	ItemData itemData;
	ImageView cancel;
	UserData uData = UserManager.getInstance().getuData();
	NoRoomInMyRoomListView noRoomView;
	RoomNameAdapter rAdapter;
	int iNum = 0;
	int rNum = 0;

	public interface OnRoomSelectedListener{
		public void onRoomSelected(boolean roomSelected, int roomNum);
	}

	OnRoomSelectedListener rListener;

	public void setOnRoomSelectedListener(OnRoomSelectedListener listener){
		rListener = listener;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NORMAL, R.style.HouseDialog);

		//item data & room data 받아오기
		Bundle iB = getArguments();
		if(iB != null){
			iNum = iB.getInt("iData");
			rNum = iB.getInt("rData");
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Dialog d = getDialog();
		rAdapter = new RoomNameAdapter(getActivity());

		View v= inflater.inflate(R.layout.item_like_choose_room, container, false);
		cancel = (ImageView)v.findViewById(R.id.cancel_chooseRoom);
		noRoomView = (NoRoomInMyRoomListView)v.findViewById(R.id.noRoomInList);
		room_list = (ListView)v.findViewById(R.id.room_list);

		room_list.setAdapter(rAdapter);
		room_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				RoomData room = (RoomData) room_list.getItemAtPosition(position);
				if(rListener != null){
					rListener.onRoomSelected(true, room.room_num);
				}

				dismiss();
			}
		});

		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
		});

		Button btn = (Button)v.findViewById(R.id.create_room_btn);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), CreateNewRoomActivity.class);
				i.putExtra("iData", iNum);
				i.putExtra("from", 0);

				startActivity(i);
				dismiss();
			}
		});


		//initData();
		return v;
	}
	@Override
	public void onResume() {
		super.onResume();
		rAdapter.clear();
		initData();
		
	}

	
	private void initData(){
		uData = UserManager.getInstance().getuData();
		//roomNum = roomNumFromItemNum(iNum);
		NetworkManager.getInstance().getUserInfoData(getActivity(), uData.user_num, new NetworkManager.OnResultListener<UserRoomsResult>() {

			@Override
			public void onSuccess(UserRoomsResult result) {
				urData = result.result;
				rooms = result.result.rooms;

				if(rooms != null){
					noRoomView.setVisibility(View.GONE);
					room_list.setVisibility(View.VISIBLE);
					if(rooms.size() == 0){
						room_list.setVisibility(View.GONE);
						noRoomView.setVisibility(View.VISIBLE);
					}else{

						for(int i=0; i<result.result.rooms.size(); i++){
							if(result.result.rooms != null){
								if(result.result.rooms.get(i).room_num != rNum)
									rAdapter.add(result.result.rooms.get(i));
							}
						}
						room_list.setAdapter(rAdapter);
					}
				}
			}

			@Override
			public void onFail(int code) {
				if(getActivity() != null){
					Toast.makeText(getActivity(), "방 정보 받아오는데 실패", Toast.LENGTH_SHORT).show();
				}
			}

		});
	}

	
	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
		Dialog d = getDialog();
	}
	
	
}
