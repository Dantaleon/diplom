package com.logic.commandAJAX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntityAJAX;
import com.logic.enums.CommandNameEnum;
import com.model.dao.RoleDAO;
import com.model.entity.Role;
import com.utils.NextPage;

public class TableRoleCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response,
			IEnumFactoryEntityAJAX specEnum) throws ServletException, IOException {
		
		RoleDAO roleDao = new RoleDAO();
		
		response.setContentType(NextPage.CONTENT_TYPE_JSP);
		
		ArrayList<Role> roles = new ArrayList<Role>();
		
		roles = roleDao.getAllRecords();
		
		request.setAttribute("roleList", roles);
		
		request.getRequestDispatcher(specEnum.getMyView(CommandNameEnum.TableRole.name())).forward(request, response);
		//request.getRequestDispatcher("WEB-INF/jsp/tableViewTemplates/RoleTemplate.jsp").forward(request, response);
	}

}
