package com.gpachov.fb.messenger;

import static org.junit.Assert.*;

import org.jivesoftware.smack.XMPPException;
import org.junit.Before;
import org.junit.Test;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

public class ChatTest {

	private FacebookClient fbClient;
	private ChatClient chatClient;
	private GroupFetcher groupFetcher;
	
	@Before
	public void setUp() throws XMPPException {
		fbClient = new DefaultFacebookClient(Credentials.ACCESS_TOKEN);
		chatClient = new ChatClient(Credentials.USERNAME, Credentials.PASSWORD);
		groupFetcher = new GroupFetcher(fbClient, Credentials.GROUP_ID);
	}
	
	@Test
	public void test() {
		chatClient.sendMessage(groupFetcher.getGroupMembers(), "Testing");
	}

}
