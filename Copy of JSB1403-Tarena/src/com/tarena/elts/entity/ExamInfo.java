package com.tarena.elts.entity;
/**
 * 考试考卷信息
 * 考试科目  考试时间  试题数目  满分  考生
 */
public class ExamInfo {
	private String title;//考试科目
	private User user;//考生
	private int timeLimit;//考试时间
	private int quesstionCount;//试题数目
	private int totalScore;//满分
	public ExamInfo(){
	}
	//显示一条考试信息
	public String toString(){
		StringBuffer sb=new StringBuffer();
		return sb.append("姓名:").append(user.getName())
		.append("编号:").append(user.getId())
		.append("考试科目:").append(title)
		.append("时间（分钟）").append(timeLimit)
		.append("总分：").append(totalScore)
		.append("试题数量：").append(quesstionCount)
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
	public int getQuesstionCount() {
		return quesstionCount;
	}
	public void setQuesstionCount(int quesstionCount) {
		this.quesstionCount = quesstionCount;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	
}
