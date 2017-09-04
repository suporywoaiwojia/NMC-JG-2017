package com.menksoft.cms.application.permission.service;

import java.util.List;

import com.menksoft.cms.application.permission.entity.Resource;

public interface ResourceService {

	public abstract List<Resource> getAllResources();

	public abstract List<Resource> getFatherResources();

	public abstract List<Resource> getChildResources(Resource resource);

	public List<Resource> getRootModuleResources() throws Throwable;
	
	public List<Resource> getChildModuleResources() throws Throwable;
	
	public List<Resource> getAllResourcesWithRolesAndParent() throws Throwable;
	
}