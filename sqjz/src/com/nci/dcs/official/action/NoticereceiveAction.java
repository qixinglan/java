package com.nci.dcs.official.action;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.official.model.CcNoticereceive;
import com.nci.dcs.official.service.NoticeReceiveServie;

public class NoticereceiveAction extends BaseAction<CcNoticereceive>{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6322761706968630244L;

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
	
	@Autowired
	private NoticeReceiveServie noticeServie;
	/**
	 * @name 查看签收情况
	 * @return
	 * @author clj
	 * @date 2014年9月12日 上午10:07:34 
	 * @message：
	 */
	@SuppressWarnings("unchecked")
	public String noticeReceive(){
		String noticeid = request.getParameter("id");
		getRequestPage();
		page.getCriterions().add(Restrictions.eq("noticeid", noticeid));
		noticeServie.findPaged(page);
		return SUCCESS;
	}
}
