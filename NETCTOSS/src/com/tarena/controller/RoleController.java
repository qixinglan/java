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

import com.tarena.dao.Module;
import com.tarena.dao.Role;
import com.tarena.dao.RoleMapper;
import com.tarena.dao.RolePage;
@SessionAttributes("rolePage")
@Controller
@RequestMapping("/role")
public class RoleController {
	@Value("#{db.pageSize}")
	private int pageSize;
	@Resource
	private RoleMapper roleMapper;
	@RequestMapping("findRole.do")
	public String findRole(RolePage page,Model model){
		page.setPageSize(pageSize);
		int rows=roleMapper.findTotalRows();
		page.setRows(rows);
		List<Role> list=roleMapper.findRole(page);
		model.addAttribute("role",list);
		model.addAttribute("rolePage",page);
		return "/role/findRole";
	}
	@RequestMapping("addRole.do")
	public String addRole(Model model){
		List<Module> list=roleMapper.findAllModule();
		model.addAttribute("module",list);
		return "/role/addRole";
	}
	@RequestMapping("insertRole.do")
	public String insertRole(Role role,int[]moduleIds){
		roleMapper.insertRole(role);
		if(moduleIds!=null||moduleIds.length>0){
			Map<String,Object> map=new HashMap();
			for(int i:moduleIds){
				map.put("role_id",role.getRole_id());
				map.put("module_id", i);
				roleMapper.insertRoleModule(map);
			}
		}
		return "redirect:findRole.do";
	}
	@RequestMapping("deleteRole.do")
		public String deleteRole(int role_id){
		roleMapper.deleteRoleModule(role_id);
		roleMapper.deleteRole(role_id);
		return "redirect:findRole.do";
	}
	@ResponseBody
	@RequestMapping("checkRoleName.do")
	public Boolean checkRoleName(String name){
		Role role=roleMapper.findByName(name);
		if(role==null){
			return true;
		}
		else{
			return false;
		}
	}
	@RequestMapping("updateRole.do")
	public String  updateRole(int role_id,Model model){
		Role role=roleMapper.findByRole_id(role_id);
		model.addAttribute("role",role);
		List<Module> list=roleMapper.findAllModule();
		model.addAttribute("module",list);
		return "/role/updateRole";
	}
	@RequestMapping("fixRole.do")
	public String  fixRole(Role role,int[]moduleIds){
		roleMapper.updateRole(role);
		roleMapper.deleteRoleModule(role.getRole_id());
		System.out.println(moduleIds);
		if(moduleIds!=null||moduleIds.length>0){
		Map<String,Object> map=new HashMap();
		for(int i:moduleIds){
			map.put("role_id",role.getRole_id());
			map.put("module_id", i);
			roleMapper.insertRoleModule(map);
		}
	}
		return "redirect:findRole.do";
	}
	
}
