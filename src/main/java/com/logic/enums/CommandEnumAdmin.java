package com.logic.enums;

import com.logic.abstraction.ICommandProcessor;
import com.logic.abstraction.IEnumFactoryEntity;
import com.logic.command.*;

public enum CommandEnumAdmin implements IEnumFactoryEntity {
	INSTANCE,
	Home(new HomeCP(), "/WEB-INF/jsp/index.jsp"),
	Catalog(new CatalogCP(), "/WEB-INF/jsp/view3images.jsp"),
	Login(new LoginCP(), "/WEB-INF/jsp/login.jsp"),
	Registration(new RegistrationCP(), "/WEB-INF/jsp/registration.jsp"),
	Contact(new ContactCP(), "/WEB-INF/jsp/contact.jsp"),
	About(new AboutCP(), "/WEB-INF/jsp/about.jsp"),
	AdminTableView(new AdminTableViewCP(), "/WEB-INF/jsp/admin-table-view.jsp"),
	ErrorPage404(new ErrorPage404CP(), "/WEB-INF/jsp/404.jsp");
	
	private ICommandProcessor command;
	private String view;
	
	CommandEnumAdmin(ICommandProcessor command, String view){
		this.command = command;
		this.view = view;
	}
	
	CommandEnumAdmin() {
	}

	public ICommandProcessor getCommand() {
		return command;
	}
	
	public String getView() {
		return view;
	}

	@Override
	public ICommandProcessor getMyCommand(String name) {
		
		return this.valueOf(name).getCommand();
		
	}

	@Override
	public String getMyView(String name) {
		
		return this.valueOf(name).getView();
	}
	
	
	
}
