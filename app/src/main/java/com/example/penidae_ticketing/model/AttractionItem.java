package com.example.penidae_ticketing.model;

import com.google.gson.annotations.SerializedName;

public class AttractionItem{

	@SerializedName("id_watersport")
	private int idWatersport;

	@SerializedName("image")
	private String image;

	@SerializedName("price")
	private int price;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("desc")
	private String desc;

	public void setIdWatersport(int idWatersport){
		this.idWatersport = idWatersport;
	}

	public int getIdWatersport(){
		return idWatersport;
	}

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
			"AttractionItem{" + 
			"id_watersport = '" + idWatersport + '\'' + 
			",image = '" + image + '\'' + 
			",price = '" + price + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",desc = '" + desc + '\'' + 
			"}";
		}
}