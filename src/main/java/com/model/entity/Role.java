package com.model.entity;

import java.math.BigDecimal;

public class Role {
	
	private BigDecimal id;
	private String name;
	
	public Role(BigDecimal id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public BigDecimal getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
}
