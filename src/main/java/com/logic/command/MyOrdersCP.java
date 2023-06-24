package com.logic.command;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessor;
import com.logic.abstraction.IEnumFactoryEntity;
import com.logic.enums.CommandNameEnum;
import com.model.dao.OrderAccDAO;
import com.model.entity.OrderAcc;
import com.utils.NextPage;

public class MyOrdersCP implements ICommandProcessor {
	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response, NextPage nextPage,
			IEnumFactoryEntity specEnum) {
		String subCommand = (String) request.getParameter("sub");
		BigDecimal uid = new BigDecimal(request.getSession().getAttribute("id").toString());
		if (request.getMethod().equals("GET") && subCommand == null) {
			OrderAccDAO orderAccDao = new OrderAccDAO();
			
			ArrayList<OrderAcc> orderAccList = new ArrayList<OrderAcc>();
			orderAccList = orderAccDao.getAllRecordsByUserId(uid);
			
			request.setAttribute("orderAccList", orderAccList);
			
			nextPage.setRedirectType(NextPage.REDIRECT_TYPE_FORWARD);
			nextPage.setPage(specEnum.getMyView(CommandNameEnum.MyOrders.name()));
			return nextPage;
		} else {
			nextPage.setRedirectType(NextPage.REDIRECT_TYPE_FORWARD);
			nextPage.setPage(specEnum.getMyView(CommandNameEnum.ErrorPage404.name()));
			return nextPage;
		}
	}
}
