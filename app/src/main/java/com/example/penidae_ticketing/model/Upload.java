package com.example.penidae_ticketing.model;

import com.google.gson.annotations.SerializedName;

public class Upload{

	@SerializedName("name")
	private String name;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	@SerializedName("url")
	private String url;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
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

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"Upload{" + 
			"name = '" + name + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}