/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : UserAction.java
 * 描述        : 用户管理
 * 作者        : 呼和
 * 日期        : 下午3:48:44
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       下午3:48:44  呼和
 */
package com.menksoft.cms.application.organization.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.application.organization.entity.User;
import com.menksoft.cms.application.organization.service.UserService;
import com.menksoft.cms.application.permission.entity.Role;
import com.menksoft.cms.application.permission.service.RoleService;
import com.menksoft.util.Const;

/**
 * @author 呼和
 */
@Controller
public class UserAction {
	private Logger log = Logger.getLogger(this.getClass());
	@javax.annotation.Resource
	UserService userService;
	@javax.annotation.Resource
	RoleService roleService;
	/**
	 * 用户列表查询
	 * @param modelMap
	 * @param startIndex
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/manage/user/list/{startIndex}", method=RequestMethod.GET)
	public String queryUser(ModelMap modelMap,
			@PathVariable("startIndex") Integer startIndex,User user){
		PagingTools<User> pagingTools=null;
		try {
			pagingTools=userService.queryUser(user, startIndex-1, PagingTools.PAGER_ECORD_DEFAULT);
		}catch(Throwable e){
			log.error("用户查询"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			
			return "manage/errer.html";
		}
		modelMap.put("user", user);
		modelMap.put("pagingTools", pagingTools);
		return "manage/user/user_li.jsp";
	}
	
	/**
	 * 新建页面跳转
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/manage/user/newpage", method=RequestMethod.GET)
	public String gotoNewPage(ModelMap modelMap){
		List<Role> role=null;
		try {
			role=roleService.queryAllRole();
		} catch (Throwable e) {
			log.error("用户信息新建页面：角色信息"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			return "manage/errer.html";
		}
		modelMap.put("role", role);
		return "manage/user/user_new.jsp";
	}
	
	/**
	 * 编辑页面跳转
	 * @param modelMap
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/manage/user/editpage/{id}", method=RequestMethod.GET)
	public  String gotoEditPage(ModelMap modelMap,@PathVariable("id") long id){
		User user=null;
		List<Role> role=null;
		try{
			//保存操作
			user=userService.queryUserById(id);
			role=roleService.queryAllRole();
		}catch (Throwable e) {
			//报错失败，并跳转至错误页面
			log.error("用户信息"+Const.MESSAGE_FAIL_QUERY_NOT_EXISTED, e);
			return "manage/errer.html";
		}
		modelMap.put("user", user);
		modelMap.put("role", role);
		return "manage/user/user_ed.jsp";
	}
	
	/**
	 * 新建保存
	 * @param user
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/manage/user/save", method=RequestMethod.POST)
	public  @ResponseBody String saveUser(User user,ModelMap modelMap) {
		String msg = "0";
		try{
			userService.saveUser(user);
			msg="1";
		}catch (Throwable e) {
			//报错失败，并跳转至错误页面
			log.error("用户信息"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
		}
		return msg;
	}
	
	/**
	 * 编辑保存
	 * @param modelMap
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/manage/user/update", method=RequestMethod.POST)
	public @ResponseBody String updateUser(ModelMap modelMap,User user) {
		String msg = "0";
		try{
			//保存操作
		    userService.updateUser(user);
		    msg="1";
		}catch (Throwable e) {
			//报错失败
			log.error("用户信息"+Const.MESSAGE_FAIL_UNCHECKED_SAVE, e);
		}
		 return msg;
		
	}

	/**
	 * 删除用户信息	
	 * @param modelMap
	 * @param id	用户对象 逗号分隔
	 * @param pagnum	当前页数
	 * @param user	查询对象
	 * @return
	 */
	@RequestMapping(value="/manage/user/deleteUser/{ids}/{pagnum}", method=RequestMethod.GET)
	public String deleteUser(ModelMap modelMap,@PathVariable("ids") String ids,
			@PathVariable("pagnum") Integer pagnum,User user){
		try{			
			//删除操作
            //删除r_tab_user_role与ID相关的内容
           
			userService.deleteUser(ids);
			modelMap.put("message", "删除成功");
		}catch (Throwable e) {
			//报错失败，并跳转至错误页面
			log.error("用户信息删除失败", e);
			modelMap.put("message", "删除失败,"+Const.MESSAGE_FAIL_PRIFX+e.getMessage());
		}
		
		return queryUser(modelMap,pagnum-1,user);
	}
	      
	@RequestMapping(value="/manage/user/back", method=RequestMethod.POST)
	public String goBack(ModelMap modelMap){
		User user_search=new User();
		return queryUser(modelMap,0,user_search);
	}
	
	
	/**
	 * 页面表单提交 日期格式化
	 * @param request
	 * @param binder
	 */
	@InitBinder
    public void InitBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        // 不要删除下行注释!!!   将来"yyyy-MM-dd"将配置到properties文件中
        //SimpleDateFormat dateFormat = new SimpleDateFormat(getText("date.format", request.getLocale()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
    }
}
