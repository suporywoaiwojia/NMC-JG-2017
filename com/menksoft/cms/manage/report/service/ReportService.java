package com.menksoft.cms.manage.report.service;

import java.util.List;


public interface ReportService {
	public List<Object[]> getProReport(String tablename) throws Throwable;
	
	public List<Object[]>getListreportBysql(String sql) throws Throwable;
	
	public int getIntreportBysql(String sql) throws Throwable;
}
