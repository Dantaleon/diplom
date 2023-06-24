package com.logic.command;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessor;
import com.logic.abstraction.IEnumFactoryEntity;
import com.logic.enums.CommandNameEnum;
import com.model.dao.OrdersItemsDAO;
import com.model.entity.OrdersItems;
import com.utils.NextPage;

public class OrderDetailsCP implements ICommandProcessor {
	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response, NextPage nextPage,
			IEnumFactoryEntity specEnum) {
		BigDecimal oid = new BigDecimal(request.getParameter("oid").toString());
		BigDecimal uid = new BigDecimal(request.getSession().getAttribute("id").toString());
		OrdersItemsDAO ordersItemsDao = new OrdersItemsDAO();
		
		ArrayList<OrdersItems> ordersItemsList = new ArrayList<OrdersItems>();
		ordersItemsList = ordersItemsDao.getOrdersItemsByUserIdAndOrderId(uid, oid);
		
		request.setAttribute("ordersItemsList", ordersItemsList);
		
		nextPage.setRedirectType(NextPage.REDIRECT_TYPE_FORWARD);
		nextPage.setPage(specEnum.getMyView(CommandNameEnum.OrderDetails.name()));
		return nextPage;
	}
}
