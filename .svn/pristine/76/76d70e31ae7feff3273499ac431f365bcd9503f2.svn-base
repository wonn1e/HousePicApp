package com.tacademy.penthouse.ranking;

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

public class RankItemView extends FrameLayout {

	public RankItemView(Context context) {
		super(context);
		init();
	}
	
	public interface OnPopularItemLikeListener{
		public void onPopularItemLikeListener(View v, ItemData iData);
	}
	
	OnPopularItemLikeListener likeListener;
	public void setOnPopularItemLikeListener(OnPopularItemLikeListener l){
		likeListener  = l;
	}
	
	ItemData iData;
	ImageView item_img;
	ImageView item_rank;
	TextView item_name;
	TextView item_price;
	TextView item_like_num;
	ImageView item_like;
	ImageLoader loader;
	DisplayImageOptions options;

	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.rank_item_view, this);
		item_img = (ImageView)findViewById(R.id.itemImgRank);
		item_rank = (ImageView)findViewById(R.id.showItemRank);
		item_name = (TextView)findViewById(R.id.itemNameRank);
		item_price = (TextView)findViewById(R.id.itemPriceRank);
		item_like_num = (TextView)findViewById(R.id.itemLikeNumRank);
		item_like = (ImageView)findViewById(R.id.likeItemBtnRank);
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
		
		item_like.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(likeListener != null){
					likeListener.onPopularItemLikeListener(RankItemView.this, iData);
				}
			}
		});
		
	}

	public void setRankItemData(ItemData data, int position){
		iData = data;
		/*if(position == 0 || position == 1 || position == 2){
			item_rank.setVisibility(VISIBLE);
			item_rank.setImageResource(R.drawable.ic_launcher);
		}
		else{*/
			item_rank.setVisibility(GONE);
	//	}
		
		if(data.islike == 1){
			item_like.setImageResource(R.drawable.ic_ranking_pick_on);
		}else{
			item_like.setImageResource(R.drawable.ic_ranking_pick_off);
		}
		
		loader.displayImage(data.item_img_url[0], item_img, options);
		item_name.setText((position+1)+ ". "+ data.item_name);
		
		DecimalFormat df = new DecimalFormat("#,##0");
		item_price.setText(""+ df.format(data.price) + "¿ø");	
		item_like_num.setText(data.likeCnt+"");

	}
}
