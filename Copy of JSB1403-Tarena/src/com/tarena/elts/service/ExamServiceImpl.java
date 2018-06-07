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
	//���ݹ�������
	private EntityContext entityContext;

	public void setEntityContext(EntityContext entityContext) {
		this.entityContext = entityContext;
	}
	//��½�߼�
	public User login(int id,String pwd)throws IdOrPwdException{
		//����id������ݹ�����һ��user
		User u=entityContext.getUserById(id);
		//���û�������û�
		if(u==null){
			throw new IdOrPwdException("�޸��û�");
		}
		//����У��ж����룬����ɹ���ִ�С�������
		if(u.getPassword().equals(pwd)){
			//��������
			this.u=u;//���û����ȫ�ֱ���
			return u;
		}
		//�������
		throw new IdOrPwdException("�������");
	}
	//��д��ʼ���Եķ���
	public ExamInfo start(){
		if(isFinish){
			throw new RuntimeException("�����Խ���");
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
	//��д��û�ÿ��ⷽ��
	public QuestionInfo getQuestionInfoByIndex(int index){
		return paper.get(index-1);
	}
	//����һ���������п���ļ���
	private List<QuestionInfo>paper=new ArrayList();
	//��ÿ�����
	private void createPaper(int number){
		List<Question> question=entityContext.getQuestions();
		for(int i=0;i<number;i++){
			Question q=question.remove((int)Math.random()*number);//�������ظ�
			QuestionInfo questionInfo=new QuestionInfo();
			questionInfo.setQuestionIndex(i+1);
			questionInfo.setQuestion(q);
			paper.add(questionInfo);
			setScore();
		}
		//��һ������һ����������
		paper.get(0).setState(QuestionInfo.STATE_FIRST);
		paper.get(paper.size()-1).setState(QuestionInfo.STATE_LAST);
		
	}
	//������÷����ķ���
	private void setScore(){
		int score=entityContext.gettotalScore()/entityContext.getQuestionCount();
		for(QuestionInfo qi:paper){
			qi.setScore(score);
		}
	}
	//��дover���������ط���
	public int over(){
		isFinish=true;
		int score=0;
		//�������
		for(QuestionInfo qi:paper){
			Question q=qi.getQuestion();
			//�жϴ��Ƿ���ȷ
			if(qi.getUserAnswer().equals(q.getAnswer())){
				score+=qi.getScore();
			}
		}
		isFinish=true;
		this.score=score;
		return score;
	}
	//����һ�������ж��Ƿ�ʼ�����ˣ�Ĭ��û��ʼ
	boolean isFinish=false;
	//���ڱ������
	private int score;
	//��д��÷����ķ���
	public int getScore(){
		if(!isFinish){
			throw new RuntimeException("����δ����");
		}
		return score;
	}
	//����û���
	public void saveUserAnswer(int questionindex,List<Integer>userAnswer){
		paper.get(questionindex-1).setUserAnswer(userAnswer);
	}
	//��д��ù��򷽷�
	public String getRule(){
		return entityContext.getRule();
	}

}
