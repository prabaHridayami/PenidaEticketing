package com.example.penidae_ticketing.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TransWatersport{

	@SerializedName("trans_watersport")
	private List<TransWatersportItem> transWatersport;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setTransWatersport(List<TransWatersportItem> transWatersport){
		this.transWatersport = transWatersport;
	}

	public List<TransWatersportItem> getTransWatersport(){
		return transWatersport;
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
			"TransWatersport{" + 
			"trans_watersport = '" + transWatersport + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}