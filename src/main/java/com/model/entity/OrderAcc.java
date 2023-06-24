package com.model.entity;

import java.math.BigDecimal;

public class OrderAcc {
	
	private BigDecimal id;
	private double sum_cost;
	private String dateOform;
	private String dateDeliver;
	private PaymentVar paymentVar;
	private User user;
	private OrderStatus orderStatus;
	
	public OrderAcc(BigDecimal id, double sum_cost, String dateOform, String dateDeliver, PaymentVar paymentVar,
			User user, OrderStatus orderStatus) {
		this.id = id;
		this.sum_cost = sum_cost;
		this.dateOform = dateOform;
		this.dateDeliver = dateDeliver;
		this.paymentVar = paymentVar;
		this.user = user;
		this.orderStatus = orderStatus;
	}

	public OrderAcc(double sum_cost, String dateOform, String dateDeliver, PaymentVar paymentVar, User user,
			OrderStatus orderStatus) {
		this.sum_cost = sum_cost;
		this.dateOform = dateOform;
		this.dateDeliver = dateDeliver;
		this.paymentVar = paymentVar;
		this.user = user;
		this.orderStatus = orderStatus;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public double getSum_cost() {
		return sum_cost;
	}

	public void setSum_cost(double sum_cost) {
		this.sum_cost = sum_cost;
	}

	public String getDateOform() {
		return dateOform;
	}

	public void setDateOform(String dateOform) {
		this.dateOform = dateOform;
	}

	public String getDateDeliver() {
		return dateDeliver;
	}

	public void setDateDeliver(String dateDeliver) {
		this.dateDeliver = dateDeliver;
	}

	public PaymentVar getPaymentVar() {
		return paymentVar;
	}

	public void setPaymentVar(PaymentVar paymentVar) {
		this.paymentVar = paymentVar;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
}
