package com.utils;

public class NextPage {
	
	private String page;
	private String redirectType;
	
	public static final String REDIRECT_TYPE_FORWARD = "forward";
	public static final String REDIRECT_TYPE_REDIRECT = "redirect";
	
	public static final String CONTENT_TYPE_JSP = "html/jsp; charset=UTF-8";
	public static final String CONTENT_TYPE_JSON = "application/json; charset=UTF-8";
	
	public NextPage(String page, String redirectType) {
		
		this.page = page;
		this.redirectType = redirectType;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRedirectType() {
		return redirectType;
	}

	public void setRedirectType(String redirectType) {
		this.redirectType = redirectType;
	}
	
	
}
