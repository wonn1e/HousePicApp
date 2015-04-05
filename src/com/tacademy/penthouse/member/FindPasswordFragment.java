package com.tacademy.penthouse.member;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.manager.NetworkManager;

public class FindPasswordFragment extends DialogFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NORMAL, R.style.EditImgDialog);
	}
	
	ImageView cancel;
	EditText email;
	String emailAdd;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Dialog d = getDialog();
		
		View v = inflater.inflate(R.layout.find_password_view, container,false);
		cancel = (ImageView)v.findViewById(R.id.cancel_findpw);
		email = (EditText)v.findViewById(R.id.emailForLostPW);
		
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		Button btn = (Button)v.findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				emailAdd = email.getText().toString();
				if(emailAdd != null && !emailAdd.equals("")){
					NetworkManager.getInstance().postLostPWData(getActivity(), emailAdd, new NetworkManager.OnResultListener<ResultData>() {

						@Override
						public void onSuccess(ResultData result) {
							dismiss();
						}

						@Override
						public void onFail(int code) {
							Toast.makeText(getActivity(), "�̸��� ������ �߸��Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();
						}
					});
				}else{
					Toast.makeText(getActivity(), "�̸����� �Է����ּ���.", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
		Dialog d = getDialog();
	}
	
}
