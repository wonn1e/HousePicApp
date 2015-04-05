package com.tacademy.penthouse.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tacademy.penthouse.R;


public class ItemRecommandView extends FrameLayout{

	public ItemRecommandView(Context context) {
		super(context);
		init();
		// TODO Auto-generated constructor stub
	}
	String Image_URL;
	ImageView item_img;


	ImageLoader loader;
	DisplayImageOptions options;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.item_img_view, this);
		item_img = (ImageView)findViewById(R.id.item_img);
		loader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.placeholder)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		//.displayer(new RoundedBitmapDisplayer(100))
		.build();

		
	}
	
	public void setItemRecommandData(String image_URL){
//		iResId = resId;
//		item_img.setImageResource(resId);
		
		Image_URL = image_URL;
		loader.displayImage(image_URL,item_img ,options);
		
	}

}
