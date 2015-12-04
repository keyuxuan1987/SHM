package com.mypro.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * 操作数据库
 */
public class ConnectionManager {

	/*
	 * 获取数据库连接
	 */
	public static Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection(PropertiesUtils.getValue("jdbc", "url.url"),
					PropertiesUtils.getValue("jdbc", "url.username"), PropertiesUtils.getValue("jdbc", "url.password"));
			Class.forName(PropertiesUtils.getValue("jdbc", "url.driverClassName"));
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 使用ResultSetMetaData获取数据库表信息
	 */
	public static List<String> getMetaDataRsmd(String clz) {
		Connection conn = getConnection();
		List<String> list = new ArrayList<String>();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from " + clz;
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				StringBuffer sb = new StringBuffer();
				sb.append(rsmd.getColumnName(i) + ",");
				sb.append(rsmd.getColumnTypeName(i) + ",");
				sb.append(rsmd.getPrecision(i));
				list.add(sb.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * 使用DatabaseMetaData获取数据库表信息
	 */
	public static List<String> getMetaDataDbmd(String clz) {
		Connection conn = getConnection();
		List<String> list = new ArrayList<String>();
		try {
			DatabaseMetaData dbmd = conn.getMetaData();
			ResultSet columnSet = dbmd.getColumns(null, "%", clz, "%");
			while (columnSet.next()) {
				StringBuffer sb = new StringBuffer();
				sb.append(columnSet.getString(4) + ",");
				sb.append(columnSet.getString(6) + ",");
				sb.append(columnSet.getString(7) + ",");
				sb.append(URLEncoder.encode(columnSet.getString(12), "UTF-8"));
				list.add(sb.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {
		List<String> list = getMetaDataRsmd("User");
		for (String string : list) {
			System.out.println(string);
		}
		System.err.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		list = getMetaDataDbmd("User");
		for (String string : list) {
			System.out.println(string);
		}
	}
}
