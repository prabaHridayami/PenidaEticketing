package com.example.penidae_ticketing.model;

import com.google.gson.annotations.SerializedName;

public class Payment{

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	@SerializedName("payment")
	private Integer payment;

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

	public void setPayment(Integer payment){
		this.payment = payment;
	}

	public Integer getPayment(){
		return payment;
	}

	@Override
 	public String toString(){
		return 
			"Payment{" + 
			"error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			",payment = '" + payment + '\'' +
			"}";
		}
}