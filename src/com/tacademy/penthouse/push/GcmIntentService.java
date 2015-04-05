package com.tacademy.penthouse.push;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.tacademy.penthouse.MainActivity;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.UserRoomsResult;
import com.tacademy.penthouse.house.HouseActivity;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.manager.UserManager;

public class GcmIntentService extends IntentService {
	private static final String TAG="GcmIntengService";
	public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;
    int fUser;
    int tUser;
    String toUserName;
    String fromUserName;
    Handler mHandler = new Handler();
    
    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) {  // has effect of unparcelling Bundle
            /*
             * Filter messages based on message type. Since it is likely that GCM
             * will be extended in the future with new message types, just ignore
             * any message types you're not interested in, or that you don't
             * recognize.
             */
            if (GoogleCloudMessaging.
                    MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                //sendNotification("Send error: " + extras.toString());
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_DELETED.equals(messageType)) {
                //sendNotification("Deleted messages on server: " +
                  //      extras.toString());
            // If it's a regular GCM message, do some work.
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                // This loop represents the service doing some work.
            	String fromUser = intent.getStringExtra("from_user");
            	String toUser = intent.getStringExtra("to_user");
            	fUser = Integer.parseInt(fromUser);
            	tUser = Integer.parseInt(toUser);
            	mHandler.post(new Runnable() {
					
					@Override
					public void run() {
					NetworkManager.getInstance().getUserInfoData(GcmIntentService.this, fUser, new NetworkManager.OnResultListener<UserRoomsResult>() {

						@Override
						public void onSuccess(UserRoomsResult result) {
							fromUserName = result.result.user.user_nickname;
							getUser(tUser);
						}
						@Override
						public void onFail(int code) {
							// TODO Auto-generated method stub
							
						}
					});
					
					}
				});
                //sendNotification(fromUser,toUser);
            }
        }
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    // Put the message into a notification and post it.
    // This is just one simple example of what you might choose to do with
    // a GCM message.
    public void getUser(int toUser){
    	NetworkManager.getInstance().getUserInfoData(GcmIntentService.this, tUser, new NetworkManager.OnResultListener<UserRoomsResult>(){

			@Override
			public void onSuccess(UserRoomsResult result) {
				toUserName = result.result.user.user_nickname;
				if(result.result.user.alert == 1){
					sendNotification(fromUserName, toUserName);
				}
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
    		
    	});
    }
    private void sendNotification(String fromUser, String toUser) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent i = new Intent(this, HouseActivity.class);
        i.putExtra("uData", fUser);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                i, 0);
        
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.icon_launcher)//HousePicæ∆¿Ãƒ‹¿∏∑Œ~
        .setContentTitle("HousePic")
        .setStyle(new NotificationCompat.BigTextStyle()
        .bigText(fromUser+"¥‘¿Ã " + toUser + "¥‘¿ª Follow «’¥œ¥Ÿ"))
        .setDefaults(Notification.DEFAULT_ALL)
        .setAutoCancel(true)
        .setContentText(fromUser+"¥‘¿Ã " + toUser + "¥‘¿ª Follow «’¥œ¥Ÿ");

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}

