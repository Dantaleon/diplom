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
import com.model.dao.OrderStatusDAO;
import com.model.entity.MaterialType;
import com.model.entity.OrderStatus;
import com.utils.NextPage;

public class TableOrderStatusCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, IEnumFactoryEntityAJAX specEnum)
			throws ServletException, IOException {
		
		OrderStatusDAO orderStatusDao = new OrderStatusDAO();
		
		response.setContentType(NextPage.CONTENT_TYPE_JSP);
		
		ArrayList<OrderStatus> orderStatusList = new ArrayList<OrderStatus>();
		
		orderStatusList = orderStatusDao.getAllRecords();
		
		request.setAttribute("orderStatusList", orderStatusList);
		
		request.getRequestDispatcher(specEnum.getMyView(CommandNameEnum.TableOrderStatus.name())).forward(request, response);
	}
}
