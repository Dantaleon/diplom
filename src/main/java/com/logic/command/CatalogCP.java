package com.logic.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessor;
import com.logic.abstraction.IEnumFactoryEntity;
import com.logic.enums.CommandNameEnum;
import com.model.dao.WindowsPartsDAO;
import com.model.entity.WindowsParts;
import com.utils.NextPage;

public class CatalogCP implements ICommandProcessor {
	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response,
			NextPage nextPage, IEnumFactoryEntity specEnum) {
		WindowsPartsDAO windowsPartsDao = new WindowsPartsDAO();
		
		response.setContentType(NextPage.CONTENT_TYPE_JSP);
		
		ArrayList<WindowsParts> distinctWindowsList = new ArrayList<WindowsParts>();
		ArrayList<WindowsParts> windowsPartsList = new ArrayList<WindowsParts>();
		
		distinctWindowsList = windowsPartsDao.getAllRecordsDistinctWindowId();
		windowsPartsList = windowsPartsDao.getAllRecords();
		
		request.setAttribute("distinctWindowsList", distinctWindowsList);
		request.setAttribute("windowsPartsList", windowsPartsList);
		
		nextPage.setRedirectType(NextPage.REDIRECT_TYPE_FORWARD);
		nextPage.setPage(specEnum.getMyView(CommandNameEnum.Catalog.name()));
		return nextPage;
	}
}