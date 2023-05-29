package com.logic.commandAJAX;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntity;
import com.logic.abstraction.IEnumFactoryEntityAJAX;
import com.logic.controller.ControllerAJAX;
import com.logic.enums.CommandEnumAdmin;
import com.logic.enums.CommandNameEnum;
import com.model.dao.UserDAO;
import com.model.entity.User;
import com.utils.NextPage;

public class TableUser_accCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response,
			IEnumFactoryEntityAJAX specEnum) throws ServletException, IOException {

		response.setCharacterEncoding(NextPage.CONTENT_CHARSET_UTF8);
		
		String id = request.getParameter("id");
		String subCommand = (String) request.getParameter("sub");
		System.out.println("id: " + id + " subCommand: " + subCommand );
		UserDAO userDao = new UserDAO();
		if (request.getParameter("sub") == null || request.getParameter("sub").equals("")) {
			
			response.setContentType(NextPage.CONTENT_TYPE_JSP);
			
			ArrayList<User> users = new ArrayList<User>();
			
			users = userDao.getAllRecords();
			
			String jsonUsers = userDao.getAllRecordsJSON(users);
			
			request.setAttribute("userList", users);
			request.setAttribute("userListJSON", jsonUsers);
			request.getRequestDispatcher("WEB-INF/jsp/tableViewTemplates/User_accTemplate.jsp").forward(request, response);
			return;
		} 
		else if (request.getParameter("sub").equals("getById") && 
				(request.getParameter("id") != null || !request.getParameter("id").equals(""))) {
			
			response.setContentType(NextPage.CONTENT_TYPE_JSON);
			System.out.println("INSIDE edit if");
			User userObj = userDao.getRecordByPK(new BigDecimal((String)request.getParameter("id")));
			String userJSON = userDao.getRecordJSON(userObj);
			
			
			response.getWriter().write(userJSON);
			return;
		} else if (request.getParameter("sub").equals("postNew") &&
				isValidUser_acc(request)) 
		{
			System.out.println("IN THE RIGHT PLACE");
			User user = new User(new BigDecimal(request.getParameter("newId")),
					request.getParameter("newNickname"),
					request.getParameter("newEmail"),
					request.getParameter("newPassword"));
			userDao.registerUser(user);
			
			request.setAttribute("noError", new String("Регистрация успешна"));
			response.setContentType(NextPage.CONTENT_TYPE_JSP);
			request.getRequestDispatcher("WEB-INF/jsp/tableViewTemplates/User_accTemplate.jsp")
				.forward(request, response);
			return;
		}else {
			response.setContentType(NextPage.CONTENT_TYPE_TEXT);
			response.getWriter().write("invalid command or subcommand");
		}
	}
	
	private boolean isValidUser_acc(HttpServletRequest request) {
		
		boolean isValid = false;
		
		UserDAO userDao = new UserDAO();
		BigDecimal id;
		try {
			id = new BigDecimal((String)request.getParameter("newId"));
		}catch(NumberFormatException nfe) {
			id = BigDecimal.ZERO;
		}
		String nickname = request.getParameter("newNickname");
		String email = request.getParameter("newEmail");
		String password = request.getParameter("newPassword");
		
		StringBuilder error = new StringBuilder();
		
		if (id == null || userDao.getUserIdByEmail(email) != BigDecimal.ZERO) {
			error.append("id уже зарегистрирован");
		}
		if (nickname == null || nickname.trim().isEmpty()) {
			error.append("Имя пользователя не введено <br />");
		}
		if (email == null || email.trim().isEmpty()) {
			error.append("Эл. почта не введена <br />");
		}
		if (password == null || password.trim().isEmpty()) {
			error.append("Пароль не введен <br />");
		}
		if (userDao.isUserEmailExist(email)) {
			error.append("Эл. почта уже зарегистрирована");
		}
		if (error.length() > 0) {
			request.setAttribute("error", error);
		
		} else {
			isValid = true;
		}
		
		return isValid;
		
	}
	
	
}
