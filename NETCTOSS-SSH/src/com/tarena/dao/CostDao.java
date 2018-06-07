package com.tarena.dao;

import java.util.List;

import com.tarena.entity.Cost;
import com.tarena.entity.costPage;

public interface CostDao {
	
	Cost findById(int id);
	
	void save(Cost cost);
	
	void update(Cost cost);
	
	void delete(int id);
	int findTotalRows();
	List<Cost> findCost(costPage costPage);

}