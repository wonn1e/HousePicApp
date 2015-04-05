package com.tacademy.penthouse.room;

import java.text.DecimalFormat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ItemData;

public class ItemView extends FrameLayout{

	public ItemView(Context context) {
		super(context);
		init();
	}
	
	public interface OnItemLikeClickListener{
		public void onLikeClick(View v, ItemData i);
	}
	
	OnItemLikeClickListener mListener;
	public void setOnItemLikeClickListener(OnItemLikeClickListener l){
		mListener = l;
	}

	ItemData iData;
	ImageView item_img;
	TextView item_name;
	ImageView item_like;
	TextView itemPrice;

	ImageLoader loader;
	DisplayImageOptions options;
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.item_view, this);
		item_img = (ImageView)findViewById(R.id.item_img);
		item_name = (TextView)findViewById(R.id.item_name);
		item_like = (ImageView)findViewById(R.id.item_like);
		itemPrice = (TextView)findViewById(R.id.itemPrice);	
		loader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.placeholder)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.img_sold_out)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.build();
		
		item_like.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//여기서 처리하나요~~~~~?
				//좋아요 Count ++;
				
				if(mListener != null){
					mListener.onLikeClick(ItemView.this, iData);
				}
			}
		});
	}
	
	
	public void setItemData(ItemData data){
		iData = data;
		//첫번째 이미지를 나오게 한다.
		//item_img.setImageResource(iData.item_img[0]);
		item_name.setText(data.item_name);
		DecimalFormat df = new DecimalFormat("#,##0");
		itemPrice.setText(""+ df.format(data.price) + "won");
		loader.displayImage(data.item_img_url[0], item_img,options);
		if(data.islike == 0){
			item_like.setImageResource(R.drawable.ic_info_pick_off);
		}else if(data.islike == 1){
			item_like.setImageResource(R.drawable.ic_info_pick_on);
		}
	}
}