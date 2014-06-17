package com.gpachov.fb.messenger;

import java.util.Collection;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;

public class GroupChatClient extends ChatClient {

	private Collection<RosterEntry> people;

	public GroupChatClient(String username, String password) throws XMPPException {
		super(username, password);
	}

	public void setPeople(Collection<RosterEntry> people) {
		this.people = people;
	}

	public void sendMessage(String message, MessageListener messageListener) throws XMPPException {
		assertPeopleAreSet();

//		Chat chat = connection.getChatManager().createChat(ChatTest.mockJabber, messageListener);
//		chat.sendMessage(message);
		for (RosterEntry person : people) {
			connection.getChatManager().createChat(person.getUser(), messageListener).sendMessage(message);
		}

	}

	public void sendMessage(String message) throws XMPPException {
		sendMessage(message, this);
	}

	private void assertPeopleAreSet() {
		if (people == null || people.size() == 0) {
			throw new BadInitilizationException(
					"You should set the people you want to have a group chat with, before sending messages!");
		}
	}

}
