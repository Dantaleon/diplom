package com.logic.commandAJAX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntityAJAX;
import com.logic.enums.CommandNameEnum;
import com.model.dao.CartDAO;
import com.model.entity.Cart;
import com.utils.NextPage;

public class TableCartCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, IEnumFactoryEntityAJAX specEnum)
			throws ServletException, IOException {
		
		CartDAO cartDao = new CartDAO();
		
		response.setContentType(NextPage.CONTENT_TYPE_JSP);
		
		ArrayList<Cart> carts = new ArrayList<Cart>();
		
		carts = cartDao.getAllRecords();
		
		request.setAttribute("cartList", carts);
		
		request.getRequestDispatcher(specEnum.getMyView(CommandNameEnum.TableCart.name())).forward(request, response);
		
	}
	
	
	
}
