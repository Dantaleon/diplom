package com.logic.command;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessor;
import com.logic.abstraction.IEnumFactoryEntity;
import com.logic.enums.CommandNameEnum;
import com.logic.enums.RoleEnums;
import com.model.dao.UserDAO;
import com.model.entity.User;
import com.utils.NextPage;

public class LoginCP implements ICommandProcessor {
	
	private UserDAO userDao = new UserDAO();
	
	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response, 
			NextPage nextPage, IEnumFactoryEntity specEnum) {
		
		String reqMethod = request.getMethod();
		System.out.println("Method: " + reqMethod);
		
		if (reqMethod.equals("GET") && !reqMethod.equals("POST")) {
			
			nextPage.setRedirectType(NextPage.REDIRECT_TYPE_FORWARD);
			nextPage.setPage(specEnum.getMyView(CommandNameEnum.Login.name()));
			
			return nextPage;
		}
		
		System.out.println("REACH AFTER IF");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		
		StringBuilder error = new StringBuilder();
		StringBuilder noError = new StringBuilder();
		
		if (email == null || email.trim().isEmpty()) {
			error.append("Эл. почта не введена <br />");
		}
		if (password == null || password.trim().isEmpty()) {
			error.append("Пароль не введен <br />");
		}
		if (!(userDao.isUserEmailExist(email)) ) {
			error.append("Эл. почта не зарегистрирована <br />");
		}
		if (error.length() > 0) {
			request.setAttribute("error", error);
		}
			
		if(userDao.checkUserOnLogin(email, password)) {
			
			BigDecimal id = userDao.getUserIdByEmail(email);
			User user = userDao.getRecordByPK(id);
			
			String role = userDao.getTopRole(id);
			
			request.getSession().setAttribute("id", user.getId());
			request.getSession().setAttribute("role", role);
			request.getSession().setAttribute("nickname", user.getNickname());
			System.out.println(request.getSession().getAttribute("id"));
			System.out.println(request.getSession().getAttribute("role"));
		} else {
			error.append("Логин или пароль не верны");
			request.setAttribute("error", error);
		}
		
		
		
		nextPage.setRedirectType(NextPage.REDIRECT_TYPE_FORWARD);
		nextPage.setPage(specEnum.getMyView(CommandNameEnum.Home.name()));
		
		return nextPage;
	}
	
}