package com.tarena.elts.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装考题信息
 * 试卷题号每道题分数  考题   考生给出的 答案集合
 * @author asus
 *
 */

public class QuestionInfo {
	private int questionIndex;//题号
	private Question question;//一个考题
	private int score;//每个考题分数
	private List<Integer>  userAnswer=new ArrayList();//答案集合
	private int state;//1代表第一题  2代表最后一题
	//添加两个常量
	public static final int STATE_FIRST=1;
	public static final int STATE_LAST=2;
	public QuestionInfo(){
		
	}
	public QuestionInfo(int questionIndex,Question question){
		this.questionIndex=questionIndex;
		this.question=question;
	}
	//用来显示一个考题信息
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
