package com.logic.commandAJAX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntityAJAX;
import com.logic.enums.CommandNameEnum;
import com.model.dao.UserDetailsDAO;
import com.model.entity.UserDetails;
import com.utils.NextPage;

public class TableUserDetailsCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, IEnumFactoryEntityAJAX specEnum)
			throws ServletException, IOException {
		
		UserDetailsDAO userDetailsDao = new UserDetailsDAO();
		
		response.setContentType(NextPage.CONTENT_TYPE_JSP);
		
		ArrayList<UserDetails> userDetailsList = new ArrayList<UserDetails>();
		
		userDetailsList = userDetailsDao.getAllRecords();
		
		request.setAttribute("userDetailsList", userDetailsList);
		
		request.getRequestDispatcher(specEnum.getMyView(CommandNameEnum.TableUserDetails.name())).forward(request, response);
	}
}
