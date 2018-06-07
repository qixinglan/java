package com.tarena.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tarena.dao.EmpDao;
import com.tarena.entity.Emp;

@Controller
@RequestMapping("/emp")
@Transactional
public class EmpController {
	
	@Resource
	private EmpDao empDao;

	/**
	 * 查询员工
	 */
	@RequestMapping("/findEmp.do")
	@Transactional(readOnly=true)
	public String find() {
		// 模拟查询员工数据
		System.out.println("查询员工数据，发送至列表页面.");
		// 制造一个异常，便于测试异常通知
//		Integer.valueOf("abc");
		return "emp/emp_list.jsp";
	}

	/**
	 * 模拟批量添加员工
	 * @throws ClassNotFoundException 
	 */
	@RequestMapping("/addEmps.do")
//	@Transactional(rollbackFor=ClassNotFoundException.class)
//	@Transactional(readOnly=true)
	public String addBatch() throws ClassNotFoundException {
		// 插入第一个员工
		Emp e1 = new Emp();
		e1.setEname("刘备");
		e1.setJob("皇叔");
		e1.setSal(1000.0);
		e1.setEmpno(10);
		empDao.save(e1);
		
		// 模拟异常
//		Integer.valueOf("abc"); //ClassCastException
//		Class.forName("BadClass"); //ClassNotFoundException
		
		// 插入第二个员工
		Emp e2 = new Emp();
		e2.setEname("关羽");
		e2.setJob("候");
		e2.setSal(1000000000.0);
		e2.setEmpno(10);
		empDao.save(e2);
		
		return "redirect:findEmp.do";
	}

}
