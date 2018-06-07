package com.nci.dcs.system.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.exceptions.InsertException;
import com.nci.dcs.common.exceptions.QueryException;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.system.model.Role;
import com.nci.dcs.system.model.User;
import com.nci.dcs.system.service.RoleService;
import com.nci.dcs.system.service.UserService;
import com.opensymphony.xwork2.Action;

public class RoleAction extends BaseAction<Role> {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 8874756054796215959L;
	@Autowired
	private RoleService service;
	@Autowired
	private UserService userService;

	private List<User> userList;
	private String userIds;

	private int isQuery = 0;
	private String tmpMc;
	private String mc;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	@Override
	public String add() throws Throwable {
		try {
			service.create(entity);
		} catch (Throwable e) {
			throw new InsertException(e.getMessage());
		}
		return this.list();
	}

	/**
	 * Description:列表条件
	 * 
	 * @author Shuzz
	 * @return
	 */
	private List<SearchRule> defaultRule() {
		List<SearchRule> result = new ArrayList<SearchRule>();
		return result;
	}

	public String search() throws Throwable {
		try {
			List<SearchRule> searchs = this.parseJQGridRequest(defaultRule());
			service.findPersonPaged(this.fillPageWithJQGridRequest(), searchs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Override
	public String audit() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String auditBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Throwable {
		String userId = request.getParameter("roleId");
		if (isNotNull(userId)) {
			List<String> ids = new ArrayList<String>();
			ids.addAll(Arrays.asList(userId.split(",")));
			for (String id : ids) {
				service.delete(Integer.parseInt(id));
			}
		}
		return SUCCESS;
	}

	@Override
	public String deleteBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disableBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enableBulk() throws Throwable {
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

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	@Override
	public String list() throws Throwable {
		try {
		} catch (Throwable e) {
			throw new QueryException(e.getMessage());
		}
		return Action.SUCCESS;
	}

	@Override
	public String update() throws Throwable {
		try {
			Role role = service.get(entity.getId());
			CommonUtils.copyWithOutNull(role, entity);
			service.update(role);
		} catch (Throwable e) {
			throw new InsertException(e.getMessage());
		}
		return this.list();
	}

	@Override
	public String view() throws Throwable {
		try {
			String roleId = request.getParameter("roleId");
			if (isNotNull(roleId)) {
				entity = service.get(Integer.parseInt(roleId));
				getUsers();
			}
		} catch (Throwable e) {
			throw new QueryException(e.getMessage());
		}
		return Action.SUCCESS;
	}

	public String showInfo() throws Throwable {
		try {
			String roleId = request.getParameter("roleId");
			if (isNotNull(roleId)) {
				entity = service.get(Integer.parseInt(roleId));
				getUsers();
			}
		} catch (Throwable e) {
			throw new QueryException(e.getMessage());
		}
		return Action.SUCCESS;
	}

	public String choiceUser() throws Throwable {
		request.setAttribute("roleId", request.getParameter("roleId"));
		return SUCCESS;
	}

	public String updateUsers() throws Throwable {
		try {
			String roleId = request.getParameter("roleId");
			String userId = request.getParameter("userId");
			String type = request.getParameter("type");
			if (isNotNull(roleId)) {
				Role updateRole = service.get(Integer.parseInt(roleId));
				User user = userService.get(userId);
				if ("1".equals(type)) {
					updateRole.getUsers().add(user);
				} else {
					updateRole.getUsers().remove(user);
				}
				service.update(updateRole);
			}
		} catch (Throwable e) {
			throw new InsertException(e.getMessage());
		}
		return null;
	}

	public int getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(int isQuery) {
		this.isQuery = isQuery;
	}

	public String getTmpMc() {
		return tmpMc;
	}

	public void setTmpMc(String tmpMc) {
		this.tmpMc = tmpMc;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public void getUsers(){
		Set<User> userSet = entity.getUsers();
		String users="";
		String userName="";
		for(User user:userSet){
			userName = user.getName();
			if(userName!=null){
				users += user.getName() + ",";
			}
		}
		String userStr = users.equals("")?"":users.substring(0,users.lastIndexOf(","));
		request.setAttribute("users", userStr);
	}
}
