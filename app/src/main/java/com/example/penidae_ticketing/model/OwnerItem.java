package com.example.penidae_ticketing.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class OwnerItem implements Parcelable {

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

	@SerializedName("desc")
	private String desc;

	protected OwnerItem(Parcel in) {
		image = in.readString();
		address = in.readString();
		phone = in.readString();
		name = in.readString();
		id = in.readInt();
		idUser = in.readInt();
		desc = in.readString();
	}

	public static final Creator<OwnerItem> CREATOR = new Creator<OwnerItem>() {
		@Override
		public OwnerItem createFromParcel(Parcel in) {
			return new OwnerItem(in);
		}

		@Override
		public OwnerItem[] newArray(int size) {
			return new OwnerItem[size];
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

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return desc;
	}

	@Override
 	public String toString(){
		return 
			"OwnerItem{" + 
			"image = '" + image + '\'' + 
			",address = '" + address + '\'' + 
			",phone = '" + phone + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",desc = '" + desc + '\'' + 
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
		dest.writeString(desc);
	}
}