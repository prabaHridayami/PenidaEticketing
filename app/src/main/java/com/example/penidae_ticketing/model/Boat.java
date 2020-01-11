package com.example.penidae_ticketing.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Boat{

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	@SerializedName("boat")
	private List<BoatItem> boat;

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

	public void setBoat(List<BoatItem> boat){
		this.boat = boat;
	}

	public List<BoatItem> getBoat(){
		return boat;
	}

	@Override
 	public String toString(){
		return 
			"Boat{" + 
			"error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			",boat = '" + boat + '\'' + 
			"}";
		}
}