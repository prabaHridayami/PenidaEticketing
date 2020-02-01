package com.example.penidae_ticketing.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TourPackageItem implements Parcelable {

	@SerializedName("image")
	private String image;

	@SerializedName("price")
	private int price;

	@SerializedName("name")
	private String name;

	@SerializedName("id_tour")
	private int idTour;

	@SerializedName("max_qty")
	private int maxQty;

	@SerializedName("id")
	private int id;

	@SerializedName("desc")
	private String desc;

	protected TourPackageItem(Parcel in) {
		image = in.readString();
		price = in.readInt();
		name = in.readString();
		idTour = in.readInt();
		maxQty = in.readInt();
		id = in.readInt();
		desc = in.readString();
	}

	public static final Creator<TourPackageItem> CREATOR = new Creator<TourPackageItem>() {
		@Override
		public TourPackageItem createFromParcel(Parcel in) {
			return new TourPackageItem(in);
		}

		@Override
		public TourPackageItem[] newArray(int size) {
			return new TourPackageItem[size];
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

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setIdTour(int idTour){
		this.idTour = idTour;
	}

	public int getIdTour(){
		return idTour;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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
			"TourPackageItem{" + 
			"image = '" + image + '\'' + 
			",price = '" + price + '\'' + 
			",name = '" + name + '\'' + 
			",id_tour = '" + idTour + '\'' + 
			",id = '" + id + '\'' +
			",maxQty = '" + maxQty + '\'' +
			",desc = '" + desc + '\'' +
			"}";
		}

	public int getMaxQty() {
		return maxQty;
	}

	public void setMaxQty(int maxQty) {
		this.maxQty = maxQty;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(image);
		dest.writeInt(price);
		dest.writeString(name);
		dest.writeInt(idTour);
		dest.writeInt(maxQty);
		dest.writeInt(id);
		dest.writeString(desc);
	}
}