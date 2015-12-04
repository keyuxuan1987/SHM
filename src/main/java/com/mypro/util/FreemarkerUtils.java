package com.mypro.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@SuppressWarnings("all")
public class FreemarkerUtils {
	
	/*
	 * 将文件流写入指定文件
	 */
	public static void writeStreamToFile(String clz, String base, StringWriter out) throws IOException {
		String filePath = FreemarkerUtils.class.getResource("/").getPath() + base.replace(".", "/");
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 写入文件
		FileWriter fw = new FileWriter(new File(filePath + "/" + clz + ".java"));
		fw.write(out.toString());
		fw.close();
	}

	/*
	 * freemarker填充数据
	 */
	public static StringWriter freeMarker(Map<String, Object> map, String flt) throws IOException,
			TemplateNotFoundException, MalformedTemplateNameException, ParseException, TemplateException {
		Configuration cfg = new Configuration();
		String path = FreemarkerUtils.class.getResource("/").getPath();
		cfg.setDirectoryForTemplateLoading(new File(path + "template"));
		Template template = cfg.getTemplate(flt);
		StringWriter out = new StringWriter();
		template.process(map, out);
		return out;
	}
}
