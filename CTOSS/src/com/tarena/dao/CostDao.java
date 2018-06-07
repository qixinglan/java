package com.tarena.dao;

import java.util.List;

import com.tarena.entity.Cost;

public interface CostDao {
	List<Cost> findCost();
	void deleteCost(int id);
	Cost findByName(String name);
}
