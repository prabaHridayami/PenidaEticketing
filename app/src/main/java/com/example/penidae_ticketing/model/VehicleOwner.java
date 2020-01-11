package com.example.penidae_ticketing.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class VehicleOwner{

	@SerializedName("owner")
	private List<OwnerItem> owner;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setOwner(List<OwnerItem> owner){
		this.owner = owner;
	}

	public List<OwnerItem> getOwner(){
		return owner;
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
			"VehicleOwner{" + 
			"owner = '" + owner + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}