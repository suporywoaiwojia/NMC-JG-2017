package com.menksoft.cms.manage.report.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.menksoft.cms.application.dao.hibernate.HibernateTemplate;
import com.menksoft.cms.manage.report.service.ReportService;
@Service("ReportService")
public class ReprotServiceImpl implements ReportService {
	@Resource
	private HibernateTemplate hibernateTemplate;
	@Override
	public List<Object[]> getProReport( String tablename) throws Throwable {
		String sql="select b.name,count(a.id) as num from r_data_level b left join "+tablename+" a on b.id=a.level_id group by b.id";
		return hibernateTemplate.getBySql(sql);
	}
	@Override
	public List<Object[]> getListreportBysql(String sql) throws Throwable {
		return hibernateTemplate.getBySql(sql);
	}
	@Override
	public int getIntreportBysql(String sql) throws Throwable {
		return hibernateTemplate.getCountBySql(sql);
	}
}
