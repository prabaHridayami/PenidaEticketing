package com.example.penidae_ticketing.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Attraction{

	@SerializedName("attraction")
	private List<AttractionItem> attraction;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setAttraction(List<AttractionItem> attraction){
		this.attraction = attraction;
	}

	public List<AttractionItem> getAttraction(){
		return attraction;
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
			"Attraction{" + 
			"attraction = '" + attraction + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}