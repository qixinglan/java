package com.tarena.controller;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tarena.dao.Cost;
import com.tarena.dao.CostMapper;
import com.tarena.dao.costPage;
@Controller
@RequestMapping("/cost")
@SessionAttributes("costPage")
public class CostController extends BaseController{
	@Value("#{db.pageSize}")
	int pageSize;
	@Resource
	CostMapper costMapper;
//	@RequestMapping("/findAllCost.do")
//	public String findAllCost(Model model){
//		List<Cost> list=costMapper.findAllCost();
//		model.addAttribute("cost",list);
//		return "/cost/findCost" ;
//	}
	@RequestMapping("/findAllCost.do")
	public String findAllCost1(Model model,costPage page){
		page.setPageSize(pageSize);
		List<Cost> list=costMapper.findByPage(page);
		int rows=costMapper.findTotalRows();
		page.setRows(rows);
		model.addAttribute("cost",list);
		model.addAttribute("costPage",page);
		return "/cost/findCost" ;
	}
	@RequestMapping("/addCost.do")
	public String addCost(){
		return "/cost/addCost" ;
	}
	@RequestMapping("/insertCost.do")
	public String insertCost(Cost cost){
		Date d=new Date(System.currentTimeMillis());
		cost.setCreateTime(d);
		cost.setStatus('1');
		costMapper.addCost(cost);
		return "redirect:findAllCost.do";
	}
	@RequestMapping("updateCost.do")
	public String updateCost(int cost_id,Model model){	
		Cost cost=costMapper.findCostById(cost_id);
		model.addAttribute("cost", cost);
		return "/cost/updateCost";
	}
	@RequestMapping("fixCost.do")
	public String updateCost(Cost cost){	
		costMapper.updateCost(cost);
		return "redirect:findAllCost.do";
	}
	@RequestMapping("deleteCost.do")
	public String deleteCost(int cost_id){	
		costMapper.deleteCost(cost_id);
		return "redirect:findAllCost.do";
	}
	
	
}
