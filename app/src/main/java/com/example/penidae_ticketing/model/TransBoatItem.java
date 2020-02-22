package com.example.penidae_ticketing.model;

import com.google.gson.annotations.SerializedName;


public class TransBoatItem{

	@SerializedName("boat_quota")
	private int boatQuota;

	@SerializedName("boat_price")
	private int boatPrice;

	@SerializedName("image")
	private String image;

	@SerializedName("total_price")
	private int totalPrice;

	@SerializedName("depart_date")
	private String departDate;

	@SerializedName("reserve_date")
	private String reserveDate;

	@SerializedName("dropup_loc")
	private String dropupLoc;

	@SerializedName("id_schedule")
	private int idSchedule;

	@SerializedName("phone")
	private String phone;

	@SerializedName("qty")
	private int qty;

	@SerializedName("name")
	private String name;

	@SerializedName("boat_image")
	private String boatImage;

	@SerializedName("id_trans")
	private int idTrans;

	@SerializedName("id")
	private int id;

	@SerializedName("time")
	private String time;

	@SerializedName("boat_desc")
	private String boatDesc;

	@SerializedName("pickup_loc")
	private String pickupLoc;

	@SerializedName("desc")
	private String desc;

	@SerializedName("id_boat")
	private int idBoat;

	@SerializedName("status")
	private String status;

	public void setBoatQuota(int boatQuota){
		this.boatQuota = boatQuota;
	}

	public int getBoatQuota(){
		return boatQuota;
	}

	public void setBoatPrice(int boatPrice){
		this.boatPrice = boatPrice;
	}

	public int getBoatPrice(){
		return boatPrice;
	}

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

	public void setDepartDate(String departDate){
		this.departDate = departDate;
	}

	public String getDepartDate(){
		return departDate;
	}

	public void setReserveDate(String reserveDate){
		this.reserveDate = reserveDate;
	}

	public String getReserveDate(){
		return reserveDate;
	}

	public void setDropupLoc(String dropupLoc){
		this.dropupLoc = dropupLoc;
	}

	public String getDropupLoc(){
		return dropupLoc;
	}

	public void setIdSchedule(int idSchedule){
		this.idSchedule = idSchedule;
	}

	public int getIdSchedule(){
		return idSchedule;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
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

	public void setBoatImage(String boatImage){
		this.boatImage = boatImage;
	}

	public String getBoatImage(){
		return boatImage;
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

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

	public void setBoatDesc(String boatDesc){
		this.boatDesc = boatDesc;
	}

	public String getBoatDesc(){
		return boatDesc;
	}

	public void setPickupLoc(String pickupLoc){
		this.pickupLoc = pickupLoc;
	}

	public String getPickupLoc(){
		return pickupLoc;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return desc;
	}

	public void setIdBoat(int idBoat){
		this.idBoat = idBoat;
	}

	public int getIdBoat(){
		return idBoat;
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
			"TransBoatItem{" + 
			"boat_quota = '" + boatQuota + '\'' + 
			",boat_price = '" + boatPrice + '\'' + 
			",image = '" + image + '\'' + 
			",total_price = '" + totalPrice + '\'' + 
			",depart_date = '" + departDate + '\'' + 
			",reserve_date = '" + reserveDate + '\'' + 
			",dropup_loc = '" + dropupLoc + '\'' + 
			",id_schedule = '" + idSchedule + '\'' + 
			",phone = '" + phone + '\'' + 
			",qty = '" + qty + '\'' + 
			",name = '" + name + '\'' + 
			",boat_image = '" + boatImage + '\'' + 
			",id_trans = '" + idTrans + '\'' + 
			",id = '" + id + '\'' + 
			",time = '" + time + '\'' + 
			",boat_desc = '" + boatDesc + '\'' + 
			",pickup_loc = '" + pickupLoc + '\'' + 
			",desc = '" + desc + '\'' + 
			",id_boat = '" + idBoat + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}