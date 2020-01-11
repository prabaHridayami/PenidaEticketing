package com.example.penidae_ticketing.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TourPack{

	@SerializedName("tour_package")
	private List<TourPackageItem> tourPackage;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setTourPackage(List<TourPackageItem> tourPackage){
		this.tourPackage = tourPackage;
	}

	public List<TourPackageItem> getTourPackage(){
		return tourPackage;
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
			"TourPack{" + 
			"tour_package = '" + tourPackage + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}