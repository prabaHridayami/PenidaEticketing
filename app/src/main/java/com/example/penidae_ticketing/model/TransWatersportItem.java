package com.example.penidae_ticketing.model;

import com.google.gson.annotations.SerializedName;

public class TransWatersportItem{

	@SerializedName("image")
	private String image;

	@SerializedName("address")
	private String address;

	@SerializedName("total_price")
	private int totalPrice;

	@SerializedName("id_att")
	private int idAtt;

	@SerializedName("att_name")
	private String attName;

	@SerializedName("reserve_date")
	private String reserveDate;

	@SerializedName("att_price")
	private int attPrice;

	@SerializedName("att_desc")
	private String attDesc;

	@SerializedName("phone")
	private String phone;

	@SerializedName("play_date")
	private String playDate;

	@SerializedName("qty")
	private int qty;

	@SerializedName("name")
	private String name;

	@SerializedName("id_trans")
	private int idTrans;

	@SerializedName("id")
	private int id;

	@SerializedName("att_image")
	private String attImage;

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

	public void setIdAtt(int idAtt){
		this.idAtt = idAtt;
	}

	public int getIdAtt(){
		return idAtt;
	}

	public void setAttName(String attName){
		this.attName = attName;
	}

	public String getAttName(){
		return attName;
	}

	public void setReserveDate(String reserveDate){
		this.reserveDate = reserveDate;
	}

	public String getReserveDate(){
		return reserveDate;
	}

	public void setAttPrice(int attPrice){
		this.attPrice = attPrice;
	}

	public int getAttPrice(){
		return attPrice;
	}

	public void setAttDesc(String attDesc){
		this.attDesc = attDesc;
	}

	public String getAttDesc(){
		return attDesc;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setPlayDate(String playDate){
		this.playDate = playDate;
	}

	public String getPlayDate(){
		return playDate;
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

	public void setAttImage(String attImage){
		this.attImage = attImage;
	}

	public String getAttImage(){
		return attImage;
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
			"TransWatersportItem{" + 
			"image = '" + image + '\'' + 
			",address = '" + address + '\'' + 
			",total_price = '" + totalPrice + '\'' + 
			",id_att = '" + idAtt + '\'' + 
			",att_name = '" + attName + '\'' + 
			",reserve_date = '" + reserveDate + '\'' + 
			",att_price = '" + attPrice + '\'' + 
			",att_desc = '" + attDesc + '\'' + 
			",phone = '" + phone + '\'' + 
			",play_date = '" + playDate + '\'' + 
			",qty = '" + qty + '\'' + 
			",name = '" + name + '\'' + 
			",id_trans = '" + idTrans + '\'' + 
			",id = '" + id + '\'' + 
			",att_image = '" + attImage + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}