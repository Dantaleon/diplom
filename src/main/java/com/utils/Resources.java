package com.utils;

import javax.servlet.http.HttpServletRequest;

public class Resources {
	
	public static final String ROLE_GUEST = "guest";
	public static final String ROLE_USER = "user";
	public static final String ROLE_ADMIN = "administrator";
	
	public static String getRoleStr(HttpServletRequest request) {
		
		String role = (String) request.getSession().getAttribute("role");
		
		if (role == null || role.trim().isEmpty()) {
			return ROLE_GUEST;
		}
		return role;
	}
}
