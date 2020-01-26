package com.example.penidae_ticketing.model;

import com.google.gson.annotations.SerializedName;

public class VehicleItem{

	@SerializedName("id_vehicle")
	private int idVehicle;

	@SerializedName("price")
	private int price;

	@SerializedName("name")
	private String name;

	@SerializedName("category")
	private String category;

	@SerializedName("desc")
	private String desc;

	public void setIdVehicle(int idVehicle){
		this.idVehicle = idVehicle;
	}

	public int getIdVehicle(){
		return idVehicle;
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

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
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
			"VehicleItem{" + 
			"id_vehicle = '" + idVehicle + '\'' + 
			",price = '" + price + '\'' + 
			",name = '" + name + '\'' + 
			",category = '" + category + '\'' + 
			",desc = '" + desc + '\'' + 
			"}";
		}
}