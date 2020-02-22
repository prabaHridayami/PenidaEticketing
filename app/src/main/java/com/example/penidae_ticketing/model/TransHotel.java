package com.example.penidae_ticketing.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TransHotel{

	@SerializedName("trans_hotel")
	private List<TransHotelItem> transHotel;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setTransHotel(List<TransHotelItem> transHotel){
		this.transHotel = transHotel;
	}

	public List<TransHotelItem> getTransHotel(){
		return transHotel;
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
			"TransHotel{" + 
			"trans_hotel = '" + transHotel + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}