package com.example.penidae_ticketing.model;

import com.google.gson.annotations.SerializedName;

public class TourPackageItem{

	@SerializedName("image")
	private String image;

	@SerializedName("price")
	private int price;

	@SerializedName("name")
	private String name;

	@SerializedName("id_tour")
	private int idTour;

	@SerializedName("id")
	private int id;

	@SerializedName("desc")
	private String desc;

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
			",desc = '" + desc + '\'' + 
			"}";
		}
}