package com.model.entity;

import java.math.BigDecimal;

public class CartsItems {
	
	private BigDecimal id;
	private int amount;
	private Cart cart;
	private CommonWindow commonWindow;
	
	public CartsItems(BigDecimal id, int amount, Cart cart, CommonWindow commonWindow) {
		super();
		this.id = id;
		this.amount = amount;
		this.cart = cart;
		this.commonWindow = commonWindow;
	}

	public CartsItems(int amount, Cart cart, CommonWindow commonWindow) {
		super();
		this.amount = amount;
		this.cart = cart;
		this.commonWindow = commonWindow;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public CommonWindow getCommonWindow() {
		return commonWindow;
	}

	public void setCommonWindow(CommonWindow commonWindow) {
		this.commonWindow = commonWindow;
	}
	
	
}
