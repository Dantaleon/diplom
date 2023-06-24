package com.logic.command;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.logic.abstraction.ICommandProcessor;
import com.logic.abstraction.IEnumFactoryEntity;
import com.logic.enums.CommandNameEnum;
import com.model.dao.CartDAO;
import com.model.dao.CartsItemsDAO;
import com.model.dao.CommonWindowDAO;
import com.model.dao.WindowsPartsDAO;
import com.model.entity.Cart;
import com.model.entity.CartsItems;
import com.model.entity.CommonWindow;
import com.utils.NextPage;
import com.utils.TransferPiece;

public class MyCartCP implements ICommandProcessor {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response, NextPage nextPage,
			IEnumFactoryEntity specEnum) {
		CartsItemsDAO cartsItemsDao = new CartsItemsDAO();
		CommonWindowDAO commonWindowDao = new CommonWindowDAO();
		WindowsPartsDAO windowsPartsDao = new WindowsPartsDAO();
		
		CartDAO cartDao = new CartDAO();
		
		response.setContentType(NextPage.CONTENT_TYPE_JSP);
		
		String method = request.getMethod().toString();
		String subCommand = (String) request.getParameter("sub");
		
		BigDecimal uid = new BigDecimal(request.getSession().getAttribute("id").toString());
		
		if (method.equals("POST") && subCommand.equals("addFromDesign")) {
			try {
				TransferPiece[] transferPieceArr;
				
				StringBuilder sb = new StringBuilder();
				BufferedReader reader = request.getReader();
				String line;
				
				while ((line = reader.readLine()) != null) {
					sb.append(line).append('\n');
				}
				String json = sb.toString();
				transferPieceArr = new Gson().fromJson(json, TransferPiece[].class);
				if (transferPieceArr == null || transferPieceArr.length < 1) {
					request.setAttribute("error", "Нет элементов для добавления");
					
					nextPage.setRedirectType(NextPage.REDIRECT_TYPE_FORWARD);
					nextPage.setPage(specEnum.getMyView(CommandNameEnum.Design.name()));
					return nextPage;
				}
				BigDecimal newWindowId = commonWindowDao.createNewRecordTriple(new CommonWindow(100, 200, 30));
				windowsPartsDao.fillWindowWithParts(newWindowId, transferPieceArr);
				
				Cart cart = cartDao.getRecordByUserId(uid);;
				cartsItemsDao.addWindowToCart(newWindowId, cart.getId());
			} catch (Exception e) {
				nextPage.setRedirectType(NextPage.REDIRECT_TYPE_FORWARD);
				nextPage.setPage(specEnum.getMyView(CommandNameEnum.ErrorPage404.name()));
				return nextPage;
			}
			request.setAttribute("noError", "Окно создано и добавлена в корзину");
			
			nextPage.setRedirectType(NextPage.REDIRECT_TYPE_FORWARD);
			nextPage.setPage(specEnum.getMyView(CommandNameEnum.Design.name()));
			return nextPage;
		} else if (request.getMethod().equals("GET") && subCommand == null) {
			ArrayList<CartsItems> cartsItemsList = new ArrayList<CartsItems>();
			cartsItemsList = cartsItemsDao.getCartItemsByUserId(uid);
			
			request.setAttribute("cartsItemsList", cartsItemsList);
			
			nextPage.setRedirectType(NextPage.REDIRECT_TYPE_FORWARD);
			nextPage.setPage(specEnum.getMyView(CommandNameEnum.MyCart.name()));
			return nextPage;
		} else {
			nextPage.setRedirectType(NextPage.REDIRECT_TYPE_FORWARD);
			nextPage.setPage(specEnum.getMyView(CommandNameEnum.ErrorPage404.name()));
			return nextPage;
		}
	}
}