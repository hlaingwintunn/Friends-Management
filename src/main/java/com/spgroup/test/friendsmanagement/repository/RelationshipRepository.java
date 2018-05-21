package com.spgroup.test.friendsmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spgroup.test.friendsmanagement.model.Relationship;
import com.spgroup.test.friendsmanagement.model.RelationshipPK;

/**
 * 
 * @author hlaingwintunn
 *
 */

public interface RelationshipRepository extends CrudRepository<Relationship, RelationshipPK>{
	@Query("select r.relationshipPK.friendEmail "
		      + "from Relationship r "
		      + "where r.relationshipPK.userEmail = :email "
		      + "and r.areFriends = true")
		  List<String> getFriendList(@Param("email") String email);

		  @Query("SELECT r.relationshipPK.friendEmail "
		      + "FROM Relationship r "
		      + "where r.relationshipPK.userEmail in :email "
		      + "and r.areFriends = true "
		      + "group by r.relationshipPK.friendEmail "
		      + "having count(r.relationshipPK.friendEmail) > 1 ")
		  List<String> getCommonFriendList(@Param("email") List<String> email);

		  @Query("SELECT r.relationshipPK.friendEmail "
		      + "FROM Relationship r "
		      + "where r.relationshipPK.userEmail = :email "
		      + "and r.areFriends = true "
		      + "and r.receiveUpdates = true "
		      + "and r.isBlocked = false")
		  List<String> getReceiveUpdatesList(@Param("email") String email);
}
