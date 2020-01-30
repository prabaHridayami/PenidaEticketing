package com.example.penidae_ticketing.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class WatersportItem implements Parcelable {

	@SerializedName("image")
	private String image;

	@SerializedName("address")
	private String address;

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("id_user")
	private int idUser;

	protected WatersportItem(Parcel in) {
		image = in.readString();
		address = in.readString();
		phone = in.readString();
		name = in.readString();
		id = in.readInt();
		idUser = in.readInt();
	}

	public static final Creator<WatersportItem> CREATOR = new Creator<WatersportItem>() {
		@Override
		public WatersportItem createFromParcel(Parcel in) {
			return new WatersportItem(in);
		}

		@Override
		public WatersportItem[] newArray(int size) {
			return new WatersportItem[size];
		}
	};

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
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

	public void setIdUser(int idUser){
		this.idUser = idUser;
	}

	public int getIdUser(){
		return idUser;
	}

	@Override
 	public String toString(){
		return 
			"WatersportItem{" + 
			"image = '" + image + '\'' + 
			",address = '" + address + '\'' + 
			",phone = '" + phone + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",id_user = '" + idUser + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(image);
		dest.writeString(address);
		dest.writeString(phone);
		dest.writeString(name);
		dest.writeInt(id);
		dest.writeInt(idUser);
	}
}