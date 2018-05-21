package com.spgroup.test.friendsmanagement.common.response;

public class ResponseFriends {

	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public ResponseFriends setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	@Override
	public String toString() {
		return "ResponseFriends [success=" + success + "]";
	}
	
	
}
