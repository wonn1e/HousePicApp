package com.tacademy.penthouse.house;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tacademy.penthouse.MainActivity;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UserRoomsResult;
import com.tacademy.penthouse.itemlike.CreateNewRoomActivity;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.manager.UserManager;
import com.tacademy.penthouse.member.LogInActivity;
import com.tacademy.penthouse.neighbor.NeighborListActivity;
import com.tacademy.penthouse.ranking.RankingFragment;
import com.tacademy.penthouse.room.MyRoomInfoActivity;
import com.tacademy.penthouse.room.UserRoomInfoActivity;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;

public class MyHouseFragment extends Fragment {

   public static final String TAG_NICKNAME = "nickname";
   public static final String TAG_HOUSENAME = "housename";
   public static final String TAG_HOUSEINTRO = "houseintro";
   public static final int REQUEST_CODE_EDITING_USER = 0;
   public static final int REQUEST_CODE_EDITING_HOUSE = 1;
   public static final int REQEUST_MAKE_NEW_ROOM = 2;
   
   public static final int REQUEST_CODE_EDIT = 30;
   public static final String PARAM_USER_DATA = "uData";
   public static final String PARAM_MY_DATA = "myData";
  
   GridView house_room_gridView;   
   ImageView editBtn;
   TextView houseName;
   File mSavedFile;
   UserData uData;
   UserData userData;
   UserData myData = UserManager.getInstance().getuData();
   int uDataNum;
   ArrayList<RoomData> rooms = new ArrayList<RoomData>();
   MyRoomAdapter myRoomAdapter;
   UserRoomsResult userRoomsResult;
   boolean isClicked;
   int type;
   String pw = UserManager.getInstance().getPassword();


//   private void setClicked(boolean clicked){
//      if(isClicked != clicked){
//         isClicked = clicked;
//         drawIsClick();
//      }
//      else{
//         isClicked = !clicked;
//         drawIsClick();
//      }
//   }

//   private void drawIsClick(){
//      if(isClicked){
//         editBtn.setImageResource(R.drawable.ic_mypage_done);
//      }else{
//         editBtn.setImageResource(R.drawable.ic_mypage_edit);
//      }
//   }

   @Override
   public View onCreateView(LayoutInflater inflater,
         @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View v = inflater.inflate(R.layout.house_layout, container, false);
      editBtn = (ImageView)v.findViewById(R.id.editHouseBtn);

      ((ActionBarActivity)getActivity()).getSupportActionBar().hide();
      myData = UserManager.getInstance().getuData();
      if(myData != null){
         uDataNum = myData.user_num;
      }
      ImageView homeHouse = (ImageView)v.findViewById(R.id.home_house);
      houseName = (TextView)v.findViewById(R.id.myHouseName);
      homeHouse.setImageResource(R.drawable.ic_gnb_menu);
      Bundle b = getArguments();
      if(b != null){
         type = b.getInt(RankingFragment.SHOW_TYPE);
      }
      if(type == 1){
         //homeHouse.setImageResource(resId);
      }

      editBtn.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View v) {
        	 
        	 Intent i = new Intent(getActivity(), EditMyHouseActivity.class);
        	 i.putExtra("uData", myData);
        	 startActivityForResult(i, REQUEST_CODE_EDIT);
         }
      });

      homeHouse.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View v) {
            if(type == 1){
               Intent i = new Intent(getActivity(), MainActivity.class);
               i.putExtra(MainActivity.MENU_TYPE, 1);
               startActivity(i);
            }else{
               ((MainActivity)getActivity()).getSlidingMenu().toggle();
            }

         }
      });
      house_room_gridView = (GridView)v.findViewById(R.id.header_grid_view);
      ((StickyGridHeadersGridView)house_room_gridView).setAreHeadersSticky(false);
      ((StickyGridHeadersGridView)house_room_gridView).setPadding(0, 0, 0, 0);
      house_room_gridView.setOnItemClickListener(new OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view,
               int position, long id) {
            if(position != userRoomsResult.result.rooms.size()){
               Intent i = new Intent(getActivity(), UserRoomInfoActivity.class);
               i.putExtra("rData", userRoomsResult.result.rooms.get(position).room_num);
               startActivity(i);
            }
         }
      });
      myRoomAdapter = new MyRoomAdapter(getActivity());
      myRoomAdapter.setOnAdapterDeleteClickListener(new MyRoomAdapter.OnAdapterDeleteClickListener() {

         @Override
         public void onDeleteClick(View v, RoomData room) {
            final RoomData deleteRoom = room;

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("방 삭제")
            .setMessage("방 삭제시 해당 방에 있는 아이템이 모두 함께 삭제됩니다. 방을 삭제하시겠습니까?");
            builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {

               @Override
               public void onClick(DialogInterface dialog, int which) {
                  NetworkManager.getInstance().postDeleteRoom(getActivity(), myData.user_num, deleteRoom.room_num, 
                        new NetworkManager.OnResultListener<ResultData>() {

                     @Override
                     public void onSuccess(ResultData result) {
                        myRoomAdapter.clear();
                        initData();
                     }
                     @Override
                     public void onFail(int code) {
                        if(getActivity() != null)
                           Toast.makeText(getActivity(), "방 삭제 실패", Toast.LENGTH_SHORT).show();
                     }
                  });
               }
            });
            builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {

               @Override
               public void onClick(DialogInterface dialog, int which) {
                  // TODO Auto-generated method stub

               }
            });   

            AlertDialog dialog = builder.create();
            dialog.show();
         }
      });

      myRoomAdapter.setOnAdapterEditClickListener(new MyRoomAdapter.OnAdapterEditClickListener() {
         @Override
         public void onEditClick(View v, UserData uData) {
         }


//         @Override
//         public void onNickNameEditClick(View v, UserData uData) {
//            EditUserNickname f = new EditUserNickname();
//            f.setOnReceiveMessageListener(new EditUserNickname.OnReceiveMessageListener() {
//
//               @Override
//               public void onReceiveMessage(int intMsg) {
//                  if(intMsg == 1)
//                     myRoomAdapter.clear();
//                  initData();         
//               }
//            });
//            f.show(getFragmentManager(), TAG_NICKNAME);
//         }
//
//         @Override
//         public void onHouseIntroEditClick(View v, UserData uData) {
//            EditHouseIntro f = new EditHouseIntro();
//            f.setOnReceiveHouseintroListener(new EditHouseIntro.OnReceiveHouseintroListener() {
//
//               @Override
//               public void onReceiveHousename(int i) {
//                  if(i == 1){
//                     myRoomAdapter.clear();
//                     initData();
//                  }
//               }
//            });
//            f.show(getFragmentManager(), TAG_HOUSEINTRO);
//         }
//
//         @Override
//         public void onUserImgEditClick(View v, UserData uData) {
//
//            ImgDialogFragment imgDialog = new ImgDialogFragment();
//            imgDialog.setOnItemDataClickListener(new ImgDialogFragment.OnItemDataClickListener() {
//
//               @Override
//               public void bringGallery(int galleryResult) {
//                  Intent i = new Intent(
//                        Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                  i.putExtra("aspectX", 1);
//                  i.putExtra("aspectY", 1);
//                  i.setType("image/*");
//                  i.putExtra("crop", "true");
//                  i.putExtra("output", getTempUri());
//                  i.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());            
//                  startActivityForResult(i, REQUEST_CODE_EDITING_USER);               
//               }
//
//               @Override
//               public void bringCamera(int cameraResult) {
//
//                  Intent i = new Intent(
//                        MediaStore.ACTION_IMAGE_CAPTURE);
//                  i.putExtra("aspectX", 1);
//                  i.putExtra("aspectY", 1);
//                  i.putExtra("crop", "true");
//                  i.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
//                  i.putExtra("outputFormat",
//                        Bitmap.CompressFormat.JPEG.toString());            
//                  startActivityForResult(i, REQUEST_CODE_EDITING_USER); 
//               }
//
//               @Override
//               public void deletePicture(int deleteResult) {
//                  NetworkManager.getInstance().postEditMyData(getActivity(), userData.user_num, userData.user_nickname,
//                        "mypage_default.jpg", userData.house_name, null, userData.house_intro, userData.alert, new NetworkManager.OnResultListener<ResultData>() {
//
//                     @Override
//                     public void onSuccess(ResultData result) {
//                        if(getActivity() != null){
//
//                        //   Toast.makeText(getActivity(), "유저 이미지 삭제", Toast.LENGTH_SHORT).show();
//                        }
//                     }
//
//                     @Override
//                     public void onFail(int code) {
//                        if(getActivity() != null)
//                           Toast.makeText(getActivity(), "내 사진을 삭제하는데 실패했습니다.", Toast.LENGTH_SHORT).show();
//                     }
//                  }); 
//               }
//               
//            
//            });
//            imgDialog.show(getFragmentManager(),"dialog");
//         }
//         @Override
//         public void onHouseImgEditClick(View v, UserData uData) {
//            ImgDialogFragment imgDialog = new ImgDialogFragment();
//            imgDialog.setOnItemDataClickListener(new ImgDialogFragment.OnItemDataClickListener() {
//
//               @Override
//               public void bringGallery(int galleryResult) {
//                  Intent i = new Intent(
//                        Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                  
//                  i.putExtra("aspectX", 12);
//                  i.putExtra("aspectY", 7);
//                  i.setType("image/*");
//                  i.putExtra("crop", "true");
//                  i.putExtra("output", getTempUri());
//                  i.putExtra("outputFormat",
//                        Bitmap.CompressFormat.JPEG.toString());            
//                  startActivityForResult(i, REQUEST_CODE_EDITING_HOUSE);               
//               }
//
//               @Override
//               public void bringCamera(int cameraResult) {
//
//                  Intent i = new Intent(
//                        MediaStore.ACTION_IMAGE_CAPTURE);
//                  i.putExtra("aspectX", 12);
//                  i.putExtra("aspectY", 7);
//                  i.putExtra("crop", "true");
//                  i.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
//                  i.putExtra("outputFormat",
//                        Bitmap.CompressFormat.JPEG.toString());            
//                  startActivityForResult(i, REQUEST_CODE_EDITING_HOUSE); 
//               }
//
//               @Override
//               public void deletePicture(int deleteResult) {
//                  NetworkManager.getInstance().postEditMyData(getActivity(), userData.user_num, userData.user_nickname,
//                        null, userData.house_name, "myhouse_default.jpg", userData.house_intro, userData.alert, new NetworkManager.OnResultListener<ResultData>() {
//
//                     @Override
//                     public void onSuccess(ResultData result) {
//                        if(getActivity() != null){
//                           
//                           
//                        }
//                     }
//
//                     @Override
//                     public void onFail(int code) {
//                        if(getActivity() != null)
//                           Toast.makeText(getActivity(), "내 사진을 삭제하는데 실패했습니다.", Toast.LENGTH_SHORT).show();
//                     }
//                  });            
//
//               }
//            });
//            imgDialog.show(getFragmentManager(),"dialog");
//         }
         
         @Override
         public void onFollowingClick(View v, UserData uData) {
            Intent i = new Intent(getActivity(), NeighborListActivity.class);
            i.putExtra("uData", uDataNum);//uData);
            i.putExtra(NeighborListActivity.PARAM_CURRENT_TAB, 0);//uData);
            startActivity(i);
         }

         @Override
         public void onFollowClick(View v, UserData uData) {
            //Follower ! Button
            Intent i = new Intent(getActivity(), NeighborListActivity.class);
            i.putExtra(NeighborListActivity.PARAM_CURRENT_TAB, 1);//uData);
            i.putExtra("uData", uDataNum);//uData);
            startActivity(i);            
         }


      });

      house_room_gridView.setOnItemClickListener(new OnItemClickListener() {

         @Override
         public void onItemClick(AdapterView<?> parent, View view,
               int position, long id) {
            //when it's + img

            if(rooms.get(position).room_num == 0){
               Intent i = new Intent(getActivity(), CreateNewRoomActivity.class);
               //pass on myData (UserData) 
               i.putExtra("myData", userData);
               startActivityForResult(i, REQEUST_MAKE_NEW_ROOM);

            }else{

               Intent i = new Intent(getActivity(), MyRoomInfoActivity.class);
               i.putExtra("rData", rooms.get(position).room_num);
               startActivity(i);
            }
         }            
      });

      return v;
   }
   private Uri getTempUri() {
      mSavedFile = new File(Environment.getExternalStorageDirectory(),
            "temp_" + System.currentTimeMillis() / 1000 + ".jpg");
      return Uri.fromFile(mSavedFile);
   }

   @Override
   public void onSaveInstanceState(Bundle outState){
      super.onSaveInstanceState(outState);
      if (mSavedFile != null) {
         outState.putString("filename", mSavedFile.getAbsolutePath());
      }
   }

   @Override
   public void onResume() {
      super.onResume();
      setUserData(myData.user_num);
      if(uData!=null)
         myData = uData;
      //getNewData();
      myRoomAdapter.clear();
      initData();
   }
   @Override
   public void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
//      if(requestCode ==  REQUEST_CODE_EDITING_USER && resultCode == Activity.RESULT_OK){	//user img
//         myRoomAdapter.updateUserImg(userData, mSavedFile.getAbsolutePath());
//         String userImgURL = (mSavedFile == null) ? null : mSavedFile.getAbsolutePath();
//         NetworkManager.getInstance().postEditMyData(getActivity(), userData.user_num, userData.user_nickname,
//               userImgURL, userData.house_name, null, userData.house_intro, userData.alert, new NetworkManager.OnResultListener<ResultData>() {
//
//            @Override
//            public void onSuccess(ResultData result) {
//               getNewData();
//            }
//
//            @Override
//            public void onFail(int code) {
//               if(getActivity() != null)
//                  Toast.makeText(getActivity(), "내 사진을 업로드하는데 실패했습니다.", Toast.LENGTH_SHORT).show();
//            }
//         });
//
//      }else if(requestCode ==  REQUEST_CODE_EDITING_HOUSE && resultCode == Activity.RESULT_OK){	//house img
//         String house_img_url = (mSavedFile == null) ? null :  mSavedFile.getAbsolutePath();
//         NetworkManager.getInstance().postEditMyData(getActivity(), userData.user_num, userData.user_nickname,
//               null, userData.house_name, house_img_url, userData.house_intro, userData.alert, new NetworkManager.OnResultListener<ResultData>() {
//
//            @Override
//            public void onSuccess(ResultData result) {
//               if(getActivity() != null){
//                  getNewData();
//               }
//            }
//
//            @Override
//            public void onFail(int code) {
//               if(getActivity() != null)
//                  Toast.makeText(getActivity(), "집 사진을 업로드하는데 실패했습니다.", Toast.LENGTH_SHORT).show();
//            }
//         });
      if(requestCode == REQUEST_CODE_EDIT && resultCode == Activity.RESULT_OK){
    	  
      }else if(requestCode == REQEUST_MAKE_NEW_ROOM && resultCode == Activity.RESULT_OK){
    	  myRoomAdapter.clear();
    	  initData();
      }
   }

   public UserData setUserData(int userNum){
      NetworkManager.getInstance().getUserInfoData(getActivity(), userNum, new NetworkManager.OnResultListener<UserRoomsResult>() {

         @Override
         public void onSuccess(UserRoomsResult result) {
            if(result.result != null){
               uData = result.result.user;
               houseName.setText(uData.house_name);
               UserManager.getInstance().setuData(uData);
            }else{
               uData = UserManager.getInstance().getuData();
            }
         }

         @Override
         public void onFail(int code) {
            // TODO Auto-generated method stub
            if(getActivity() != null)
               Toast.makeText(getActivity(), "Fail to get UserData" + code, Toast.LENGTH_SHORT).show();

         }

      });
      return uData;
   }

   private void initData(){

      NetworkManager.getInstance().getUserInfoData(getActivity(), uDataNum, new NetworkManager.OnResultListener<UserRoomsResult>() {

         @Override
         public void onSuccess(UserRoomsResult result) {
            if(result.result == null){
               startActivity(new Intent(getActivity(), LogInActivity.class));
            }else{
               userData = result.result.user;
               houseName.setText(userData.house_name);
               rooms = result.result.rooms;
               userRoomsResult = result;
               house_room_gridView.setAdapter(myRoomAdapter);
               myRoomAdapter.put(result.result);
               rooms = myRoomAdapter.set();
            }
         }

         @Override
         public void onFail(int code) {
            if(getActivity() != null)
               Toast.makeText(getActivity(), "fail in ItemInfo", Toast.LENGTH_SHORT).show();
         }
      });
   }
   @Override
   public void onPause() {
      super.onPause();
//      isClicked = true;
//      drawIsClick();
      
   }

   public void getNewData(){
      NetworkManager.getInstance().getUserInfoData(getActivity(), uDataNum, new NetworkManager.OnResultListener<UserRoomsResult>() {

         @Override
         public void onSuccess(UserRoomsResult result) {
            userData = result.result.user;
            UserManager.getInstance().setuData(userData);
            houseName.setText(userData.house_name);
            rooms = result.result.rooms;
            userRoomsResult = result;
            house_room_gridView.setAdapter(myRoomAdapter);
            myRoomAdapter.replace(result.result);
            rooms = myRoomAdapter.set();

         }

         @Override
         public void onFail(int code) {
            if(getActivity() != null)
               Toast.makeText(getActivity(), "fail in ItemInfo", Toast.LENGTH_SHORT).show();
         }
      });
   }
}