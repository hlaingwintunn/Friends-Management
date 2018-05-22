package com.spgroup.test.friendsmanagement.common.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "requestor", "target" })
public class RequestSubscribe {

	@JsonProperty("requestor")
	@Email
	@NotEmpty
	private String requestor;

	@JsonProperty("target")
	@Email
	@NotEmpty
	private String target;

	public String getRequestor() {
		return requestor;
	}

	public RequestSubscribe setRequestor(String requestor) {
		this.requestor = requestor;
		return this;
	}

	public String getTarget() {
		return target;
	}

	public RequestSubscribe setTarget(String target) {
		this.target = target;
		return this;
	}

	@Override
	public String toString() {
		return "RequestSubscribe [requestor=" + requestor + ", target=" + target + "]";
	}

}
