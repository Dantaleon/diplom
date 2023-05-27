package com.logic.enums;

import com.logic.abstraction.ICommandProcessor;
import com.logic.abstraction.ICommandProcessorAJAX;
import com.logic.enums.*;

public enum RoleEnums {
	
	administrator(CommandEnumAdmin.INSTANCE, CommandEnumAdminAJAX.INSTANCE),
	user(CommandEnumUser.INSTANCE, CommandEnumUserAJAX.INSTANCE),
	guest(CommandEnumGuest.INSTANCE, CommandEnumGuestAJAX.INSTANCE);
	
	private Enum<?> simpleEnum;
	private Enum<?> ajaxEnum;
	
	RoleEnums(Enum<?> simpleEnum, Enum<?> ajaxEnum) {
		this.simpleEnum = simpleEnum;
		this.ajaxEnum = ajaxEnum;
	}

	public Enum<?> getSimpleEnum() {
		return simpleEnum;
	}

	public Enum<?> getAjaxEnum() {
		return ajaxEnum;
	}
	
}
