package com.logic.command;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessor;
import com.logic.abstraction.IEnumFactoryEntity;
import com.logic.enums.CommandNameEnum;
import com.model.dao.WindowsPartsDAO;
import com.model.entity.WindowsParts;
import com.utils.NextPage;

public class WindowDetailsCP implements ICommandProcessor {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response, NextPage nextPage,
			IEnumFactoryEntity specEnum) {
		BigDecimal wid = new BigDecimal(request.getParameter("wid").toString());
		WindowsPartsDAO windowsPartsDao = new WindowsPartsDAO();
		
		response.setContentType(NextPage.CONTENT_TYPE_JSP);
		
		ArrayList<WindowsParts> windowsPartsList = new ArrayList<WindowsParts>();
		windowsPartsList = windowsPartsDao.getRecordsByWindowId(wid);
		
		request.setAttribute("windowsPartsList", windowsPartsList);
		
		nextPage.setRedirectType(NextPage.REDIRECT_TYPE_FORWARD);
		nextPage.setPage(specEnum.getMyView(CommandNameEnum.WindowDetails.name()));
		return nextPage;
	}

}
