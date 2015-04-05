package com.tacademy.penthouse.slidingmenu;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.manager.UserManager;

public class ChangePWDialog extends Activity {
	EditText getPw, reEnter;
	String pW, reenterPw;
	Button btn;
	boolean samePw = false;

	UserData myData = UserManager.getInstance().getuData();
	
	TextWatcher textWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			String text = s.toString();
			String password = getPw.getText().toString();
			if (password.startsWith(text)) {
				if (password.equals(text)) {
					samePw = true;
					verifyButton();
				
				} else {
					samePw = false;
					verifyButton();
				}
			} else {
				//not same
				samePw = false;
				verifyButton();
			//	reEnter.setBackgroundResource(getResources().getColor(R.drawable.deactiviated_btn));
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_user_password);
		getPw = (EditText)findViewById(R.id.newPW);
		reEnter = (EditText)findViewById(R.id.newPW_reEnter);
		reEnter.addTextChangedListener(textWatcher);
		btn = (Button)findViewById(R.id.edit_pw);

		ImageView cancel = (ImageView)findViewById(R.id.cancel_pw);
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				pW = getPw.getText().toString();
				reenterPw = reEnter.getText().toString();


				if(pW!=null && !pW.equals("") && reenterPw!=null && !reenterPw.equals("") && pW.equals(reenterPw)){

					NetworkManager.getInstance().postChangePW(ChangePWDialog.this, myData.user_num, pW, new NetworkManager.OnResultListener<ResultData>() {


						@Override
						public void onSuccess(ResultData result) {
							Toast.makeText(ChangePWDialog.this, "비밀번호가 변경되었습니다.", Toast.LENGTH_SHORT).show();
							
						}

						@Override
						public void onFail(int code) {
							Toast.makeText(ChangePWDialog.this, "비밀 번호 변경 실패", Toast.LENGTH_SHORT).show();
						}
					});
				}
				finish();
				
			}
		});
	}
	
	public void verifyButton(){
		if (samePw) {
			btn.setBackgroundResource(R.drawable.blue_btn);
			btn.setFocusable(false);
		} else {
			btn.setFocusable(true);
			btn.setBackgroundResource(R.drawable.deactiviated_btn);
		}
	}
}
