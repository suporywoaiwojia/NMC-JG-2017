package com.menksoft.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.menksoft.cms.application.permission.entity.Resource;
import com.menksoft.cms.manage.webContent.entity.Columns;

public class TreeBuild {
	
	public static List<Map<String,Object>> createComboTreeTree(List<Columns> list) {  
		List<Map<String,Object>> comboTreeList  =new ArrayList<Map<String,Object>>();  
	    for (int i = 0; i < list.size(); i++) {  
	        Map<String, Object> map = null;  
	        Columns c =list.get(i);  
	        if (c.getParent()==null) {  
	            map = new HashMap<String, Object>();  
	            //这里必须要将对象角色的id、name转换成ComboTree在页面的显示形式id、text  
	            //ComboTree,不是数据表格，没有在页面通过columns转换数据的属性 
	         
	            map.put("id", c.getId());         //id
	            	map.put("text",c.getName());      //名称
	            if(c.getActionPath()==null)
	            	c.setActionPath("");
	            map.put("action",c.getActionPath());
	            map.put("children", createComboTreeChildren(list, c));  
	        }  
	        if (map != null)  
	            comboTreeList.add(map);  
	    }  
	    return comboTreeList;
	} 
	private static List<Map<String, Object>> createComboTreeChildren(List<Columns> list, Columns column) {  
	    List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();  
	    for (int j = 0; j < list.size(); j++) {  
	        Map<String, Object> map = null;  
	        Columns c =  list.get(j);  
	        if (c.getParent()!=null&&c.getParent().equals(column)) {  
	            map = new HashMap<String, Object>();  
	            //这里必须要将对象角色的id、name转换成ComboTree在页面的显示形式id、text  
	            //ComboTree,不是数据表格，没有在页面通过columns转换数据的属性  
	            map.put("id", c.getId());  
	            map.put("text", c.getName()); 
	            if(c.getActionPath()==null)
	            	c.setActionPath("");
	            map.put("action",c.getActionPath());
	            map.put("children", createComboTreeChildren(list, c));  
	        }  
	          
	        if (map != null)  
	            childList.add(map);  
	    }  
	    return childList;  
	} 
	public static List<Map<String,Object>> createResourceTree(List<Resource> list) {  
		List<Map<String,Object>> comboTreeList  =new ArrayList<Map<String,Object>>();  
	    for (int i = 0; i < list.size(); i++) {  
	        Map<String, Object> map = null;  
	        Resource r =list.get(i);  
	        if (r.getParent()==null) {  
	            map = new HashMap<String, Object>();  
	            //这里必须要将对象角色的id、name转换成ComboTree在页面的显示形式id、text  
	            //ComboTree,不是数据表格，没有在页面通过columns转换数据的属性 
	         
	            map.put("id", r.getId());         //id  
	            map.put("text",r.getResourceName());      //名称
	           
	            map.put("children", createResourceTreeChildren(list, r));  
	        }  
	        if (map != null)  
	            comboTreeList.add(map);  
	    }  
	    return comboTreeList;
	} 
	private static List<Map<String, Object>> createResourceTreeChildren(List<Resource> list, Resource resource) {  
	    List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();  
	    for (int j = 0; j < list.size(); j++) {  
	        Map<String, Object> map = null;  
	        Resource r =  list.get(j);  
	        if (r.getParent()!=null&&r.getParent().equals(resource)) {  
	            map = new HashMap<String, Object>();  
	            //这里必须要将对象角色的id、name转换成ComboTree在页面的显示形式id、text  
	            //ComboTree,不是数据表格，没有在页面通过columns转换数据的属性  
	            map.put("id", r.getId());  
	            map.put("text", r.getResourceName()); 
	            map.put("children", createResourceTreeChildren(list, r));  
	        }  
	          
	        if (map != null)  
	            childList.add(map);  
	    }  
	    return childList;  
	} 
}
