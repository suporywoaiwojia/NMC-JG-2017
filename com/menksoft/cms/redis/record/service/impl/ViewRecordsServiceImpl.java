package com.menksoft.cms.redis.record.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.application.dao.redis.RedisTemplate;
import com.menksoft.cms.manage.member.entity.Member;
import com.menksoft.cms.manage.member.service.MemberService;
import com.menksoft.cms.redis.record.entity.ViewRecords;
import com.menksoft.cms.redis.record.service.ViewRecordsService;
import com.menksoft.util.Const;

import redis.clients.jedis.Jedis;
@Service("viewRecordsService")
public class ViewRecordsServiceImpl implements ViewRecordsService {
	private final Logger log = Logger.getLogger(getClass());
	
	@Resource
	private RedisTemplate redisTemplate;
	@Resource
	private MemberService memberService;

	@Override
	public void setViewRecords(String loginid, String[] viewRecord)
			throws Throwable {
		Member member=memberService.getMemberByWebsite(loginid);
		String key=Const.VIEWRECORD+member.getId();
		Jedis jedis = null;
		String basePath = Const.BASEPATH;
		try {
			//拼接数据值 viewRecord[0]为标题  viewRecord[1]页面地址 viewRecord[2]封面地址
			//  java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    //  String  s = format1.format(new Date());
//			String value=viewRecord[0]+","+basePath+viewRecord[1]+","+viewRecord[2]+","+s;
			String value=viewRecord[0]+","+basePath+viewRecord[1]+","+viewRecord[2]+","+viewRecord[1].replace("page/", "").replace(".html", "");
			jedis = redisTemplate.getConnection();
			jedis.lrem(key, 0, value);//删除所有相同数据
			jedis.lpush(key, value);//插入数据
			jedis.ltrim(key, 0, 299);//只保留300个数据
			
		} catch (Throwable e) {
			log.warn("观看记录数据插入失败。" + e);
		} finally {
			redisTemplate.closeConnection(jedis);
		}
	}

	@Override
	public PagingTools<ViewRecords> getViewRecords(long id,int pageRecord,int startpage) throws Throwable {
		String key=Const.VIEWRECORD+id;
		Jedis jedis = null;
		List<ViewRecords> ViewRecords_list= new ArrayList<ViewRecords>();
		List<String> list=null;
		long count=0;
		try {
			jedis = redisTemplate.getConnection();
			count=jedis.llen(key);
			long start = new Long(startpage)*pageRecord;
			long stop = start + (pageRecord - 1);
			
			if (start <= count - 1) {
				list=jedis.lrange(key,  start, stop);
				long i=start;
				for (String v : list) {
					String [] a=splitValue(v);
						ViewRecords vr=new ViewRecords();
						vr.setId(String.valueOf(i));
						i++;
						vr.setMemberid(String.valueOf(id));
						vr.setTitle(a[0]);
						vr.setUrl(a[1]);
						vr.setCoverPath(a[2]);
//						vr.setViewdate(a[3]);
						vr.setContentId( Long.parseLong(a[3]));
					ViewRecords_list.add(vr);
					
				}
			}
		} catch (Throwable e) {
			log.warn("观看记录数据获取失败。" + e);
		} finally {
			redisTemplate.closeConnection(jedis);
		}
		return  new PagingTools<ViewRecords>(count, pageRecord, (int) startpage,
				ViewRecords_list);
	}
	
	public String[] splitValue(String val){
//		String []v=val.split("\\|");
		String []v=val.split(",");
		return v;
		
	}

	@Override
	public String deleteViewRecords(long id, int index) throws Throwable {
		String key=Const.VIEWRECORD+id;
		Jedis jedis = null;
		String f="0";
		try {
			jedis = redisTemplate.getConnection();
			String v=jedis.lindex(key, index);
			jedis.lrem(key, 0, v);
			f="1";
			
		} catch (Throwable e) {
			log.warn("观看记录数据删除失败。" + e);
		} finally {
			redisTemplate.closeConnection(jedis);
		}
		return f;
	}
}
