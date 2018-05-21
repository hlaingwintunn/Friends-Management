package com.spgroup.test.friendsmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.spgroup.test.friendsmanagement.model.User;

@RepositoryRestResource
public interface FriendManagementRepository extends CrudRepository<User, String> {

}
