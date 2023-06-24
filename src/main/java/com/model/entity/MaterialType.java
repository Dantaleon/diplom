package com.model.entity;

import java.math.BigDecimal;

public class MaterialType {
	
	private BigDecimal id;
	private String name;
	private Double multiplier;
	
	public MaterialType(BigDecimal id, String name, Double multiplier) {
		super();
		this.id = id;
		this.name = name;
		this.multiplier = multiplier;
	}
	public MaterialType(String name, Double multiplier) {
		super();
		this.name = name;
		this.multiplier = multiplier;
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
	public Double getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(Double multiplier) {
		this.multiplier = multiplier;
	}
}
