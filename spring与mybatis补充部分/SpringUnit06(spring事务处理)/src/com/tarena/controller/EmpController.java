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
	 * ��ѯԱ��
	 */
	@RequestMapping("/findEmp.do")
	@Transactional(readOnly=true)
	public String find() {
		// ģ���ѯԱ������
		System.out.println("��ѯԱ�����ݣ��������б�ҳ��.");
		// ����һ���쳣�����ڲ����쳣֪ͨ
//		Integer.valueOf("abc");
		return "emp/emp_list.jsp";
	}

	/**
	 * ģ���������Ա��
	 * @throws ClassNotFoundException 
	 */
	@RequestMapping("/addEmps.do")
//	@Transactional(rollbackFor=ClassNotFoundException.class)
//	@Transactional(readOnly=true)
	public String addBatch() throws ClassNotFoundException {
		// �����һ��Ա��
		Emp e1 = new Emp();
		e1.setEname("����");
		e1.setJob("����");
		e1.setSal(1000.0);
		e1.setEmpno(10);
		empDao.save(e1);
		
		// ģ���쳣
//		Integer.valueOf("abc"); //ClassCastException
//		Class.forName("BadClass"); //ClassNotFoundException
		
		// ����ڶ���Ա��
		Emp e2 = new Emp();
		e2.setEname("����");
		e2.setJob("��");
		e2.setSal(1000000000.0);
		e2.setEmpno(10);
		empDao.save(e2);
		
		return "redirect:findEmp.do";
	}

}
