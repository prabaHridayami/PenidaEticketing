package com.example.penidae_ticketing.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TransBoat{

	@SerializedName("trans_boat")
	private List<TransBoatItem> transBoat;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setTransBoat(List<TransBoatItem> transBoat){
		this.transBoat = transBoat;
	}

	public List<TransBoatItem> getTransBoat(){
		return transBoat;
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
			"TransBoat{" + 
			"trans_boat = '" + transBoat + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}