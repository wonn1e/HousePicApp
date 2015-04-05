package com.tacademy.penthouse.itemlike;
import it.sephiroth.android.library.widget.AdapterView.OnItemClickListener;
import it.sephiroth.android.library.widget.HListView;

import java.io.File;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ColorData;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.ItemItemsResult;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UserRoomsResult;
import com.tacademy.penthouse.house.ImgDialogFragment;
import com.tacademy.penthouse.house.MyRoomAdapter;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.manager.UserManager;

public class CreateNewRoomActivity extends FragmentActivity{
	public final static String PARAM_ITEM_IN_NEW_ROOM ="item in new room";
	public final static String PARAM_NEWROOM = "new room";
	public static final int REQUEST_CODE_CAMERA = 10;
	public static int FROM_EDIT_ROOM = -1;
	RoomData originalRoom;
	MyRoomAdapter myRoomAdapter;
	File mSavedFile;
	UserData myData = UserManager.getInstance().getuData();
	ItemData iData = new ItemData();
	RoomData newRoomData;
	ImageView new_room_img, cameraImg, checkBox;
	TextView text;
	EditText roomName;
	HListView colorList;
	ColorAdapter cAdapter;
	String colorCode = "";
	Dialog dialog;
	int roomPrivacy = 0;
	boolean roomIsCreated = false;
	int iNum = 0;
	int from, rNum;

	boolean nameSelect = false, imgSelect = false, colorSelect = false;

	ColorData[] colors = {new ColorData(R.drawable.btn_color_normal_01, R.drawable.btn_color_selected_01, "#D9B779"),
			new ColorData(R.drawable.btn_color_normal_02, R.drawable.btn_color_selected_02, "#B28850"),
			new ColorData(R.drawable.btn_color_normal_03, R.drawable.btn_color_selected_03, "#98826F"),
			new ColorData(R.drawable.btn_color_normal_04, R.drawable.btn_color_selected_04, "#E0673D"),
			new ColorData(R.drawable.btn_color_normal_05, R.drawable.btn_color_selected_05, "#D99982"),
			new ColorData(R.drawable.btn_color_normal_06, R.drawable.btn_color_selected_06, "#D9B79A"),
			new ColorData(R.drawable.btn_color_normal_07, R.drawable.btn_color_selected_07, "#F8B847"),
			new ColorData(R.drawable.btn_color_normal_08, R.drawable.btn_color_selected_08, "#BCAE86"),
			new ColorData(R.drawable.btn_color_normal_09, R.drawable.btn_color_selected_09, "#BFBBA4"),
			new ColorData(R.drawable.btn_color_normal_10, R.drawable.btn_color_selected_10, "#D4CFA5"),

			new ColorData(R.drawable.btn_color_normal_11, R.drawable.btn_color_selected_11, "#B5BF6B"),
			new ColorData(R.drawable.btn_color_normal_12, R.drawable.btn_color_selected_12, "#80A665"),
			new ColorData(R.drawable.btn_color_normal_13, R.drawable.btn_color_selected_13, "#3F8C8C"),
			new ColorData(R.drawable.btn_color_normal_14, R.drawable.btn_color_selected_14, "#7FC7BC"),
			new ColorData(R.drawable.btn_color_normal_15, R.drawable.btn_color_selected_15, "#789EB4"),
			new ColorData(R.drawable.btn_color_normal_16, R.drawable.btn_color_selected_16, "#8E94AC"),
			new ColorData(R.drawable.btn_color_normal_17, R.drawable.btn_color_selected_17, "#40639A"),
			new ColorData(R.drawable.btn_color_normal_18, R.drawable.btn_color_selected_18, "#595173"),
			new ColorData(R.drawable.btn_color_normal_19, R.drawable.btn_color_selected_19, "#4E5A68"),
			new ColorData(R.drawable.btn_color_normal_20, R.drawable.btn_color_selected_20, "#6A6A6A"),

			new ColorData(R.drawable.btn_color_normal_21, R.drawable.btn_color_selected_21, "#988F87")
	};
	String roomImgUrl= "";

	TextWatcher textWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			String text = s.toString();
			if(text != null && !text.equals("")) {
				nameSelect = true;
			} else {
				nameSelect = false;
			}
			verifyButton();
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
	Button btn;
	ImageLoader loader;
	DisplayImageOptions options;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_new_room);
		loader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.img_addroom_default)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.img_addroom_default)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.build();

		btn = (Button)findViewById(R.id.room_create_btn);
		verifyButton();    
		colorList = (HListView)findViewById(R.id.colorsHorizontal);
		cAdapter = new ColorAdapter(CreateNewRoomActivity.this);
		for(int i = 0; i < colors.length ; i++){
			cAdapter.add(colors[i]);
		}
		colorList.setAdapter(cAdapter);
		colorList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		new_room_img = (ImageView)findViewById(R.id.newRoomImg);
		checkBox = (ImageView)findViewById(R.id.addRoom_checkBox);
		myRoomAdapter= new MyRoomAdapter(CreateNewRoomActivity.this);

		text = (TextView)findViewById(R.id.text_addRoom);
		cameraImg = (ImageView)findViewById(R.id.cameraImg_addRoom);
		roomName = (EditText)findViewById(R.id.get_new_name);
		InputFilter[] FilterArray = new InputFilter[1];
		FilterArray[0] = new InputFilter.LengthFilter(18);
		roomName.setFilters(FilterArray);
		roomName.addTextChangedListener(textWatcher);

		Intent intent = getIntent();
		originalRoom = intent.getParcelableExtra("rData");
		from = intent.getIntExtra("from", -1);
		if(from == 1){
			FROM_EDIT_ROOM = 1;
			btn.setText("방 수정 완료");

			if(originalRoom.room_name !=null && !originalRoom.room_name.equals("")){
				roomName.setText(originalRoom.room_name);
				nameSelect = true;
				verifyButton();
			}

			colorCode = originalRoom.room_color;
			int position = -1;
			for(int i= 0; i<colors.length; i++){
				if(colorCode.equals(colors[i].colorName)){
					colorSelect = true;
					verifyButton();
					colorList.setItemChecked(i, true);
					break;
				}
			}

			if(position != ListView.INVALID_POSITION){
				colorCode = ((ColorData)cAdapter.getItem(position)).colorName;
				verifyButton();
			}
			
			roomImgUrl = (originalRoom.room_img_url == null) ? null : originalRoom.room_img_url;
			if(originalRoom.room_img_url!=null && !originalRoom.room_img_url.equals("")){
				loader.displayImage("http://54.178.158.103/images/" + originalRoom.room_img_url, new_room_img, options);
				imgSelect = true;
				verifyButton();
			}

			if(originalRoom.room_ispublic == 0){
				checkBox.setImageResource(R.drawable.btn_checkbox_off);
			}else{
				checkBox.setImageResource(R.drawable.btn_checkbox_on);
			}

			btn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					//room name is given
					if(roomName.getText().toString()!= null && !(roomName.getText().toString()).equals("")){
						if(colorCode != null && !colorCode.equals("")){
							if(roomImgUrl != null){
								NetworkManager.getInstance().postEditRoom(CreateNewRoomActivity.this, myData.user_num, originalRoom.room_num,
										roomName.getText().toString(), roomImgUrl, colorCode, roomPrivacy, new NetworkManager.OnResultListener<ResultData>() {

									@Override
									public void onSuccess(ResultData result) {
										setResult(Activity.RESULT_OK);
										finish();
										FROM_EDIT_ROOM = -1;
									}

									@Override
									public void onFail(int code) {
										Toast.makeText(CreateNewRoomActivity.this, "방 수정에 실패했습니다.", Toast.LENGTH_SHORT).show();
										FROM_EDIT_ROOM = -1;
									}

								});
							}else{
								NetworkManager.getInstance().postEditRoom(CreateNewRoomActivity.this, myData.user_num, originalRoom.room_num,
										roomName.getText().toString(), null, colorCode, roomPrivacy, new NetworkManager.OnResultListener<ResultData>() {

									@Override
									public void onSuccess(ResultData result) {
										setResult(Activity.RESULT_OK);
										finish();
										FROM_EDIT_ROOM = -1;
									}

									@Override
									public void onFail(int code) {
										Toast.makeText(CreateNewRoomActivity.this, "방 수정에 실패했습니다.", Toast.LENGTH_SHORT).show();
										FROM_EDIT_ROOM = -1;
									}

								});
							}
						}
						//}
					}
				}
			});
		}
		else{	//0 넘어옴 - ItemLikeShowListDialog
			iNum =intent.getIntExtra("iData", 0);
			btn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					//room name is given
					if(roomName.getText().toString()!= null && !(roomName.getText().toString()).equals("")){
						if(roomImgUrl!= null && !roomImgUrl.equals("")){
							if(colorCode != null && !colorCode.equals("")){
								dialog = new Dialog(CreateNewRoomActivity.this, R.style.ProgDialog);
								dialog.setCancelable(true);
								dialog.addContentView(new ProgressBar(CreateNewRoomActivity.this), new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
								dialog.show();

								
								NetworkManager.getInstance().postMakeRoom(CreateNewRoomActivity.this, 
										myData.user_num, roomName.getText().toString(), roomImgUrl, colorCode, roomPrivacy, 
										new NetworkManager.OnResultListener<ResultData>() {

									@Override
									public void onSuccess(ResultData result) {
										dialog.dismiss();
										if(iNum != 0)
											findRoomNum();
										roomIsCreated=true;
										finish();
									}

									@Override
									public void onFail(int code) {
										Toast.makeText(CreateNewRoomActivity.this, "방 만들기에 실패했습니다. error code : " + code, Toast.LENGTH_SHORT).show();
									}
								});
							}
						}
					}
				}
			});
		}

		roomName.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(actionId == EditorInfo.IME_ACTION_DONE){
					InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(roomName.getWindowToken(), 0);
				}
				return false;
			}
		});


		colorList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(
					it.sephiroth.android.library.widget.AdapterView<?> parent,
					View view, int position, long id) {
				colorSelect = true;
				int pos = position;    
				if(pos != ListView.INVALID_POSITION){
					colorCode = ((ColorData)cAdapter.getItem(pos)).colorName;
					verifyButton();
				}
			}
		});

		new_room_img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ImgDialogFragment imgDialog = new ImgDialogFragment();
				imgDialog.setOnItemDataClickListener(new ImgDialogFragment.OnItemDataClickListener() {

					@Override
					public void bringGallery(int galleryResult) {
						Intent i = new Intent(
								Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						i.putExtra("aspectX", 12);
						i.putExtra("aspectY", 7);
						i.setType("image/*");
						i.putExtra("crop", "true");
						i.putExtra("output", getTempUri());
						i.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
						i.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());            
						startActivityForResult(i, REQUEST_CODE_CAMERA);					
					}

					@Override
					public void bringCamera(int cameraResult) {

						Intent i = new Intent(
								MediaStore.ACTION_IMAGE_CAPTURE);
						i.putExtra("aspectX", 12);
						i.putExtra("aspectY", 7);
						i.putExtra("crop", "true");
						i.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
						i.putExtra("outputFormat",
								Bitmap.CompressFormat.JPEG.toString());            
						startActivityForResult(i, REQUEST_CODE_CAMERA); 
					}

					@Override
					public void deletePicture(int deleteResult) {

					}
				});
				imgDialog.show(getSupportFragmentManager(),"dialog");
			}
		});

		checkBox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(roomPrivacy == 1){
					roomPrivacy = 0;
					checkBox.setImageResource(R.drawable.btn_checkbox_off);
				}else if(roomPrivacy == 0){
					roomPrivacy = 1;
					checkBox.setImageResource(R.drawable.btn_checkbox_on);
					Toast.makeText(CreateNewRoomActivity.this, "5개 이상 상품을 추가 시 Everyone Feed에 나타납니다.", Toast.LENGTH_SHORT).show();
				}
			}
		});

		if(roomIsCreated){
			NetworkManager.getInstance().getItemInfoResultData(CreateNewRoomActivity.this, iNum, new NetworkManager.OnResultListener<ItemItemsResult>() {

				@Override
				public void onSuccess(ItemItemsResult result) {
					iData = result.result.item;

				}

				@Override
				public void onFail(int code) {
					// TODO Auto-generated method stub

				}
			});
			NetworkManager.getInstance().getUserInfoData(CreateNewRoomActivity.this, myData.user_num, new NetworkManager.OnResultListener<UserRoomsResult>() {

				@Override
				public void onSuccess(UserRoomsResult result) {
					RoomData newRoom = result.result.rooms.get(result.result.rooms.size()-1);
					if(iData != null){
						NetworkManager.getInstance().postPickItem(CreateNewRoomActivity.this, myData.user_num, newRoom.room_num, iData.item_num,
								new NetworkManager.OnResultListener<ResultData>() {

							@Override
							public void onSuccess(ResultData result) {
							}

							@Override
							public void onFail(int code) {
								Toast.makeText(CreateNewRoomActivity.this, "아이템 넣기에 실패했습니다.", Toast.LENGTH_SHORT).show();
							}
						});
					}
				}

				@Override
				public void onFail(int code) {
					// TODO Auto-generated method stub

				}
			});


		}//else부분끝
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode ==  REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK){
			imgSelect = true;
			verifyButton();

			text.setVisibility(View.GONE);
			cameraImg.setVisibility(View.GONE);

			roomImgUrl = (mSavedFile == null) ? null : mSavedFile.getAbsolutePath();
			Bitmap bm = BitmapFactory.decodeFile(mSavedFile.getAbsolutePath() );//, options);
			new_room_img.setImageBitmap(bm);
		}
	}

	private Uri getTempUri() {
		mSavedFile = new File(Environment.getExternalStorageDirectory(),
				"temp_" + System.currentTimeMillis() / 1000 + ".jpg");

		return Uri.fromFile(mSavedFile);
	}

	public void verifyButton() {
		if (nameSelect && colorSelect && imgSelect) {
			btn.setBackgroundResource(R.drawable.button);
		} else {
			btn.setBackgroundResource(R.drawable.deactiviated_btn);
		}
	}

	public void likeItem(int rNum, int iNum){
		NetworkManager.getInstance().postPickItem(CreateNewRoomActivity.this, myData.user_num, rNum , iNum, new NetworkManager.OnResultListener<ResultData>() {

			@Override
			public void onSuccess(ResultData result) {
				if(result.success == 1){
					finish();
				}

			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub

			}
		});
	}
	public void findRoomNum(){
		NetworkManager.getInstance().getUserInfoData(CreateNewRoomActivity.this, myData.user_num, new NetworkManager.OnResultListener<UserRoomsResult>() {
			@Override
			public void onSuccess(UserRoomsResult result) {
				int size = 1;
				if(result.result.rooms.size() != 0){
					size = result.result.rooms.size();
				}
				rNum = result.result.rooms.get(size - 1).room_num;
				if(iNum != 0){
					likeItem(rNum, iNum);
				}
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				rNum = 0;
			}
		});
	}


}
