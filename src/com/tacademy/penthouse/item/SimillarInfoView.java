package com.tacademy.penthouse.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;

public class SimillarInfoView extends FrameLayout{

	public SimillarInfoView(Context context) {
		super(context);
		init();
	}
	ItemData iData;
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
		.showImageOnFail(R.drawable.img_sold_out)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		//.displayer(new RoundedBitmapDisplayer(100))
		.build();
		
	}

	public void setItemImageData(ItemData data){
		iData = data;
		for(int i = 0; i < data.item_img_url.length ; i++){
			//item_img.setImageResource(data.item_img_url[i]);
			loader.displayImage(data.item_img_url[i], item_img,options);
			
		}
		
	}
}
