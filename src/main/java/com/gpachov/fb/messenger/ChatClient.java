package com.gpachov.fb.messenger;

import java.util.Collection;
import java.util.List;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.sasl.SASLDigestMD5Mechanism;

public class ChatClient {
	private XMPPConnection connection;

	public ChatClient(String username, String password) throws XMPPException {
		SASLAuthentication.registerSASLMechanism("DIGEST-MD5", SASLDigestMD5Mechanism.class);
		SASLAuthentication.supportSASLMechanism("DIGETST-MD5", 0); // first
																	// place for
																	// MD5

		ConnectionConfiguration configuration = new ConnectionConfiguration(Credentials.XMPP_HOST,
				Credentials.XMPP_PORT);
		configuration.setSASLAuthenticationEnabled(true);
		connection = new XMPPConnection(configuration);
		connection.connect();
		connection.login(username, password);
	}

	public void sendMessage(List<GroupMember> members, String message) {
		Collection<RosterEntry> entries = connection.getRoster().getEntries();
		int counter = 0;
		for (GroupMember groupMember : members) {
			for (RosterEntry chatFriend : entries) {
				final String groupMemberName = groupMember.getFirstName() + " " + groupMember.getLastName();
				// boolean containsFirstName =
				// chatFriend.getName().contains(groupMember.getFirstName().trim());
				// boolean containsSecondName =
				// chatFriend.getName().contains(groupMember.getLastName().trim());
				if (groupMemberName.trim().equalsIgnoreCase(chatFriend.getName().trim())) {
					counter++;
					System.out.println("Name => " + chatFriend.getName());
					System.out.println("User => " + chatFriend.getUser());
				}
			}
		}

		System.out.println("All rosters " + entries.size());
		System.out.println("Group members " + members.size());
		System.out.println("Matched people " + counter);

		System.out.println(entries.toString());
	}
}
