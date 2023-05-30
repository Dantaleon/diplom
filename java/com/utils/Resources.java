package com.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
