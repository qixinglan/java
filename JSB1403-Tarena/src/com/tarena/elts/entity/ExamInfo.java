package com.tarena.elts.entity;

import java.io.Serializable;

/**
 * ��װ������Ϣ
 * ���Կ�Ŀ   ����ʱ��  ������Ŀ  ����   ����
 * @author asus
 *
 */
public class ExamInfo implements Serializable{
	private String title;//���Կ�Ŀ
	private User user;//����
	private int timeLimit;//����ʱ��
	private int quessionCount;//������Ŀ
	private int totalScore;//����
	public ExamInfo() {
		super();
	}
	//��ʾһ��������Ϣ
	public String toString(){
		StringBuffer sb=new StringBuffer();
		return sb.append("����:").append(user.getName())
		.append(" ���:").append(user.getId())
		.append(" ���Կ�Ŀ:")
		.append(title).append(" ʱ��(����):").append(timeLimit)
		.append(" �ܷ�:").append(totalScore)
		.append(" ��������:").append(quessionCount)
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
