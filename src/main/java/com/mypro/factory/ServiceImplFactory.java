package com.mypro.factory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.mypro.util.FreemarkerUtils;
import com.mypro.util.PropertiesUtils;

import freemarker.template.TemplateException;

public class ServiceImplFactory {
	/*
	 * serviceImpl文件生成
	 */
	public static void createserviceImpl() {
		// 获取配置信息
		System.out.println("@@@@@@@@@@@@@@@@@@@@开始createserviceImpl@@@@@@@@@@@@@@@@@@");
		String clz = PropertiesUtils.getValue("base", "tableName");
		String pojo = PropertiesUtils.getValue("base", "pojo-package", new Object[] { clz.toLowerCase() });
		String servicebase = PropertiesUtils.getValue("base", "service-package", new Object[] { clz.toLowerCase() });
		String daobase = PropertiesUtils.getValue("base", "dao-package", new Object[] { clz.toLowerCase() });
		String serviceImplbase = PropertiesUtils.getValue("base", "serviceimpl-package",
				new Object[] { clz.toLowerCase() });

		Map<String, Object> map = dataMapserviceImpl(clz, pojo, daobase, servicebase, serviceImplbase);// 组装map信息
		try {
			StringWriter out = FreemarkerUtils.freeMarker(map, "/serviceImpl.ftl");// 构建freemarker
			String javaName = clz + "ServiceImpl";
			FreemarkerUtils.writeStreamToFile(javaName, serviceImplbase, out);// 将freemark返回的文件流写入文件
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@完成createserviceImpl@@@@@@@@@@@@@@@@@@");
	}

	/*
	 * 读取数据库表信息，组装map对象
	 */
	private static Map<String, Object> dataMapserviceImpl(String clz, String pojo, String daobase, String servicebase,
			String serviceImplbase) {
		// 模板赋值
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pojopackage", pojo);
		map.put("daopackage", daobase);
		map.put("servicepackage", servicebase);
		map.put("serviceImplpackage", serviceImplbase);
		map.put("className", clz);
		return map;
	}
}
