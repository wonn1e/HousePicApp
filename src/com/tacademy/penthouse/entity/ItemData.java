package com.tacademy.penthouse.entity;


import android.os.Parcel;
import android.os.Parcelable;


public class ItemData implements Parcelable {
	
	public int room_num ;
	public int item_num;
	public String sort_code;
	public String item_name;
	public String brand;
	public int price;
	public String item_size;
	public String material;
	public String[]theme;
	public int likeCnt;
	public int sort_category;
	public String link;
	public int islike;
	public String[] item_img_url;
	public UserRoomItemsData parent;
	

	public ItemData(){}
	public ItemData(int room_num,int item_num,           
	String sort_code ,     
	String item_name ,     
	String brand ,         
	int price,          
	String material,       
	String[]theme,  
	int likeCnt ,    String item_size,         
	int sort_category,String []item_img_url ,String link ,int islike){
		this.room_num = room_num;
		this.item_num = item_num ;           
		this.sort_code = sort_code;     
		this.item_name = item_name;     
		this.brand = brand;         
		this.price = price;          
		this.material = material;       
		this.theme = theme;  
		this.likeCnt = likeCnt;             
		this.sort_category = sort_category; 
		this.item_img_url = item_img_url ; 
		this.link = link;
		this.islike = islike;
		this.item_size = item_size;
	
	}
	public ItemData(Parcel p){
		room_num = p.readInt();
		item_num = p.readInt();
		sort_code = p.readString();
		item_name = p.readString();
		brand = p.readString();
		price = p.readInt();
		material = p.readString();
		int length = p.readInt();
		likeCnt = p.readInt();
		sort_category = p.readInt();
		item_size = p.readString();
		int item_cnt = p.readInt();
		item_img_url = new String[item_cnt];
		p.readStringArray(item_img_url);
		link = p.readString();
		islike = p.readInt();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(room_num);
		dest.writeInt(item_num);
		dest.writeString(sort_code);
		dest.writeString(item_name);
		dest.writeString(brand);
		dest.writeInt(price);
		dest.writeString(material);
		dest.writeInt(likeCnt);
		dest.writeInt(sort_category);
		dest.writeString(item_size);
		dest.writeInt(item_img_url.length);
		dest.writeStringArray(item_img_url);
		dest.writeString(link);
		dest.writeInt(islike);
	}
	public static Parcelable.Creator<ItemData> CREATOR = new Parcelable.Creator<ItemData>() {
		@Override
		public ItemData[] newArray(int size) {
			return new ItemData[size];
		}

		@Override
		public ItemData createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new ItemData(source);
		}
	};
}
