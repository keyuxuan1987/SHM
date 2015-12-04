package com.mypro.factory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.mypro.util.FreemarkerUtils;
import com.mypro.util.PropertiesUtils;

import freemarker.template.TemplateException;

public class ServiceFactory {
	/*
	 * serice文件生成
	 */
	public static void createService() {
		// 获取配置信息
		System.out.println("@@@@@@@@@@@@@@@@@@@@开始createService@@@@@@@@@@@@@@@@@@");
		String clz = PropertiesUtils.getValue("base", "tableName");
		String pojo = PropertiesUtils.getValue("base", "pojo-package",new Object[]{clz.toLowerCase()});
		String servicebase = PropertiesUtils.getValue("base", "service-package",new Object[]{clz.toLowerCase()});
		Map<String, Object> map = dataMapService(clz, pojo,servicebase);// 组装map信息
		try {
			StringWriter out = FreemarkerUtils.freeMarker(map, "/service.ftl");// 构建freemarker
			String javaName = "I" + clz + "Service";
			FreemarkerUtils.writeStreamToFile(javaName, servicebase, out);// 将freemark返回的文件流写入文件
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@完成createService@@@@@@@@@@@@@@@@@@");
	}
	/*
	 * 读取数据库表信息，组装map对象
	 */
	private static Map<String, Object> dataMapService(String clz, String pojo,String servicebase) {
		// 模板赋值
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pojopackage", pojo);
		map.put("servicepackage", servicebase);
		map.put("className", clz);
		return map;
	}
}
