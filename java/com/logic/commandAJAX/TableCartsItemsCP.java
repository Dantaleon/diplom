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
import com.model.dao.CartsItemsDAO;
import com.model.entity.Cart;
import com.model.entity.CartsItems;
import com.utils.NextPage;

public class TableCartsItemsCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, IEnumFactoryEntityAJAX specEnum)
			throws ServletException, IOException {
		
		CartsItemsDAO cartsItemsDao = new CartsItemsDAO();
		
		response.setContentType(NextPage.CONTENT_TYPE_JSP);
		
		ArrayList<CartsItems> cartsItemsList = new ArrayList<CartsItems>();
		
		cartsItemsList = cartsItemsDao.getAllRecords();
		
		request.setAttribute("cartsItemsList", cartsItemsList);
		
		request.getRequestDispatcher(specEnum.getMyView(CommandNameEnum.TableCartsItems.name())).forward(request, response);

	}

}
