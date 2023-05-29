package com.logic.enums;

public enum RoleEnums {
	
	administrator(CommandEnumAdmin.INSTANCE, CommandEnumAdminAJAX.INSTANCE, 
			"/WEB-INF/jsp/navigations/adminNavigation.jsp"),
	user(CommandEnumUser.INSTANCE, CommandEnumUserAJAX.INSTANCE,
			"/WEB-INF/jsp/navigations/userNavigation.jsp"),
	guest(CommandEnumGuest.INSTANCE, CommandEnumGuestAJAX.INSTANCE,
			"/WEB-INF/jsp/navigations/guestNavigation.jsp");
	
	private Enum<?> simpleEnum;
	private Enum<?> ajaxEnum;
	private String headerPath;
	
	RoleEnums(Enum<?> simpleEnum, Enum<?> ajaxEnum, String headerPath) {
		this.simpleEnum = simpleEnum;
		this.ajaxEnum = ajaxEnum;
		this.headerPath = headerPath;
	}

	public Enum<?> getSimpleEnum() {
		return simpleEnum;
	}

	public Enum<?> getAjaxEnum() {
		return ajaxEnum;
	}
	
	public String getHeaderPath() {
		return headerPath;
	}
}
