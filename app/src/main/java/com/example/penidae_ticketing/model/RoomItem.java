package com.example.penidae_ticketing.model;

import com.google.gson.annotations.SerializedName;

public class RoomItem{

	@SerializedName("room_name")
	private String roomName;

	@SerializedName("price")
	private int price;

	@SerializedName("id_cat")
	private int idCat;

	@SerializedName("category")
	private String category;

	@SerializedName("id_room")
	private int idRoom;

	@SerializedName("desc")
	private String desc;

	public void setRoomName(String roomName){
		this.roomName = roomName;
	}

	public String getRoomName(){
		return roomName;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setIdCat(int idCat){
		this.idCat = idCat;
	}

	public int getIdCat(){
		return idCat;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setIdRoom(int idRoom){
		this.idRoom = idRoom;
	}

	public int getIdRoom(){
		return idRoom;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
 	public String toString(){
		return 
			"RoomItem{" + 
			"room_name = '" + roomName + '\'' + 
			",price = '" + price + '\'' + 
			",id_cat = '" + idCat + '\'' + 
			",category = '" + category + '\'' + 
			",id_room = '" + idRoom + '\'' +
			",desc = '" + desc + '\'' +
			"}";
		}


}