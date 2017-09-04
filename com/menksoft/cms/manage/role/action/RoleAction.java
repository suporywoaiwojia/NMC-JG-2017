/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : RoleAction.java
 * 描述          : 
 * 作者          : 王弘
 * 日期          : 2012-11-13
 * 公司          : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *     1     2012-11-13       王弘               新建文件
 */
package com.menksoft.cms.manage.role.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menksoft.cms.application.permission.entity.Resource;
import com.menksoft.cms.application.permission.entity.Role;
import com.menksoft.cms.application.permission.service.ResourceService;
import com.menksoft.cms.application.permission.service.RoleService;
import com.menksoft.util.Const;
import com.menksoft.util.TreeBuild;

/**
 * @author wangh
 * 角色控制器类，负责角色管理模块各类业务处理的控制转发。
 */
@Controller
public class RoleAction {
	private Logger log = Logger.getLogger(this.getClass());
	@javax.annotation.Resource
	private RoleService roleService;
	@javax.annotation.Resource
	private ResourceService resourceService;
	
	/**
	 * 跳转到角色列表页面
	 * @param modelMap 保存角色列表信息
	 * @return 角色列表页面地址
	 */
	@RequestMapping(value="/manage/role/list", method=RequestMethod.GET)
	public String forwardToRoleListPage(ModelMap modelMap) {
		String foreardPage = "manage/role/role-list.jsp";
		try {
			List<Role> roles = roleService.getAllRole();
			modelMap.put("roles", roles);
		} catch (Throwable e) {
			log.error("角色"+Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
			modelMap.put(Const.MESSAGE_NAME, e);
		}
		
		return foreardPage;
	}
	
	//
	/**
	 * 打开权限管理界面
	 * @param roleId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/manage/role/resource/{roleId}")
	public String forwardToRoleResourceTreePage(@PathVariable String roleId, ModelMap modelMap) {
		String forwardPage = "manage/role/resource.jsp";
		try {
			List<Resource> resources = resourceService.getAllResourcesWithRolesAndParent();
			List<Map<String,Object>> ResourceTreeList  =new ArrayList<Map<String,Object>>(); 
			ResourceTreeList=TreeBuild.createResourceTree(resources);
			 JSONArray jsonArray =JSONArray.fromObject(ResourceTreeList);
			Role r= roleService.getRoleById(roleId);
			List<Resource> list=r.getResources();
			modelMap.put("Treedata", jsonArray);
			String s="";
			for(Resource r1:list){
				s=s+r1.getId()+",";
				if(r1.getParent()!=null&&r1.getParent().getId()>0)
					s=s.replace(String.valueOf(r1.getParent().getId()+","),"");
			}
			s=s.substring(0,s.lastIndexOf(","));

			modelMap.put("roleId", roleId);
			modelMap.put("s", s);
		} catch (Throwable e) {
			log.error("资源信息"+Const.MESSAGE_FAIL_UNCHECKED_QUERY, e);
			modelMap.put(Const.MESSAGE_NAME, e);
			// TODO 错误处理，需要跳转到公共错误页面。
		}
		
		return forwardPage;
	}
	
	/*
	 * 
	 * @param roleId
	 * @param resourceIds
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/manage/role/resource/save", method=RequestMethod.GET)
	public @ResponseBody String savePermission(String roleId, String resourceIds, ModelMap modelMap) {
		String message = null;
		Role role = new Role();
		role.setId(roleId);
		
		List<Resource> resources = new ArrayList<Resource>();
		String[] resourcesId = resourceIds.split(",");
		for(String resourceId : resourcesId) {
			long id = new Long(resourceId);
			Resource resource = new Resource();
			resource.setId(id);
			resources.add(resource);
		}
		
		role.setResources(resources);
		
		try {
			roleService.saveRoleResource(role);
			message = Const.MESSAGE_SUCCESS_SAVE;
		} catch (Throwable e) {
			log.error("角色权限" + Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
			message = "角色权限" + Const.MESSAGE_FAIL_UNCHECKED_SAVE;
		}
		return message;
	}
	
	/**
	 * 保存角色
	 * @param role 角色对象
	 * @param modelMap 保存角色列表信息
	 * @return 角色列表页面地址
	 */
	@RequestMapping(value="/manage/role/save", method=RequestMethod.POST)
	public @ResponseBody String addSlideshow(Role role, ModelMap modelMap) {
		String msg="0";
		try {
			if(!isExist(role)){//不重复
				roleService.saveRole(role);
			    msg="1";
			}
		} catch (Throwable e) {
			return msg;
		}
		return msg;
	}
	
	/**
	 * 更新角色
	 * @param role 角色对象
	 * @param modelMap 保存角色列表信息
	 * @return 角色列表页面地址
	 */
	@RequestMapping(value="/manage/role/update", method=RequestMethod.POST)
	public @ResponseBody String editSlideshow(Role role, ModelMap modelMap) {
		String msg="0";
		try {
			roleService.updateRole(role);
			modelMap.put(Const.MESSAGE_NAME, Const.MESSAGE_SUCCESS_SAVE);
			msg="1";
		} catch (Throwable e) {
			log.error("角色"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
		}
		return msg;
	}
	
	/**
	 * 批量删除角色
	 * @param ids 角色Id的数组
	 */
	@RequestMapping(value="/manage/role/delete/{ids}", method=RequestMethod.GET)
	public @ResponseBody void deleteRoles(@PathVariable String ids) {
		Assert.hasText(ids, "角色"+Const.MESSAGE_FAIL_DELETE_NOT_EXISTED);
		
		String[] idArray = ids.split(",");
		try {
			roleService.deleteBatch(idArray);
		} catch (Throwable e) {
			log.error("角色"+Const.MESSAGE_FAIL_UNCHECKED_DELETE, e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * 判断是否存在给定名称的角色
	 * @param name 角色名称
	 * @return 如果存在，返回true；否则返回false；执行过程中发生错误时，返回true。
	 */
	public boolean isExist(Role role) {
		return roleService.isExist(role);
	}
	
	protected void checkedSetting(Resource resource) {
//		if(resource.getTreeItemIsChecked() != null && resource.getTreeItemIsChecked().equals("1")) {
//			resource.setTreeItemIsChecked("-1");
//		}
		
		Resource parent = resource.getParent();
		if(parent != null && parent.getId() > 0) {
			checkedSetting(parent);
		}
	}
}
