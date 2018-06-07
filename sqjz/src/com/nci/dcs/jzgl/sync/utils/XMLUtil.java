package com.nci.dcs.jzgl.sync.utils;

import java.io.IOException;

import com.nci.dcs.jzgl.sync.xmlmodels.Result;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncPerson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class XMLUtil {
	public static XStream stream;
	static {
		//NoNameCoder 去掉默认的特殊字符转换
		stream = new XStream(new SyncReflectionProvider(), new XppDriver(new NoNameCoder()));
		stream.alias("Result", Result.class);//解析时注解无效，此处注册标签Result代表的类
		stream.alias("List", SyncPerson.class);
		stream.autodetectAnnotations(true);
	}
	public static XStream getStream(){
		return stream;
	}

	public static String format(Object o){
		return  stream.toXML(o);
	}
	
	public static Object parse(String xml) throws IOException{
		return stream.fromXML(xml);
	}
	
	public static String getHeader(String encoding){
		return "<?xml version=\"1.0\" encoding=\"" + encoding + "\"?>";
	}
}
