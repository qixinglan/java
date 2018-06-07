package com.tarena.elts.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * ��װ������Ϣ
 * �Ծ����ÿ�������  ����   ���������� �𰸼���
 * @author asus
 *
 */

public class QuestionInfo {
	private int questionIndex;//���
	private Question question;//һ������
	private int score;//ÿ���������
	private List<Integer>  userAnswer=new ArrayList();//�𰸼���
	private int state;//1�����һ��  2�������һ��
	//�����������
	public static final int STATE_FIRST=1;
	public static final int STATE_LAST=2;
	public QuestionInfo(){
		
	}
	public QuestionInfo(int questionIndex,Question question){
		this.questionIndex=questionIndex;
		this.question=question;
	}
	//������ʾһ��������Ϣ
	public String toString(){
		return questionIndex+":("+score+")"+question.toString();
	}
	public int getQuestionIndex() {
		return questionIndex;
	}
	public void setQuestionIndex(int questionIndex) {
		this.questionIndex = questionIndex;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public List<Integer> getUserAnswer() {
		return userAnswer;
	}
	public void setUserAnswer(List<Integer> userAnswer) {
		this.userAnswer = userAnswer;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public static int getStateFirst() {
		return STATE_FIRST;
	}
	public static int getStateLast() {
		return STATE_LAST;
	}
	
}
