package com.tarena.controller;

import java.sql.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;

import com.tarena.util.DateEditor;

public class BaseController {
	//new DateEditor()为处理Date格式的对象
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(
				Date.class, new DateEditor());
	}

}
