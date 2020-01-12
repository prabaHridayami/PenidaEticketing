package com.example.penidae_ticketing.model;

import com.google.gson.annotations.SerializedName;

public class HotelPayment{

	@SerializedName("check_out")
	private String checkOut;

	@SerializedName("total_price")
	private String totalPrice;

	@SerializedName("check_in")
	private String checkIn;

	@SerializedName("id")
	private int id;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("id_room")
	private String idRoom;

	@SerializedName("reserve_date")
	private String reserveDate;

	public void setCheckOut(String checkOut){
		this.checkOut = checkOut;
	}

	public String getCheckOut(){
		return checkOut;
	}

	public void setTotalPrice(String totalPrice){
		this.totalPrice = totalPrice;
	}

	public String getTotalPrice(){
		return totalPrice;
	}

	public void setCheckIn(String checkIn){
		this.checkIn = checkIn;
	}

	public String getCheckIn(){
		return checkIn;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setIdRoom(String idRoom){
		this.idRoom = idRoom;
	}

	public String getIdRoom(){
		return idRoom;
	}

	public void setReserveDate(String reserveDate){
		this.reserveDate = reserveDate;
	}

	public String getReserveDate(){
		return reserveDate;
	}

	@Override
 	public String toString(){
		return 
			"HotelPayment{" + 
			"check_out = '" + checkOut + '\'' + 
			",total_price = '" + totalPrice + '\'' + 
			",check_in = '" + checkIn + '\'' + 
			",id = '" + id + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",id_room = '" + idRoom + '\'' + 
			",reserve_date = '" + reserveDate + '\'' + 
			"}";
		}
}