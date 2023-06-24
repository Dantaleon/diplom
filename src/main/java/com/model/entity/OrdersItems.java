package com.model.entity;

import java.math.BigDecimal;

public class OrdersItems {
	
	private BigDecimal id;
	private int amount;
	private OrderAcc orderAcc;
	private CommonWindow commonWindow;
	
	public OrdersItems(BigDecimal id, int amount, OrderAcc orderAcc, CommonWindow commonWindow) {
		this.id = id;
		this.amount = amount;
		this.orderAcc = orderAcc;
		this.commonWindow = commonWindow;
	}

	public OrdersItems(int amount, OrderAcc orderAcc, CommonWindow commonWindow) {
		this.amount = amount;
		this.orderAcc = orderAcc;
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

	public OrderAcc getOrderAcc() {
		return orderAcc;
	}

	public void setOrderAcc(OrderAcc orderAcc) {
		this.orderAcc = orderAcc;
	}

	public CommonWindow getCommonWindow() {
		return commonWindow;
	}

	public void setCommonWindow(CommonWindow commonWindow) {
		this.commonWindow = commonWindow;
	}
}
