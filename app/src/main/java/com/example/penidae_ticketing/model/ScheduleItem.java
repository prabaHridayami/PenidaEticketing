package com.example.penidae_ticketing.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ScheduleItem implements Parcelable {

	@SerializedName("id_schedule")
	private int idSchedule;

	@SerializedName("price")
	private int price;

	@SerializedName("quota")
	private int quota;

	@SerializedName("time")
	private String time;

	@SerializedName("pickup_loc")
	private String pickupLoc;

	@SerializedName("id_boat")
	private int idBoat;

	@SerializedName("dropup_loc")
	private String dropupLoc;

	protected ScheduleItem(Parcel in) {
		idSchedule = in.readInt();
		price = in.readInt();
		quota = in.readInt();
		time = in.readString();
		pickupLoc = in.readString();
		idBoat = in.readInt();
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

	public void setIdSchedule(int idSchedule){
		this.idSchedule = idSchedule;
	}

	public int getIdSchedule(){
		return idSchedule;
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

	public void setIdBoat(int idBoat){
		this.idBoat = idBoat;
	}

	public int getIdBoat(){
		return idBoat;
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
			"id_schedule = '" + idSchedule + '\'' + 
			",price = '" + price + '\'' + 
			",quota = '" + quota + '\'' + 
			",time = '" + time + '\'' + 
			",pickup_loc = '" + pickupLoc + '\'' + 
			",id_boat = '" + idBoat + '\'' + 
			",dropup_loc = '" + dropupLoc + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(idSchedule);
		dest.writeInt(price);
		dest.writeInt(quota);
		dest.writeString(time);
		dest.writeString(pickupLoc);
		dest.writeInt(idBoat);
		dest.writeString(dropupLoc);
	}
}