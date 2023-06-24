package com.model.entity;

public class UserDetails {
	
	private User user;
	private String city;
	private String address;
	private String phone;
	
	public UserDetails(User user, String city, String address, String phone) {
		this.user = user;
		this.city = city;
		this.address = address;
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}	
}
