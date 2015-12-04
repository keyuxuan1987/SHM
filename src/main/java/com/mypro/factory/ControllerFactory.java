package com.mypro.factory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.mypro.util.FreemarkerUtils;
import com.mypro.util.PropertiesUtils;

import freemarker.template.TemplateException;

public class ControllerFactory {
	/*
	 * serviceImpl文件生成
	 */
	public static void createController() {
		// 获取配置信息
		System.out.println("@@@@@@@@@@@@@@@@@@@@开始createController@@@@@@@@@@@@@@@@@@");
		String clz = PropertiesUtils.getValue("base", "tableName");
		String basepackage = PropertiesUtils.getValue("base", "base-package", new Object[] { clz.toLowerCase() });
		String pojo = PropertiesUtils.getValue("base", "pojo-package", new Object[] { clz.toLowerCase() });
		String servicebase = PropertiesUtils.getValue("base", "service-package", new Object[] { clz.toLowerCase() });
		String controllerpackage = PropertiesUtils.getValue("base", "controller-package",
				new Object[] { clz.toLowerCase() });

		Map<String, Object> map = dataMapController(clz, basepackage, pojo, servicebase, controllerpackage);// 组装map信息
		try {
			StringWriter out = FreemarkerUtils.freeMarker(map, "/controller.ftl");// 构建freemarker
			String javaName = clz + "Controller";
			FreemarkerUtils.writeStreamToFile(javaName, controllerpackage, out);// 将freemark返回的文件流写入文件
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@完成createController@@@@@@@@@@@@@@@@@@");
	}

	/*
	 * 读取数据库表信息，组装map对象
	 */
	private static Map<String, Object> dataMapController(String clz, String basepackage, String pojo,
			String servicebase, String controllerpackage) {
		// 模板赋值
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("basepackage", basepackage);
		map.put("pojopackage", pojo);
		map.put("servicepackage", servicebase);
		map.put("controllerpackage", controllerpackage);
		map.put("className", clz);
		return map;
	}
}
