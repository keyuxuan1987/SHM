package com.mypro.factory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.mypro.util.FreemarkerUtils;
import com.mypro.util.PropertiesUtils;

import freemarker.template.TemplateException;

public class DaoFactory {
	/*
	 * dao文件生成
	 */
	public static void createDao() {
		// 获取配置信息
		System.out.println("@@@@@@@@@@@@@@@@@@@@开始createDao@@@@@@@@@@@@@@@@@@");
		String clz = PropertiesUtils.getValue("base", "tableName");
		String basepackage = PropertiesUtils.getValue("base", "base-package", new Object[] { clz.toLowerCase() });
		String pojo = PropertiesUtils.getValue("base", "pojo-package", new Object[] { clz.toLowerCase() });
		String daobase = PropertiesUtils.getValue("base", "dao-package", new Object[] { clz.toLowerCase() });
		Map<String, Object> map = dataMapDao(clz, basepackage, pojo, daobase);// 组装map信息
		try {
			StringWriter out = FreemarkerUtils.freeMarker(map, "/dao.ftl");// 构建freemarker
			String javaName = "I" + clz + "Dao";
			FreemarkerUtils.writeStreamToFile(javaName, daobase, out);// 将freemark返回的文件流写入文件
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@完成createDao@@@@@@@@@@@@@@@@@@");
	}

	/*
	 * 读取数据库表信息，组装map对象
	 */
	private static Map<String, Object> dataMapDao(String clz, String basepackage, String pojo, String daobase) {
		// 模板赋值
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("basepackage", basepackage+".dao");
		map.put("pojopackage", pojo);
		map.put("daopackage", daobase);
		map.put("className", clz);
		return map;
	}
}
