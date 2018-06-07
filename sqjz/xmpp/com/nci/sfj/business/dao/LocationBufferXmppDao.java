package com.nci.sfj.business.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.sfj.business.model.LocationBufferXmpp;

/**
 * 
 * 项目名称： sqjz
 * 包名称： com.nci.sfj.business.dao
 * 类名称: LocationBufferXmppDao
 * 类描述：
 * 创建人： yang
 * 创建时间: 2016-6-14上午10:32:41
 * 修改人： yang
 * 修改时间: 2016-6-14上午10:32:41
 * 修改备注：
 *
 */
@Repository
@Transactional
public class LocationBufferXmppDao extends HibernateDao<LocationBufferXmpp, String> {

}
