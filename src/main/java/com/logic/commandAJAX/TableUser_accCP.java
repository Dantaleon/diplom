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
import com.model.dao.UserDAO;
import com.model.entity.User;
import com.utils.NextPage;

public class TableUser_accCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response,
			IEnumFactoryEntityAJAX specEnum) throws ServletException, IOException {
		
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
			request.getRequestDispatcher("/tableViewTemplates/User_accTemplate.jsp").forward(request, response);
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
		}
		return;
	}
	
}
