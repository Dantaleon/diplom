package com.model.entity;

import java.math.BigDecimal;

public class Piece {
	
	private BigDecimal id;
	private String name;
	
	private String base64Img;
	
	private int picx;
	private int picy;
	private int picw;
	private int pich;
	private int defaultw;
	private int defaulth;
	
	private Double cost;
	
	private int realw;
	private int realh;
	private int realth;
	
	private MaterialType materialType;
	private PieceType pieceType;
	
	public Piece(BigDecimal id, String name, String base64Img, int picx, int picy, int picw, int pich, int defaultw,
			int defaulth, Double cost, int realw, int realh, int realth, MaterialType materialType,
			PieceType pieceType) {
		this.id = id;
		this.name = name;
		this.base64Img = base64Img;
		this.picx = picx;
		this.picy = picy;
		this.picw = picw;
		this.pich = pich;
		this.defaultw = defaultw;
		this.defaulth = defaulth;
		this.cost = cost;
		this.realw = realw;
		this.realh = realh;
		this.realth = realth;
		this.materialType = materialType;
		this.pieceType = pieceType;
	}

	public Piece(String name, String base64Img, int picx, int picy, int picw, int pich, int defaultw, int defaulth,
			Double cost, int realw, int realh, int realth, MaterialType materialType, PieceType pieceType) {
		super();
		this.name = name;
		this.base64Img = base64Img;
		this.picx = picx;
		this.picy = picy;
		this.picw = picw;
		this.pich = pich;
		this.defaultw = defaultw;
		this.defaulth = defaulth;
		this.cost = cost;
		this.realw = realw;
		this.realh = realh;
		this.realth = realth;
		this.materialType = materialType;
		this.pieceType = pieceType;
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

	public String getBase64Img() {
		return base64Img;
	}

	public void setBase64Img(String base64Img) {
		this.base64Img = base64Img;
	}

	public int getPicx() {
		return picx;
	}

	public void setPicx(int picx) {
		this.picx = picx;
	}

	public int getPicy() {
		return picy;
	}

	public void setPicy(int picy) {
		this.picy = picy;
	}

	public int getPicw() {
		return picw;
	}

	public void setPicw(int picw) {
		this.picw = picw;
	}

	public int getPich() {
		return pich;
	}

	public void setPich(int pich) {
		this.pich = pich;
	}

	public int getDefaultw() {
		return defaultw;
	}

	public void setDefaultw(int defaultw) {
		this.defaultw = defaultw;
	}

	public int getDefaulth() {
		return defaulth;
	}

	public void setDefaulth(int defaulth) {
		this.defaulth = defaulth;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
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

	public MaterialType getMaterialType() {
		return materialType;
	}

	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
	}

	public PieceType getPieceType() {
		return pieceType;
	}

	public void setPieceType(PieceType pieceType) {
		this.pieceType = pieceType;
	}	
}
