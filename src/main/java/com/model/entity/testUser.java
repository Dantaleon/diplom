package com.model.entity;

import java.math.BigDecimal;

import com.model.abstraction.HasGenericCompPK;
import com.model.abstraction.HasGenericPK;
import com.model.dao.UserDAO;

public class testUser implements HasGenericPK<BigDecimal> {
	
	private BigDecimal pkey;
	private String name;
	
	public testUser(BigDecimal pkey, String name) {
		this.pkey = pkey;
		this.name = name;
	}
	
	@Override
	public Object getRecordByPK(BigDecimal pkey) {
		
		return new testUser((BigDecimal)pkey, "nick").toString();
	}
	
	
	public String toString() {
		return pkey + " " + name;
	}
	// DEPENDENCY INVERSION PRINCIPLE
	public static void main(String[] args) {
		
		HasGenericPK<BigDecimal> dao = new UserDAO();
		outRecord(dao);
	}
	
	public static void outRecord(HasGenericPK<BigDecimal> record) {
		
		System.out.println(record.getRecordByPK(BigDecimal.valueOf(1)));
		System.out.println(record.getRecordByPK(BigDecimal.valueOf(2)));
		System.out.println(record.getRecordByPK(BigDecimal.valueOf(3)));
	}
}

