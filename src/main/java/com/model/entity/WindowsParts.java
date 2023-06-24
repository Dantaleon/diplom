package com.model.entity;

import java.math.BigDecimal;

public class WindowsParts {
	
	private BigDecimal id;
	private CommonWindow commonWindow;
	private Piece piece;
	
	public WindowsParts(BigDecimal id, CommonWindow commonWindow, Piece piece) {
		this.id = id;
		this.commonWindow = commonWindow;
		this.piece = piece;
	}

	public WindowsParts(CommonWindow commonWindow, Piece piece) {
		this.commonWindow = commonWindow;
		this.piece = piece;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public CommonWindow getCommonWindow() {
		return commonWindow;
	}

	public void setCommonWindow(CommonWindow commonWindow) {
		this.commonWindow = commonWindow;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
}
