package com.tarena.action;

import java.util.List;

import com.tarena.dao.CostDaoImp;
import com.tarena.entity.Cost;

public class CostAction {
	private List<Cost> cost;
	private int id;
	private String name;
	private Boolean repeat;
	
	public String findCost(){
		CostDaoImp costDaoImp=new CostDaoImp();
		cost=costDaoImp.findCost();
		return "success";
	}
	public String deleteCost(){
		CostDaoImp costDaoImp=new CostDaoImp();
		costDaoImp.deleteCost(id);
		return "success";
	}
	public String addCost(){
		return "success";
	}
	public String checkCostName(){
		CostDaoImp costDaoImp=new CostDaoImp();
		Cost cost=costDaoImp.findByName(name);
		if(cost==null){
			repeat=false;
		}else{
			repeat=true;
		}
		return "success";
				
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Cost> getCost() {
		return cost;
	}
	public void setCost(List<Cost> cost) {
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Boolean getRepeat() {
		return repeat;
	}
	public void setRepeat(Boolean repeat) {
		this.repeat = repeat;
	}
	
}
