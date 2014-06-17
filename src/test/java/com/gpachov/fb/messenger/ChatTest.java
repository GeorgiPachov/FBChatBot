package com.gpachov.fb.messenger;

import static org.junit.Assert.*;

import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Packet;
import org.junit.Before;
import org.junit.Test;

import com.gpachov.fb.volleyballinviter.GroupFetcher;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

public class ChatTest {

	//XXX FIXME use mocks for this
	public static final String mockJabber = "-100003095055271@chat.facebook.com";
	private FacebookClient fbClient;
	private ChatClient chatClient;
	private GroupFetcher groupFetcher;
	
	@Before
	public void setUp() throws XMPPException {
		fbClient = new DefaultFacebookClient(CredentialsAndConstants.ACCESS_TOKEN);
		chatClient = new ChatClient(CredentialsAndConstants.USERNAME, CredentialsAndConstants.PASSWORD);
		groupFetcher = new GroupFetcher(fbClient, CredentialsAndConstants.GROUP_ID);
	}
	
	@Test
	public void test() throws XMPPException {
//		chatClient.sendMessage(groupFetcher.getGroupMembers(), "Test");
		PacketCollector collector = chatClient.sendMessage(mockJabber, "testing man, just testing!");
		Packet packet  = collector.nextResult();
		System.out.println(packet.toString());
		System.out.println(packet.toXML());
	}

}
