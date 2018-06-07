package com.tarena.dao;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface RoleMapper {
	List<Role> findRole(RolePage page);
	int findTotalRows();
	List<Module> findAllModule();
	void insertRole(Role role);
	void insertRoleModule(Map<String,Object> map);
	void deleteRole(int id);
	void deleteRoleModule(int id);
	Role findByName(String name);
	Role findByRole_id(int id);
	void updateRole(Role role);
}