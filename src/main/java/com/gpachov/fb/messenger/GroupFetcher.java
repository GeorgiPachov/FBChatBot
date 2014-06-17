package com.gpachov.fb.messenger;

import java.util.List;

import com.restfb.FacebookClient;

public class GroupFetcher {
	private long groupId;
	private FacebookClient facebookClient;

	public GroupFetcher(FacebookClient facebookClient, long _groupId) {
		this.groupId = _groupId;
		this.facebookClient = facebookClient;
	}

	public List<GroupMember> getGroupMembers() {
		final String FQL = "select uid, first_name,last_name from user where uid in (select uid from group_member where gid="
				+ Credentials.GROUP_ID + ")  LIMIT 50";
		List<GroupMember> result = facebookClient.executeFqlQuery(FQL, GroupMember.class);
		return result;
	}

	
}
