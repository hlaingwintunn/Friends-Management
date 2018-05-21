package com.spgroup.test.friendsmanagement.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RELATIONSHIP")
public class Relationship {

	@EmbeddedId
	private RelationshipPK relationshipPK;

	@Column(name = "ARE_FRIENDS", nullable = false)
	private boolean areFriends;

	@Column(name = "IS_BLOCKED", nullable = false)
	private boolean isBlocked;

	@Column(name = "RECEIVE_UPDATES", nullable = false)
	private boolean receiveUpdates;

	public Relationship() {
	}

	public Relationship(RelationshipPK relationshipPK, boolean areFriends, boolean isBlocked, boolean receiveUpdates) {
		super();
		this.relationshipPK = relationshipPK;
		this.areFriends = areFriends;
		this.isBlocked = isBlocked;
		this.receiveUpdates = receiveUpdates;
	}

	public RelationshipPK getRelationshipPK() {
		return relationshipPK;
	}

	public void setRelationshipPK(RelationshipPK relationshipPK) {
		this.relationshipPK = relationshipPK;
	}

	public boolean isAreFriends() {
		return areFriends;
	}

	public void setAreFriends(boolean areFriends) {
		this.areFriends = areFriends;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public boolean isReceiveUpdates() {
		return receiveUpdates;
	}

	public void setReceiveUpdates(boolean receiveUpdates) {
		this.receiveUpdates = receiveUpdates;
	}

	@Override
	public String toString() {
		return "Relationship [relationshipPK=" + relationshipPK + ", areFriends=" + areFriends + ", isBlocked="
				+ isBlocked + ", receiveUpdates=" + receiveUpdates + "]";
	}

}
