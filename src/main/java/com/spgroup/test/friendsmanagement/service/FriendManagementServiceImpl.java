package com.spgroup.test.friendsmanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spgroup.test.friendsmanagement.model.Relationship;
import com.spgroup.test.friendsmanagement.model.RelationshipPK;
import com.spgroup.test.friendsmanagement.model.User;
import com.spgroup.test.friendsmanagement.repository.FriendManagementRepository;
import com.spgroup.test.friendsmanagement.repository.RelationshipRepository;

@Service
public class FriendManagementServiceImpl implements FriendManagementService {
	private static final Logger logger = LoggerFactory.getLogger(FriendManagementServiceImpl.class);

	private FriendManagementRepository fmRepository;
	private RelationshipRepository relationshipRepository;

	@Autowired
	public FriendManagementServiceImpl(FriendManagementRepository fmRepository,
			RelationshipRepository relationshipRepository) {
		this.fmRepository = fmRepository;
		this.relationshipRepository = relationshipRepository;
	}

	@Override
	public boolean friend(List<String> emailList) {

		if (emailList.get(0).equals(emailList.get(1))) {
			logger.warn("Email address identical");
		}

		List<User> newUsers = emailList.stream().filter(email -> !fmRepository.existsById(email)).map(User::new)
				.collect(Collectors.toList());

		if (!newUsers.isEmpty()) {
			fmRepository.saveAll(newUsers);
			logger.info("Users [{}] saved successfully.", newUsers);
			// return true;
		} else {
			logger.info("Users is empty and saved failured");
			// return false;
		}

		RelationshipPK relationshipPK = new RelationshipPK(emailList.get(0), emailList.get(1));
		Relationship relationship = getRelationship(relationshipPK);
		if (relationship == null) {
			return false;
		}

		RelationshipPK relationshipPK2 = new RelationshipPK(emailList.get(1), emailList.get(0));
		Relationship relationship2 = getRelationship(relationshipPK2);
		if (relationship2 == null) {
			return false;
		}

		relationshipRepository.save(relationship);
		relationshipRepository.save(relationship2);

		return true;
	}

	private Relationship getRelationship(RelationshipPK relationshipPK) {
		Relationship relationship = null;
		if (relationshipRepository.existsById(relationshipPK)) {
			Optional<Relationship> relationshipOpt = relationshipRepository.findById(relationshipPK);
			if (relationshipOpt.isPresent()) {
				relationship = relationshipOpt.get();
				if (!relationship.isBlocked()) {
					relationship.setAreFriends(true);
				} else {
					return null;
				}
			} else {
				logger.warn("There is no Relationship with key: {}", relationshipPK);
			}

		} else {
			relationship = new Relationship(relationshipPK, true, false, false);
		}
		return relationship;
	}

	@Override
	public List<String> friendList(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> commonFriendList(List<String> emailList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean subscribeToUpdates(String requestor, String target) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean block(String requestor, String target) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> receiveUpdatesList(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
