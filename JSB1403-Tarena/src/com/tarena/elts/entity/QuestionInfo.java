package com.tarena.elts.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *��װ������Ϣ
 *�Ծ����   ÿ�������   ����  ���������� �𰸼���
 * @author asus
 *
 */
public class QuestionInfo implements Serializable{
	private int questionIndex;//���
	private Question question;//һ������
	private int score;//ÿ���������
	private List<Integer> userAnswers=new ArrayList();//��
	private int state;//1�����һ�⣬2�������һ�⣬0��������
	//�����������
	public static final int STATE_FIRST=1;
	public static final int STATE_LAST=2;
	public static final int STATE_MIDDLE=0;
	
	public QuestionInfo() {
		super();
	}
	public QuestionInfo(int questionIndex, Question question) {
		super();
		this.questionIndex = questionIndex;
		this.question = question;
	}
	//������ʾһ���������Ϣ
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
	public List<Integer> getUserAnswers() {
		return userAnswers;
	}
	public void setUserAnswers(List<Integer> userAnswers) {
		this.userAnswers = userAnswers;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
