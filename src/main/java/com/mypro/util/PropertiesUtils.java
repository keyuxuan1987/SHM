package com.mypro.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

	private static Properties propBase = null;

	private static Properties propDbType = null;

	private static Properties propJdbc = null;

	public static String getValue(String config, String key,Object... objs) {
		Properties prop = getProps(config);
		String ret = prop.getProperty(key);
		for (int i = 0; i < objs.length; i++) {
			ret = ret.replace("{"+ i +"}", objs[i].toString());
		}
		return ret;
	}

	protected static Properties getProps(String type) {
		try {
			if (type == "base") {
				String config = "base.properties";
				String path = PropertiesUtils.class.getClassLoader().getResource(config).getPath();
				if (propBase == null) {
					propBase = new Properties();
					FileInputStream fis = new FileInputStream(path);
					propBase.load(fis);
				}
				return propBase;
			}
			if (type == "dbType") {
				String config = "dbType.properties";
				String path = PropertiesUtils.class.getClassLoader().getResource(config).getPath();
				if (propDbType == null) {
					propDbType = new Properties();
					FileInputStream fis = new FileInputStream(path);
					propDbType.load(fis);
				}
				return propDbType;
			}

			if (type == "jdbc") {
				String config = "jdbc.properties";
				String path = PropertiesUtils.class.getClassLoader().getResource(config).getPath();
				if (propJdbc == null) {
					propJdbc = new Properties();
					FileInputStream fis = new FileInputStream(path);
					propJdbc.load(fis);
				}
				return propJdbc;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
