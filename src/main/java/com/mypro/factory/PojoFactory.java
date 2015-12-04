package com.mypro.factory;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mypro.util.ConnectionManager;
import com.mypro.util.FreemarkerUtils;
import com.mypro.util.PropertiesUtils;

import freemarker.template.TemplateException;

/*
 * 实体对象生成
 */
public class PojoFactory {

	/*
	 * pojo文件生成
	 */
	public static void createPojo() {
		// 获取配置信息
		System.out.println("@@@@@@@@@@@@@@@@@@@@开始createPojo@@@@@@@@@@@@@@@@@@");
		String clz = PropertiesUtils.getValue("base", "tableName");
		String pojo = PropertiesUtils.getValue("base", "pojo-package",new Object[]{clz.toLowerCase()});
		Map<String, Object> map = dataMapPojo(clz, pojo);// 组装map信息
		try {
			StringWriter out = FreemarkerUtils.freeMarker(map, "/pojo.ftl");// 构建freemarker
			String javaName = clz;
			FreemarkerUtils.writeStreamToFile(javaName, pojo, out);// 将freemark返回的文件流写入文件
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@完成createPojo@@@@@@@@@@@@@@@@@@");
	}

	/*
	 * 读取数据库表信息，组装map对象
	 */
	private static Map<String, Object> dataMapPojo(String clz, String pojo) {
		// 构建对象
		List<Object> list = new ArrayList<Object>();
		List<String> columns = ConnectionManager.getMetaDataDbmd(clz);
		try {
			for (String column : columns) {
				String[] attr = column.split(",");
				String type = PropertiesUtils.getValue("dbType", attr[1]);
				list.add(new Attribute(attr[0], type, attr[2], URLDecoder.decode(attr[3], "UTF-8")));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 模板赋值
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pojopackage", pojo);
		map.put("className", clz);
		map.put("attrs", list);
		return map;
	}
}
