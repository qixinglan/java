package com.tarena.dao;

import java.util.List;
@MyBatisRepository
public interface CostMapper {
	List<Cost> findAllCost();
	void addCost(Cost cost);
	Cost findCostById(int id);
	void updateCost(Cost cost);
	void deleteCost(int id);
	List<Cost> findByPage(costPage page);
	int findTotalRows();
} 
