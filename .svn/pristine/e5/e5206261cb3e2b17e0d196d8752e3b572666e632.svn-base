package com.tacademy.penthouse.room;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tacademy.penthouse.MainActivity;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UserRoomItemsResult;
import com.tacademy.penthouse.house.DeleteRoom;
import com.tacademy.penthouse.item.ItemInfoActivity;
import com.tacademy.penthouse.itemlike.ItemLikeShowListDialog;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.manager.UserManager;

public class UserRoomInfoActivity extends ActionBarActivity {
   public static final String PARAM_USER = "uData";
   ItemLikeShowListDialog itemLikeDialog;
   ItemAdapter iAdapter;

   ImageView u_room_img;//, u_room_userImg;
   TextView u_room_name, u_room_intro;
   StaggeredGridView u_room_item_gridview;
   NoItemView deleteView;

   UserData myData = UserManager.getInstance().getuData();
   ActionBar mActionBar;
   ImageLoader loader;
   DisplayImageOptions options, userImgOptions;

	UserData uData = new UserData();
	ArrayList<ItemData> iData = new ArrayList<ItemData>();
	ItemData itemData;
	RoomData rData = new RoomData();
	int myUserNum;
	int rNum = 0;
	int uNum = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_info);
		mActionBar = getSupportActionBar();
		mActionBar.show();
		mActionBar.setCustomView(R.layout.gnb_menu_title);
		
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowHomeEnabled(false);
		
		ImageView gnb_menu = (ImageView)findViewById(R.id.gnb_menu);
		gnb_menu.setImageResource(R.drawable.ic_gnb_home);
		gnb_menu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent i = new Intent(UserRoomInfoActivity.this, MainActivity.class);
		            i.putExtra(MainActivity.MENU_TYPE, 1);
		            startActivity(i);			
			}
		});
		

      itemLikeDialog = new ItemLikeShowListDialog();
      Intent i = getIntent();
      rNum = i.getIntExtra("rData", rNum);
      uNum = i.getIntExtra("uData", uNum);

      loader = ImageLoader.getInstance();
      options = new DisplayImageOptions.Builder()
      .showImageOnLoading(R.drawable.placeholder)
      .showImageForEmptyUri(R.drawable.ic_empty)
      .showImageOnFail(R.drawable.ic_error)
      .cacheInMemory(true)
      .cacheOnDisc(true)
      .considerExifParams(true)
      .build();
      userImgOptions = new DisplayImageOptions.Builder()
      .showImageOnLoading(R.drawable.placeholder)
      .showImageForEmptyUri(R.drawable.ic_empty)
      .showImageOnFail(R.drawable.btn_color_normal_03)
      .cacheInMemory(true)
      .cacheOnDisc(true)
      .considerExifParams(true)
      .displayer(new RoundedBitmapDisplayer(100))
      .build();

      View v = getLayoutInflater().inflate(R.layout.header_view_room_layout, null);
      deleteView = (NoItemView)v.findViewById(R.id.noItemView2);
      u_room_img = (ImageView)v.findViewById(R.id.u_room_img);
      u_room_name = (TextView)v.findViewById(R.id.u_room_name);
    //  u_room_userImg = (ImageView)v.findViewById(R.id.u_room_my_img);
      u_room_item_gridview = (StaggeredGridView)findViewById(R.id.gridView_room);
      u_room_item_gridview.addHeaderView(v);
      //누른 사용자의 Activity로 이동!
//      u_room_userImg.setOnClickListener(new View.OnClickListener() {
//
//         @Override
//         public void onClick(View v) {
//            Intent i = new Intent(UserRoomInfoActivity.this, HouseActivity.class);
//            i.putExtra(PARAM_USER, uData);
//            startActivity(i);
//         }
//      });

      iAdapter = new ItemAdapter(this);
      u_room_item_gridview.setAdapter(iAdapter);

      iAdapter.setOnAdapterItemClickListener(new ItemAdapter.OnAdapterItemClickListener() {
         @Override
         public void onItemLikeClick(View v, ItemData data) {
            if(myData == null){
               Toast.makeText(UserRoomInfoActivity.this, "마이페이지에서 로그인 해주세요!", Toast.LENGTH_SHORT).show();
            }else{
               itemData = data;
               //now unlike!!
               if(data.islike == 1){
                  NetworkManager.getInstance().postPickItem(UserRoomInfoActivity.this, myData.user_num, rNum, itemData.item_num,
                        new NetworkManager.OnResultListener<ResultData>() {

                     @Override
                     public void onSuccess(ResultData result) {
                        //iAdapter.updateData(itemData, 0, itemData.likeCnt--);
                        getNewItemData();
                     }

                     @Override
                     public void onFail(int code) {
                        Toast.makeText(UserRoomInfoActivity.this, "아이템 Pic하기 실패했습니다.", Toast.LENGTH_SHORT).show();
                     }
                  });
               }
               //now like!!
               else{
                  Bundle b = new Bundle();
                  //b.putParcelable(ItemLikeShowListDialog.PARAM_ITEM_DATA, data.item_num);
                  b.putInt("iData", data.item_num);
                  b.putParcelable("uData", myData);
                  itemLikeDialog.setArguments(b);
                  itemLikeDialog.show(getSupportFragmentManager(), "dialog");
                  itemLikeDialog.setOnRoomSelectedListener(new ItemLikeShowListDialog.OnRoomSelectedListener() {

                     @Override
                     public void onRoomSelected(boolean roomSelected, int roomNum) {
                        if(roomSelected){
                           NetworkManager.getInstance().postPickItem(UserRoomInfoActivity.this, myData.user_num, roomNum, itemData.item_num, 
                                 new NetworkManager.OnResultListener<ResultData>() {

                              @Override
                              public void onSuccess(ResultData result) {
                                 Toast.makeText(UserRoomInfoActivity.this, "아이템을 Pic했습니다.", Toast.LENGTH_SHORT).show();
                                 //iAdapter.updateData(itemData, 1, itemData.likeCnt++);
                                 getNewItemData();
                              }

                              @Override
                              public void onFail(int code) {
                                 Toast.makeText(UserRoomInfoActivity.this, "아이템 Pic하기 실패했습니다.", Toast.LENGTH_SHORT).show();
                              }
                           });
                        }   
                     }
                  });
               }
            }
         }
      });


      u_room_item_gridview.setOnItemClickListener(new OnItemClickListener() {

         @Override
         public void onItemClick(AdapterView<?> parent, View view,
               int position, long id) {
            //Item의 상세정보 Activity로 이동!
            if(position != 0){
               Intent i = new Intent(UserRoomInfoActivity.this, ItemInfoActivity.class);
               ItemData d =(ItemData)u_room_item_gridview.getItemAtPosition(position);
               i.putExtra("iData", d.item_num);
               startActivity(i);
            }
         }
      });
      //initData();
   }

   private void initData(){
      iAdapter.clear();
      if(myData == null){
         myUserNum = 0;
      }else{
         myUserNum = myData.user_num;
      }
      
      NetworkManager.getInstance().getRoomInfo(this, myUserNum, rNum, new NetworkManager.OnResultListener<UserRoomItemsResult>() {
         @Override
         public void onSuccess(UserRoomItemsResult result) {
        	 deleteView.setVisibility(View.VISIBLE);
            if(result.result != null){
               rData = result.result.room;
               mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(rData.room_color)));
               uData = result.result.user;
               iData = result.result.items;
               if(iData.size() ==  0){
                  u_room_name.setText(rData.room_name);
                  loader.displayImage(rData.room_img_url, u_room_img ,options);
           //       loader.displayImage(rData.room_img_url, u_room_userImg, userImgOptions);
               }else{
            	   deleteView.setVisibility(View.GONE);
                  for(int i = 0; i<iData.size(); i++){
                     iAdapter.add(iData.get(i));
                  }
                  u_room_name.setText(rData.room_name);
                  loader.displayImage(rData.room_img_url, u_room_img ,options);
           //       loader.displayImage(rData.room_img_url, u_room_userImg, userImgOptions);
               }
            }else{
            }
            u_room_name.setText(rData.room_name);
            
            if((rData.room_img_url.substring(0, 4)).equals("http")){
           //    u_room_userImg.setVisibility(View.GONE);
               loader.displayImage(rData.room_img_url,u_room_img, options);
            }else{
           //    loader.displayImage("http://54.178.158.103/images/" +rData.room_img_url, u_room_userImg, userImgOptions);   
               loader.displayImage("http://54.178.158.103/images/" + rData.room_img_url, u_room_img, options);
            }
            
         }

         @Override
         public void onFail(int code) {
            Toast.makeText(UserRoomInfoActivity.this, "fail to get data in UserRoomInfo", Toast.LENGTH_SHORT).show();
         }
      });
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      switch(item.getItemId()){
      case android.R.id.home:
         startActivity(new Intent(UserRoomInfoActivity.this, MainActivity.class));
      }
      return super.onOptionsItemSelected(item);
   }
   @Override
   protected void onResume() {
      super.onResume();
      initData();
//      getNewItemData()
   }
   public void getNewItemData(){
      iAdapter.clear();
      NetworkManager.getInstance().getRoomInfo(UserRoomInfoActivity.this, myUserNum, rNum, new NetworkManager.OnResultListener<UserRoomItemsResult>() {

         @Override
         public void onSuccess(UserRoomItemsResult result) {
            iAdapter.replace(result.result.items);

         }

         @Override
         public void onFail(int code) {
            // TODO Auto-generated method stub

         }
      });
   }
}