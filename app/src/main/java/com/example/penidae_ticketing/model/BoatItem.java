package com.example.penidae_ticketing.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BoatItem implements Parcelable {

	@SerializedName("image")
	private String image;

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

	protected BoatItem(Parcel in) {
		image = in.readString();
		phone = in.readString();
		name = in.readString();
		id = in.readInt();
		idUser = in.readInt();
		desc = in.readString();
	}

	public static final Creator<BoatItem> CREATOR = new Parcelable.Creator<BoatItem>() {
		@Override
		public BoatItem createFromParcel(Parcel in) {
			return new BoatItem(in);
		}

		@Override
		public BoatItem[] newArray(int size) {
			return new BoatItem[size];
		}
	};

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
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
			"BoatItem{" + 
			"image = '" + image + '\'' + 
			",phone = '" + phone + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",desc = '" + desc + '\'' + 
			"}";
		}

	public int describeContents(){
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(image);
		dest.writeString(phone);
		dest.writeString(name);
		dest.writeInt(id);
		dest.writeInt(idUser);
		dest.writeString(desc);
	}
}