package com.spgroup.test.friendsmanagement.common.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "sender", "text" })
public class RequestReceiveUpdates {

	@JsonProperty("sender")
	@Email
	@NotBlank
	private String sender;

	@JsonProperty("text")
	private String text;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "RequestReceiveUpdates [sender=" + sender + ", text=" + text + "]";
	}

}
