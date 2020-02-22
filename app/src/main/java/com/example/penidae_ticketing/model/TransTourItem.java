package com.example.penidae_ticketing.model;

import com.google.gson.annotations.SerializedName;

public class TransTourItem{

	@SerializedName("image")
	private String image;

	@SerializedName("total_price")
	private int totalPrice;

	@SerializedName("qty")
	private int qty;

	@SerializedName("name")
	private String name;

	@SerializedName("tour_date")
	private String tourDate;

	@SerializedName("id")
	private int id;

	@SerializedName("id_user")
	private int idUser;

	@SerializedName("id_tour_package")
	private int idTourPackage;

	@SerializedName("reserve_date")
	private String reserveDate;

	@SerializedName("desc")
	private String desc;

	@SerializedName("status")
	private String status;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setTotalPrice(int totalPrice){
		this.totalPrice = totalPrice;
	}

	public int getTotalPrice(){
		return totalPrice;
	}

	public void setQty(int qty){
		this.qty = qty;
	}

	public int getQty(){
		return qty;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setTourDate(String tourDate){
		this.tourDate = tourDate;
	}

	public String getTourDate(){
		return tourDate;
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

	public void setIdTourPackage(int idTourPackage){
		this.idTourPackage = idTourPackage;
	}

	public int getIdTourPackage(){
		return idTourPackage;
	}

	public void setReserveDate(String reserveDate){
		this.reserveDate = reserveDate;
	}

	public String getReserveDate(){
		return reserveDate;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return desc;
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
			"TransTourItem{" + 
			"image = '" + image + '\'' + 
			",total_price = '" + totalPrice + '\'' + 
			",qty = '" + qty + '\'' + 
			",name = '" + name + '\'' + 
			",tour_date = '" + tourDate + '\'' + 
			",id = '" + id + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",id_tour_package = '" + idTourPackage + '\'' + 
			",reserve_date = '" + reserveDate + '\'' + 
			",desc = '" + desc + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}