package com.tarena.test;

import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.dao.Account;
import com.tarena.dao.AccountMapper;
import com.tarena.dao.Cost;
import com.tarena.dao.CostMapper;
import com.tarena.dao.accountPage;
import com.tarena.dao.costPage;




public class TestCase {
	@Test
	public void test0(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		CostMapper costMapper=ac.getBean("costMapper",CostMapper.class);
		Cost cost=new Cost();
		cost.setName("aa");
		costMapper.addCost(cost);
	}
	@Test
	public void test1(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		Properties p=ac.getBean("db",Properties.class);
		System.out.println(p.getProperty("pageSize"));
		costPage page=ac.getBean("costPage",costPage.class);
		System.out.println(page.getPageSize());
	}
	@Test
	public void test2(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		AccountMapper am=ac.getBean("accountMapper",AccountMapper.class);
		accountPage accountPage=new accountPage();
		int rows=am.findTotalRows(accountPage);
		accountPage ap=new accountPage();
		ap.setRows(rows);
		ap.setCurrentPage(2);
		ap.setPageSize(5);
		List<Account> list=am.findByPage(ap);
		for(Account l:list){
			System.out.println(l.getEmail());
		}
	}
	
}
