package com.tarena.dao;

import com.tarena.entity.Admin;

public interface AdminDao {
	Admin findByAdmin_code(String admin_code);
}
