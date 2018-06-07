package com.tarena.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tarena.dao.CostDaoImp;
import com.tarena.entity.Cost;
import com.tarena.entity.costPage;
@Controller
@Scope("prototype")
public class CostAction {
	@Resource
	private CostDaoImp costDaoImp;
	private costPage page=new costPage();
	List<Cost> costs;
	public String  execute(){
		//查询总行数
		int rows=costDaoImp.findTotalRows();
		page.setRows(rows);
		//查询当前页的数据
		costs=costDaoImp.findCost(page);
		return "success";
	}
	public costPage getPage() {
		return page;
	}
	public void setPage(costPage page) {
		this.page = page;
	}
	public List<Cost> getCosts() {
		return costs;
	}
	public void setCosts(List<Cost> costs) {
		this.costs = costs;
	}
	
}
