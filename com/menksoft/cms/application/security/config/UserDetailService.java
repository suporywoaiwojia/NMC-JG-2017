/**
 * Copyright (c) 2011, The David.Wang All rights reserved.
 * 
 * You can use this software for free.
 * If you want to report bug, please send mail to SeptWolves800515@gmail.com
 */
package com.menksoft.cms.application.security.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.menksoft.cms.application.organization.service.UserService;
import com.menksoft.cms.application.permission.entity.Role;

/**
 * @author David
 *
 */
public class UserDetailService implements UserDetailsService {
	private UserService userService;

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Collection<GrantedAuthority> grantedAuthorityes = new ArrayList<GrantedAuthority>();
		com.menksoft.cms.application.organization.entity.User user = userService.getUserWithRolesByLoginId(userName);
		List<Role> roles = user.getRoles();
		for(Role role : roles) {
			SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getId());
			grantedAuthorityes.add(grantedAuthority);
		}

//		return new User(user.getLoginId(), user.getPassword(), grantedAuthorityes);
		return new User(user.getLoginId()+"-"+user.getUserName(), user.getPassword(), grantedAuthorityes);
	}
   
	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}