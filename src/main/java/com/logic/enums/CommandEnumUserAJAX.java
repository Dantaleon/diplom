package com.logic.enums;

import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.abstraction.IEnumFactoryEntityAJAX;
import com.logic.command.LogoutCP;
import com.logic.commandAJAX.CartUCPAJAX;
import com.logic.commandAJAX.FooterIncludeCP;
import com.logic.commandAJAX.HeaderIncludeCP;
import com.logic.commandAJAX.HomeIncludeCP;
import com.logic.commandAJAX.PieceUCP;

public enum CommandEnumUserAJAX implements IEnumFactoryEntityAJAX {
	
	INSTANCE,
	HeaderInclude(new HeaderIncludeCP(), "/WEB-INF/jsp/navigations/userNavigation.jsp"),
	HomeInclude(new HomeIncludeCP(), "/WEB-INF/jsp/homeMain.jsp"),
	FooterInclude(new FooterIncludeCP(), "/WEB-INF/jsp/footer.jsp"),
	Logout(new LogoutCP(), "/WEB-INF/jsp/index.jsp"),
	
	PieceUCP(new PieceUCP(), "/WEB-INF/jsp/design.jsp"),
	
	CartUCPAJAX(new CartUCPAJAX(), "/WEB-INF/jsp/404.jsp");
	
	private String view;
	private ICommandProcessorAJAX commandAJAX;
	private Enum<CommandEnumUserAJAX> selfReference;
	
	CommandEnumUserAJAX(ICommandProcessorAJAX commandAJAX, String view) {
		this.commandAJAX = commandAJAX;
		this.view = view;
	}

	CommandEnumUserAJAX() {
		selfReference = this;
	}
	
	public Enum getSelfReference() {
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
