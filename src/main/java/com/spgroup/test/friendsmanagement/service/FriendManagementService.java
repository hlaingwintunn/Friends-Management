package com.spgroup.test.friendsmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 
 * @author hlaingwintunn
 *
 */

public interface FriendManagementService {
	
	  boolean friend(List<String> emailList) throws FriendException;

	  List<String> friendList(String email) throws FriendException;

	  List<String> commonFriendList(List<String> emailList) throws FriendException;

	  boolean subscribeToUpdates(String requestor, String target) throws FriendException;

	  boolean block(String requestor, String target) throws FriendException;

	  List<String> receiveUpdatesList(String email) throws FriendException;

}
