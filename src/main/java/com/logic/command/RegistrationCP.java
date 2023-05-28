package com.logic.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessor;
import com.logic.abstraction.IEnumFactoryEntity;
import com.logic.enums.CommandNameEnum;
import com.model.dao.UserDAO;
import com.model.entity.User;
import com.utils.NextPage;

public class RegistrationCP implements ICommandProcessor {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response, NextPage nextPage,
			IEnumFactoryEntity specEnum) {
		
		UserDAO userDao = new UserDAO();
		
		if (request.getMethod().equals("GET") && !request.getMethod().equals("POST")) {
			
			nextPage.setRedirectType(NextPage.REDIRECT_TYPE_FORWARD);
			nextPage.setPage(specEnum.getMyView(CommandNameEnum.Registration.name()));
			
			return nextPage;
		}
		
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		
		StringBuilder error = new StringBuilder();
		StringBuilder noError = new StringBuilder();
		
		if (nickname == null || nickname.trim().isEmpty()) {
			error.append("Имя пользователя не введено <br />");
		}
		if (email == null || email.trim().isEmpty()) {
			error.append("Эл. почта не введена <br />");
		}
		if (password == null || password.trim().isEmpty()) {
			error.append("Пароль не введен <br />");
		}
		if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
			error.append("Подтверждание пароля не введено <br />");
		}
		if (!password.equals(confirmPassword)) {
			error.append("Пароль и его подтверждение не совпадают <br />");
		}
		if (userDao.isUserEmailExist(email)) {
			error.append("Эл. почта уже зарегистрирована");
		}
		if (error.length() > 0) {
			request.setAttribute("error", error);
		
		} else {
			User user = new User(nickname, email, password);
			
			try {
				userDao.registerUser(user);
			}catch(Exception e) {
				e.printStackTrace();
			}
			noError.append("Аккаунт успешно зарегистрирован");
			request.setAttribute("noError", noError);
		
		}
		
		nextPage.setRedirectType(NextPage.REDIRECT_TYPE_FORWARD);
		nextPage.setPage(specEnum.getMyView(CommandNameEnum.Registration.name()));
		
		return nextPage;
		
	}

}
