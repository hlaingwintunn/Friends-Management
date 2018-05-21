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
import com.spgroup.test.friendsmanagement.common.response.ResponseFriends;
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
	public ResponseFriends friends(@Valid @RequestBody RequestFriends reqFriend) {
		
		boolean success = fmService.friend(reqFriend.getEmails());
		ResponseFriends resp = new ResponseFriends()
				.setSuccess(success);		
		return resp;	
	}
	
	@RequestMapping(value = "/list")
	public ResponseFriends friendsList(@Valid @RequestBody RequestFriendList req) {
		
		List<String> friendList = fmService.friendList(req.getEmail());
		ResponseFriends resp = new ResponseFriends()
				.setSuccess(true)
				.setFriends(friendList)
				.setCount(friendList.size());
		
		return resp;
		}
	

}
