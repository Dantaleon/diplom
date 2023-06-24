package com.utils;

import java.math.BigDecimal;

public class TransferPiece {
	
	private BigDecimal id;

	public TransferPiece(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}
}
