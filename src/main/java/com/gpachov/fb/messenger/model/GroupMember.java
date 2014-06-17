package com.gpachov.fb.messenger.model;

import com.restfb.Facebook;

public class GroupMember {
	@Override
	public String toString() {
		return "GroupMember [uid=" + uid + ", first_name=" + first_name + ", last_name=" + last_name + ", jabberId="
				+ jabberId + "]";
	}

	@Facebook
	private int uid;
	@Facebook
	private String first_name;
	@Facebook
	private String last_name;
	private String jabberId;

	public void setUid(final int uid) {
		this.uid = uid;
	}

	public GroupMember() {

	}

	public GroupMember(final int fbUserId, final String firstName, final String lastName, final String jabberId) {
		super();
		this.uid = fbUserId;
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.jabberId = jabberId;
	}

	public String getLastName() {
		return last_name;
	}

	private void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getFirstName() {
		return first_name;
	}

	private void setFirstName(String first_name) {
		this.first_name = first_name;
	}
}
