package com.tarena.elts.service;

import java.util.ArrayList;
import java.util.List;

import com.tarena.elts.entity.EntityContext;
import com.tarena.elts.entity.ExamInfo;
import com.tarena.elts.entity.Question;
import com.tarena.elts.entity.QuestionInfo;
import com.tarena.elts.entity.User;

public class ExamServiceImpl implements ExamService {
	User u;
	//数据管理层对象
	private EntityContext entityContext;

	public void setEntityContext(EntityContext entityContext) {
		this.entityContext = entityContext;
	}
	//登陆逻辑
	public User login(int id,String pwd)throws IdOrPwdException{
		//根据id获得数据管理层的一个user
		User u=entityContext.getUserById(id);
		//如果没有则无用户
		if(u==null){
			throw new IdOrPwdException("无该用户");
		}
		//如果有，判断密码，如果成功，执行。。。。
		if(u.getPassword().equals(pwd)){
			//创建考卷
			this.u=u;//把用户变成全局变量
			return u;
		}
		//密码错误
		throw new IdOrPwdException("密码错误");
	}
	//重写开始考试的方法
	public ExamInfo start(){
		if(isFinish){
			throw new RuntimeException("考试以结束");
		}
		ExamInfo examInfo=new ExamInfo();
		createPaper(20);
		examInfo.setTitle(entityContext.getExamTitle());
		examInfo.setTimeLimit(entityContext.getTimeLimit());
		examInfo.setTotalScore(entityContext.gettotalScore());
		examInfo.setQuesstionCount(entityContext.getQuestionCount());
		examInfo.setUser(u);
		return examInfo;
	}
	//重写获得获得考题方法
	public QuestionInfo getQuestionInfoByIndex(int index){
		return paper.get(index-1);
	}
	//创建一个包含所有考卷的集合
	private List<QuestionInfo>paper=new ArrayList();
	//获得考卷方法
	private void createPaper(int number){
		List<Question> question=entityContext.getQuestions();
		for(int i=0;i<number;i++){
			Question q=question.remove((int)Math.random()*number);//这样不重复
			QuestionInfo questionInfo=new QuestionInfo();
			questionInfo.setQuestionIndex(i+1);
			questionInfo.setQuestion(q);
			paper.add(questionInfo);
			setScore();
		}
		//第一题和最后一题设置属性
		paper.get(0).setState(QuestionInfo.STATE_FIRST);
		paper.get(paper.size()-1).setState(QuestionInfo.STATE_LAST);
		
	}
	//添加设置分数的方法
	private void setScore(){
		int score=entityContext.gettotalScore()/entityContext.getQuestionCount();
		for(QuestionInfo qi:paper){
			qi.setScore(score);
		}
	}
	//重写over方法，返回分数
	public int over(){
		isFinish=true;
		int score=0;
		//计算分数
		for(QuestionInfo qi:paper){
			Question q=qi.getQuestion();
			//判断答案是否正确
			if(qi.getUserAnswer().equals(q.getAnswer())){
				score+=qi.getScore();
			}
		}
		isFinish=true;
		this.score=score;
		return score;
	}
	//定义一个属性判断是否开始考试了，默认没开始
	boolean isFinish=false;
	//用于保存分数
	private int score;
	//重写获得分数的方法
	public int getScore(){
		if(!isFinish){
			throw new RuntimeException("您还未考试");
		}
		return score;
	}
	//获得用户答案
	public void saveUserAnswer(int questionindex,List<Integer>userAnswer){
		paper.get(questionindex-1).setUserAnswer(userAnswer);
	}
	//重写获得规则方法
	public String getRule(){
		return entityContext.getRule();
	}

}
