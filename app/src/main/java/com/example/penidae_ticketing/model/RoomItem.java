package com.example.penidae_ticketing.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RoomItem implements Parcelable {

	@SerializedName("num_room")
	private int num_room;

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

	protected RoomItem(Parcel in) {
		num_room = in.readInt();
		price = in.readInt();
		idCat = in.readInt();
		category = in.readString();
		desc = in.readString();
	}

	public static final Creator<RoomItem> CREATOR = new Creator<RoomItem>() {
		@Override
		public RoomItem createFromParcel(Parcel in) {
			return new RoomItem(in);
		}

		@Override
		public RoomItem[] newArray(int size) {
			return new RoomItem[size];
		}
	};

	public void setNumRoom(Integer num_room){
		this.num_room = num_room;
	}

	public Integer getNumRoom(){
		return num_room;
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
			"num_room = '" + num_room + '\'' +
			",price = '" + price + '\'' + 
			",id_cat = '" + idCat + '\'' + 
			",category = '" + category + '\'' +
			",desc = '" + desc + '\'' +
			"}";
		}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(num_room);
		dest.writeInt(price);
		dest.writeInt(idCat);
		dest.writeString(category);
		dest.writeString(desc);
	}
}