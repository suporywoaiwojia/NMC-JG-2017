/**
 * Copyright (c) 2011, The David.Wang All rights reserved.
 * 
 * You can use this software for free.
 * If you want to report bug, please send mail to SeptWolves800515@gmail.com
 */
package com.menksoft.cms.application.security.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.menksoft.cms.application.permission.entity.Resource;
import com.menksoft.cms.application.permission.entity.Role;
import com.menksoft.cms.application.permission.service.ResourceService;

/**
 * @author David
 *
 */
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	private static Map<String, Collection<ConfigAttribute>> resources = null;
	private static PathMatcher pathMatcher = new AntPathMatcher();

	private ResourceService resourceService;

	/* (non-Javadoc)
	 * @see org.springframework.security.access.SecurityMetadataSource#getAllConfigAttributes()
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		if (resources == null) {
			initResources();
		}
		Collection<ConfigAttribute> allConfigAttributes = new ArrayList<ConfigAttribute>();
		Iterator<String> iterator = resources.keySet().iterator();
		while (iterator.hasNext()) {
			Collection<ConfigAttribute> configAttribute = resources.get(iterator.next());
			allConfigAttributes.addAll(configAttribute);
		}

		return allConfigAttributes;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.SecurityMetadataSource#getAttributes(java.lang.Object)
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (resources == null) {
			initResources();
		}
		Collection<ConfigAttribute> configAttribute = null;

		String url = ((FilterInvocation)object).getRequestUrl();
		Iterator<String> iterator = resources.keySet().iterator();
		while (iterator.hasNext()) {
			String resourceURL = iterator.next();
			if(isMatches(resourceURL, url)) {
				configAttribute = resources.get(resourceURL);
				break;
			}
		}
		return configAttribute;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.SecurityMetadataSource#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

	public boolean isMatches(String resourceURL, String url) {
		boolean isMatch = false;
		if("/**".equals(resourceURL) || "**".equals(resourceURL)) {
			isMatch = true;
		} else {
			isMatch = pathMatcher.match(resourceURL, url);
		}
//		if(url.equals("/websocket"))
//			isMatch = true;
		return isMatch;
	}

	public void initResources() {
		SecurityMetadataSource.resources = new LinkedHashMap<String, Collection<ConfigAttribute>>();
		List<Resource> resources = resourceService.getAllResources();
		for(Resource resource : resources) {
			String resourceURL = resource.getUrl();
			List<Role> roles = resource.getRoles();

			Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();

			for(Role role : roles) {
				ConfigAttribute configAttribute = new SecurityConfig(role.getId());
				configAttributes.add(configAttribute);
			}

			SecurityMetadataSource.resources.put(resourceURL, configAttributes);
		}
	}

	/**
	 * @param resourceService the resourceService to set
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
}
