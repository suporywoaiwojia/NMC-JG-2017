package com.menksoft.cms.manage.data.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.manage.data.service.CheckRepeatService;
@Service("CheckRepeatService")
public class CheckRepeatServiceImpl implements CheckRepeatService {
	@Resource 
    private HibernateTemplate hibernateTemplate;
	@Override
	public boolean getRepeatListForSave(Class<?> c,String bm, String name, String table)
			throws Throwable {
		boolean b=false;
		List<?> list=new ArrayList<>();
		System.out.println();
		String sql="select * from "+table+" where bm='"+bm+"' or name='"+name+"'";
		list=hibernateTemplate.getEntitiesBySql(c, sql, null);
		if(list.size()==0)
			b=true;
		return b;
	}
	@Override
	public boolean getRepeatListForUpdate(Class<?> c,String bm, String name, String table,long id)
			throws Throwable {
		boolean b=false;
		List<?> list=new ArrayList<>();
		String sql="select * from "+table+" where (bm='"+bm+"' or name='"+name+"') and id!="+id;
		list=hibernateTemplate.getEntitiesBySql(c, sql, null);
		if(list.size()==0)
			b=true;
		return b;
	}

}
