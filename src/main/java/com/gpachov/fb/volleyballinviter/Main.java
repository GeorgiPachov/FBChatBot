package com.gpachov.fb.volleyballinviter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

import com.gpachov.fb.messenger.CredentialsAndConstants;
import com.gpachov.fb.messenger.GroupChatClient;
import com.gpachov.fb.messenger.model.GroupMember;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

public class Main {
	public static void main(String[] args) {
		final FacebookClient facebookClient = new DefaultFacebookClient(CredentialsAndConstants.ACCESS_TOKEN);
		final GroupFetcher fetcher = new GroupFetcher(facebookClient, CredentialsAndConstants.GROUP_ID);
		GroupChatClient fbChatClient = null;
		try {
			fbChatClient = new GroupChatClient(CredentialsAndConstants.USERNAME, CredentialsAndConstants.PASSWORD);

			final List<GroupMember> groupMembers = fetcher.getGroupMembers();
			Collection<RosterEntry> entries = fbChatClient.getAllFriends();

			Collection<RosterEntry> people = getTargetedFriends(groupMembers, entries);
			fbChatClient.setPeople(people);

			fbChatClient.sendMessage(CredentialsAndConstants.INVITE_MESSAGE, new MessageListener() {

				private final Collection<String> friendsThatSaidYes = new ArrayList<>();

				@Override
				public void processMessage(Chat chat, Message message) {
					boolean isAYes = message.getBody().contains("da") || message.getBody().contains("да");
					if (isAYes && message.getBody().length() <= 5) {
						System.out.println(chat.getParticipant() + " said YES!");

						friendsThatSaidYes.add(chat.getParticipant());
						System.out.println("People that said yes: " + friendsThatSaidYes.toString());
					} else {
						System.out.println(chat.getParticipant() + ":" + message.getBody());
					}
				}
			});
		} catch (XMPPException e) {
			e.printStackTrace();
			fbChatClient.quit();
			return;
		}

	}

	private static Queue<RosterEntry> getTargetedFriends(final List<GroupMember> groupMembers,
			Collection<RosterEntry> entries) {
		final Queue<RosterEntry> messageQueue = new LinkedList<>();
		for (GroupMember groupMember : groupMembers) {
			for (RosterEntry chatFriend : entries) {
				final String groupMemberName = groupMember.getFirstName() + " " + groupMember.getLastName();
				if (groupMemberName.trim().equalsIgnoreCase(chatFriend.getName().trim())) {
					messageQueue.add(chatFriend);
				}
			}
		}
		return messageQueue;
	}
}
