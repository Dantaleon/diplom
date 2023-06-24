package com.logic.abstraction;

import javax.servlet.http.HttpServletRequest;

import com.logic.enums.CommandEnumAdmin;
import com.logic.enums.CommandEnumAdminAJAX;
import com.logic.enums.CommandEnumGuest;
import com.logic.enums.CommandEnumGuestAJAX;
import com.logic.enums.CommandEnumUser;
import com.logic.enums.CommandEnumUserAJAX;
import com.logic.enums.RoleEnums;
import com.utils.Resources;

public final class EnumFactory {

	private EnumFactory() {}

	public static IEnumFactoryEntity getSimpleEnum(HttpServletRequest request) {

		String role = Resources.getRoleStr(request);
		Enum<?> generalEnum = RoleEnums.valueOf(role).getSimpleEnum();

		if (generalEnum instanceof CommandEnumAdmin) {
			return (CommandEnumAdmin) generalEnum;
		} else if (generalEnum instanceof CommandEnumUser) {
			return (CommandEnumUser) generalEnum;
		} else {
			return (CommandEnumGuest) generalEnum;
		}
	}

	public static IEnumFactoryEntityAJAX getAjaxEnum(HttpServletRequest request) {

		String role = Resources.getRoleStr(request);
		Enum<?> generalEnum = RoleEnums.valueOf(role).getAjaxEnum();

		if (generalEnum instanceof CommandEnumAdminAJAX) {
			return (CommandEnumAdminAJAX) generalEnum;
		} else if (generalEnum instanceof CommandEnumUserAJAX) {
			return (CommandEnumUserAJAX) generalEnum;
			
		} else {
			return (CommandEnumGuestAJAX) generalEnum;
		}
	}
}
