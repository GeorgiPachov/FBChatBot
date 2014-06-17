package com.gpachov.fb.messenger;

import java.util.ArrayList;
import java.util.List;

import com.restfb.Facebook;
import com.restfb.FacebookClient;

public class GroupFetcher {
    private long groupId;
    private FacebookClient facebookClient;

    public GroupFetcher(FacebookClient facebookClient, long _groupId) {
        this.groupId = _groupId;
        this.facebookClient = facebookClient;
    }

    public List<GroupMember> getGroupMembers() {
        final String FQL = "select uid, firstName,lastName from user where uid in (select uid from group_member where gid=" + Credentials.GROUP_ID + ")  LIMIT 50";
        List<GroupMember> result = facebookClient.executeFqlQuery(FQL, GroupMember.class);
        return result;
    }

    public static class GroupMember {
        @Facebook
        private int uid;
        @Facebook
        private String firstName;
        @Facebook
        private String lastName;
        private String jabberId;

        public void setUid(int uid) {
            this.uid = uid;
        }
        
        public GroupMember(){
            
        }

        public GroupMember(int fbUserId, String firstName, String lastName, String jabberId) {
            super();
            this.uid = fbUserId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.jabberId = jabberId;
        }
    }
}
