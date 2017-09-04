/**
 * 项目名称: 蒙科立SSH框架
 * 文件名    : MD5Encoder.java
 * 描述        : 
 * 作者        : lenovo
 * 日期        : 下午2:58:36
 * 公司        : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *    1       下午2:58:36  lenovo
 */
package com.menksoft.util;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 * @author 呼和
 */
public class MD5Encoder  implements PasswordEncoder {

	 public String encodePassword(String origPwd, Object salt)  
	            throws DataAccessException { 
		 System.out.println(origPwd+"==="+MD5.getMD5ofStr(origPwd));
	        return MD5.getMD5ofStr(origPwd);  
	     }  
	     public boolean isPasswordValid(String encPwd, String origPwd, Object salt)  
	             throws DataAccessException {  
	         return encPwd.equals(encodePassword(origPwd, salt));  
	     }  

}
