package com.logic.commandAJAX;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntity;
import com.logic.abstraction.IEnumFactoryEntityAJAX;
import com.logic.enums.CommandEnumAdminAJAX;
import com.logic.enums.CommandEnumGuestAJAX;
import com.logic.enums.CommandEnumUserAJAX;
import com.logic.enums.RoleEnums;
import com.utils.Resources;

public class HeaderIncludeCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response,
			IEnumFactoryEntityAJAX specEnum) throws ServletException, IOException {
		
		String role = Resources.getRoleStr(request);
		
		if (role.equals(RoleEnums.administrator.name())) {
			
			request.getRequestDispatcher(CommandEnumAdminAJAX.HeaderInclude.getView())
				.include(request, response);
			
		} else if (role.equals(RoleEnums.user.name())) {
			
			request.getRequestDispatcher(CommandEnumUserAJAX.HeaderInclude.getView())
				.include(request, response);

		} else {
			
			request.getRequestDispatcher(CommandEnumGuestAJAX.HeaderInclude.getView())
			.include(request, response);
		}
		
		
		
	}
	
	
	
}
