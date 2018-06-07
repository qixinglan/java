package com.tarena.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tarena.dao.Account;
import com.tarena.dao.AccountMapper;
import com.tarena.dao.accountPage;

@Controller
@RequestMapping("/account")
public class AccountController extends BaseController{
	@Value("#{db.pageSize}")
	private int pageSize;
	@Resource
	private AccountMapper accountMapper;
	@RequestMapping("/findAccount.do")
	public String findAccount(accountPage accountPage,Model m){
		accountPage.setPageSize(pageSize);
		accountPage.setRows(accountMapper.findTotalRows(accountPage));
		List<Account> list=accountMapper.findByPage(accountPage);
		m.addAttribute("list",list);
		m.addAttribute("accountPage", accountPage);
		return "/account/accountList";
	}
	@RequestMapping("/addAccount.do")
	public String addAccount(){
		return "/account/accountAdd";
	}
}
