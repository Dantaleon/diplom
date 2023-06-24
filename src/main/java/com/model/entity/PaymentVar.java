package com.model.entity;

import java.math.BigDecimal;

public class PaymentVar {
	
	private BigDecimal id;
	private String name;
	
	public PaymentVar(BigDecimal id, String name) {
		this.id = id;
		this.name = name;
	}

	public PaymentVar(String name) {
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
