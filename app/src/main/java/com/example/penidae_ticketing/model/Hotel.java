package com.example.penidae_ticketing.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Hotel{

	@SerializedName("hotel")
	private List<HotelItem> hotel;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setHotel(List<HotelItem> hotel){
		this.hotel = hotel;
	}

	public List<HotelItem> getHotel(){
		return hotel;
	}

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"Hotel{" + 
			"hotel = '" + hotel + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}