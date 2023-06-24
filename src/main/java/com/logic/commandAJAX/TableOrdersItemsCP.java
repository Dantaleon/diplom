package com.logic.commandAJAX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntityAJAX;
import com.logic.enums.CommandNameEnum;
import com.model.dao.OrderAccDAO;
import com.model.dao.OrdersItemsDAO;
import com.model.entity.OrderAcc;
import com.model.entity.OrdersItems;
import com.utils.NextPage;

public class TableOrdersItemsCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, IEnumFactoryEntityAJAX specEnum)
			throws ServletException, IOException {

		OrdersItemsDAO ordersItemsDao = new OrdersItemsDAO();

		response.setContentType(NextPage.CONTENT_TYPE_JSP);

		ArrayList<OrdersItems> ordersItemsList = new ArrayList<OrdersItems>();

		ordersItemsList = ordersItemsDao.getAllRecords();

		request.setAttribute("ordersItemsList", ordersItemsList);

		request.getRequestDispatcher(specEnum.getMyView(CommandNameEnum.TableOrdersItems.name())).forward(request,
				response);
	}
}
