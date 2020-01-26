package com.example.penidae_ticketing.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Vehicle{

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	@SerializedName("vehicle")
	private List<VehicleItem> vehicle;

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

	public void setVehicle(List<VehicleItem> vehicle){
		this.vehicle = vehicle;
	}

	public List<VehicleItem> getVehicle(){
		return vehicle;
	}

	@Override
 	public String toString(){
		return 
			"Vehicle{" + 
			"error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			",vehicle = '" + vehicle + '\'' + 
			"}";
		}
}