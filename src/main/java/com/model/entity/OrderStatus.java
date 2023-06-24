package com.model.entity;

import java.math.BigDecimal;

public class OrderStatus {
	
	private BigDecimal id;
	private String name;
	
	public OrderStatus(BigDecimal id, String name) {
		this.id = id;
		this.name = name;
	}

	public OrderStatus(String name) {
		this.name = name;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
