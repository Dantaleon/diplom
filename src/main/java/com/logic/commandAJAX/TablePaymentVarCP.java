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
import com.model.dao.PaymentVarDAO;
import com.model.entity.MaterialType;
import com.model.entity.PaymentVar;
import com.utils.NextPage;

public class TablePaymentVarCP implements ICommandProcessorAJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, IEnumFactoryEntityAJAX specEnum)
			throws ServletException, IOException {
		
		PaymentVarDAO paymentVarDao = new PaymentVarDAO();
		
		response.setContentType(NextPage.CONTENT_TYPE_JSP);
		
		ArrayList<PaymentVar> paymentVarList = new ArrayList<PaymentVar>();
		
		paymentVarList = paymentVarDao.getAllRecords();
		
		request.setAttribute("paymentVarList", paymentVarList);
		
		request.getRequestDispatcher(specEnum.getMyView(CommandNameEnum.TablePaymentVar.name())).forward(request, response);
	}
}
