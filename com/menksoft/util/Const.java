package com.menksoft.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author wangh 系统常量类
 */
public class Const {
	/**
	 * MESSAGE
	 */
	public static final String MESSAGE_NAME = "message";
	/**
	 * 保存成功。
	 */
	public static final String MESSAGE_SUCCESS_SAVE = "保存成功。";
	/**
	 * 删除成功。
	 */
	public static final String MESSAGE_SUCCESS_DELETE = "删除成功。";
	/**
	 * 已存在，保存失败。
	 */
	public static final String MESSAGE_FAIL_EXISTED = "已存在，保存失败。";
	/**
	 * 失败原因：
	 */
	public static final String MESSAGE_FAIL_PRIFX = "失败原因：";

	/**
	 * 操作失败， 请检查输入的内容是否符合系统的要求。
	 */
	public static final String MESSAGE_FAIL_ILLEGAL_ARGUMENT = "操作失败， 请检查输入的内容是否符合系统的要求。";
	/**
	 * 可能已不存在，保存失败。
	 */
	public static final String MESSAGE_FAIL_SAVE_NOT_EXISTED = "可能已不存在，保存失败。";
	/**
	 * 可能已不存在，删除失败。
	 */
	public static final String MESSAGE_FAIL_DELETE_NOT_EXISTED = "可能已不存在，删除失败。";
	/**
	 * 可能已不存在，查询失败。
	 */
	public static final String MESSAGE_FAIL_QUERY_NOT_EXISTED = "可能已不存在，查询失败。";

	/**
	 * 保存失败。
	 */
	public static final String MESSAGE_FAIL_UNCHECKED_SAVE = "保存失败。";
	/**
	 * 删除失败。
	 */
	public static final String MESSAGE_FAIL_UNCHECKED_DELETE = "删除失败。";
	/**
	 * 查询失败。
	 */
	public static final String MESSAGE_FAIL_UNCHECKED_QUERY = "查询失败。";
	/**
	 * 请输入
	 */
	public static final String MESSAGE_FAIL_SAVE_ISNULL = "请输入";

	/**
	 * 输入内容不符合规范，输入内容中不能包含`~!@#$^&*=|{}':;',[].<>《》/?~%！@#￥……&*——|{}【】‘；：”“'。，、？
	 */
	public static final String MESSAGE_FAIL_VALIDATE_NAMESPECIALCHARACTERS = "项输入内容不符合规范，输入内容中不能包含`~!@#$^&*=|{}':;',[].<>《》/?~%！@#￥……&*——|{}【】‘；：”“'。，、？";
	/**
	 * 项输入字符为非正整数，请重新输入。
	 */
	public static final String MESSAGE_FAIL_VALIDATE_POSITIVE_INTEGER = "项输入字符为非正整数，请重新输入。";
	/**
	 * 项输入字符为非数字，请重新输入。
	 */
	public static final String MESSAGE_FAIL_VALADATE_NUMBER = "项输入字符为非数字，请重新输入。";

	/**
	 * 邮箱校验 2012-11-14
	 */
	public static final String MESSAGE_FAIL_VALADATE_MAIL = "邮箱输入内容不符合规范，输入内容中只能包含英文字母、数字、@、.、_。";
	/**
	 * 请选择要删除的对象。
	 */
	public static final String MESSAGE_FAIL_VALIDATE_DELETE_UNCHECKED = "请选择要删除的对象。";

	public static final String MESSAGE_FAIL_FILE_NOT_FOUND = "未检测到上传文件。";

	private static final String FORMAT_DATE_TIME_MS = "yyyyMMddHHmmssSSS";
	private static final String FORMAT_DATE_TIME_SS_MS = "yyyy-MM-dd HH:mm:ss.SSS";
	private static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm";
	private static final String FORMAT_DATE_DAY = "yyyy-MM-dd";
	private static final String FORMAT_DATE_MONTH = "yyyy-MM";
	private static final String FORMAT_DATE_YEAR = "yyyy";

	public static final DateFormat FORMAT_TIMESTAMP = new SimpleDateFormat(
			FORMAT_DATE_TIME_MS);
	public static final DateFormat FORMAT_DATATIME_MS = new SimpleDateFormat(
			FORMAT_DATE_TIME_SS_MS);
	public static final DateFormat FORMAT_DATETIME = new SimpleDateFormat(
			FORMAT_DATE_TIME);
	public static final DateFormat FORMAT_DATEDAY = new SimpleDateFormat(
			FORMAT_DATE_DAY);
	public static final DateFormat FORMAT_DATEMONTH = new SimpleDateFormat(
			FORMAT_DATE_MONTH);
	public static final DateFormat FORMAT_DATEYEAR = new SimpleDateFormat(
			FORMAT_DATE_YEAR);

	public static String BASEPATH;
	//项目名称 用于在REDIS区别中不同的项目
	public static final String PRO = "news_";
	public static final String DAYTOP =PRO+"Dtop_";
	public static final String WEEKTOP = PRO+ "Wtop_";
	public static final String MONTHTOP = PRO+ "Mtop_";
	public static final String TOTALTOP = PRO+ "Ttop_";
	//注册验证码
	public static final String REGCODE ="Register_";
	
	
	//收藏记录 观看记录
	public static final String VIEWRECORD=PRO+ "ViewRecords:member:";
	public static final String FAVORITERECORD=PRO+ "FavoriteRecords:member:";
	//排行榜获取数量
	public static final int TOPNUM = 20;
	public static String APP_ROOT = System.getProperty("yys.root");
}
