package com.spgroup.test.friendsmanagement.service;

import java.util.ArrayList;
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
		List<String> relationList = new ArrayList<>();
		if(fmRepository.existsById(email)) {
			relationList = relationshipRepository.getFriendList(email);
		} else {
			logger.warn("User not found");
		}
		return relationList;
	}

	@Override
	public List<String> commonFriendList(List<String> emailList) {
		if(emailList.get(0).equals(emailList.get(1))) {
			logger.warn("Email address identical");
		}
		
		List<String> commonList = new ArrayList<>();
		
		final Long friendsCount = emailList.stream()
				.filter(email -> fmRepository.existsById(email))
				.count();
		
		if(friendsCount == emailList.size()) {
			commonList = relationshipRepository.getCommonFriendList(emailList);
		} else {
			logger.warn("email address not found");
		}
		return commonList;
	}

	@Override
	public boolean subscribeToUpdates(String requestor, String target) {
		if(requestor.equalsIgnoreCase(target)) {
			logger.warn("Email address identical");
		}
		
		if(fmRepository.existsById(requestor) && fmRepository.existsById(target)) {
			RelationshipPK relPK = new RelationshipPK(requestor, target);
			Relationship rel = new Relationship();
			
			if(relationshipRepository.existsById(relPK)) {
				Optional<Relationship> relOpt = relationshipRepository.findById(relPK);
				if(relOpt.isPresent()) {
					rel = relOpt.get();
					rel.setReceiveUpdates(true);
				} else {
					logger.warn("There is no Relationship between requestor {}, target {}", requestor, target);
				}
			} else {
				rel = new Relationship(relPK, false, false, true);
			}
			relationshipRepository.save(rel);
		} else {
			logger.warn("Email address not found");
		}
		return true;
	}

	@Override
	public boolean block(String requestor, String target) {
		if(requestor.equals(target)) {
			logger.warn("Email Address identical");
		}
		
		if(fmRepository.existsById(requestor) && fmRepository.existsById(target)) {
			RelationshipPK relPK = new RelationshipPK(requestor, target);
			Relationship rel = new Relationship();
			
			if(relationshipRepository.existsById(relPK)) {
				Optional<Relationship> relOpt = relationshipRepository.findById(relPK);
				
				if(relOpt.isPresent()) {
					rel = relOpt.get();
					rel.setReceiveUpdates(false);
					if(!rel.isAreFriends()) {
						rel.setBlocked(true);
					}
				}
			} else {
				rel = new Relationship(relPK, false, true, false);
			}
			
			relationshipRepository.save(rel);
		} else {
			logger.warn("Email address not found");
		}
		
		return true;
	}

	@Override
	public List<String> receiveUpdatesList(String email) {
		List<String> updateList = new ArrayList<>();
		
		if(fmRepository.existsById(email)) {
			updateList = relationshipRepository.getReceiveUpdatesList(email);
		} else {
			logger.warn("User not found");
		}
		return updateList;
	}

}
