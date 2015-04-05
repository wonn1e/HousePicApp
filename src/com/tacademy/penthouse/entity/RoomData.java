package com.tacademy.penthouse.entity;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;


public class RoomData implements Parcelable{
		
	public int user_num;
	
	public int room_num;
	public String room_name;
	public String room_img_url;
	public String room_date;
	public String room_color;
	public String source_name;
	public String source_url;
	public int room_ispublic;
	public ArrayList<ItemData> items = new ArrayList<ItemData>();
	public UserRoomItemsData parent;
	public UserRoomsData parent2;
	
	public RoomData(){}
	
	public RoomData(int u_n, int r_num, String r_name, String r_img, String r_info, int ispublic, String r_color){
		this.user_num = u_n;
		this.room_num = r_num;
		this.room_name = r_name;
		this.room_img_url = r_img;
		this.room_color = r_color;
		this.room_ispublic = ispublic;
	}
	
	public RoomData(Parcel p){
		user_num = p.readInt();
		room_num = p.readInt();
		room_name = p.readString();
		room_img_url = p.readString();
	//	passedtime = new Date(p.readLong());
		//isPublic = p.readByte() != 0;
		room_ispublic = p.readInt();
		room_color = p.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(user_num);
		dest.writeInt(room_num);
		dest.writeString(room_name);
		dest.writeString(room_img_url);
	//	dest.writeLong(passedtime.getTime());
		//dest.writeByte((byte)(isPublic ? 1:0));
		dest.writeInt(room_ispublic);
		dest.writeString(room_color);
	}
	
	public static Parcelable.Creator<RoomData> CREATOR = new Parcelable.Creator<RoomData>() {

		@Override
		public RoomData createFromParcel(Parcel source) {
			return new RoomData(source);
		}

		@Override
		public RoomData[] newArray(int size) {
			return new RoomData[size];
		}
	};
}
