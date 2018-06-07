package com.tarena.dao;

import java.util.List;
import java.util.Map;


@MyBatisRepository
public interface AdminMapper {
	List<Admin> findAdmin(AdminPage adminPage);
	int findTotalRows(AdminPage adminPage);
	void resttingPassword(Map<String,Object> map);
	List<Role> findTotalRole();
	void insertAdmin(Admin admin);
	void insertAdminRole(Map<String,Object> map); 
	Admin findById(int id);
	void fixAdmin(Admin admin);
	void deleteAdminRole(int admin_id);
	void deleteAdminById(int admin_id);
	Admin findByAdmin_code(String Admin_code);
	List<Module> findModulesByAdmin_id(int admin_id);
}
