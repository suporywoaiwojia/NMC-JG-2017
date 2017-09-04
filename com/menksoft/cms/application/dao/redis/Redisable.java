/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : KeyValueDatabase.java
 * 描述          : 
 * 作者          : 王弘
 * 日期          : 2012-12-11
 * 公司          : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *     1     2012-12-11       王弘               新建文件
 */
package com.menksoft.cms.application.dao.redis;

import java.util.Map;

/**
 * @author wangh
 *
 */
public interface Redisable {
	public abstract String getKey();
	
	public abstract Map<String,String> getVlaue();
	
	public abstract void buildEntity(String key, Map<String,String> value);
}
