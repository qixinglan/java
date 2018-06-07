package com.nci.dcs.system.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.exceptions.DeleteException;
import com.nci.dcs.common.exceptions.InsertException;
import com.nci.dcs.common.exceptions.QueryException;
import com.nci.dcs.common.exceptions.UpdateException;
import com.nci.dcs.system.model.Authorization;
import com.nci.dcs.system.model.Role;
import com.nci.dcs.system.service.AuthorizationService;
import com.nci.dcs.system.service.RoleService;
import com.opensymphony.xwork2.Action;

public class AuthorizationAction extends BaseAction<Authorization> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private AuthorizationService service;
	@Autowired
	private RoleService roleService;

	private String treeStr;// 树

	private String parentDm;// 代码

	private String roleIds;

	private List<Role> roleList;

	private Integer parentId;

	private String parentName;

	private String noteType;

	private int isQuery = 0;
	private String tmpMc;
	private String mc;

	public String getNoteType() {
		return noteType;
	}

	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentDm() {
		return parentDm;
	}

	public void setParentDm(String parentDm) {
		this.parentDm = parentDm;
	}

	public String getTreeStr() {
		return treeStr;
	}

	public void setTreeStr(String treeStr) {
		this.treeStr = treeStr;
	}

	@Override
	public String add() throws Throwable {
		try {
			Authorization auth = new Authorization();

			PropertyUtils.copyProperties(auth, entity);
			int pid = auth.getParent().getId();
			Set<Role> roles = service.get(pid).getRoles();
			Set<Role> roles2 = new HashSet<Role>();
			if (roles != null && roles.size() > 0) {
				for (Role r : roles) {
					if (r != null) {
						roles2.add(r);
					}
				}
			}

			// auth.setRoles(roleone); //这有问题 zjc adds.2014-02-14，必须注释掉.
			service.create(auth);

			// System.out.println("auth.id():"+auth.getId());
			// 先添加再绑定
			Authorization justone = service.getNew();
			// System.out.println("justone.id():"+justone.getId());
			// System.out.println("pid():"+pid);
			justone.setRoles(roles);
			service.update(justone);

			// 父节点
			Authorization father = service.get(pid);
			father.setRoles(roles2);
			service.update(father);

			Authorization action = entity;
			entity = new Authorization();
			entity.setId(action.getParent().getId()); // 这是为了显示list()所以设定其父节点id为entity-id,并设置parentId
			parentId = entity.getId();
		} catch (Throwable e) {
			throw new InsertException(e.getMessage());
		}
		return this.list();
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
		try {
			Authorization parent = new Authorization();
			parent.setId(entity.getParent().getId());
			service.delete(entity.getId());
			entity.setParent(parent);
			Authorization action = entity;
			entity.setId(action.getParent().getId());
			parentId = entity.getId();
		} catch (Throwable e) {
			throw new DeleteException(e.getMessage());
		}
		return this.list();
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
		return null;
	}

	@Override
	public String list() throws Throwable {
		try {
			if (isQuery == 1 && !mc.equals(tmpMc))
				mc = tmpMc;
			Authorization newOne = new Authorization();
			newOne.setName(mc);
			newOne.setId(parentId);
			page.setOrder("asc");
			page.setOrderBy("cjsj");
			service.findPaged(page, newOne);
			isQuery = 0;
		} catch (Throwable e) {
			throw new QueryException(e.getMessage());
		}
		return SUCCESS;
	}

	@Override
	public String update() throws Throwable {
		try {
			entity.setRoles((service.get(entity.getId()).getRoles())); // 保存当前绑定的角色关系，
			service.update(entity);
			Authorization action = entity;
			entity = new Authorization();
			entity.setId(action.getParent().getId());
			parentId = entity.getId();
		} catch (Throwable e) {
			throw new UpdateException(e.getMessage());
		}
		return this.list();
	}

	@Override
	public String view() throws Throwable {
		try {
			Authorization authorization = service.get(entity.getId());
			PropertyUtils.copyProperties(entity, authorization);
			if (!authorization.getType().equals("0")) {// 如果该节点属于动作对应的叶子节点，直接转到
				parentName = authorization.getParent().getName();
				System.out.println("parentName:" + parentName);
				return NONE;
			}
		} catch (Throwable e) {
			throw new QueryException(e.getMessage());
		}
		return SUCCESS;
	}

	public String listInit() throws Throwable {
		try {
			// 如果传进来的parentId属于
			Authorization auth = service.get(parentId);
			noteType = auth.getType();
			if (!noteType.equals("0")) { // 如果该节点属于动作对应的叶子节点，直接转到
				PropertyUtils.copyProperties(entity, auth);
				return NONE;
			}
			List<Authorization> authorizations = service.findPagedInit(page,
					parentId);
			page.setResult(authorizations); // 设置page的result
			if (authorizations == null) {
				page.setTotalCount(0);
			} else {
				page.setTotalCount(authorizations.size());// 设置page的totalcount
			}
			parentName = service.get(parentId).getName();

		} catch (Throwable e) {
			throw new QueryException(e.getMessage());
		}
		return SUCCESS;
	}

	public String tree() throws Throwable {
		try {
			// 通过authorizationtree.js传来的当前节点ID作为父节点编号(parentDm)获取其下属节点列表JSON
			treeStr = service.findByParentId(parentDm);
		} catch (Throwable e) {
			throw new QueryException(e.getMessage());
		}
		try {
			ServletActionContext.getResponse().setContentType("text/html");
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().printf(treeStr);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public String choiceRole() throws Throwable {
		Authorization authorization = service.get(entity.getId()); // 先获取该条action
		StringBuffer sbf = new StringBuffer();
		for (Role r : authorization.getRoles()) {
			sbf.append(r.getId() + ",");
		}
		roleIds = sbf.toString(); // 确定checkbox
		roleList = roleService.find();
		return SUCCESS;
	}

	public String updateRoles() throws Throwable {
		try {
			Set<Role> roles = new HashSet<Role>();
			if (roleList != null && roleList.size() > 0) {
				for (Role r : roleList) {
					if (r != null) {
						roles.add(r);
					}
				}
			}
			Authorization updateAction = service.get(entity.getId());
			updateAction.setRoles(roles);
			service.update(updateAction);
			if (updateAction.getType().equals("0")) { // 如果该节点是模块
				List<Authorization> childrenAction = new ArrayList<Authorization>();
				childrenAction = service.findChildrenActions(updateAction
						.getId());
				if (childrenAction != null && childrenAction.size() > 0) {
					for (Authorization auth : childrenAction) {
						auth.setRoles(roles);
						service.update(auth);
					}
				}
			}
		} catch (Throwable e) {
			throw new InsertException(e.getMessage());
		}
		return null;
	}

	public String showInfo() throws Throwable {
		try {
			Authorization a = service.get(entity.getId()); // 获取该条action对象
			PropertyUtils.copyProperties(entity, a);
		} catch (Throwable e) {
			throw new QueryException(e.getMessage());
		}
		return SUCCESS;
	}

	public String addAction() throws Throwable {
		try {
			parentName = service.get(parentId).getName();
		} catch (Throwable e) {
			throw new Exception(e.getMessage());
		}
		return SUCCESS;
	}

	public String beforeAdd() {

		return Action.SUCCESS;
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
}
