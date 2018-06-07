package com.tarena.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tarena.entity.Emp;

@Controller
@RequestMapping("/test")
public class JsonController {

	@RequestMapping("/test1.do")
	@ResponseBody
	public boolean test1() {
		return true;
	}
	
	@RequestMapping("/test2.do")
	@ResponseBody
	public Map<String,Object> test2() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", 16);
		map.put("name", "刘备");
		return map;
	}
	
	@RequestMapping("/test3.do")
	@ResponseBody
	public List<String> test3() {
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		return list;
	}
	
	@RequestMapping("/test4.do")
	@ResponseBody
	public Emp test4() {
		Emp e = new Emp();
		e.setEmpno(1);
		e.setEname("刘苍松");
		e.setJob("老师");
		e.setMgr(1);
		e.setSal(10000.0);
		e.setDeptno(30);
		return e;
	}
	
	@RequestMapping("/test5.do")
	@ResponseBody
	public List<Emp> test5() {
		List<Emp> list = new ArrayList<Emp>();
		
		Emp e1 = new Emp();
		e1.setEmpno(1);
		e1.setEname("aaa");
		e1.setJob("aaa");
		e1.setMgr(1);
		e1.setSal(10000.0);
		e1.setDeptno(30);
		list.add(e1);
		
		Emp e2 = new Emp();
		e2.setEmpno(1);
		e2.setEname("bbb");
		e2.setJob("bbb");
		e2.setMgr(1);
		e2.setSal(20000.0);
		e2.setDeptno(30);
		list.add(e2);
		
		Emp e3 = new Emp();
		e3.setEmpno(1);
		e3.setEname("ccc");
		e3.setJob("ccc");
		e3.setMgr(1);
		e3.setSal(30000.0);
		e3.setDeptno(30);
		list.add(e3);
		
		return list;
	}
	
	@RequestMapping(value="/test6.do", produces="application/json; charset=utf-8")
//	@RequestMapping("/test6.do")
	@ResponseBody
	public String test6() {
		return "你好";
	}
	
}
