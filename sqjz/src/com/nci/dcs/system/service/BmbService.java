package com.nci.dcs.system.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.system.dao.BmbDao;
import com.nci.dcs.system.model.Bmb;

@Service
@Transactional
public class BmbService extends BaseService<Bmb, String>{
	
	@Autowired
	private BmbDao bmbDao;
	
	@Override
	public void audit(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(Bmb entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Bmb entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disable(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enable(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Bmb> find() {
		// TODO Auto-generated method stub
		return bmbDao.getAll();
	}

	@Override
	public Page<Bmb> findPaged(Page<Bmb> page, Bmb entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Bmb> findPaged(Page<Bmb> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bmb get(String id) {
		return bmbDao.get(id);
	}

	@Override
	public void update(Bmb entity) {
		// TODO Auto-generated method stub
		
	}
	
	public String findByParentDm(String bm){
		String hql="from Bmb where path like '%/"+bm+"/' order by mc";
		List<Bmb> list=bmbDao.find(hql);
		
		StringBuffer strTree = new StringBuffer();
		strTree.append("[");
		for(Iterator<Bmb> i = list.iterator(); i.hasNext(); ){
			Bmb s=i.next();
			strTree.append("{");
			strTree.append("\"attr\" : ");
			strTree.append("{\"id\" : \"");
			strTree.append(s.getBm());
			strTree.append("\", ");
			
			strTree.append("\"name\" : \"");
			strTree.append(s.getMc());
			strTree.append("\", ");
			

			strTree.append("\"dm\" : \"");
			strTree.append(s.getBm());
			strTree.append("\"");
			strTree.append("},");
			
			strTree.append("\"data\" : \"");
			strTree.append(s.getMc());
			strTree.append("\"");
			
			if(this.getChildCount(s.getBm())>0){
				strTree.append(",\"state\" : \"closed\"");
				strTree.append(", \"icon\" : \"folder\"");
			}
			
			
			strTree.append("}");
			if(i.hasNext()) strTree.append(",");
		}		
		strTree.append("]");	
		
		return strTree.toString();
	}
	
	public int getChildCount(String bm){
		String hql="from Bmb where path like '%/"+bm+"/%'";
		List<Bmb> list=bmbDao.find(hql);
		if(list==null){
			return 0;
		}
		return list.size();
	}


}
