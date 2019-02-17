package com.astrika.abg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.astrika.abg.model.AreaMaster;
import com.astrika.abg.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select u from User u left join fetch u.roles where u.active=1 and u.loginId=?1")
	User findByLoginIdIgnoreCase(String loginId);
	
	@Query("select u from User u left join fetch u.roles where u.userId=?1")
	User findByUserId(Long userId);
	
	
	@Query("select u from User u   where u.active =?1 ")
	List<User>  findALLByActiveOrInactive(boolean active);
	

}