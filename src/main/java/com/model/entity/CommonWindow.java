package com.model.entity;

import java.math.BigDecimal;

public class CommonWindow {
	
	private BigDecimal id;
	private String name;
	private int realw;
	private int realh;
	private int realth;
	
	public CommonWindow(BigDecimal id, String name, int realw, int realh, int realth) {
		this.id = id;
		this.name = name;
		this.realw = realw;
		this.realh = realh;
		this.realth = realth;
	}
	
	public CommonWindow(String name, int realw, int realh, int realth) {
		this.name = name;
		this.realw = realw;
		this.realh = realh;
		this.realth = realth;
	}
	
	
	
	public CommonWindow(int realw, int realh, int realth) {
		this.realw = realw;
		this.realh = realh;
		this.realth = realth;
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

	public int getRealw() {
		return realw;
	}

	public void setRealw(int realw) {
		this.realw = realw;
	}

	public int getRealh() {
		return realh;
	}

	public void setRealh(int realh) {
		this.realh = realh;
	}

	public int getRealth() {
		return realth;
	}

	public void setRealth(int realth) {
		this.realth = realth;
	}
	
}
