package com.nci.dcs.homepage.todo.model;

public class TodoCount {
	// 数量
	private long total;
	private long deadline;

	public TodoCount() {
		this.total = 0;
		this.deadline = 0;
	}

	public TodoCount(long total, long deadline) {
		this.total = total;
		this.deadline = deadline;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getDeadline() {
		return deadline;
	}

	public void setDeadline(long deadline) {
		this.deadline = deadline;
	}
}
