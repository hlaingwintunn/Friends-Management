package com.spgroup.test.friendsmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spgroup.test.friendsmanagement.common.request.RequestFriendList;
import com.spgroup.test.friendsmanagement.common.request.RequestFriends;
import com.spgroup.test.friendsmanagement.common.request.RequestReceiveUpdates;
import com.spgroup.test.friendsmanagement.common.request.RequestSubscribe;
import com.spgroup.test.friendsmanagement.common.response.ResponseFriends;
import com.spgroup.test.friendsmanagement.service.FriendException;
import com.spgroup.test.friendsmanagement.service.FriendManagementService;

import io.swagger.annotations.Api;

/**
 * 
 * @author hlaingwintunn
 *
 */

@RestController
@RequestMapping("/api")
@Api(value="FriendManagement REST API")
public class ApiController {
	
	private FriendManagementService fmService;
	
	@Autowired
	public void setFmService(final FriendManagementService fmService) {
		this.fmService = fmService;
	}
	
	@RequestMapping(value = "/friends" , method = RequestMethod.POST, produces = "application/json")
	public ResponseFriends friends(@Valid @RequestBody RequestFriends reqFriend) throws FriendException {
		
		boolean success = fmService.friend(reqFriend.getEmails());
		ResponseFriends resp = new ResponseFriends()
				.setSuccess(success);		
		return resp;	
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
	public ResponseFriends friendsList(@Valid @RequestBody RequestFriendList req) throws FriendException {
		
		List<String> friendList = fmService.friendList(req.getEmail());
		ResponseFriends resp = new ResponseFriends()
				.setSuccess(true)
				.setFriends(friendList)
				.setCount(friendList.size());
		
		return resp;
		}
	
	@RequestMapping(value = "/commonfriend", method = RequestMethod.POST, produces = "application/json")
	public ResponseFriends commmonFriendList(@Valid @RequestBody RequestFriends req) throws FriendException {
		List<String> commonList = fmService.commonFriendList(req.getEmails());
		ResponseFriends resp = new ResponseFriends()
				.setSuccess(true)
				.setFriends(commonList)
				.setCount(commonList.size());
		
		return resp;
		
	}
	
	  @RequestMapping(value ="/subscribe", method = RequestMethod.POST, produces = "application/json")
	  public ResponseFriends subscribe(@Valid @RequestBody RequestSubscribe req) throws FriendException {
	    boolean  subscribed = fmService.subscribeToUpdates(req.getRequestor(), req.getTarget());
	    ResponseFriends resp = new ResponseFriends()
	    		.setSuccess(subscribed);
	    return resp;
	  }
	  
	  @RequestMapping(value = "/block", method = RequestMethod.POST, produces = "application/json")
	  public ResponseFriends block(@Valid @RequestBody RequestSubscribe req) throws FriendException {
		boolean subscribed = fmService.block(req.getRequestor(), req.getTarget());
		ResponseFriends resp = new ResponseFriends()
				.setSuccess(subscribed);
		  
		return resp;
		  
	  }
	
	  @RequestMapping(value = "/receiveUpdates", method = RequestMethod.POST, produces = "application/json")
	  public ResponseFriends receiveUpdatesList(@Valid @RequestBody RequestReceiveUpdates req) throws FriendException {
		  List<String> friendsList = fmService.receiveUpdatesList(req.getSender());
		  ResponseFriends resp = new ResponseFriends()
				  .setSuccess(true)
				  .setRecipients(friendsList);
		return resp;
		}

}
