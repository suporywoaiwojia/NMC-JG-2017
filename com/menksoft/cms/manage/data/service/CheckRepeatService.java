package com.menksoft.cms.manage.data.service;


public interface CheckRepeatService {
	public boolean getRepeatListForSave(Class<?> c,String bm,String name,String table)throws Throwable;
	public boolean getRepeatListForUpdate(Class<?> c,String bm,String name,String table,long id)throws Throwable;

}
