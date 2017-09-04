package com.menksoft.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UtilCms {
	
	/**
	 * 生成订单号
	 * @param payId 缴费TYPEID
	 * @param MemberId
	 * @return
	 */
	public static String orderNum(String payId,String MemberId){
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
		String str = df.format(new Date());  
		String orderNum="AD"+str+"P"+payId+"M"+MemberId;
		return orderNum;
	}
	
	
}
