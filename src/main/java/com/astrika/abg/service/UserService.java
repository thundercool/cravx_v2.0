package com.astrika.abg.service;

import java.util.List;

import com.astrika.abg.exception.DuplicateLoginException;
import com.astrika.abg.exception.NoSuchUserException;
import com.astrika.abg.model.User;

public interface UserService {

	User findByLoginId(String loginId);

	User findByUserId(Long userId);

	User getLoggedInUser();

	User saveUser(User user);

	User updateUser(Long userId, String firstName, String lastName, String emailId, String mobile) throws DuplicateLoginException;
	
	void sendRegstrationMail(User user, String compName, String url);
	
	User activateUser(long userId) throws NoSuchUserException;

	User inActivateUser(long userId) throws NoSuchUserException;
	
    List<User> findAllUser(boolean active);

	//List<User> findByEmailId(String emailId);	

}
