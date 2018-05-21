package com.spgroup.test.friendsmanagement.common.request;

import java.util.List;

public class RequestFriends {
	private List<String> emails;

	public List<String> getEmails() {
		return emails;
	}

	public RequestFriends setEmails(List<String> emails) {
		this.emails = emails;
		return this;
	}

	@Override
	public String toString() {
		return "RequestFriends [emails=" + emails + "]";
	}

}
