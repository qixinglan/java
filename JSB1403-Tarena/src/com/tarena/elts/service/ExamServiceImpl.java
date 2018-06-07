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
 * ҵ���߼���ʵ����
 */
public class ExamServiceImpl implements ExamService {
	
	/**
	 * ���ݹ�����EntityContext����
	 */
	private EntityContext entityContext;
	public void setEntityContext(EntityContext entityContext) {
		this.entityContext = entityContext;
	}
	//��½�߼�
	public  User login(int id, String pwd) throws IdOrPwdException {
		//����ID������ݹ���ɵ�һ��user
		User u=entityContext.getUserById(id);
		
		//���û�������û�
		if(u==null){
			throw new IdOrPwdException("�޸��û�");
		}
		//����У��ж����룬����ɹ���ִ�С�����
		if(u.getPassword().equals(pwd)){
			//��������
			this.u=u;//���û����ȫ�ֱ���
			return u;
		}
		//���ʧ���׳�IdOrPwdExeption
		throw new IdOrPwdException("�������");
	}
	/**
	 * ��дstart����
	 * ������������createPaper��20��
	 * ����ExamInfo���󣬸�ExamInfo�������Ը�ֵ��
	 * �����Կ�Ŀ��Java���ԣ�����ʱ�䣺�����ӣ������ܷ֣�100����Ŀ������20����������½�û���
	 * ����һ��ExamInfo����
	 */
	User u;//ȫ�ֱ���
	
	public ExamInfo start(){
		if(isFinish){
			throw new RuntimeException("�����Խ���");
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
	 * �����д��getQuestionInfo��int number��
	 * ���ؿ�����ĵ�һ����
	 */
	public QuestionInfo getQuestionInfoByIndex(int index){
		return paper.get(index-1);
	}
	/*
	 * ���һ��˽�еĴ�������Ĺ��ܷ���creatPaper��int numbers��
	 * ���ݲ��������ȡentityContext��� ���⼯�ϵĿ��⣬�������ǻ�ȡ�ĸ���
	 * ������Ӧ��QuestionInfo���������ţ����������⣩
	 * ����һ������QuestionInfo�����ռ���������Ϊ��������п��⣨���ϣ�
	 * 
	 */
	
	private void  creatPaper(int numbers){
		List<Question> question=entityContext.getQuestions();
		Random ran=new Random();
		for(int i=0;i<numbers;i++){
			Question q=question.remove(ran.nextInt(numbers));///remove���ظ�
			QuestionInfo questionInfo=new QuestionInfo();
			questionInfo.setQuestionIndex(i+1);
			questionInfo.setQuestion(q);
			//questionInfo.setScore(100/numbers);
			paper.add(questionInfo);
			setScore();
			//questionInfo.setUserAnswers(userAnswers);
		}
		//��һ������һ����������
		paper.get(0).setState(QuestionInfo.STATE_FIRST);
		paper.get(paper.size()-1).setState(QuestionInfo.STATE_LAST);
	}
	/**
	 * ����һ���������п���ļ���List��QuestionInfo��paper
	 */
	private List<QuestionInfo> paper=new ArrayList();
	
	/*
	 * ���һ��˽�е�����ÿ����������ķ���setScore����
	 * ֪���Ծ���ܷ֣�ExamInfo�����ܷ֣�
	 * ֪���Ծ���Ŀ������
	 * �ܷ�/����=����
	 * �ѷ������������ÿһ������
	 */
	private void setScore(){
		
		int score=entityContext.gettotalScore()/entityContext.getQuestionCount();
		//int score=5;
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
			if(qi.getUserAnswers().equals(q.getAnswers())){//�ü��ϵ�equals������ʵ���ǱȽ�ÿ��Ԫ���Ƿ���ȣ���Ϊapi��д��equals
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
	public void saveUserAnswer(int questionIndex, List<Integer> userAnswer) {
		// TODO Auto-generated method stub
		//��һ�ֱ���paper����Ԫ�أ�ֻҪ��źʹ�������һ�£������������û���
		//�ڶ�����źͽǱ�ֻ��1
		paper.get(questionIndex-1).setUserAnswers(userAnswer);
	}
	//��д��ù��򷽷�
	public String getRule(){
		return entityContext.getRule();
	}
//	//��д��ÿ���ʱ�䷽��
//	public int getTime(){
//		return entityContext.getTimeLimit();
//	}
}
