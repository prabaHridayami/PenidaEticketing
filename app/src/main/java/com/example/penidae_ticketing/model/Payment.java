package com.example.penidae_ticketing.model;

import com.google.gson.annotations.SerializedName;

public class Payment{

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	@SerializedName("hotelPayment")
	private HotelPayment hotelPayment;

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

	public void setHotelPayment(HotelPayment hotelPayment){
		this.hotelPayment = hotelPayment;
	}

	public HotelPayment getHotelPayment(){
		return hotelPayment;
	}

	@Override
 	public String toString(){
		return 
			"Payment{" + 
			"error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			",hotelPayment = '" + hotelPayment + '\'' + 
			"}";
		}
}