package com.tarena.elts.entity;
/**
 * ���Կ�����Ϣ
 * ���Կ�Ŀ  ����ʱ��  ������Ŀ  ����  ����
 */
public class ExamInfo {
	private String title;//���Կ�Ŀ
	private User user;//����
	private int timeLimit;//����ʱ��
	private int quesstionCount;//������Ŀ
	private int totalScore;//����
	public ExamInfo(){
	}
	//��ʾһ��������Ϣ
	public String toString(){
		StringBuffer sb=new StringBuffer();
		return sb.append("����:").append(user.getName())
		.append("���:").append(user.getId())
		.append("���Կ�Ŀ:").append(title)
		.append("ʱ�䣨���ӣ�").append(timeLimit)
		.append("�ܷ֣�").append(totalScore)
		.append("����������").append(quesstionCount)
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
