/**
 * Copyright (c) 2011, The David.Wang All rights reserved.
 * 
 * You can use this software for free.
 * If you want to report bug, please send mail to SeptWolves800515@gmail.com
 */
package com.menksoft.cms.application.permission.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.application.organization.entity.behavior.UserBehavior;
import com.menksoft.cms.application.permission.entity.Resource;
import com.menksoft.cms.application.permission.entity.Role;
import com.menksoft.cms.application.permission.entity.behavior.impl.ResourceBehaviorImpl;
import com.menksoft.cms.application.permission.service.ResourceService;

/**
 * @author David
 *
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
	private Logger log = Logger.getLogger(this.getClass());

	@javax.annotation.Resource
	private ResourceBehaviorImpl resourceBehavior;
	
	@javax.annotation.Resource
	private UserBehavior userBehavior;

	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.service.impl.ResourceService#getAllResources()
	 */
	@Override
	public List<Resource> getAllResources() {
		List<Resource> resources = null;
		try {
			
			resources = resourceBehavior.getAllResources();
		} catch (Throwable e) {
			log.error("无法获得系统资源。", e);
			throw new RuntimeException("无法获得系统资源。", e);
		}

		return resourceBehavior.removeReduplicate(resources);
	}

	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.service.impl.ResourceService#getFatherResources()
	 */
	@Override
	public List<Resource> getFatherResources() {
		List<Resource> resources = null;
		try {
			resources = resourceBehavior.getFatherResources();
		} catch (Throwable e) {
			log.error("无法获得系统资源。", e);
			throw new RuntimeException("无法获得系统资源。", e);
		}
		return resources;
	}
	
	/* (non-Javadoc)
	 * @see com.menksoft.cms.application.permission.service.impl.ResourceService#getChildResources(com.menksoft.cms.application.permission.entity.Resource)
	 */
	@Override
	public List<Resource> getChildResources(Resource resource) {
		List<Resource> resources = null;
		try {
			UserDetails userDatails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String loginId = userDatails.getUsername().split("-")[0];
			User user = userBehavior.getUserWithRolesByLoginId(loginId);
			List<Role> roles = user.getRoles();
			for(Role role : roles) {
				if(role.getBuiltIn() == 1) {
					resources = resourceBehavior.getChildResources(resource);
					return resourceBehavior.removeReduplicate(resources);
				}
			}
			resources = resourceBehavior.getResourcesByUser(user, resource.getParent());
		} catch (Throwable e) {
			log.error("无法获得系统资源。", e);
			throw new RuntimeException("无法获得系统资源。", e);
		}
		return resourceBehavior.removeReduplicate(resources);
	}
	
	public List<Resource> getRootModuleResources() throws Throwable {
		List<Resource> rootModules = null;
		UserDetails userDatails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String loginId = userDatails.getUsername().split("-")[0];
		User user = userBehavior.getUserWithRolesByLoginId(loginId);
		List<Role> roles = user.getRoles();
		for(Role role : roles) {
			if(role.getBuiltIn() == 1) {
				rootModules = resourceBehavior.getAllRootModuleResources();
//				rootModules = resourceBehavior.getAllModuleResources();
				return resourceBehavior.removeReduplicate(rootModules);
			}
		}
		rootModules = resourceBehavior.getResourcesByUser(user, resourceBehavior.getRootResource());
//		rootModules = resourceBehavior.getResourcesByUser(user);
		return resourceBehavior.removeReduplicate(rootModules);
	}
	
	public List<Resource> getChildModuleResources() throws Throwable {
		List<Resource> rootModules = null;
		UserDetails userDatails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String loginId = userDatails.getUsername().split("-")[0];;
		User user = userBehavior.getUserWithRolesByLoginId(loginId);
		List<Role> roles = user.getRoles();
		for(Role role : roles) {
			if(role.getBuiltIn() == 1) {
				rootModules = resourceBehavior.getAllChildModuleResources();
				return resourceBehavior.removeReduplicate(rootModules);
			}
		}
		rootModules = resourceBehavior.getResourcesByUser(user);
		return resourceBehavior.removeReduplicate(rootModules);
	}
	
	public List<Resource> getAllResourcesWithRolesAndParent() throws Throwable {
		Resource resource = resourceBehavior.getRootResource();
		List<Resource> resources = resourceBehavior.getAllResourcesWithNotRoot();
		resources.add(0, resource);
		
		return resourceBehavior.removeReduplicate(resources);
	}
}
