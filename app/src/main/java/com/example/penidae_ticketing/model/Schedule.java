package com.example.penidae_ticketing.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Schedule{

	@SerializedName("schedule")
	private List<ScheduleItem> schedule;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setSchedule(List<ScheduleItem> schedule){
		this.schedule = schedule;
	}

	public List<ScheduleItem> getSchedule(){
		return schedule;
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
			"Schedule{" + 
			"schedule = '" + schedule + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}