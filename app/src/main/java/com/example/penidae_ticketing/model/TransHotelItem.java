package com.example.penidae_ticketing.model;

import com.google.gson.annotations.SerializedName;

public class TransHotelItem{

	@SerializedName("image")
	private String image;

	@SerializedName("address")
	private String address;

	@SerializedName("total_price")
	private int totalPrice;

	@SerializedName("cat_name")
	private String catName;

	@SerializedName("check_in")
	private String checkIn;

	@SerializedName("id_cat")
	private int idCat;

	@SerializedName("reserve_date")
	private String reserveDate;

	@SerializedName("check_out")
	private String checkOut;

	@SerializedName("phone")
	private String phone;

	@SerializedName("cat_desc")
	private String catDesc;

	@SerializedName("cat_price")
	private int catPrice;

	@SerializedName("name")
	private String name;

	@SerializedName("id_trans")
	private int idTrans;

	@SerializedName("id")
	private int id;

	@SerializedName("status")
	private String status;

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

	public void setTotalPrice(int totalPrice){
		this.totalPrice = totalPrice;
	}

	public int getTotalPrice(){
		return totalPrice;
	}

	public void setCatName(String catName){
		this.catName = catName;
	}

	public String getCatName(){
		return catName;
	}

	public void setCheckIn(String checkIn){
		this.checkIn = checkIn;
	}

	public String getCheckIn(){
		return checkIn;
	}

	public void setIdCat(int idCat){
		this.idCat = idCat;
	}

	public int getIdCat(){
		return idCat;
	}

	public void setReserveDate(String reserveDate){
		this.reserveDate = reserveDate;
	}

	public String getReserveDate(){
		return reserveDate;
	}

	public void setCheckOut(String checkOut){
		this.checkOut = checkOut;
	}

	public String getCheckOut(){
		return checkOut;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setCatDesc(String catDesc){
		this.catDesc = catDesc;
	}

	public String getCatDesc(){
		return catDesc;
	}

	public void setCatPrice(int catPrice){
		this.catPrice = catPrice;
	}

	public int getCatPrice(){
		return catPrice;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setIdTrans(int idTrans){
		this.idTrans = idTrans;
	}

	public int getIdTrans(){
		return idTrans;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"TransHotelItem{" + 
			"image = '" + image + '\'' + 
			",address = '" + address + '\'' + 
			",total_price = '" + totalPrice + '\'' + 
			",cat_name = '" + catName + '\'' + 
			",check_in = '" + checkIn + '\'' + 
			",id_cat = '" + idCat + '\'' + 
			",reserve_date = '" + reserveDate + '\'' + 
			",check_out = '" + checkOut + '\'' + 
			",phone = '" + phone + '\'' + 
			",cat_desc = '" + catDesc + '\'' + 
			",cat_price = '" + catPrice + '\'' + 
			",name = '" + name + '\'' + 
			",id_trans = '" + idTrans + '\'' + 
			",id = '" + id + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}