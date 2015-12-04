package com.mypro.common;

import java.util.HashMap;
import java.util.Map;

public class ShortMsg {

	private static Map<String, String> retMap = new HashMap<String, String>();

	public ShortMsg() {}

	public static final String SUCCESS = "SUCCESS";
	public static final String ERROR = "ERROR";
	public static final String EXISTS = "EXISTS";

	public static final String _5999 = "_5999";
	public static final String _5998 = "_5998";
	public static final String _5997 = "_5997";
	public static final String _5996 = "_5996";
	public static final String _5995 = "_5995";
	public static final String _5994 = "_5994";
	public static final String _5993 = "_5993";
	public static final String _5992 = "_5992";
	public static final String _5991 = "_5991";
	public static final String _5990 = "_5990";
	public static final String _5989 = "_5989";
	public static final String _5988 = "_5988";
	public static final String _5987 = "_5987";
	public static final String _5986 = "_5986";
	public static final String _5985 = "_5985";
	public static final String _5984 = "_5984";
	public static final String _5983 = "_5983";
	public static final String _5982 = "_5982";
	public static final String _5981 = "_5981";

	/**
	 * Function: getValue; Author : ASUS, Version : 1.0, First complete date :
	 * 2015年12月1日 下午3:15:20; Description : Param and Desciption : Return:
	 * History: 1. Date:2015年12月1日 下午3:15:20 Author:kelvin Version:
	 * Modification:
	 *
	 */
	public static String getValue(String key) {
		if(retMap.isEmpty()){
			load();
		}
		return retMap.get(key);
	}
	
	private static void load(){
		retMap.put("SUCCESS", "处理成功");
		retMap.put("ERROR", "处理失败");
		retMap.put("EXISTS", "数据存在");
		retMap.put("-5999", "参数错误");
		retMap.put("-5998", "签名格式错误");
		retMap.put("-5997", "后端网络错误");
		retMap.put("-5996", "HTTP请求方法错误");
		retMap.put("-5995", "文件大小错误");
		retMap.put("-5994", "url参数解析不匹配");
		retMap.put("-5993", "multipart/formdata参数错误");
		retMap.put("-5992", "请求参数错误");
		retMap.put("-5991", "分片过大");
		retMap.put("-5990", "找不到filecontent");
		retMap.put("-5989", "上传失败");
		retMap.put("-5988", "cgi初始化错误");
		retMap.put("-5987", "wup编码失败");
		retMap.put("-5986", "wup解码失败");
		retMap.put("-5985", "获取路由失败");
		retMap.put("-5984", "sha1不匹配");
		retMap.put("-5983", "错误的session");
		retMap.put("-5982", "建立连接错误");
		retMap.put("-5981", "建立连接错误");
	}

}
