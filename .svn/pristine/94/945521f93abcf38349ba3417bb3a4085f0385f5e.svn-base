package com.tacademy.penthouse.house;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.internal.gt;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.manager.UserManager;

public class DeleteRoom extends DialogFragment {
	
	public interface OnReceiveDeleteRoomListener{
		public void onReceiveDelete(int success);
	}
	
	OnReceiveDeleteRoomListener mListener;
	
	public void setOnReceiveHousenameListener(OnReceiveDeleteRoomListener listener){
		mListener = listener;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NORMAL, R.style.HouseDialog);
	}
	MyRoomAdapter myRoomAdapter;
	UserData myData = UserManager.getInstance().getuData();
	int roomNum;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Dialog d  = getDialog();
		myRoomAdapter = new MyRoomAdapter(getActivity());
		View v= inflater.inflate(R.layout.confirm_room_delete, container, false);
		Bundle b = getArguments();
		roomNum = b.getInt("rNum");
		
		Button cancel = (Button)v.findViewById(R.id.cancelDeleteRoom);
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		Button delete = (Button)v.findViewById(R.id.deleteRoomBtn);
		delete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				NetworkManager.getInstance().postDeleteRoom(getActivity(), myData.user_num, roomNum, 
						new NetworkManager.OnResultListener<ResultData>() {

					@Override
					public void onSuccess(ResultData result) {
						
						if(mListener != null){
							mListener.onReceiveDelete(1);
						}
						dismiss();
						
					}
					@Override
					public void onFail(int code) {
						if(getActivity() != null)
							Toast.makeText(getActivity(), "방 삭제 실패", Toast.LENGTH_SHORT).show();
					}
				});
				
			}
		});
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
		Dialog d= getDialog();
	}
}
