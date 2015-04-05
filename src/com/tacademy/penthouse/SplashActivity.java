package com.tacademy.penthouse;


import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Request.GraphUserCallback;
import com.facebook.Session.StatusCallback;
import com.facebook.model.GraphUser;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UserNumResult;
import com.tacademy.penthouse.entity.UserRoomsResult;
import com.tacademy.penthouse.entity.UsersResult;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.manager.PropertyManager;
import com.tacademy.penthouse.manager.UserManager;

public class SplashActivity extends Activity {
   public static int LOGIN_TYPE = 0;
   // 0 = Not Login // 1 = Login // 2 = FaceBook
   private static final int DELAY_TIME = 2000;
   private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
   private static final String SENDER_ID = "237627063630";
   String userId;
   String password;
   int autoLogin;
   int userNum;
   int loginOk = 0;
   String regId;
   UserManager uManager= UserManager.getInstance();
   ArrayList<Integer> followUsers = new ArrayList<Integer>();
   UserData uData;
   String token = PropertyManager.getInstance().getToken();
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState); 
      dispatch();
      setContentView(R.layout.activity_splash);
      uManager.setuData(null);
      uManager.setFollowUsers(null);
      uManager.setUserId(null);
      uManager.setUserNum(0);
      uManager.setPassword(null);
      if(PropertyManager.getInstance().getWalkThroughRead() == 0){
         PropertyManager.getInstance().setAutoLogin(0);
      }
      
      if (checkPlayServices()) {
         String regId = PropertyManager.getInstance().getRegistrationId();
         if (regId.equals("")) {
            registerInBackground();
         } else {
            
         }
      } else {
         finish();
      }
      
      //Auto Sign in
      userId = PropertyManager.getInstance().getUserId();
      password = PropertyManager.getInstance().getPassword();
      autoLogin = PropertyManager.getInstance().getAutoLogin();
      if(autoLogin == 1){
         if(userId != null && password != null){
            Toast.makeText(SplashActivity.this, "로그인 중입니다.", Toast.LENGTH_SHORT).show();
            LOGIN_TYPE = 1;
            regId = PropertyManager.getInstance().getRegistrationId();
            NetworkManager.getInstance().postLoginData(SplashActivity.this, userId, password,regId, new NetworkManager.OnResultListener<UserNumResult>() {

               @Override
               public void onSuccess(UserNumResult result) {
                  if(result.success == 1){
                     userNum = result.result.user_num;
                     loginOk = 1;
                     uManager.setUserId(userId);
                     uManager.setPassword(password);
                     uManager.setUserNum(userNum);
                     setUserData(userNum);
                     //setFollowList(userNum);
                  }
               }

               @Override
               public void onFail(int code) {
                  // TODO Auto-generated method stub
                  finish();
               }
            });
         } else if(token != null) {
            LOGIN_TYPE = 2;
            Toast.makeText(SplashActivity.this, "로그인 중입니다.", Toast.LENGTH_SHORT).show();
            Session.openActiveSession(SplashActivity.this, true, new StatusCallback() {

               @Override
               public void call(Session session, SessionState state, Exception exception) {
                  if (session.isOpened()) {
                     final String token = session.getAccessToken();
                     Request.newMeRequest(session, new GraphUserCallback() {

                        @Override
                        public void onCompleted(GraphUser user, Response response) {
                           if (user != null) {
                              //String id = user.getId();
                              // NetworkModel... id, token
                              regId = PropertyManager.getInstance().getRegistrationId();
                              NetworkManager.getInstance().postFacebookLogin(SplashActivity.this, token,regId, new NetworkManager.OnResultListener<UserNumResult>() {

                                 @Override
                                 public void onSuccess(UserNumResult result) {
                                    if(result.success == 1){
                                       userNum = result.result.user_num;
                                       loginOk = 1;

                                       if(loginOk == 1){
                                          
                                          UserManager.getInstance().setUserNum(userNum);
                                          setUserData(result.result.user_num);
                                       }

                                    }

                                 }

                                 @Override
                                 public void onFail(int code) {
                                    Toast.makeText(SplashActivity.this, "페이스북 로그인 정보가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                                    finish();
                                 }
                              });

                           }
                        }
                     }).executeAsync();
                  }
               }
            });
         }else{
            new Handler().postDelayed(new Runnable() {

               @Override
               public void run() {
                  int start_wt;
                  start_wt = PropertyManager.getInstance().getWalkThroughRead();
                  if(start_wt == 0){
                     startActivity(new Intent(SplashActivity.this,WalkThroughActivity.class));
                  }else{
                     startActivity(new Intent(SplashActivity.this,MainActivity.class));
                  }
                  overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                  finish();
               }
            }, DELAY_TIME);
         //   Toast.makeText(SplashActivity.this, "로그인에 실패하였습니다. 다시 실행해주세요", Toast.LENGTH_SHORT).show();
            PropertyManager.getInstance().setAutoLogin(0);
         }
      }else{
         new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
               int start_wt;
               start_wt = PropertyManager.getInstance().getWalkThroughRead();
               if(start_wt == 0){
                  startActivity(new Intent(SplashActivity.this,WalkThroughActivity.class));
               }else{
                  startActivity(new Intent(SplashActivity.this,MainActivity.class));
               }
               overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
               finish();
            }
         }, DELAY_TIME);
      }
    }
   public UserData setUserData(int userNum){
       NetworkManager.getInstance().getUserInfoData(SplashActivity.this, userNum, new NetworkManager.OnResultListener<UserRoomsResult>() {

         @Override
         public void onSuccess(UserRoomsResult result) {
            if(result.result != null){
               uData = result.result.user;
               uManager.setuData(uData);
               setFollowList(uData.user_num);
            }
         }

         @Override
         public void onFail(int code) {
            // TODO Auto-generated method stub
            Toast.makeText(SplashActivity.this, "Fail to get UserData" + code, Toast.LENGTH_SHORT).show();
            
         }
          
      });
       return uData;
    }
   
   public void setFollowList(int userNum){
      NetworkManager.getInstance().getFollowingResultData(SplashActivity.this, userNum, new NetworkManager.OnResultListener<UsersResult>() {

         @Override
         public void onSuccess(UsersResult result) {
            if(result.result != null){
               for(int i = 0; i < result.result.users.size(); i++){
                  followUsers.add(result.result.users.get(i).user_num);
               }
               uManager.setFollowUsers(followUsers);
            }
            int start_wt;
            start_wt = PropertyManager.getInstance().getWalkThroughRead();
            if(start_wt == 0){
               startActivity(new Intent(SplashActivity.this,WalkThroughActivity.class));
            }else{
               startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
         }

         @Override
         public void onFail(int code) {
            // TODO Auto-generated method stub
            
         }
      });
   }
   private void dispatch() {
        Uri uri = getIntent().getData();
        if(uri != null){
            String target = uri.getQueryParameter("target");
            if(target != null && target.equals("main"))
                startActivity(new Intent(this, MainActivity.class));
        }
    }
   
   private void registerInBackground() {
       new AsyncTask<Void,Integer,String>() {
           @Override
           protected String doInBackground(Void... params) {
               String msg = "";
               GoogleCloudMessaging gcm = null;
               String regid;
               try {
                   if (gcm == null) {
                       gcm = GoogleCloudMessaging.getInstance(SplashActivity.this);
                   }
                   regid = gcm.register(SENDER_ID);
                   PropertyManager.getInstance().setRegistrationId(regid);
               } catch (IOException ex) {
               }
               return msg;
           }
           
           protected void onPostExecute(String result) {
//              Toast.makeText(SplashActivity.this, "register regId", Toast.LENGTH_SHORT).show();
           }
       }.execute(null, null, null);
   }   
   
   private boolean checkPlayServices() {
       int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
       if (resultCode != ConnectionResult.SUCCESS) {
           if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
               GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                       PLAY_SERVICES_RESOLUTION_REQUEST).show();
           } else {
               finish();
           }
           return false;
       }
       return true;
   }
   
}
 