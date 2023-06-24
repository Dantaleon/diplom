package com.logic.commandAJAX;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntityAJAX;
import com.logic.enums.CommandEnumAdmin;
import com.logic.enums.CommandNameEnum;
import com.model.dao.CartDAO;
import com.model.dao.CartsItemsDAO;
import com.model.dao.CommonWindowDAO;
import com.model.dao.OrderAccDAO;
import com.model.dao.PaymentVarDAO;
import com.model.dao.UserDAO;
import com.model.entity.Cart;
import com.model.entity.CartsItems;
import com.model.entity.OrderAcc;
import com.model.entity.OrderStatus;
import com.model.entity.PaymentVar;
import com.utils.NextPage;
import com.utils.Resources;

public class CartUCPAJAX implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, IEnumFactoryEntityAJAX specEnum)
			throws ServletException, IOException {
		
		String method = request.getMethod().toString();
		
		String subCommand = (String) request.getParameter("sub");
		
		BigDecimal uid = new BigDecimal(request.getSession().getAttribute("id").toString());
		
		System.out.println(method + " " + subCommand + " " + uid);
		
		if (method.equals("POST") && subCommand.equals("addWindowById")) {
			
			System.out.println("post addwindowbyid");
			
			BigDecimal wid = new BigDecimal( (String) request.getParameter("wid"));
			
			CartDAO cartDao = new CartDAO();
			
			CartsItemsDAO cartsItemsDao = new CartsItemsDAO();
			
			int rowIns = 0;
			
			Cart cart = cartDao.getRecordByUserId(uid);
			
			rowIns = cartsItemsDao.addWindowToCart(wid, cart.getId());
			
			System.out.println("rowIns добавление по id:" + rowIns);
			
			response.setContentType(NextPage.CONTENT_TYPE_TEXT);
			response.getWriter().write("rowIns: " + rowIns);
			
			return;
			
		} else if (method.equals("POST") && subCommand.equals("delWindowById")) {
			
			System.out.println("post delwindowbyid");
			
			BigDecimal wid = new BigDecimal( (String) request.getParameter("wid"));
			
			CartDAO cartDao = new CartDAO();
			
			CartsItemsDAO cartsItemsDao = new CartsItemsDAO();
			
			int rowDel = 0;
			
			Cart cart = cartDao.getRecordByUserId(uid);
			
			rowDel = cartsItemsDao.delWindowFromCart(wid, cart.getId());
			
			System.out.println("rowDel удаление по id:" + rowDel);
			
			request.getRequestDispatcher(CommandEnumAdmin.MyCart.getView()).forward(request, response);
			return;
		} else if (method.equals("POST") && subCommand.equals("cartToOrder")) {
			
			BigDecimal payVarId = new BigDecimal(request.getParameter("payVarId").toString());
			
			if (payVarId != BigDecimal.ONE && payVarId != new BigDecimal("2")) payVarId = new BigDecimal("2");
			
			PaymentVarDAO payVarDao = new PaymentVarDAO();
			
			PaymentVar payVar = payVarDao.getRecordByPK(payVarId);
			
			CartDAO cartDao = new CartDAO();
			UserDAO userDao = new UserDAO();
			OrderAccDAO orderAccDao = new OrderAccDAO();
			
			Cart cart = cartDao.getRecordByUserId(uid);
			
			Timestamp orderDate = Resources.getCurrentTimeTimestamp();
			
			OrderAcc orderAcc = new OrderAcc(
					0.0,	
					Resources.timestampToString(orderDate),
					Resources.timestampToString(Resources.getTimestampIncDays(orderDate, 30)),
					payVar,
					userDao.getRecordByPK(uid),
					new OrderStatus(new BigDecimal("1"), "на рассмотрении")
				);
			
			boolean transportOk = orderAccDao.transferCartToOrder(uid, orderAcc);
			
			if (!transportOk) {
				System.out.println("transport is not okay");
				
				response.setContentType(NextPage.CONTENT_TYPE_TEXT);
				response.getWriter().write("transport is not okay");
				return;
			}
			System.out.println("everything is okay");
			
			response.setContentType(NextPage.CONTENT_TYPE_TEXT);
			response.getWriter().write("everything is okay");
			return;
		}
	}
}
