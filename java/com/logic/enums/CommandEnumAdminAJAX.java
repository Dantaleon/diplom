package com.logic.enums;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntityAJAX;
import com.logic.command.LogoutCP;
import com.logic.commandAJAX.FooterIncludeCP;
import com.logic.commandAJAX.HeaderIncludeCP;
import com.logic.commandAJAX.HomeIncludeCP;
import com.logic.commandAJAX.ProductCP;
import com.logic.commandAJAX.TableCartCP;
import com.logic.commandAJAX.TableCartsItemsCP;
import com.logic.commandAJAX.TableCommonWindowCP;
import com.logic.commandAJAX.TableRoleCP;
import com.logic.commandAJAX.TableUser_accCP;

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
	
	Image(new ProductCP(), "/WEB-INF/jsp/404.jsp");
	
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
