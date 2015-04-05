package com.tacademy.penthouse;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class MyApplication extends Application {

	private static Context mContext;
	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
		initImageLoader(this);
	}
	
	public static Context getContext(){
		return mContext;
	}
	
	public static void initImageLoader(Context context){
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		//.showImageOnLoading(Defalut Image (Drawable) )
		//.showImageForEmptyUri()
		//.showImageOnFail()
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.displayer(new RoundedBitmapDisplayer(100))
		.build();
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
		.threadPriority(Thread.NORM_PRIORITY - 2)
		.denyCacheImageMultipleSizesInMemory()
		.discCacheFileNameGenerator(new Md5FileNameGenerator())
		.tasksProcessingOrder(QueueProcessingType.LIFO)
		.writeDebugLogs() // Remove for release app
		.defaultDisplayImageOptions(options)
		.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
}
