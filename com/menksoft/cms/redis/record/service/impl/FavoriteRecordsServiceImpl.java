package com.menksoft.cms.redis.record.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.menksoft.cms.application.dao.hibernate.PagingTools;
import com.menksoft.cms.application.dao.redis.RedisTemplate;
import com.menksoft.cms.manage.member.entity.Member;
import com.menksoft.cms.manage.member.service.MemberService;
import com.menksoft.cms.redis.record.entity.FavoriteRecords;
import com.menksoft.cms.redis.record.service.FavoriteRecordsService;
import com.menksoft.util.Const;
@Service("favoriteRecordsService")
public class FavoriteRecordsServiceImpl implements FavoriteRecordsService {
private final Logger log = Logger.getLogger(getClass());
	
	@Resource
	private RedisTemplate redisTemplate;
	@Resource
	private MemberService memberService;
	@Override
	public String setFavoriteRecords(String loginid, String[] FavoriteRecord)
			throws Throwable {
		Member member=memberService.getMemberByWebsite(loginid);
		String key=Const.FAVORITERECORD+member.getId();
		Jedis jedis = null;
		String basePath = Const.BASEPATH;
		String f="0";
		try {
			//拼接数据值 FavoriteRecord[0]为标题  FavoriteRecord[1]页面地址 FavoriteRecord[2]封面地址
			 
			String value=FavoriteRecord[0]+","+basePath+FavoriteRecord[1]+","+FavoriteRecord[2]+","+FavoriteRecord[1].replace("page/", "").replace(".html", "");
			jedis = redisTemplate.getConnection();
			jedis.lrem(key, 0, value);//删除所有相同数据
			jedis.lpush(key, value);//插入数据
			f="1";
			
		} catch (Throwable e) {
			log.warn("收藏记录数据插入失败。" + e);
			
		} finally {
			redisTemplate.closeConnection(jedis);
		}
		return f;
	}

	@Override
	public PagingTools<FavoriteRecords> getFavoriteRecords(long id, int pageRecord,
			int startpage) throws Throwable {
		String key=Const.FAVORITERECORD+id;
		Jedis jedis = null;
		List<FavoriteRecords> FavoriteRecords_list= new ArrayList<FavoriteRecords>();
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
					FavoriteRecords fr=new FavoriteRecords();
						fr.setId(String.valueOf(i));
						i++;
						fr.setMemberid(String.valueOf(id));
						fr.setTitle(a[0]);
						fr.setUrl(a[1]);
						fr.setCoverPath(a[2]);
						fr.setContentId( Long.parseLong(a[3]));
						FavoriteRecords_list.add(fr);
						
				}
			}
		} catch (Throwable e) {
			log.warn("收藏记录数据读取失败。" + e);
		} finally {
			redisTemplate.closeConnection(jedis);
		}
		return  new PagingTools<FavoriteRecords>(count, pageRecord, (int) startpage,
				FavoriteRecords_list);
	}
	public String[] splitValue(String val){
		String []v=val.split(",");
		return v;
		
	}

	@Override
	public String deleteFavoriteRecords(long id, int index) throws Throwable {
		String key=Const.FAVORITERECORD+id;
		Jedis jedis = null;
		String f="0";
		try {
			jedis = redisTemplate.getConnection();
			String v=jedis.lindex(key, index);
			jedis.lrem(key, 0, v);
			f="1";
			
		} catch (Throwable e) {
			log.warn("收藏记录数据删除失败。" + e);
		} finally {
			redisTemplate.closeConnection(jedis);
		}
		return f;
	}
}
