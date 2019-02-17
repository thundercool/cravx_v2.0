package com.astrika.abg.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.astrika.abg.exception.DuplicateLoginException;
import com.astrika.abg.exception.NoSuchUserException;
import com.astrika.abg.model.User;
import com.astrika.abg.repository.UserRepository;
import com.astrika.abg.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	Logger log = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepo;

	@Override
	public User findByLoginId(String loginId) {
		return userRepo.findByLoginIdIgnoreCase(loginId);
	}

	@Override
	public User findByUserId(Long userId) {
		return userRepo.findByUserId(userId);
	}

	@Override
	public User getLoggedInUser() {
		User user = null;
		Object object =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (object instanceof User)
			user = (User) object;
		return user;
	}

	@Override
	public User saveUser(User user) {
		user = userRepo.save(user);
		return user;
	}

	@Override
	public User updateUser(Long userId, String firstName, String lastName, String emailId, String mobile)
			throws DuplicateLoginException {
		User user=findByUserId(userId);
		user.setUser_Id(userId);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmailId(emailId);
		user.setMobile(mobile);
		user.setActive(true);
		user = userRepo.save(user);
		return user;
	}
	
	@Override
	public User activateUser(long userId) throws NoSuchUserException {
		User user = findByUserId(userId);
		user.setActive(true);
		return userRepo.save(user);
	}

	@Override
	public User inActivateUser(long userId) throws NoSuchUserException {
		User user = findByUserId(userId);
		user.setActive(false);
		return userRepo.save(user);
	}

	@Override
	public void sendRegstrationMail(User user, String compName, String url) {
		
	}

	@Override
	public List<User> findAllUser(boolean active) {
	
		return userRepo.findALLByActiveOrInactive(active);
	}


}
