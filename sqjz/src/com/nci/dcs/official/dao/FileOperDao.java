package com.nci.dcs.official.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.official.model.Dataupload;

@Repository
@Transactional
public class FileOperDao extends HibernateDao<Dataupload, String> {
}
