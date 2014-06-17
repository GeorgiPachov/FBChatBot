package com.gpachov.fb.messenger;

import java.util.ResourceBundle;

public class CredentialsAndConstants {
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("res");
	public static final String ACCESS_TOKEN = RESOURCE_BUNDLE.getString("accessToken");
	public static final long GROUP_ID = Long.parseLong(RESOURCE_BUNDLE.getString("groupId"));
	public static final String USERNAME = RESOURCE_BUNDLE.getString("username");
	public static final String PASSWORD = RESOURCE_BUNDLE.getString("password");
	public static final String XMPP_HOST = RESOURCE_BUNDLE.getString("xmppHost");
	public static final int XMPP_PORT = Integer.parseInt(RESOURCE_BUNDLE.getString("xmppPort"));
	public static final String INVITE_MESSAGE = "Волейболче в неделя? 19 - 20.30 някъде. Отговори с 'да' или 'da' ако ще идваш, че е автоматизирано. ## Ако не искаш да получаваш такива съобщения, напусни фейсбук групата за волейбол -> https://www.facebook.com/groups/228242420710209/?fref=ts . Sorry ако съм те наспамил";
}
