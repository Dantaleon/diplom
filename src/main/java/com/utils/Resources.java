package com.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.logic.enums.RoleEnums;

public class Resources {
	
	public static String getRoleStr(HttpServletRequest request) {
		
		String role = (String) request.getSession().getAttribute("role");
		
		if (role == null || role.trim().isEmpty()) {
			request.getSession().setAttribute("role", RoleEnums.guest.name());
			return RoleEnums.guest.name();
		}
		return role;
	}
	
	public static void freeRes(PreparedStatement pstm, Connection conn, 
			ResultSet resultSet) {
		try {
			if (pstm != null) pstm.close();
			if (conn != null) conn.close();
			if (resultSet != null) resultSet.close();
		}catch(SQLException sqle) {
			System.out.println("Ошибка во время освобождения ресурсов DB");
			sqle.printStackTrace();
		}
	}
	
	public static void freeRes(Statement stm, Connection conn,
			ResultSet resultSet) {
		try {
			if (stm != null) stm.close();
			if (conn != null) conn.close();
			if (resultSet != null) resultSet.close();
		}catch(SQLException sqle) {
			System.out.println("Ошибка во время освобождения ресурсов DB");
			sqle.printStackTrace();
		}
	}
	
	public static String timestampToString(Timestamp timestamp) {
		
		Date date = new Date(timestamp.getTime());
		
		return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(date);
		
	}
	
	public static Timestamp stringToTimestamp(String timeStr) {
		
		try {
			
			Date date = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse(timeStr);
			Timestamp timestamp = new Timestamp(date.getTime());
			
			return timestamp;
		} catch (ParseException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	public static Timestamp getCurrentTimeTimestamp() {

		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
   
	}
	
	public static Timestamp getTimestampIncDays(Timestamp timestamp, int days) {
		
		Date date = new Date(timestamp.getTime());
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		Date newDate = cal.getTime();
		
		return new Timestamp(newDate.getTime());
	}
	
	public static void freeRes(PreparedStatement pstm, Connection conn) {
		try {
			if (pstm != null) pstm.close();
			if (conn != null) conn.close();
		}catch(SQLException sqle) {
			System.out.println("Ошибка во время освобождения ресурсов DB");
			sqle.printStackTrace();
		}
		
	}
}
