package com.tarena.controller;

import java.util.ArrayList;
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

import com.tarena.dao.Admin;
import com.tarena.dao.AdminMapper;
import com.tarena.dao.AdminPage;
import com.tarena.dao.Module;
import com.tarena.dao.Role;
import com.tarena.dao.RoleMapper;
@SessionAttributes("adminPage")
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Resource
	RoleMapper roleMapper;
	@Resource
	AdminMapper adminMapper;
	@Value("#{db.pageSize}")
	int pageSize;
	@RequestMapping("findAdmin.do")
	public String findAdmin(AdminPage adminPage,Model model){
		adminPage.setPageSize(pageSize);
		int rows=adminMapper.findTotalRows(adminPage);
		adminPage.setRows(rows);
		List<Admin> list=adminMapper.findAdmin(adminPage);
		List<Module> modules=roleMapper.findAllModule();
		model.addAttribute("adminPage",adminPage);
		model.addAttribute("admin",list);
		model.addAttribute("modules",modules);
		return "/admin/findAdmin";
	}
	@ResponseBody
	@RequestMapping("resettingPassword.do")
	public Boolean resettingPassword(String str){
		if(str==null||str.equals("")){
			return false;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		String []ids=str.split(",");
		List<Integer> list=new ArrayList<Integer>();
		for(String i:ids){
			list.add(Integer.parseInt(i));
		}
		String defaultPassword="123456";
		map.put("ids", list);
		map.put("defaultPassword", defaultPassword);
		adminMapper.resttingPassword(map);
		return true;
	}
	@RequestMapping("addAdmin.do")
	public String addAdmin(Model model){
		Integer.parseInt("abc");//用于记载日志例子
		List<Role> roles=adminMapper.findTotalRole();
		model.addAttribute("roles", roles);
		return "/admin/addAdmin";
	}
	@RequestMapping("insertAdmin.do")
	public String insertAdmin(Admin admin){
		adminMapper.insertAdmin(admin);
		Map<String,Object> map=new HashMap();
		map.put("admin_id",admin.getAdmin_id());
		for(Integer i:admin.getRoleIds()){
			map.put("role_id", i);
			adminMapper.insertAdminRole(map);
		}
		return "redirect:findAdmin.do";
	}
	@RequestMapping("updateAdmin.do")
	public String updateAdmin(int admin_id,Model model){
		Admin admin=adminMapper.findById(admin_id);
		model.addAttribute("admin",admin);
		List<Role> roles=adminMapper.findTotalRole();
		model.addAttribute("roles", roles);
		return "/admin/updateAdmin";
	}
	@RequestMapping("fixAdmin.do")
	public String fixAdmin(Admin admin){
		adminMapper.fixAdmin(admin);
		adminMapper.deleteAdminRole(admin.getAdmin_id());
		Map<String,Object> map=new HashMap();
		map.put("admin_id", admin.getAdmin_id());
		for(Integer i:admin.getRoleIds()){
			map.put("role_id", i);
			adminMapper.insertAdminRole(map);
		}
		return "redirect:findAdmin.do";
	}
	@RequestMapping("deleteAdmin.do")
	public String deleteAdmin(int admin_id){
		adminMapper.deleteAdminRole(admin_id);
		adminMapper.deleteAdminById(admin_id);
		return "redirect:findAdmin.do";
	}
	
}
