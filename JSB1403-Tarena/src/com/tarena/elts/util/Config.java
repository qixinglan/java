package com.tarena.elts.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
	private Properties table=new Properties();
	public Config(String fileName){
		try {
			table.load(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("配置文件没有找到");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getInt(String key){
		return Integer.parseInt(table.getProperty(key));
	}
	public double getDouble(String key){
		return Double.parseDouble(table.getProperty(key));
	}
	public String getString(String key){
		return table.getProperty(key);
	}
}