package com.nci.dcs.homepage.todo.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.SpringContextUtil;
import com.nci.dcs.homepage.todo.model.TodoCount;
import com.nci.dcs.homepage.todo.model.TodoModule;
import com.nci.dcs.homepage.todo.service.ITodoService;
import com.nci.dcs.homepage.todo.service.TodoService;
import com.nci.dcs.system.model.Bmb;

public class TodoAction extends BaseAction<TodoModule> {
	@Autowired
	private TodoService todoService;
	
	private List<TodoModule> todoModules;

	public String getMineData() {
		todoModules = getVisibleModules();
		return SUCCESS;
	}

	/**
	 * 获取用户首页可见的待办快捷
	 * 
	 * @return
	 */
	private List<TodoModule> getVisibleModules() {
		List<TodoModule> mine = getModules();
		Iterator<TodoModule> iter = mine.iterator();
		while (iter.hasNext()) {
			TodoModule module = iter.next();
			if (!"1".equals(module.getVisible())) {
				iter.remove();
				continue;
			}
			if (!CommonUtils.isNull(module.getService())) {
				try {
					ITodoService service = (ITodoService) SpringContextUtil
							.getBean(module.getService());
					TodoCount count = service.getTodoCount(getUser());
					module.setTotal(count.getTotal());
					module.setDeadline(count.getDeadline());
				} catch (BeansException e) {
					logger.warn("获取待办数量错误，Bean不存在：" + module.getService());
				} catch (Exception e) {
					logger.warn("获取待办数量错误，Bean必须实现接口ITodoService："
							+ module.getService());
				}
			}
		}
		return mine;
	}

	private List<TodoModule> getModules() {
		List<TodoModule> all = todoService.getAllModules();
		Bmb org = this.getCurOrg();
		List<TodoModule> visibleModules = new ArrayList<TodoModule>();
		for (TodoModule module : all) {
			if (org.getOrgType().equals(module.getPermission())) {
				visibleModules.add(module);
			}
		}
		Collections.sort(visibleModules, new Comparator<TodoModule>() {
			public int compare(TodoModule left, TodoModule right) {
				if (left.getSort() == null || right.getSort() == null) {
					return 0;
				}
				return left.getSort().compareTo(right.getSort());
			}
		});
		return visibleModules;
	}

	@Override
	public String list() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String view() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String audit() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String add() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enableBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disableBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String auditBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getIds() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TodoModule> getTodoModules() {
		return todoModules;
	}

	public void setTodoModules(List<TodoModule> todoModules) {
		this.todoModules = todoModules;
	}

}
