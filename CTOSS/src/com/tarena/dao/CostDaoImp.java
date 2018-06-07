package com.tarena.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tarena.entity.Cost;

public class CostDaoImp implements CostDao{
	public List<Cost> findCost(){
		List<Cost> list=new ArrayList<Cost>();
		Cost cost1=new Cost();
		cost1.setBase_cost(50.0);
		cost1.setBase_duration(20);
		cost1.setCost_id(1);
		cost1.setCostType('1');
		cost1.setCreateTime(new Date(System.currentTimeMillis()));
		cost1.setDescr("50ÔªÌ×²Í");
		cost1.setName("50ÔªÌ×²Í");
		cost1.setStatus('0');
		cost1.setStartTime(new Date(System.currentTimeMillis()));
		cost1.setUnit_cost(0.3);
		list.add(cost1);
		Cost cost2=new Cost();
		cost2.setBase_cost(30.0);
		cost2.setBase_duration(20);
		cost2.setCost_id(2);
		cost2.setCostType('1');
		cost2.setCreateTime(new Date(System.currentTimeMillis()));
		cost2.setDescr("30ÔªÌ×²Í");
		cost2.setName("30ÔªÌ×²Í");
		cost2.setStartTime(new Date(System.currentTimeMillis()));
		cost2.setStatus('0');
		cost2.setUnit_cost(0.5);
		list.add(cost2);
		return list;
	}

	public void deleteCost(int id) {
		// TODO Auto-generated method stub
		System.out.println("É¾³ýid="+id+"µÄCost");
		
	}

	public Cost findByName(String name) {
		// TODO Auto-generated method stub
		if(name==null||name.equals("")){
			return null;
		}
		if(name.equals("tarena")){
			Cost cost2=new Cost();
			cost2.setBase_cost(100.0);
			cost2.setBase_duration(20);
			cost2.setCost_id(3);
			cost2.setCostType('1');
			cost2.setCreateTime(new Date(System.currentTimeMillis()));
			cost2.setDescr("100ÔªÌ×²Í");
			cost2.setName("tarena");
			cost2.setStartTime(new Date(System.currentTimeMillis()));
			cost2.setStatus('0');
			cost2.setUnit_cost(0.2);
			return cost2;
		}
		return null;
	}
}
