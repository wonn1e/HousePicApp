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

public class MyItemView extends FrameLayout{

	public MyItemView(Context context) {
		super(context);
		init();
	}
	
	public interface OnItemLikeClickListener{
		public void onLikeClick(View v, ItemData i);
	}
	public interface OnItemMoveClickListener{
		public void onMoveClick(View v, ItemData i);
	}
	
	OnItemLikeClickListener lListener;
	OnItemMoveClickListener mListener;
	public void setOnItemLikeClickListener(OnItemLikeClickListener l){
		lListener = l;
	}
	public void setOnItemMoveClickListener(OnItemMoveClickListener m){
		mListener = m;
	}
	

	ItemData iData;
	ImageView my_item_img;
	TextView my_item_name;
	ImageView my_item_like;
	TextView my_item_price, itemMove;
	//ImageView my_item_move;
	ImageLoader loader;
	DisplayImageOptions options;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.my_item_view, this);
		my_item_img = (ImageView)findViewById(R.id.my_item_img);
		my_item_name = (TextView)findViewById(R.id.my_item_name);
		my_item_like = (ImageView)findViewById(R.id.my_item_like);
		my_item_price = (TextView)findViewById(R.id.my_item_price);	
		itemMove = (TextView)findViewById(R.id.moveItem);
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
		itemMove.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onMoveClick(MyItemView.this, iData);
				}
			}
		});
		
		
		
		my_item_like.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//좋아요 Count ++;
				
				if(lListener != null){
					lListener.onLikeClick(MyItemView.this, iData);
				}
			}
		});
	}
	
	
	public void setItemData(ItemData data){
		iData = data;
		//첫번째 이미지를 나오게 한다.
		my_item_name.setText(data.item_name);
		DecimalFormat df = new DecimalFormat("#,##0");
		my_item_price.setText(""+ df.format(data.price) + "won");
		loader.displayImage(data.item_img_url[0], my_item_img,options);
		
		if(data.islike == 0){
			my_item_like.setImageResource(R.drawable.ic_info_pick_off);
		}else if(data.islike == 1){
			my_item_like.setImageResource(R.drawable.ic_info_pick_on);
		}
		
		//ItemNum이 0 일때 처리해줄것
	}
}