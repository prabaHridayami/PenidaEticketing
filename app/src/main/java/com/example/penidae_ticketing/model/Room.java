package com.example.penidae_ticketing.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Room{

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	@SerializedName("room")
	private List<RoomItem> room;

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

	public void setRoom(List<RoomItem> room){
		this.room = room;
	}

	public List<RoomItem> getRoom(){
		return room;
	}

	@Override
 	public String toString(){
		return 
			"Room{" + 
			"error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			",room = '" + room + '\'' + 
			"}";
		}
}