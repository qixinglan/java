package com.tarena.elts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tarena.elts.entity.EntityContext;
import com.tarena.elts.entity.ExamInfo;
import com.tarena.elts.entity.Question;
import com.tarena.elts.entity.QuestionInfo;
import com.tarena.elts.entity.User;
/**
 * 业务逻辑的实现类
 */
public class ExamServiceImpl implements ExamService {
	
	/**
	 * 数据管理层的EntityContext对象
	 */
	private EntityContext entityContext;
	public void setEntityContext(EntityContext entityContext) {
		this.entityContext = entityContext;
	}
	//登陆逻辑
	public  User login(int id, String pwd) throws IdOrPwdException {
		//根据ID获得数据管理成的一个user
		User u=entityContext.getUserById(id);
		
		//如果没有则无用户
		if(u==null){
			throw new IdOrPwdException("无该用户");
		}
		//如果有，判断密码，如果成功，执行。。。
		if(u.getPassword().equals(pwd)){
			//创建考卷
			this.u=u;//把用户变成全局变量
			return u;
		}
		//如果失败抛出IdOrPwdExeption
		throw new IdOrPwdException("密码错误");
	}
	/**
	 * 重写start方法
	 * 创建考卷，调用createPaper（20）
	 * 创建ExamInfo对象，给ExamInfo各个属性赋值，
	 * （考试科目：Java考试，考试时间：两分钟，考试总分：100，题目数量：20，考生：登陆用户（
	 * 返回一个ExamInfo对象
	 */
	User u;//全局变量
	
	public ExamInfo start(){
		if(isFinish){
			throw new RuntimeException("考试以结束");
		}
		ExamInfo examInfo=new ExamInfo();
		creatPaper(20);
		examInfo.setTitle(entityContext.getExamTitle());
		examInfo.setTimeLimit(entityContext.getTimeLimit());
		examInfo.setTotolScore(entityContext.gettotalScore());
		examInfo.setUser(u);
		examInfo.setQuessionCount(entityContext.getQuestionCount());
		return examInfo;
	}
	/**
	 * 添加重写的getQuestionInfo（int number）
	 * 返回考卷里的第一道题
	 */
	public QuestionInfo getQuestionInfoByIndex(int index){
		return paper.get(index-1);
	}
	/*
	 * 添加一个私有的创建考卷的功能方法creatPaper（int numbers）
	 * 根据参数随机获取entityContext里的 考题集合的考题，参数就是获取的个数
	 * 创建对应的QuestionInfo对象（添加题号，分数，考题）
	 * 把这一个个的QuestionInfo对象，收集起来，作为考卷的所有考题（集合）
	 * 
	 */
	
	private void  creatPaper(int numbers){
		List<Question> question=entityContext.getQuestions();
		Random ran=new Random();
		for(int i=0;i<numbers;i++){
			Question q=question.remove(ran.nextInt(numbers));///remove不重复
			QuestionInfo questionInfo=new QuestionInfo();
			questionInfo.setQuestionIndex(i+1);
			questionInfo.setQuestion(q);
			//questionInfo.setScore(100/numbers);
			paper.add(questionInfo);
			setScore();
			//questionInfo.setUserAnswers(userAnswers);
		}
		//第一题和最后一题设置属性
		paper.get(0).setState(QuestionInfo.STATE_FIRST);
		paper.get(paper.size()-1).setState(QuestionInfo.STATE_LAST);
	}
	/**
	 * 创建一个包含所有考题的集合List《QuestionInfo》paper
	 */
	private List<QuestionInfo> paper=new ArrayList();
	
	/*
	 * 添加一个私有的设置每个考题分数的方法setScore（）
	 * 知道试卷的总分（ExamInfo里有总分）
	 * 知道试卷题目的数量
	 * 总分/数量=分数
	 * 把分数给集合里的每一个考题
	 */
	private void setScore(){
		
		int score=entityContext.gettotalScore()/entityContext.getQuestionCount();
		//int score=5;
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
			if(qi.getUserAnswers().equals(q.getAnswers())){//用集合的equals方法其实就是比较每个元素是否相等，因为api重写了equals
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
	public void saveUserAnswer(int questionIndex, List<Integer> userAnswer) {
		// TODO Auto-generated method stub
		//第一种遍历paper所有元素，只要题号和传进来的一致，就设置它的用户答案
		//第二种题号和角标只差1
		paper.get(questionIndex-1).setUserAnswers(userAnswer);
	}
	//重写获得规则方法
	public String getRule(){
		return entityContext.getRule();
	}
//	//重写获得考试时间方法
//	public int getTime(){
//		return entityContext.getTimeLimit();
//	}
}
