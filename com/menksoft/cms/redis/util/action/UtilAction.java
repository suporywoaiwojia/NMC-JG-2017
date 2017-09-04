package com.menksoft.cms.redis.util.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

import com.menksoft.cms.application.dao.redis.RedisTemplate;
import com.menksoft.util.Const;
import com.menksoft.util.Mail;

@Controller
public class UtilAction {
	
	@Resource
	private RedisTemplate redisTemplate;
	
	/**
	 * 设置注册手机验证码
	 * @param session
	 * @param Request
	 * @param tel
	 * @return
	 */
	@RequestMapping(value = "/r-setCode/{tel}", method = RequestMethod.GET)
	public @ResponseBody
	String setRegCode(HttpSession session, HttpServletRequest Request,@PathVariable String tel) {
		String key=Const.REGCODE+tel;
		Jedis jedis = null;
		String code="";
		try {
			jedis = redisTemplate.getConnection();
			code=Mail.SmsCodeGet(tel,"1");
			//code="8888";
			jedis.del(key);
			int seconds=Integer.valueOf(Mail.getString("reg.smstime"))*60;
			jedis.setex(key, seconds, code);
		} catch (Throwable e) {
			e.printStackTrace();
		}finally {
			redisTemplate.closeConnection(jedis);
		}
		return code;
	}
	
	@RequestMapping(value = "/r-getCode/{tel}", method = RequestMethod.GET)
	public @ResponseBody
	String getRegCode(HttpSession session, HttpServletRequest Request,@PathVariable String tel) {
		String key=Const.REGCODE+tel;
		Jedis jedis = null;
		String code="";
		try {
			jedis = redisTemplate.getConnection();
		
			code=jedis.get(key);
		} catch (Throwable e) {
			e.printStackTrace();
		}finally {
			redisTemplate.closeConnection(jedis);
		}
		return code;
	}

}
