package com.tarena.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tarena.dao.EmpDao;
import com.tarena.entity.Emp;

@Controller
@RequestMapping("/emp")
public class EmpController {

	@Resource
	private EmpDao empDao;

	@RequestMapping("/findEmp.do")
	public String find(Model model) {
		List<Emp> list = empDao.findAll();
		model.addAttribute("emps", list);
		return "emp/emp_list";
	}

}
