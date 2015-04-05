package com.tacademy.penthouse.item;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;


public class ItemFragment extends Fragment{

	ItemData iData;
	ImageView item_img;
	TextView tv;
	int position;
	String resURL;
	boolean isLiked; 
	ImageLoader loader;
	DisplayImageOptions options;
	
	public interface OnShowItemLikeClickedListener{
		public void onShowItemLikeClicked(boolean liked);
	}
	
	OnShowItemLikeClickedListener mListener;
	
	public void setOnShowItemLikeClickedListener(OnShowItemLikeClickedListener l){
		mListener = l;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getArguments();
		resURL = b.getString("img");
		isLiked = b.getBoolean("item_like");
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.item_fragment_layout, container,false);
		item_img = (ImageView)v.findViewById(R.id.item_img);
//		item_img.setImageResource(resId);
		loader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.placeholder)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.img_sold_out)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
	//	.displayer(new RoundedBitmapDisplayer(100))
		.build();
		
		loader.displayImage(resURL,item_img,options);
		
		return v;
		
	}
}
