package com.xyz.networkcalls.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class RegisterResponseModel implements Serializable {

	@SerializedName("email")
	private String email;

	@SerializedName("password")
	private String password;

	@SerializedName("id")
	private int id;

	public String getEmail(){
		return email;
	}

	public String getPassword(){
		return password;
	}

	public int getId(){
		return id;
	}
}