package com.example.penidae_ticketing.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TransVehicle{

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	@SerializedName("trans_rent")
	private List<TransRentItem> transRent;

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

	public void setTransRent(List<TransRentItem> transRent){
		this.transRent = transRent;
	}

	public List<TransRentItem> getTransRent(){
		return transRent;
	}

	@Override
 	public String toString(){
		return 
			"TransVehicle{" + 
			"error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			",trans_rent = '" + transRent + '\'' + 
			"}";
		}
}