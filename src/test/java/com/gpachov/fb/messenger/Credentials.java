package com.gpachov.fb.messenger;

import java.util.ResourceBundle;

public class Credentials {
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("res");
	public static final String ACCESS_TOKEN = RESOURCE_BUNDLE.getString("accessToken");
	public static final long GROUP_ID = Long.parseLong(RESOURCE_BUNDLE.getString("groupId"));
	public static final String USERNAME = RESOURCE_BUNDLE.getString("username");
	public static final String PASSWORD = RESOURCE_BUNDLE.getString("password");
	public static final String XMPP_HOST = RESOURCE_BUNDLE.getString("xmppHost");
	public static final int XMPP_PORT = Integer.parseInt(RESOURCE_BUNDLE.getString("xmppPort"));
}
