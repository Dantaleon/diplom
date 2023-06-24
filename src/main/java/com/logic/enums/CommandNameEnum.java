package com.logic.enums;

public enum CommandNameEnum {

	// Command Name
	Home, ErrorPage404, Catalog, Login, Logout, Registration, Contact, AdminTableView, Design, MyCart, MyOrders,

	// Controller command Name
	CHome,

	// AJAX Command Name
	HeaderInclude, FooterInclude,

	// AJAX admin table edit Command Name
	TableUser_acc, TableRole, TableCommonWindow, TableCart, TableCartsItems, TableMaterialType, TablePieceType,
	TablePiece, TablePaymentVar, TableOrderStatus, TableOrderAcc, TableOrdersItems, TableUserDetails, TableUsersRoles,
	TableWindowsParts,
	// other
	WindowDetails, OrderDetails;
}