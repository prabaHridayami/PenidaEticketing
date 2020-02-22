package com.example.penidae_ticketing.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class TransTour{

	@SerializedName("trans_tour")
	private List<TransTourItem> transTour;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setTransTour(List<TransTourItem> transTour){
		this.transTour = transTour;
	}

	public List<TransTourItem> getTransTour(){
		return transTour;
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
			"TransTour{" + 
			"trans_tour = '" + transTour + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}