package com.tarena;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Controller1 {
	@Resource
	private JdbcTemplate jdbcTWorkerlate;
	@RequestMapping("/jndi.do")
	public String test(HttpServletRequest request) throws SQLException{
		DataSource ds
		=jdbcTWorkerlate.getDataSource();
		request.setAttribute("con", ds.getConnection());
		return "index";
	}
}
