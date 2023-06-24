package com.model.entity;

import java.math.BigDecimal;

import org.json.JSONObject;
import org.json.JSONString;

public class User implements JSONString {
	
	private BigDecimal id;
	private String nickname;
	private String email;
	private String password;
	
	// Constructors
	// Constructor without parameters
	public User() {
		super();
	}
	// Constructor with all parameters
	public User(BigDecimal id, String nickname, String email, String password) {
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
	}
	// Constructor without id parameter
	public User(String nickname, String email, String password) {
		this.nickname = nickname;
		this.email = email;
		this.password = password;
	}
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toJSONString() {
		JSONObject jsObj = new JSONObject(this);
		return jsObj.toString();
	}	
}
