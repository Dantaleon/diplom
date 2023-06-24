package com.model.entity;

import java.math.BigDecimal;

public class Cart {
	
	private BigDecimal id;
	private User user;
	private Double sum_cost;
	
	public Cart(BigDecimal id, User user, Double sum_cost) {
		this.id = id;
		this.user = user;
		this.sum_cost = sum_cost;
	}

	public Cart(User user, Double sum_cost) {
		this.user = user;
		this.sum_cost = sum_cost;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getSum_cost() {
		return sum_cost;
	}

	public void setSum_cost(Double sum_cost) {
		this.sum_cost = sum_cost;
	}
}
