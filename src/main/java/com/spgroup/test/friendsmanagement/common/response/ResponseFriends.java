package com.spgroup.test.friendsmanagement.common.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "success", "friends", "count", "recipients", "errorMessage" })
public class ResponseFriends {

	@JsonProperty("success")
	private Boolean success;

	@JsonProperty("errorMessage")
	private List<String> errorMessage;

	@JsonProperty("friends")
	private List<String> friends;

	@JsonProperty("count")
	private Integer count;

	@JsonProperty("recipients")
	private List<String> recipients;

	public boolean isSuccess() {
		return success;
	}

	public ResponseFriends setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public List<String> getErrorMessage() {
		return errorMessage;
	}

	public ResponseFriends setErrorMessage(List<String> errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

	public List<String> getFriends() {
		return friends;
	}

	public ResponseFriends setFriends(List<String> friends) {
		this.friends = friends;
		return this;
	}

	public Integer getCount() {
		return count;
	}

	public ResponseFriends setCount(Integer count) {
		this.count = count;
		return this;
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public ResponseFriends setRecipients(List<String> recipients) {
		this.recipients = recipients;
		return this;
	}

	@Override
	public String toString() {
		return "ResponseFriends [success=" + success + ", errorMessage=" + errorMessage + ", friends=" + friends
				+ ", count=" + count + ", recipients=" + recipients + "]";
	}

}
