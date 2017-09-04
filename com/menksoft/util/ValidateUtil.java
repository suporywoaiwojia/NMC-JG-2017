package com.menksoft.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ValidateUtil {
	/**
	 * 匹配只能输入数字、英文字母、下划线的字符串
	 * @param str 匹配的字符串
	 * @return 只包含数字、字母、下划线返回true 否则返回false
	 */
	public static boolean validateContent(String str) {
		Pattern p=Pattern.compile("^[A-Za-z0-9_]+$");
		Matcher m=p.matcher(str);
		return m.find();
	}
	/**
	 * 验证字符串中是否包含特殊字符`~!@$^&*=|{}':;',[]<>《》/~！@#￥……&*——|{}【】‘；：”“'。，、？
	 * @param str 匹配的字符串
	 * @return包含返回true 不包含返回false
	 */
	public static boolean isLink(String str) {
		Pattern p=Pattern.compile("[`~!@$^&*|{}':;',\\[\\]<>《》~！@￥……&*——|{}【】‘；：”“'。，、？]");
		Matcher m=p.matcher(str);
		return m.find();
	}
	/**
	 * 验证字符串中是否包含特殊字符`~!@#$^&*=|{}':;',[].<>《》/?~%！@#￥……&*——|{}【】‘；：”“'。，、？
	 * @param str 匹配的字符串
	 * @return包含返回true 不包含返回false
	 */
	public static boolean nameSpecialCharacters(String str) {
		if(str == null || str.trim().equals("")) {
			return false;
		}
		Pattern p=Pattern.compile("[`~!@#$^&*=|{}':;',\\[\\].<>《》/+?~%！@#￥……&*——|{}【】‘；：”“'。，、？]+");
		Matcher m=p.matcher(str);
		return m.find();
	}
	/**
	 * 验证字符串中是否包含特殊字符`~!@#$^&*=|{}':',[].<>《》/?~%！@#￥……&*——|{}【】‘：”“'。，、？
	 * @param str 匹配的字符串
	 * @return包含返回true 不包含返回false
	 */
	public static boolean nameNewSpecialCharacters(String str) {
		Pattern p=Pattern.compile("[`~!@#$^&*=|{}':',\\[\\].<>《》/+?~%！@#￥……&*——|{}【】‘：”“'。，、？]+");
		Matcher m=p.matcher(str);
		return m.find();
	}
	/**
	 * 验证字符串中是否包含特殊字符`~#$^={}[]/
	 * @param str 匹配的字符串
	 * @return包含返回true 不包含返回false
	 */
	public static boolean textSpecialCharacters(String str){
		Pattern p=Pattern.compile("[`~#$^={}\\[\\]/]+");
		Matcher m=p.matcher(str);
		return m.find();
	}
	/**
	 * 空验证（空字符串）
	 * @param str 匹配的字符串
	 * @return 是空字符串返回true 否则返回false
	 */
	public static boolean isNull(String str) {
		if ( "".equals(str)  || str==null || "null".equals(str)){ 
			return true;
		}else{
			Pattern p=Pattern.compile("^[ ]+$");
			Matcher m=p.matcher(str);
			return m.find();
		}
	}
	/**
	 * 验证字符串中是否包含空格（包括首尾中间）
	 * @param str：验证字符串
	 * @return 包含空格返回true 不包含返回false
	 */
	public static boolean isMiddleNull(String str){
		String[] s = str.split("");
		for (int i = 0; i < s.length; i++) {
			if(" ".equals(s[i])){
				return true;
			}
		}
		return false;
	}
	/**
	 * 验证身份证号码是否符合要求
	 * @param str：需验证的省份证号码
	 * @return 符合要求返回true 不符合要求返回false
	 */
	public static boolean isIdCard(String str){
		Pattern p=Pattern.compile("^[0-9x]+$");
		Matcher m=p.matcher(str);
		return m.find();
	}
	/**
	 * 验证是否为数字
	 * @param str：需验证的字符串
	 * @return 是数字返回true 不是返回false
	 */
	public static boolean isInteger(String str){
		Pattern p=Pattern.compile("^[0-9]+$");
		Matcher m=p.matcher(str);
		return m.find();
	}
	/**
	 * 验证是否为正确的邮箱
	 * @param str：需验证的邮箱
	 * @return正确的邮箱返回true 错误的邮箱返回false
	 */
	public static boolean isEmail(String str){
		Pattern p=Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$");
		Matcher m=p.matcher(str);
		return m.find();
	}

}
