package com.logic.commandAJAX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntityAJAX;
import com.logic.enums.CommandNameEnum;
import com.model.dao.CommonWindowDAO;
import com.model.dao.RoleDAO;
import com.model.entity.CommonWindow;
import com.model.entity.Role;
import com.utils.NextPage;

public class TableCommonWindowCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, IEnumFactoryEntityAJAX specEnum)
			throws ServletException, IOException {

		CommonWindowDAO commonWindowDAO = new CommonWindowDAO();

		response.setContentType(NextPage.CONTENT_TYPE_JSP);

		ArrayList<CommonWindow> commonWindows = new ArrayList<CommonWindow>();

		commonWindows = commonWindowDAO.getAllRecords();

		request.setAttribute("commonWindows", commonWindows);

		request.getRequestDispatcher(specEnum.getMyView(CommandNameEnum.TableCommonWindow.name())).forward(request,
				response);
	}
}
