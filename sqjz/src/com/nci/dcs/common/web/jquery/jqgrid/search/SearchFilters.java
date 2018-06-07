package com.nci.dcs.common.web.jquery.jqgrid.search;

import java.util.List;

public class SearchFilters {
	private String groupOp;
	private List<SearchRule> rules;
	public String getGroupOp() {
		return groupOp;
	}
	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}
	public List<SearchRule> getRules() {
		return rules;
	}
	public void setRules(List<SearchRule> rules) {
		this.rules = rules;
	}
}
