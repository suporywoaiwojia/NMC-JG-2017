/**
 * Copyright (c) 2011, The David.Wang All rights reserved.
 * 
 * You can use this software for free.
 * If you want to report bug, please send mail to SeptWolves800515@gmail.com
 */
package com.menksoft.cms.application.security.config;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


/**
 * @author David
 *
 */
public class AccessDecisionManager implements org.springframework.security.access.AccessDecisionManager {

	/* (non-Javadoc)
	 * @see org.springframework.security.access.AccessDecisionManager#decide(org.springframework.security.core.Authentication, java.lang.Object, java.util.Collection)
	 */
	@Override
	public void decide(Authentication authentication, Object object,Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,InsufficientAuthenticationException {
		boolean isPass = false;
		if(configAttributes != null) {
			Iterator<ConfigAttribute> iterator=configAttributes.iterator();
			start:while (iterator.hasNext()) {
			
				ConfigAttribute configAttribute = iterator.next();
				String roleId = ((SecurityConfig)configAttribute).getAttribute();
				
				for(GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
					System.out.println(grantedAuthority.getAuthority()+"===="+roleId);
					if(roleId.equals(grantedAuthority.getAuthority())) {
						isPass = true;
						break start;
					}
				}
			}
//			isPass = true;
			System.out.println("isPass==="+isPass);
			if(!isPass) {
				throw new AccessDeniedException("访问被拒绝。");
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.AccessDecisionManager#supports(org.springframework.security.access.ConfigAttribute)
	 */
	@Override
	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.AccessDecisionManager#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
}