package com.tacademy.penthouse;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tacademy.penthouse.entity.ItemData;


public class MDItemView extends FrameLayout{

	public MDItemView(Context context) {
		super(context);
		init();
	}

	public interface OnItemDataClickListener {
		public void onItemClick(View v,ItemData data);
	}

	public interface OnItemDataLikeClickListener{
		public void onItemDataLikeClick(View v, ItemData data);
	}

	OnItemDataClickListener mListener;
	public void setOnItemDataClickListener(OnItemDataClickListener listener) {
		mListener = listener;
	}

	OnItemDataLikeClickListener lListener;
	public void setOnItemDataLikeClickListener(OnItemDataLikeClickListener listener){
		lListener = listener;
	}



	ItemData iData;
	int iDataNum;
	ImageView item_img, item_like;
	TextView item_name;
	TextView item_price;
	//	TextView md_cnt;
	//	TextView md_more;
	ImageLoader loader;
	DisplayImageOptions options;
	ArrayList<ItemData> items = new ArrayList<ItemData>();
	MDRoomAdapter mAdapter;
	int itemCnt = 0;

	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.main_md_item_view, this);

		item_img = (ImageView)findViewById(R.id.item_img);
		item_like = (ImageView)findViewById(R.id.item_like);
		item_name = (TextView)findViewById(R.id.item_name);
		item_price = (TextView)findViewById(R.id.item_price);
		//		md_cnt = (TextView)findViewById(R.id.md_cnt);
		//		md_more = (TextView)findViewById(R.id.md_more);
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
				if(lListener != null){
					lListener.onItemDataLikeClick(MDItemView.this, iData);
				}
			}
		});

	}

	public void setData(ItemData data){
		iData = data;
		if(data.item_num == 0){
			itemCnt = 0;
			item_img.setVisibility(View.GONE);
			item_name.setVisibility(View.GONE);
			item_price.setVisibility(View.GONE);
			item_like.setVisibility(View.GONE);
			//			md_cnt.setVisibility(View.VISIBLE);
			//			md_more.setVisibility(View.VISIBLE);

		}else{
			item_img.setVisibility(View.VISIBLE);
			item_name.setVisibility(View.VISIBLE);
			item_price.setVisibility(View.VISIBLE);
			item_like.setVisibility(View.VISIBLE);
			//			md_cnt.setVisibility(View.GONE);
			//			md_more.setVisibility(View.GONE);
			loader.displayImage(data.item_img_url[0], item_img, options);
			item_name.setText(data.item_name);
			DecimalFormat df = new DecimalFormat("#,##0");
			item_price.setText(""+ df.format(data.price) + "won");
			
			
			if(data.islike == 0){
				item_like.setImageResource(R.drawable.ic_home_pick_on);
			}else if(data.islike == 1){
				item_like.setImageResource(R.drawable.ic_home_pick_off);
			}
		}
	}


}
