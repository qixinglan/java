package com.tarena.util;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateEditor extends PropertyEditorSupport {
	//自定义的处理格式
	private String pattern = "yyyy-MM-dd";
	//默认的处理方法格式是"yyyy-MM-dd";
	public DateEditor() {
		
	}
	//非默认的处理
	public DateEditor(String pattern) {
		this.pattern = pattern;
	}
	//text即表单传入的日期，该方法会把date格式的提交的数据 给spring的接受的对象。
	//用BaseController实现
	
	@Override
	public void setAsText(String text) 
			throws IllegalArgumentException {
		if(text == null || text.length() == 0) {
			setValue(null);
		} else {
			SimpleDateFormat sf = new SimpleDateFormat(this.pattern);
			String dateStr = sf.format(Date.valueOf(text));
			setValue(Date.valueOf(dateStr));
		}
	}
	

}