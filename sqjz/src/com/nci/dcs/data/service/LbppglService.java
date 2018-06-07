package com.nci.dcs.data.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.data.dao.LbppglDao;
import com.nci.dcs.data.model.Lbppgl;
import com.nci.dcs.data.model.LbppglPk;


@Service
@Transactional
public class LbppglService extends BaseService<Lbppgl, LbppglPk>{
	
	@Autowired
	private LbppglDao lbppglDao;

	@Override
	public void audit(LbppglPk id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(Lbppgl entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Lbppgl entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(LbppglPk id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disable(LbppglPk id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enable(LbppglPk id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Lbppgl> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Lbppgl> findPaged(Page<Lbppgl> page, Lbppgl entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Lbppgl> findPaged(Page<Lbppgl> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lbppgl get(LbppglPk id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Lbppgl entity) {
		// TODO Auto-generated method stub
		
	}
	
//	public void save(String[] lbdms,int ppdm){
//		for(String lbdm:lbdms){
//			if(!lbdm.equals("")){
//				Lbppgl gl=new Lbppgl();
//				gl.setLbdm(Integer.valueOf(lbdm));
//				gl.setPpdm(ppdm);
//				gl.setCjsj(new Date());
//				gl.setXgsj(new Date());
//				lbppglDao.save(gl);
//			}			
//		}
//	}
//	
//	public void delByPpdms(int[] dms){
//		StringBuffer sbf=new StringBuffer();
//		for(int d:dms){
//			sbf.append(d+",");
//		}
//		String vlaues=sbf.toString().substring(0, sbf.length()-1);
//		String hql="delete from Lbppgl where ppdm in("+vlaues+")";
//		lbppglDao.createQuery(hql, null).executeUpdate();
//	}
//	public void delByLbdms(int[] dms){
//		StringBuffer sbf=new StringBuffer();
//		for(int d:dms){
//			sbf.append(d+",");
//		}
//		String vlaues=sbf.toString().substring(0, sbf.length()-1);
//		String hql="delete from Lbppgl where lbdm in("+vlaues+")";
//		lbppglDao.createQuery(hql, null).executeUpdate();
//	}
	
	
	

}
