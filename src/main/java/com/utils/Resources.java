package com.utils;

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
}
