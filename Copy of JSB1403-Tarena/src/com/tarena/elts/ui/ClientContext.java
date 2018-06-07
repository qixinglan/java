package com.tarena.elts.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.tarena.elts.entity.ExamInfo;
import com.tarena.elts.entity.QuestionInfo;
import com.tarena.elts.entity.User;
import com.tarena.elts.service.ExamService;
import com.tarena.elts.service.IdOrPwdException;

/**
 * ���������   �����Ļ���
 * @author asus
 *
 */
public class ClientContext {
	//��ӿ��Խ�������ã�ʹ�������ܹ��������Խ���
	private ExamFrame examFrame;
	
	public void setExamFrame(ExamFrame examFrame) {
		this.examFrame = examFrame;
	}
	//��ӻ�ӭ��������ã�ʹ�������ܲ�������
	private WelcomeFrame welcomFrame;

	public void setWelcomFrame(WelcomeFrame welcomFrame) {
		this.welcomFrame = welcomFrame;
	}
	//��Ӳ˵����������
	private MenuFrame menuFrame;

	public void setMenuFrame(MenuFrame menuFrame) {
		this.menuFrame = menuFrame;
	}
	//���ҵ��ģ��ʵ�ֵĶ������ԣ��ﵽ�������ܹ�����ҵ��ģ���з���
	private ExamService examService;

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}
	//��ӵ�½��������ã��ﵽ�������ܹ���������
	private LoginFrame loginFrame;

	public void setLoginFrame(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	} 
	//��ӵ�½�����½��ť����
	public void login(){
		
		try {
			String id=loginFrame.getUserId();
			String pwd=loginFrame.getPassWord();System.out.println("ncjnce");
			User user=examService.login(Integer.parseInt(id), pwd);
			
			//���²˵������û���Ϣ
			menuFrame.updateView(user);
			//�رյ�½����
			loginFrame.setVisible(false);
			//��ʾ�˵�����
			menuFrame.setVisible(true);
		} catch (IdOrPwdException e) {
			// TODO Auto-generated catch block
			loginFrame.showMessage("��½ʧ��"+e.getMessage());
			jump();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			loginFrame.showMessage("id����������");
			jump();
		}catch(Exception e){
			loginFrame.showMessage("��½ʧ�ܺǺ�");
			jump();
		}
	}
	//��½ʧ�ܴ�����
	public void jump(){
		int x=loginFrame.getX();
		int y=loginFrame.getY();
		for(int i=1;i<=24;i++){
			if(i%4==0){
				x+=10;
				y+=10;
			}else if(i%4==3){
				x-=10;
				y-=10;
			}else if(i%4==2){
				x+=10;
				y-=10;
			}else{
				x-=10;
				y+=10;
			}
			loginFrame.setLocation(x, y);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//��ʾ�������
	public void show(){
		welcomFrame.setVisible(true);
		Timer timer=new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				welcomFrame.setVisible(false);
				loginFrame.setVisible(true);
			}
		},2000 );
	}
	//�ر��������
	public void exit(JFrame form){
		int res=JOptionPane.showConfirmDialog(form, "ȷ���뿪��","��ȷ��",JOptionPane.YES_NO_CANCEL_OPTION);
		if(res==JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}
	//���һ���������
	private ExamInfo examInfo;
	private QuestionInfo currentQuestion;
	//��ӿ�ʼ���Է���
	public void start(){
		try{
			ExamInfo examInfo=examService.start();
			this.examInfo=examInfo;
			QuestionInfo questionInfo=examService.getQuestionInfoByIndex(1);
			currentQuestion=questionInfo;
			examFrame.upDataView(examInfo, questionInfo);
			menuFrame.setVisible(false);
			examFrame.setVisible(true);
			//��ʼ��ʱ
			timer();
		}catch(Exception e){
			JOptionPane.showMessageDialog(menuFrame, e.getMessage());
		}
	}
	//�����һ�ⷽ��
	public void next(){
		int index=currentQuestion.getQuestionIndex();
		//����û��𰸲���������
		List<Integer> userAnswer=examFrame.getUserAnswer();
		examService.saveUserAnswer(index, userAnswer);
		currentQuestion.setUserAnswer(userAnswer);
		//�����һ��
		QuestionInfo questionInfo=examService.getQuestionInfoByIndex(index+1);
		examFrame.upDataView(examInfo, questionInfo);
		currentQuestion=questionInfo;
	}
	//�����һ�ⷽ��
	public void last(){
		//����û��𰸣�����������
		int index=currentQuestion.getQuestionIndex();
		List<Integer>userAnswer=examFrame.getUserAnswer();
		examService.saveUserAnswer(index, userAnswer);
		currentQuestion.setUserAnswer(userAnswer);
		//�����һ��
		QuestionInfo questionInfo=examService.getQuestionInfoByIndex(index-1);
		examFrame.upDataView(examInfo, questionInfo);
		currentQuestion=questionInfo;
	}
	//��ӽ�����
	public void send(){
		timer.cancel();
		//����������ʾ��
		int res=JOptionPane.showConfirmDialog(examFrame, "ȷ���ύ��","",JOptionPane.YES_NO_CANCEL_OPTION);
		if(res!=JOptionPane.YES_OPTION){
			return;
		}
		//�������һ����
		int index=currentQuestion.getQuestionIndex();
		List<Integer>ans=examFrame.getUserAnswer();
		examService.saveUserAnswer(index, ans);
		currentQuestion.setUserAnswer(ans);
		//����ǵĻ�������������ʾ��
		int score=examService.over();
		JOptionPane.showMessageDialog(examFrame, "����"+score);
		//�رտ��Խ���
		examFrame.setVisible(false);
		//��ʾ�˵�����
		menuFrame.setVisible(true);
	}
	//��ӷ�����ť�ŷ���
	public void getScore(){
		try{
			int score=examService.getScore();
			JOptionPane.showMessageDialog(menuFrame,"����"+ score);
		}catch(Exception e){
			JOptionPane.showMessageDialog(menuFrame,e.getMessage());
		}
	}
	//�����ʾ���򷽷�
	public void getRule(){
		JOptionPane.showMessageDialog(menuFrame, examService.getRule()+"");
	}
	//��ӿ��Խ���رշ���
	public void exit(){
		send();
	}
	//��Ӽ�ʱ������
	private Timer timer;
	//��ʱ������
	public void timer(){
		final SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
		final Date date=new Date();
		final long end=examInfo.getTimeLimit()*60*1000+System.currentTimeMillis();
		timer=new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				long now =System.currentTimeMillis();//��õ�ǰʱ��
				long lasttime=end-now;
				if(lasttime<0){
					send();
					return;
				}
				date.setTime(end-now);
				examFrame.updateTime(format.format(date));
			}
		}, 0, 1000);
	}
	
}







