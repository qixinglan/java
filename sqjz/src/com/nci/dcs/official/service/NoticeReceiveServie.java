package com.nci.dcs.official.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.official.dao.NoticeReceiveDao;
import com.nci.dcs.official.model.CcNoticereceive;

@Service
@Transactional
public class NoticeReceiveServie extends BaseService<CcNoticereceive, String>{

	@Autowired
	private NoticeReceiveDao dao;
	
	@Override
	public void create(CcNoticereceive entity) {
		dao.save(entity);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CcNoticereceive entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CcNoticereceive get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CcNoticereceive> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CcNoticereceive> findPaged(Page<CcNoticereceive> page,
			CcNoticereceive entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enable(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disable(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void audit(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<CcNoticereceive> findPaged(Page<CcNoticereceive> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcNoticereceive entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @name 获取收到通知机关的通知ID
	 * @param id
	 * @return
	 * @author clj
	 * @date 2014年9月11日 下午4:09:51 
	 * @message：
	 */
	public List<?> getNoticeIds(String id) {
		return dao.getNoticeIds(id);
	}
	/**
	 * @name 查询签收情况
	 * @param page
	 * @return
	 * @author clj
	 * @date 2014年9月12日 上午10:09:15 
	 * @message：
	 */
	public Page<CcNoticereceive> findPaged(Page<CcNoticereceive> page) {
		return dao.findByCriteria(page);
	}
}
