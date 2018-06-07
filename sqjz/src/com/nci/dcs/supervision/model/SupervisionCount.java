package com.nci.dcs.supervision.model;

import java.util.HashMap;
import java.util.Map;

public class SupervisionCount {
	// 数量
	private long total;
	private Map<String, Integer> supervisions;

	public SupervisionCount() {
		this.total = 0;
		this.supervisions = new HashMap<String, Integer>();
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Map<String, Integer> getSupervisions() {
		return supervisions;
	}

	public void setSupervisions(Map<String, Integer> supervisions) {
		this.supervisions = supervisions;
	}

}
