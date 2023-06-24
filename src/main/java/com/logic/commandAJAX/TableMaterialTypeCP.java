package com.logic.commandAJAX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntityAJAX;
import com.logic.enums.CommandNameEnum;
import com.model.dao.MaterialTypeDAO;
import com.model.dao.RoleDAO;
import com.model.entity.MaterialType;
import com.model.entity.Role;
import com.utils.NextPage;

public class TableMaterialTypeCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, IEnumFactoryEntityAJAX specEnum)
			throws ServletException, IOException {
		
		MaterialTypeDAO materialTypeDao = new MaterialTypeDAO();
		
		response.setContentType(NextPage.CONTENT_TYPE_JSP);
		
		ArrayList<MaterialType> materialTypeList = new ArrayList<MaterialType>();
		
		materialTypeList = materialTypeDao.getAllRecords();
		
		request.setAttribute("materialTypeList", materialTypeList);
		
		request.getRequestDispatcher(specEnum.getMyView(CommandNameEnum.TableMaterialType.name())).forward(request, response);
	}
}
