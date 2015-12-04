package com.mypro.factory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.mypro.util.FreemarkerUtils;
import com.mypro.util.PropertiesUtils;

import freemarker.template.TemplateException;

public class DaoImplFactory {
	/*
	 * daoImpl文件生成
	 */
	public static void createDaoImpl() {
		// 获取配置信息
		System.out.println("@@@@@@@@@@@@@@@@@@@@开始createDaoImpl@@@@@@@@@@@@@@@@@@");
		String clz = PropertiesUtils.getValue("base", "tableName");
		String basepackage = PropertiesUtils.getValue("base", "base-package", new Object[] { clz.toLowerCase() });
		String pojo = PropertiesUtils.getValue("base", "pojo-package", new Object[] { clz.toLowerCase() });
		String daobase = PropertiesUtils.getValue("base", "dao-package", new Object[] { clz.toLowerCase() });
		String daoImplbase = PropertiesUtils.getValue("base", "daoimpl-package", new Object[] { clz.toLowerCase() });

		Map<String, Object> map = dataMapDaoImpl(clz, basepackage, pojo, daobase, daoImplbase);// 组装map信息
		try {
			StringWriter out = FreemarkerUtils.freeMarker(map, "/daoImpl.ftl");// 构建freemarker
			String javaName = clz + "DaoImpl";
			FreemarkerUtils.writeStreamToFile(javaName, daoImplbase, out);// 将freemark返回的文件流写入文件
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@完成createDaoImpl@@@@@@@@@@@@@@@@@@");
	}

	/*
	 * 读取数据库表信息，组装map对象
	 */
	private static Map<String, Object> dataMapDaoImpl(String clz, String basepackage, String pojo, String daobase,
			String daoImplbase) {
		// 模板赋值
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("basepackage", basepackage + ".dao.impl");
		map.put("pojopackage", pojo);
		map.put("daopackage", daobase);
		map.put("daoImplpackage", daoImplbase);
		map.put("className", clz);
		return map;
	}
}
