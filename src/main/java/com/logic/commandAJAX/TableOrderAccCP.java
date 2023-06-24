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
import com.model.dao.OrderStatusDAO;
import com.model.entity.OrderAcc;
import com.model.entity.OrderStatus;
import com.utils.NextPage;

public class TableOrderAccCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, IEnumFactoryEntityAJAX specEnum)
			throws ServletException, IOException {

		OrderAccDAO orderAccDao = new OrderAccDAO();

		response.setContentType(NextPage.CONTENT_TYPE_JSP);

		ArrayList<OrderAcc> orderAccList = new ArrayList<OrderAcc>();

		orderAccList = orderAccDao.getAllRecords();

		request.setAttribute("orderAccList", orderAccList);

		request.getRequestDispatcher(specEnum.getMyView(CommandNameEnum.TableOrderAcc.name())).forward(request,
				response);
	}
}
