package com.tarena.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tarena.dao.Account;
import com.tarena.dao.AccountMapper;
import com.tarena.dao.Cost;
import com.tarena.dao.CostMapper;
import com.tarena.dao.Service;
import com.tarena.dao.ServiceMapper;
import com.tarena.dao.ServicePage;
@SessionAttributes("servicePage")
@Controller
@RequestMapping("service")
public class ServiceController extends BaseController{
	@Value("#{db.pageSize}")
	int pageSize;
	@Resource
	CostMapper costMapper;
	@Resource
	ServiceMapper serviceMapper;
	@Resource
	AccountMapper accountMapper;
	@RequestMapping("findService.do")
	public String findService(ServicePage page,Model model){
		page.setPageSize(pageSize);
		int rows=serviceMapper.findTotalRows(page);
		page.setRows(rows);
		List<Map<String,Object>>list=serviceMapper.findService(page);
		model.addAttribute("list",list);
		model.addAttribute("servicePage",page);
		return "/service/findService";
	}
	@ResponseBody
	@RequestMapping("updateToOpen.do")
	public Map<String,Object> updateToOpen(int service_id){
		Service service=serviceMapper.findById(service_id);
		int account_id=service.getAccount_id();
		Account account=accountMapper.findById(account_id);
		Map<String,Object> map=new HashMap<String,Object>();
		if(account.getStatus().equals("0")){
			serviceMapper.updateToOpen(service_id);
			map.put("boolean", true);
			map.put("message","开通成功");
			return map;
		}
		else{
			map.put("boolean", false);
			map.put("message","账务账号未开通，开通失败");
			return map;
		}
		
	}
	@ResponseBody
	@RequestMapping("updateToPause.do")
	public Map<String,Object> updateToPause(int service_id){
		serviceMapper.updateToPause(service_id);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("boolean", true);
		map.put("message","暂停成功");
		return map;
	}
	@ResponseBody
	@RequestMapping("updateToDelete.do")
	public Map<String,Object> updateToDelete(int service_id){
		serviceMapper.updateToDelete(service_id);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("boolean", true);
		map.put("message","删除成功");
		return map;
	}
	@RequestMapping("addService.do")
	public String addService(Model model){
		List<Cost> list=costMapper.findAllCost();
		model.addAttribute("cost", list);
		return "/service/addService";
	}
	@ResponseBody
	@RequestMapping("checkIdcard_no.do")
	public Map<String,Object> scheckIdcard_no(String idcard_no){
		Account account=null;
		account=accountMapper.findByIdcard_no(idcard_no);
		Map<String,Object> map=new HashMap<String,Object>();
		if(account!=null){
			map.put("real_name",account.getReal_name());
			map.put("account_id",account.getAccount_id());
			return map;
		}
		return null;
	}
	@RequestMapping("insertService.do")
	public String insertService(Service service){
		serviceMapper.insertService(service);
		return"redirect:findService.do";
	}
	@RequestMapping("updateService.do")
	public String updateService(int service_id,Model model){
		Service service=serviceMapper.findById(service_id);
		List<Cost> list=costMapper.findAllCost();
		model.addAttribute("service",service);
		model.addAttribute("cost",list);
		System.out.println(list);
		return"/service/updateService";
	}
	@RequestMapping("fixService.do")
	public String fixService(Service service){
		serviceMapper.updateService(service);
		return"redirect:findService.do";
	}
	
}
