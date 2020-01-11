package com.example.penidae_ticketing.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class HotelItem implements Parcelable {

	@SerializedName("name_cate")
	private String nameCate;

	@SerializedName("hotel_phone")
	private String hotelPhone;

	@SerializedName("hotel_image")
	private String hotelImage;

	@SerializedName("id_hotel")
	private int idHotel;

	@SerializedName("price")
	private int price;

	@SerializedName("hotel_address")
	private String hotelAddress;

	@SerializedName("num_room")
	private int numRoom;

	@SerializedName("hotel_name")
	private String hotelName;

	@SerializedName("id_cate")
	private int idCate;

	@SerializedName("desc")
	private String desc;

	protected HotelItem(Parcel in) {
		nameCate = in.readString();
		hotelPhone = in.readString();
		hotelImage = in.readString();
		idHotel = in.readInt();
		price = in.readInt();
		hotelAddress = in.readString();
		numRoom = in.readInt();
		hotelName = in.readString();
		idCate = in.readInt();
		desc = in.readString();
	}

	public static final Creator<HotelItem> CREATOR = new Creator<HotelItem>() {
		@Override
		public HotelItem createFromParcel(Parcel in) {
			return new HotelItem(in);
		}

		@Override
		public HotelItem[] newArray(int size) {
			return new HotelItem[size];
		}
	};

	public void setNameCate(String nameCate){
		this.nameCate = nameCate;
	}

	public String getNameCate(){
		return nameCate;
	}

	public void setHotelPhone(String hotelPhone){
		this.hotelPhone = hotelPhone;
	}

	public String getHotelPhone(){
		return hotelPhone;
	}

	public void setHotelImage(String hotelImage){
		this.hotelImage = hotelImage;
	}

	public String getHotelImage(){
		return hotelImage;
	}

	public void setIdHotel(int idHotel){
		this.idHotel = idHotel;
	}

	public int getIdHotel(){
		return idHotel;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setHotelAddress(String hotelAddress){
		this.hotelAddress = hotelAddress;
	}

	public String getHotelAddress(){
		return hotelAddress;
	}

	public void setNumRoom(int numRoom){
		this.numRoom = numRoom;
	}

	public int getNumRoom(){
		return numRoom;
	}

	public void setHotelName(String hotelName){
		this.hotelName = hotelName;
	}

	public String getHotelName(){
		return hotelName;
	}

	public void setIdCate(int idCate){
		this.idCate = idCate;
	}

	public int getIdCate(){
		return idCate;
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
			"HotelItem{" + 
			"name_cate = '" + nameCate + '\'' + 
			",hotel_phone = '" + hotelPhone + '\'' + 
			",hotel_image = '" + hotelImage + '\'' + 
			",id_hotel = '" + idHotel + '\'' + 
			",price = '" + price + '\'' + 
			",hotel_address = '" + hotelAddress + '\'' + 
			",num_room = '" + numRoom + '\'' + 
			",hotel_name = '" + hotelName + '\'' + 
			",id_cate = '" + idCate + '\'' + 
			",desc = '" + desc + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(nameCate);
		dest.writeString(hotelPhone);
		dest.writeString(hotelImage);
		dest.writeInt(idHotel);
		dest.writeInt(price);
		dest.writeString(hotelAddress);
		dest.writeInt(numRoom);
		dest.writeString(hotelName);
		dest.writeInt(idCate);
		dest.writeString(desc);
	}
}