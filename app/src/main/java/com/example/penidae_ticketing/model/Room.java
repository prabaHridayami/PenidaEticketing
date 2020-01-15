package com.example.penidae_ticketing.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Room{

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public ArrayList<Integer> getId_room() {
		return id_room;
	}

	public void setId_room(ArrayList<Integer> id_room) {
		this.id_room = id_room;
	}

	@SerializedName("id_room")
	private ArrayList<Integer> id_room;

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