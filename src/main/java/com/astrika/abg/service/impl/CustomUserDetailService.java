package com.astrika.abg.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.astrika.abg.model.Role;
import com.astrika.abg.model.User;
import com.astrika.abg.service.UserService;

@Service
@Qualifier("userDetailService")
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =null;
			user = userService.findByLoginId(username);
			if (user == null) {
				throw new UsernameNotFoundException("Invalid Username");
			}
			user.setGrantedAuthorities(getRoles(user));
		return user;
	}

	private Collection<GrantedAuthority> getRoles(User user) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(1);
		List<Role> roles = user.getRoles();
		if (roles != null) {
			for(Role role:roles) {
				authList.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
			}
		}
		return authList;
	}

}
