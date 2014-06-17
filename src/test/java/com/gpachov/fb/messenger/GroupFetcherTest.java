package com.gpachov.fb.messenger;

import junit.framework.TestCase;

import org.junit.Test;

import com.gpachov.fb.volleyballinviter.GroupFetcher;
import com.restfb.DefaultFacebookClient;

public class GroupFetcherTest extends TestCase {

	private GroupFetcher groupFetcher;
	private DefaultFacebookClient facebookClient;

	protected void setUp() throws Exception {
		this.facebookClient = new DefaultFacebookClient(CredentialsAndConstants.ACCESS_TOKEN);
		this.groupFetcher = new GroupFetcher(facebookClient, CredentialsAndConstants.GROUP_ID);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGroupFetching() throws Exception {
		System.out.println(groupFetcher.getGroupMembers().size());
	}

}
