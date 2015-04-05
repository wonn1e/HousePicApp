package com.tacademy.penthouse.manager;

import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.RoomData;
import com.tacademy.penthouse.entity.RoomsData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.entity.UserRoomItemsData;
import com.tacademy.penthouse.entity.UserRoomsData;

public class DataManager {
	public RoomData getRoomData(RoomsData rr, int r_num){
		for(int i = 0; i < rr.rooms.size(); i++){
			if(r_num == rr.rooms.get(i).room.room_num){
				return rr.rooms.get(i).room;
			}
		}

		return null;

	}
	
	public RoomData getRoomData(UserRoomItemsData data, int r_num){

		if(r_num == data.room.room_num){
			return data.room;
		}

		return null;
	}

	public UserData getUserData(UserRoomItemsData data, int uNum){
		if(uNum == data.user.user_num){
			return data.user;
		}
		return null;
	}

	public ItemData getRIItemData(RoomsData resultRooms, int roomNum, int itemPos){
		if(itemPos != 5)
			return resultRooms.rooms.get(roomNum).items.get(itemPos);
		return null;
	}	
	
	public UserData getUserData(UserRoomsData data, int u_num){
		for(int i = 0; i < data.rooms.size(); i++){
			if(u_num == data.rooms.get(i).user_num){
				return data.user;
			}	
		}
		return null;
	}
}
