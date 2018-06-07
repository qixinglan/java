package com.tarena.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tarena.dao.Account;
import com.tarena.dao.AccountMapper;
import com.tarena.dao.ServiceMapper;
import com.tarena.dao.accountPage;
@SessionAttributes("accountPage")
@Controller
@RequestMapping("/account")
public class AccountController extends BaseController{
	@Value("#{db.pageSize}")
	int pageSize;
	@Resource
	private AccountMapper accountMapper;
	@Resource
	private ServiceMapper serviceMapper;
	@RequestMapping("/findAccount.do")
	public String findAccount(accountPage page,Model model){
		int rows=accountMapper.findTotalRows(page);
		page.setRows(rows);
		page.setPageSize(pageSize);
		List<Account> list=accountMapper.findByPage(page);
		model.addAttribute("account", list);
		model.addAttribute("accountPage", page);
		return "/account/findAccount";
	}
	@RequestMapping("/updateToPause.do")
	public String updateToPause(int account_id){
		accountMapper.updateToPause(account_id);
		serviceMapper.updateToPauseByAccount_id(account_id);
		return "redirect:findAccount.do";
	}
	@RequestMapping("/updateToOpen.do")
	public String updateToOpen(int account_id){
		accountMapper.updateToOpen(account_id);
		return "redirect:findAccount.do";
	}
	@RequestMapping("/deleteAccount.do")
	public String deleteAccount(int account_id){
		accountMapper.deleteAccount(account_id);
		serviceMapper.updateToDeleteByAccount_id(account_id);
		return "redirect:findAccount.do";
	}
	@RequestMapping("/addAccount.do")
	public String addAccount(){
	
		return "/account/addAccount";
	}
	@RequestMapping("/toAddAccount.do")
	public String toAddAccount(Account account){
		account.setStatus("0");
		Timestamp date=new Timestamp(System.currentTimeMillis());
		account.setCreate_date(date);
		accountMapper.toAddAccount(account);
		return "redirect:findAccount.do";
	}
	@ResponseBody
	@RequestMapping("checkRecommmend.do")
	public Integer checkRecomend(String recommendNo){
		System.out.println(recommendNo);
		Account account=accountMapper.findByIdcard_no(recommendNo);
		System.out.println(1);
		return account.getAccount_id();
	}
	@RequestMapping("updateAccount.do")
	public String updateAccount(int account_id,Model model){
		Account account=accountMapper.findById(account_id);
		model.addAttribute("account",account);
		return "/account/updateAccount";
	}
	@RequestMapping("fixAccount.do")
	public String updateAccount(Account account){
		accountMapper.fixAccount(account);
		return "redirect:findAccount.do";
	}
	
}
