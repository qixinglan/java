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
 * 界面控制器   上下文环境
 * @author asus
 *
 */
public class ClientContext {
	//添加考试界面的引用，使控制器能够操作考试界面
	private ExamFrame examFrame;
	
	public void setExamFrame(ExamFrame examFrame) {
		this.examFrame = examFrame;
	}
	//添加欢迎界面的引用，使控制器能操作界面
	private WelcomeFrame welcomFrame;

	public void setWelcomFrame(WelcomeFrame welcomFrame) {
		this.welcomFrame = welcomFrame;
	}
	//添加菜单界面的引用
	private MenuFrame menuFrame;

	public void setMenuFrame(MenuFrame menuFrame) {
		this.menuFrame = menuFrame;
	}
	//添加业务模型实现的对象属性，达到控制器能够调用业务模型中方法
	private ExamService examService;

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}
	//添加登陆界面的引用，达到控制器能够操作界面
	private LoginFrame loginFrame;

	public void setLoginFrame(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	} 
	//添加登陆界面登陆按钮方法
	public void login(){
		
		try {
			String id=loginFrame.getUserId();
			String pwd=loginFrame.getPassWord();System.out.println("ncjnce");
			User user=examService.login(Integer.parseInt(id), pwd);
			
			//更新菜单界面用户信息
			menuFrame.updateView(user);
			//关闭登陆界面
			loginFrame.setVisible(false);
			//显示菜单界面
			menuFrame.setVisible(true);
		} catch (IdOrPwdException e) {
			// TODO Auto-generated catch block
			loginFrame.showMessage("登陆失败"+e.getMessage());
			jump();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			loginFrame.showMessage("id必须是整数");
			jump();
		}catch(Exception e){
			loginFrame.showMessage("登陆失败呵呵");
			jump();
		}
	}
	//登陆失败窗口震动
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
	//显示软件界面
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
	//关闭软件界面
	public void exit(JFrame form){
		int res=JOptionPane.showConfirmDialog(form, "确定离开吗","请确定",JOptionPane.YES_NO_CANCEL_OPTION);
		if(res==JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}
	//添加一个题号属性
	private ExamInfo examInfo;
	private QuestionInfo currentQuestion;
	//添加开始考试方法
	public void start(){
		try{
			ExamInfo examInfo=examService.start();
			this.examInfo=examInfo;
			QuestionInfo questionInfo=examService.getQuestionInfoByIndex(1);
			currentQuestion=questionInfo;
			examFrame.upDataView(examInfo, questionInfo);
			menuFrame.setVisible(false);
			examFrame.setVisible(true);
			//开始计时
			timer();
		}catch(Exception e){
			JOptionPane.showMessageDialog(menuFrame, e.getMessage());
		}
	}
	//添加下一题方法
	public void next(){
		int index=currentQuestion.getQuestionIndex();
		//获得用户答案并保存起来
		List<Integer> userAnswer=examFrame.getUserAnswer();
		examService.saveUserAnswer(index, userAnswer);
		currentQuestion.setUserAnswer(userAnswer);
		//获得下一题
		QuestionInfo questionInfo=examService.getQuestionInfoByIndex(index+1);
		examFrame.upDataView(examInfo, questionInfo);
		currentQuestion=questionInfo;
	}
	//添加上一题方法
	public void last(){
		//获得用户答案，并保存起来
		int index=currentQuestion.getQuestionIndex();
		List<Integer>userAnswer=examFrame.getUserAnswer();
		examService.saveUserAnswer(index, userAnswer);
		currentQuestion.setUserAnswer(userAnswer);
		//获得上一题
		QuestionInfo questionInfo=examService.getQuestionInfoByIndex(index-1);
		examFrame.upDataView(examInfo, questionInfo);
		currentQuestion=questionInfo;
	}
	//添加交卷方法
	public void send(){
		timer.cancel();
		//弹出交卷提示框
		int res=JOptionPane.showConfirmDialog(examFrame, "确定提交吗？","",JOptionPane.YES_NO_CANCEL_OPTION);
		if(res!=JOptionPane.YES_OPTION){
			return;
		}
		//保存最后一个答案
		int index=currentQuestion.getQuestionIndex();
		List<Integer>ans=examFrame.getUserAnswer();
		examService.saveUserAnswer(index, ans);
		currentQuestion.setUserAnswer(ans);
		//如果是的话，弹出分数提示框
		int score=examService.over();
		JOptionPane.showMessageDialog(examFrame, "分数"+score);
		//关闭考试界面
		examFrame.setVisible(false);
		//显示菜单界面
		menuFrame.setVisible(true);
	}
	//添加分数按钮放方法
	public void getScore(){
		try{
			int score=examService.getScore();
			JOptionPane.showMessageDialog(menuFrame,"分数"+ score);
		}catch(Exception e){
			JOptionPane.showMessageDialog(menuFrame,e.getMessage());
		}
	}
	//添加显示规则方法
	public void getRule(){
		JOptionPane.showMessageDialog(menuFrame, examService.getRule()+"");
	}
	//添加考试界面关闭方法
	public void exit(){
		send();
	}
	//添加计时器属性
	private Timer timer;
	//计时器方法
	public void timer(){
		final SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
		final Date date=new Date();
		final long end=examInfo.getTimeLimit()*60*1000+System.currentTimeMillis();
		timer=new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				long now =System.currentTimeMillis();//获得当前时间
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







