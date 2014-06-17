package com.gpachov.fb.messenger;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.sasl.SASLDigestMD5Mechanism;

public class ChatClient implements MessageListener {
	protected XMPPConnection connection;
	protected AtomicBoolean quitRequested = new AtomicBoolean(false);

	public ChatClient(String username, String password) throws XMPPException {
		SASLAuthentication.registerSASLMechanism("DIGEST-MD5", SASLDigestMD5Mechanism.class);
		SASLAuthentication.supportSASLMechanism("DIGETST-MD5", 0); // first
																	// place for
																	// MD5

		ConnectionConfiguration configuration = new ConnectionConfiguration(CredentialsAndConstants.XMPP_HOST,
				CredentialsAndConstants.XMPP_PORT);
		configuration.setSASLAuthenticationEnabled(true);
		connection = new XMPPConnection(configuration);
		connection.connect();
		connection.login(username, password);
		awaitTermination();
	}

	public void awaitTermination() {
		final PacketCollector packetCollector = connection.createPacketCollector(new PacketFilter() {
			@Override
			public boolean accept(Packet packet) {
				return quitRequested.get();
			}
		});

		new Thread() {
			public void run() {
				packetCollector.nextResult();
			};

		}.start();
	}

	// /XXX Fixme use a dedicated JabberId class instead of string
	public PacketCollector sendMessage(String jabberId, String message) throws XMPPException {
		Chat chat = connection.getChatManager().createChat(jabberId, this);
		chat.sendMessage(message);
		return chat.createCollector();
	}

	@Override
	public void processMessage(Chat arg0, Message arg1) {
		System.out.print("Received answer from " + arg0.getParticipant() + " ");
		System.out.println(arg1.getBody());
	}

	public Collection<RosterEntry> getAllFriends() {
		return connection.getRoster().getEntries();
	}

	public void quit() {
		quitRequested = new AtomicBoolean(true);
	}
}
