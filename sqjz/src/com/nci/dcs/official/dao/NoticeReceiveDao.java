package com.nci.dcs.official.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.official.model.CcNoticereceive;

@Repository
@Transactional
public class NoticeReceiveDao extends HibernateDao<CcNoticereceive, String>{

	/**
	 * @name 获取收到通知机关的通知ID
	 * @param id
	 * @return
	 * @author clj
	 * @date 2014年9月11日 下午4:07:55 
	 * @message：
	 */
	public List<?> getNoticeIds(String id) {
		String sql = "select t.noticeid from cc_noticereceive t where t.org_id=?";
		return createSQLQuery(sql, id).list();
	}
}
