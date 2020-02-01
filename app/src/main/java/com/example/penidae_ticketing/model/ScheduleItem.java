package com.example.penidae_ticketing.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ScheduleItem implements Parcelable {

	@SerializedName("image")
	private String image;

	@SerializedName("price")
	private int price;

	@SerializedName("quota")
	private int quota;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("time")
	private String time;

	@SerializedName("pickup_loc")
	private String pickupLoc;

	@SerializedName("desc")
	private String desc;

	@SerializedName("dropup_loc")
	private String dropupLoc;

	protected ScheduleItem(Parcel in) {
		image = in.readString();
		price = in.readInt();
		quota = in.readInt();
		name = in.readString();
		id = in.readInt();
		time = in.readString();
		pickupLoc = in.readString();
		desc = in.readString();
		dropupLoc = in.readString();
	}

	public static final Creator<ScheduleItem> CREATOR = new Creator<ScheduleItem>() {
		@Override
		public ScheduleItem createFromParcel(Parcel in) {
			return new ScheduleItem(in);
		}

		@Override
		public ScheduleItem[] newArray(int size) {
			return new ScheduleItem[size];
		}
	};

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setQuota(int quota){
		this.quota = quota;
	}

	public int getQuota(){
		return quota;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

	public void setPickupLoc(String pickupLoc){
		this.pickupLoc = pickupLoc;
	}

	public String getPickupLoc(){
		return pickupLoc;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return desc;
	}

	public void setDropupLoc(String dropupLoc){
		this.dropupLoc = dropupLoc;
	}

	public String getDropupLoc(){
		return dropupLoc;
	}

	@Override
 	public String toString(){
		return 
			"ScheduleItem{" + 
			"image = '" + image + '\'' + 
			",price = '" + price + '\'' + 
			",quota = '" + quota + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",time = '" + time + '\'' + 
			",pickup_loc = '" + pickupLoc + '\'' + 
			",desc = '" + desc + '\'' + 
			",dropup_loc = '" + dropupLoc + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(image);
		dest.writeInt(price);
		dest.writeInt(quota);
		dest.writeString(name);
		dest.writeInt(id);
		dest.writeString(time);
		dest.writeString(pickupLoc);
		dest.writeString(desc);
		dest.writeString(dropupLoc);
	}
}