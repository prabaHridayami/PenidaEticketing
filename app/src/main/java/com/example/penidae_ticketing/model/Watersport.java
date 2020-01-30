package com.example.penidae_ticketing.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class Watersport{

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	@SerializedName("watersport")
	private List<WatersportItem> watersport;

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

	public void setWatersport(List<WatersportItem> watersport){
		this.watersport = watersport;
	}

	public List<WatersportItem> getWatersport(){
		return watersport;
	}

	@Override
 	public String toString(){
		return 
			"Watersport{" + 
			"error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			",watersport = '" + watersport + '\'' + 
			"}";
		}
}