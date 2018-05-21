package com.spgroup.test.friendsmanagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;

@Embeddable
public class RelationshipPK implements Serializable {
	private static final long serialVersionUID = 3139336756627017130L;

	@Column(name = "USER_EMAIL", nullable = false)
	@Email
	private String userEmail;

	@Column(name = "FRIEND_EMAIL", nullable = false)
	@Email
	private String friendEmail;

	public RelationshipPK() {
	}

	public RelationshipPK(@Email String userEmail, @Email String friendEmail) {
		super();
		this.userEmail = userEmail;
		this.friendEmail = friendEmail;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getFriendEmail() {
		return friendEmail;
	}

	public void setFriendEmail(String friendEmail) {
		this.friendEmail = friendEmail;
	}

	@Override
	public String toString() {
		return "RelationshipPK [userEmail=" + userEmail + ", friendEmail=" + friendEmail + "]";
	}

}
