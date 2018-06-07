package com.tarena.elts.entity;

import java.io.Serializable;

/**
 * 封装考卷信息
 * 考试科目   考试时间  试题数目  满分   考生
 * @author asus
 *
 */
public class ExamInfo implements Serializable{
	private String title;//考试科目
	private User user;//考生
	private int timeLimit;//考试时间
	private int quessionCount;//试题数目
	private int totalScore;//满分
	public ExamInfo() {
		super();
	}
	//显示一条考试信息
	public String toString(){
		StringBuffer sb=new StringBuffer();
		return sb.append("姓名:").append(user.getName())
		.append(" 编号:").append(user.getId())
		.append(" 考试科目:")
		.append(title).append(" 时间(分钟):").append(timeLimit)
		.append(" 总分:").append(totalScore)
		.append(" 试题数量:").append(quessionCount)
		.toString();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	public int getQuessionCount() {
		return quessionCount;
	}
	public void setQuessionCount(int quessionCount) {
		this.quessionCount = quessionCount;
	}
	public int getTotolScore() {
		return totalScore;
	}
	public void setTotolScore(int totolScore) {
		this.totalScore = totolScore;
	}
	
	
}
