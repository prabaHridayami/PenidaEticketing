package com.example.penidae_ticketing.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class VehicleItem implements Parcelable {

	@SerializedName("id_vehicle")
	private int idVehicle;

	@SerializedName("price")
	private int price;

	@SerializedName("name")
	private String name;

	@SerializedName("category")
	private String category;

	@SerializedName("desc")
	private String desc;

	protected VehicleItem(Parcel in) {
		idVehicle = in.readInt();
		price = in.readInt();
		name = in.readString();
		category = in.readString();
		desc = in.readString();
	}

	public static final Creator<VehicleItem> CREATOR = new Creator<VehicleItem>() {
		@Override
		public VehicleItem createFromParcel(Parcel in) {
			return new VehicleItem(in);
		}

		@Override
		public VehicleItem[] newArray(int size) {
			return new VehicleItem[size];
		}
	};

	public void setIdVehicle(int idVehicle){
		this.idVehicle = idVehicle;
	}

	public int getIdVehicle(){
		return idVehicle;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return desc;
	}

	@Override
 	public String toString(){
		return 
			"VehicleItem{" + 
			"id_vehicle = '" + idVehicle + '\'' + 
			",price = '" + price + '\'' + 
			",name = '" + name + '\'' + 
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
		dest.writeInt(idVehicle);
		dest.writeInt(price);
		dest.writeString(name);
		dest.writeString(category);
		dest.writeString(desc);
	}
}