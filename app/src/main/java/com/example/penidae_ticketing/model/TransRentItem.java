package com.example.penidae_ticketing.model;

import com.google.gson.annotations.SerializedName;

public class TransRentItem{

	@SerializedName("vehicle_plat")
	private String vehiclePlat;

	@SerializedName("image")
	private String image;

	@SerializedName("address")
	private String address;

	@SerializedName("total_price")
	private int totalPrice;

	@SerializedName("id_vehicle")
	private int idVehicle;

	@SerializedName("transdate")
	private String transdate;

	@SerializedName("take")
	private String take;

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("vehicle_desc")
	private String vehicleDesc;

	@SerializedName("id_trans")
	private int idTrans;

	@SerializedName("id")
	private int id;

	@SerializedName("vehicle_seat")
	private int vehicleSeat;

	@SerializedName("vehicle_price")
	private int vehiclePrice;

	@SerializedName("return")
	private String jsonMemberReturn;

	@SerializedName("vehicle_name")
	private String vehicleName;

	@SerializedName("status")
	private String status;

	public void setVehiclePlat(String vehiclePlat){
		this.vehiclePlat = vehiclePlat;
	}

	public String getVehiclePlat(){
		return vehiclePlat;
	}

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

	public void setIdVehicle(int idVehicle){
		this.idVehicle = idVehicle;
	}

	public int getIdVehicle(){
		return idVehicle;
	}

	public void setTransdate(String transdate){
		this.transdate = transdate;
	}

	public String getTransdate(){
		return transdate;
	}

	public void setTake(String take){
		this.take = take;
	}

	public String getTake(){
		return take;
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

	public void setVehicleDesc(String vehicleDesc){
		this.vehicleDesc = vehicleDesc;
	}

	public String getVehicleDesc(){
		return vehicleDesc;
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

	public void setVehicleSeat(int vehicleSeat){
		this.vehicleSeat = vehicleSeat;
	}

	public int getVehicleSeat(){
		return vehicleSeat;
	}

	public void setVehiclePrice(int vehiclePrice){
		this.vehiclePrice = vehiclePrice;
	}

	public int getVehiclePrice(){
		return vehiclePrice;
	}

	public void setJsonMemberReturn(String jsonMemberReturn){
		this.jsonMemberReturn = jsonMemberReturn;
	}

	public String getJsonMemberReturn(){
		return jsonMemberReturn;
	}

	public void setVehicleName(String vehicleName){
		this.vehicleName = vehicleName;
	}

	public String getVehicleName(){
		return vehicleName;
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
			"TransRentItem{" + 
			"vehicle_plat = '" + vehiclePlat + '\'' + 
			",image = '" + image + '\'' + 
			",address = '" + address + '\'' + 
			",total_price = '" + totalPrice + '\'' + 
			",id_vehicle = '" + idVehicle + '\'' + 
			",transdate = '" + transdate + '\'' + 
			",take = '" + take + '\'' + 
			",phone = '" + phone + '\'' + 
			",name = '" + name + '\'' + 
			",vehicle_desc = '" + vehicleDesc + '\'' + 
			",id_trans = '" + idTrans + '\'' + 
			",id = '" + id + '\'' + 
			",vehicle_seat = '" + vehicleSeat + '\'' + 
			",vehicle_price = '" + vehiclePrice + '\'' + 
			",return = '" + jsonMemberReturn + '\'' + 
			",vehicle_name = '" + vehicleName + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}