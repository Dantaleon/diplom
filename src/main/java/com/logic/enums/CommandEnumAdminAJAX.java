package com.logic.enums;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntityAJAX;
import com.logic.command.LogoutCP;
import com.logic.commandAJAX.ErrorPage404CP;
import com.logic.commandAJAX.FooterIncludeCP;
import com.logic.commandAJAX.HeaderIncludeCP;
import com.logic.commandAJAX.HomeIncludeCP;
import com.logic.commandAJAX.PieceUCP;
import com.logic.commandAJAX.TableCartCP;
import com.logic.commandAJAX.TableCartsItemsCP;
import com.logic.commandAJAX.TableCommonWindowCP;
import com.logic.commandAJAX.TableMaterialTypeCP;
import com.logic.commandAJAX.TableOrderAccCP;
import com.logic.commandAJAX.TableOrderStatusCP;
import com.logic.commandAJAX.TableOrdersItemsCP;
import com.logic.commandAJAX.TablePaymentVarCP;
import com.logic.commandAJAX.TablePieceCP;
import com.logic.commandAJAX.TablePieceTypeCP;
import com.logic.commandAJAX.TableRoleCP;
import com.logic.commandAJAX.TableUserDetailsCP;
import com.logic.commandAJAX.TableUser_accCP;
import com.logic.commandAJAX.TableUsersRolesCP;
import com.logic.commandAJAX.TableWindowsPartsCP;
import com.logic.commandAJAX.CartUCPAJAX;

public enum CommandEnumAdminAJAX implements IEnumFactoryEntityAJAX  {

	INSTANCE,
	HeaderInclude(new HeaderIncludeCP(), "/WEB-INF/jsp/navigations/adminNavigation.jsp"),
	HomeInclude(new HomeIncludeCP(), "/WEB-INF/jsp/himeMain.jsp"),
	FooterInclude(new FooterIncludeCP(), "/WEB-INF/jsp/footer.jsp"),
	Logout(new LogoutCP(), "/WEB-INF/jsp/index.jsp"),
	
	// dynamic pages for edititng tables
	TableUser_acc(new TableUser_accCP(), "/WEB-INF/jsp/tableViewTemplates/User_accTemplate.jsp"),
	TableRole(new TableRoleCP(), "/WEB-INF/jsp/tableViewTemplates/RoleTemplate.jsp"),
	TableCommonWindow(new TableCommonWindowCP(), "/WEB-INF/jsp/tableViewTemplates/Common_windowTemplate.jsp"),
	TableCart(new TableCartCP(), "/WEB-INF/jsp/tableViewTemplates/CartTemplate.jsp"),
	TableCartsItems(new TableCartsItemsCP(), "/WEB-INF/jsp/tableViewTemplates/CartsItemsTemplate.jsp"),
	TableMaterialType(new TableMaterialTypeCP(), "/WEB-INF/jsp/tableViewTemplates/MaterialTypeTemplate.jsp"),
	TablePieceType(new TablePieceTypeCP(), "/WEB-INF/jsp/tableViewTemplates/PieceTypeTemplate.jsp"),
	TablePiece(new TablePieceCP(), "/WEB-INF/jsp/tableViewTemplates/PieceTemplate.jsp"),
	TablePaymentVar(new TablePaymentVarCP(), "/WEB-INF/jsp/tableViewTemplates/PaymentVarTemplate.jsp"),
	TableOrderStatus(new TableOrderStatusCP(), "/WEB-INF/jsp/tableViewTemplates/OrderStatusTemplate.jsp"),
	TableOrderAcc (new TableOrderAccCP(), "/WEB-INF/jsp/tableViewTemplates/OrderAccTemplate.jsp"),
	TableOrdersItems(new TableOrdersItemsCP(), "/WEB-INF/jsp/tableViewTemplates/OrdersItemsTemplate.jsp"),
	TableUserDetails(new TableUserDetailsCP(), "/WEB-INF/jsp/tableViewTemplates/UserDetailsTemplate.jsp"),
	TableUsersRoles(new TableUsersRolesCP(), "/WEB-INF/jsp/tableViewTemplates/UsersRolesTemplate.jsp"),
	TableWindowsParts(new TableWindowsPartsCP(), "/WEB-INF/jsp/tableViewTemplates/WindowsPartsTemplate.jsp"),
	// other ajax
	PieceUCP(new PieceUCP(), "/WEB-INF/jsp/design.jsp"),
	CartUCPAJAX(new CartUCPAJAX(), "/WEB-INF/jsp/404.jsp"),
	// Убрать таблицу, чтобы сработала ошибка
	ErrorPage404(new ErrorPage404CP(), "/WEB-INF/jsp/404.jsp");

	private String view;
	private ICommandProcessorAJAX commandAJAX;
	private Enum<CommandEnumAdminAJAX> selfReference;

	CommandEnumAdminAJAX(ICommandProcessorAJAX commandAJAX, String view) {
		this.commandAJAX = commandAJAX;
		this.view = view;
	}

	CommandEnumAdminAJAX() {
		selfReference = this;
	}

	public Enum<CommandEnumAdminAJAX> getSelfReference() {
		return selfReference;
	}

	public ICommandProcessorAJAX getCommandAJAX() {
		return commandAJAX;
	}

	public String getView() {
		return view;
	}

	@Override
	public ICommandProcessorAJAX getMyCommand(String name) {
		return valueOf(name).getCommandAJAX();
	}

	@Override
	public String getMyView(String name) {
		return valueOf(name).getView();
	}
}