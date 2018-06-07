package com.tarena.elts.ui;

import java.awt.BorderLayout;
import java.awt.JobAttributes;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.xml.crypto.Data;

import com.tarena.elts.entity.EntityContext;
import com.tarena.elts.entity.ExamInfo;
import com.tarena.elts.entity.QuestionInfo;
import com.tarena.elts.entity.User;
import com.tarena.elts.service.ExamService;
import com.tarena.elts.service.IdOrPwdException;
import com.tarena.elts.util.Config;
/**
 * ���������  �����Ļ���
 * @author asus
 *
 */
public class ClientContext {
	 //��ӻ�ӭ���������,ʹ�������ܲ�������
	private WelcomeFrame welcomeFrame;
	public void setWelcomeFrame(WelcomeFrame welcomeFrame) {
		this.welcomeFrame = welcomeFrame;
	}
	//��Ӳ˵���������ã��ǿ������ܲ�������
	private MenuFrame menuFrame;
	public void setMenuFrame(MenuFrame menuFrame) {
		this.menuFrame = menuFrame;
	}
	//���ҵ��ģ��ʵ�ֵĶ������ԣ��ﵽ�������ܹ�����ҵ��ģ���еķ���
	private ExamService examService;
	public void setExamService(ExamService examService) {
		this.examService = examService;
	}
	//��½��������ã��ﵽ�������ܹ���������
	private LoginFrame loginFrame;
	public void setLoginFrame(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}

	/**
	 * �÷����Ǳ�����ĵ�Login����
	 * 1.�ӵ�½�����ȡ�û���ID������
	 * 2.����ҵ��ģ�͵�login������ɵ�¼���ܴ���
	 * 3.���ݽ��������ɹ����½��棬��ʾ�û���Ϣ
	 * 	�رյ�½����  �򿪲˵�����
	 * 4.����¼ʧ�ܣ��ڵ�½������ʾʧ����Ϣ
	 */
	public void login(){
		try {
		String id=loginFrame.getUserId();
		String pwd=loginFrame.getPassWord();
		User user=examService.login(Integer.parseInt(id), pwd);
		//���²˵����� ���û���Ϣ
		menuFrame.updateView(user);
		//�رյ�½����
		loginFrame.setVisible(false);
		//��ʾ�˵�����
		menuFrame.setVisible(true);
		} catch (IdOrPwdException e) {
			// TODO Auto-generated catch block
			//ִ���û�������Ĵ����߼�
			loginFrame.showMessage("��¼ʧ��"+e.getMessage());
			jump();
		}catch(NumberFormatException e){
			loginFrame.showMessage("ID����������");
			jump();
		}catch(Exception e){
			loginFrame.showMessage("��¼ʧ��");e.printStackTrace();
			jump();
		}
	}
	//��½ʧ�ܴ�����
	public void jump(){
		int x=loginFrame.getX();
		int y=loginFrame.getY();
		 for (int i = 1; i <= 24; i++) {
		     if ((i % 4) == 0) {
		      x += 10;
		      y += 10;
		     }else if((i%4)==3){
		      x -= 10;
		      y -= 10;
		     }else if((i%4)==2){
		    	 x+=10;
		    	 y-=10;
		     }else{
		    	 x-=10;
		    	 y+=10;
		     }
		     loginFrame.setLocation(x, y);
		     try {
		      Thread.sleep(50);
		     } catch (InterruptedException e1) {
		      e1.printStackTrace();
		     }
		    }
	}
	//���ô��ڽ���Ч��
	
	//��ʾ�������
	public void show(){
		welcomeFrame.setVisible(true);
		Timer timer=new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				welcomeFrame.setVisible(false);
				loginFrame.setVisible(true);
			}
		}, 2000);
	}
	//�ر��������
	public void exit(JFrame from){
		int res=JOptionPane.showConfirmDialog(from,"ȷ���뿪��","��ȷ��",JOptionPane.YES_NO_CANCEL_OPTION);
		if(res==JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}
	//���Խ�������ã��ﵽ��������������
	private ExamFrame examFrame;
	//��ӿ��Խ����set�������������ע��
	public void setExamFrame(ExamFrame examFrame) {
		this.examFrame = examFrame;
	}
	
	/**
	 * ���忪ʼ���Է���start
	 * ����ExamService��start����������һ��ExamInfo����
	 * ����ExamService��getQuestionInfo��0)��õ�һ������
	 * ���¿��Խ��棨���ÿ��Խ����upDataView��examInfo,questionInfo������
	 * �رղ˵�����
	 * ��ʾ���Խ���
	 */
	private ExamInfo examInfo;
	//���һ���������
	private QuestionInfo currentQuestion;;
	public  void start(){
		try {
			ExamInfo examInfo=examService.start();
			this.examInfo=examInfo;
			QuestionInfo questionInfo=examService.getQuestionInfoByIndex(1);
			currentQuestion=questionInfo;
			examFrame.upDataView(examInfo,questionInfo);
			menuFrame.setVisible(false);
			examFrame.setVisible(true);
			//��ʼ��ʱ
			timer();
//			//����ʱ����ʵ��
//			Timer timer=new Timer();
//			final Date date=new Date();
//			date.setTime(examService.getTime()*60*1000);
//			String time="mm:ss";
//			final SimpleDateFormat sdf=new SimpleDateFormat(time);
//			
//			timer.schedule(new TimerTask() {
//				public void run() {
//					// TODO Auto-generated method stub
//					String str=sdf.format(date);
//					
//					date.setTime((date.getTime())-1000);
//					
//					System.out.println(str);
//					examFrame.timer.setText(str);
//					System.out.println(date.getTime());
//					while(date.getTime()==0){
//						//�������һ����
//						int index=currentQuestion.getQuestionIndex();
//						List<Integer> ans=examFrame.getUserAnswers();
//						examService.saveUserAnswer(index,ans);
//						currentQuestion.setUserAnswers(ans);
//						//��÷���
//						int score=examService.over();
//						//��ʾ������
//						JOptionPane.showMessageDialog(examFrame, "����ʱ���ѵ�,���ķ���Ϊ:"+score);
//						//�رտ��Խ���
//						examFrame.setVisible(false);
//						//��ʾ�˵�����
//						menuFrame.setVisible(true);
//						break;
//					}	
//				}
//			} ,1000, 1000);
//		
			
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(menuFrame, e.getMessage());
		}
	}
	//�����һ�ⷽ��
	public void next(){
		int index=currentQuestion.getQuestionIndex();
		//����û��𰸣�����������
		List<Integer> userAnswer=examFrame.getUserAnswers();
		examService.saveUserAnswer(index,userAnswer);
		currentQuestion.setUserAnswers(userAnswer);
		//System.out.println(userAnswer);
		//�����һ��
		
		//if(index<new EntityContext(new Config("client.properties")).getQuestionCount()){
			QuestionInfo questionInfo=examService.getQuestionInfoByIndex(index+1);
			examFrame.upDataView(examInfo,questionInfo);
			currentQuestion=questionInfo;
		//}
		}
	//�����һ�ⷽ��	
	public void last(){
		//����û��𰸣�����������
		int index=currentQuestion.getQuestionIndex();
		List<Integer> userAnswer=examFrame.getUserAnswers();
		examService.saveUserAnswer(index,userAnswer);
		currentQuestion.setUserAnswers(userAnswer);
		//System.out.println(userAnswer);
		//�����һ��
		//if(index>1){
			QuestionInfo questionInfo=examService.getQuestionInfoByIndex(index-1);
			examFrame.upDataView(examInfo,questionInfo);
			currentQuestion=questionInfo;
		//}
		
	}
	//��ӽ�����
	public void send(){
		timer.cancel();
		//�������� �ǣ���ȡ������ʾ��
		int res=JOptionPane.showConfirmDialog(examFrame, "ȷ���ύ��?", "", JOptionPane.YES_NO_CANCEL_OPTION);
		if(res!=JOptionPane.YES_OPTION){
			return;
		}
		//�������һ����
		int index=currentQuestion.getQuestionIndex();
		List<Integer> ans=examFrame.getUserAnswers();
		examService.saveUserAnswer(index,ans);
		currentQuestion.setUserAnswers(ans);
		//������ǵĻ�������������ʾ��
		int score=examService.over();
		JOptionPane.showMessageDialog(examFrame, "����"+score);
		//�رտ��Խ���
		examFrame.setVisible(false);
		//��ʾ�˵�����
		menuFrame.setVisible(true);
	}
	//��ӷ�����ť����
	public void getScore(){
		try {
			int score=examService.getScore();
			JOptionPane.showMessageDialog(menuFrame, "������"+score);
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(menuFrame, e.getMessage());
		}
	}
	//�����ʾ���򷽷�
	public void rule(){
		JOptionPane.showMessageDialog(menuFrame,examService.getRule()+"");
	}
	//��ӿ��Խ���رշ���
	public void exit(){
		send();
	}
	//��Ӽ�ʱ������
	private Timer timer;
	//��ʱ������
	public void timer(){
		//��ÿ�����ʱ��
		final SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
		final Date date=new Date();
		final long end=examInfo.getTimeLimit()*60*1000+System.currentTimeMillis();;
		timer=new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				long now =System.currentTimeMillis();//��õ�ǰʱ��
				long lasttime=end-now;
				if(lasttime<0){
					send();
					return;
				}
				
				date.setTime(end-now);
				String str=format.format(date);
				examFrame.updateTime(str);
			}
		}, 0, 1000);//
	}
	
}
