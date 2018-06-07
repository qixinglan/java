package com.nci.sfj.transmit.service.dbsx;

import com.nci.dcs.homepage.todo.model.TodoCount;
import com.nci.dcs.system.model.Bmb;

public interface ITodoServiceNew {
	public TodoCount getTodoCount(Bmb bmb);
}
