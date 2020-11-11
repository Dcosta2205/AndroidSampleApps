package com.xyz.networkcalls.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class RegisterUserRequestModel implements Serializable {

	@SerializedName("email")
	private String email;

	@SerializedName("password")
	private String password;

	public String getEmail(){
		return email;
	}

	public String getPassword(){
		return password;
	}

	public RegisterUserRequestModel(String email, String password) {
		this.email = email;
		this.password = password;
	}
}