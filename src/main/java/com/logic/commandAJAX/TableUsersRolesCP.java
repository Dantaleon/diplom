package com.logic.commandAJAX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntityAJAX;
import com.logic.enums.CommandNameEnum;
import com.model.dao.UsersRolesDAO;
import com.model.entity.UsersRoles;
import com.utils.NextPage;

public class TableUsersRolesCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, IEnumFactoryEntityAJAX specEnum)
			throws ServletException, IOException {
		
		UsersRolesDAO usersRolesDao = new UsersRolesDAO();
		
		response.setContentType(NextPage.CONTENT_TYPE_JSP);
		
		ArrayList<UsersRoles> usersRolesList = new ArrayList<UsersRoles>();
		
		usersRolesList = usersRolesDao.getAllRecords();
		
		request.setAttribute("usersRolesList", usersRolesList);
		
		request.getRequestDispatcher(specEnum.getMyView(CommandNameEnum.TableUsersRoles.name())).forward(request, response);
	}
}
