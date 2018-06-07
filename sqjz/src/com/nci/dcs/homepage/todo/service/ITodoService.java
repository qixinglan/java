package com.nci.dcs.homepage.todo.service;

import com.nci.dcs.homepage.todo.model.TodoCount;
import com.nci.dcs.system.model.User;

public interface ITodoService {
	public TodoCount getTodoCount(User user);
}
