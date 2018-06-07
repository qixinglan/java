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
 * 界面控制器  上下文环境
 * @author asus
 *
 */
public class ClientContext {
	 //添加欢迎界面的引用,使控制器能操作界面
	private WelcomeFrame welcomeFrame;
	public void setWelcomeFrame(WelcomeFrame welcomeFrame) {
		this.welcomeFrame = welcomeFrame;
	}
	//添加菜单界面的引用，是控制器能操作界面
	private MenuFrame menuFrame;
	public void setMenuFrame(MenuFrame menuFrame) {
		this.menuFrame = menuFrame;
	}
	//添加业务模型实现的对象属性，达到控制器能够调用业务模型中的方法
	private ExamService examService;
	public void setExamService(ExamService examService) {
		this.examService = examService;
	}
	//登陆界面的引用，达到控制器能够操作界面
	private LoginFrame loginFrame;
	public void setLoginFrame(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}

	/**
	 * 该方法是被界面的的Login调用
	 * 1.从登陆界面获取用户的ID和密码
	 * 2.调用业务模型的login方法完成登录功能处理
	 * 3.根据结果，如果成功更新界面，显示用户信息
	 * 	关闭登陆界面  打开菜单界面
	 * 4.若登录失败，在登陆界面显示失败信息
	 */
	public void login(){
		try {
		String id=loginFrame.getUserId();
		String pwd=loginFrame.getPassWord();
		User user=examService.login(Integer.parseInt(id), pwd);
		//更新菜单界面 的用户信息
		menuFrame.updateView(user);
		//关闭登陆界面
		loginFrame.setVisible(false);
		//显示菜单界面
		menuFrame.setVisible(true);
		} catch (IdOrPwdException e) {
			// TODO Auto-generated catch block
			//执行用户名密码的错误逻辑
			loginFrame.showMessage("登录失败"+e.getMessage());
			jump();
		}catch(NumberFormatException e){
			loginFrame.showMessage("ID必须是整数");
			jump();
		}catch(Exception e){
			loginFrame.showMessage("登录失败");e.printStackTrace();
			jump();
		}
	}
	//登陆失败窗口震动
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
	//设置窗口渐无效果
	
	//显示软件界面
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
	//关闭软件界面
	public void exit(JFrame from){
		int res=JOptionPane.showConfirmDialog(from,"确定离开吗？","请确认",JOptionPane.YES_NO_CANCEL_OPTION);
		if(res==JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}
	//考试界面的引用，达到控制器操作界面
	private ExamFrame examFrame;
	//添加考试界面的set方法，方便对象注入
	public void setExamFrame(ExamFrame examFrame) {
		this.examFrame = examFrame;
	}
	
	/**
	 * 定义开始考试方法start
	 * 调用ExamService的start方法，返回一个ExamInfo对象
	 * 调用ExamService的getQuestionInfo（0)获得第一个试题
	 * 更新考试界面（调用考试界面的upDataView（examInfo,questionInfo）方法
	 * 关闭菜单界面
	 * 显示考试界面
	 */
	private ExamInfo examInfo;
	//添加一个题号属性
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
			//开始计时
			timer();
//			//倒计时功能实现
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
//						//保存最后一个答案
//						int index=currentQuestion.getQuestionIndex();
//						List<Integer> ans=examFrame.getUserAnswers();
//						examService.saveUserAnswer(index,ans);
//						currentQuestion.setUserAnswers(ans);
//						//获得分数
//						int score=examService.over();
//						//提示分数框
//						JOptionPane.showMessageDialog(examFrame, "考试时间已到,您的分数为:"+score);
//						//关闭考试界面
//						examFrame.setVisible(false);
//						//显示菜单界面
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
	//添加下一题方法
	public void next(){
		int index=currentQuestion.getQuestionIndex();
		//获得用户答案，并保存起来
		List<Integer> userAnswer=examFrame.getUserAnswers();
		examService.saveUserAnswer(index,userAnswer);
		currentQuestion.setUserAnswers(userAnswer);
		//System.out.println(userAnswer);
		//获得下一题
		
		//if(index<new EntityContext(new Config("client.properties")).getQuestionCount()){
			QuestionInfo questionInfo=examService.getQuestionInfoByIndex(index+1);
			examFrame.upDataView(examInfo,questionInfo);
			currentQuestion=questionInfo;
		//}
		}
	//添加上一题方法	
	public void last(){
		//获得用户答案，并保存起来
		int index=currentQuestion.getQuestionIndex();
		List<Integer> userAnswer=examFrame.getUserAnswers();
		examService.saveUserAnswer(index,userAnswer);
		currentQuestion.setUserAnswers(userAnswer);
		//System.out.println(userAnswer);
		//获得上一题
		//if(index>1){
			QuestionInfo questionInfo=examService.getQuestionInfoByIndex(index-1);
			examFrame.upDataView(examInfo,questionInfo);
			currentQuestion=questionInfo;
		//}
		
	}
	//添加交卷方法
	public void send(){
		timer.cancel();
		//弹出交卷 是，否，取消，提示框
		int res=JOptionPane.showConfirmDialog(examFrame, "确定提交吗?", "", JOptionPane.YES_NO_CANCEL_OPTION);
		if(res!=JOptionPane.YES_OPTION){
			return;
		}
		//保存最后一个答案
		int index=currentQuestion.getQuestionIndex();
		List<Integer> ans=examFrame.getUserAnswers();
		examService.saveUserAnswer(index,ans);
		currentQuestion.setUserAnswers(ans);
		//如果是是的话，弹出分数提示框，
		int score=examService.over();
		JOptionPane.showMessageDialog(examFrame, "分数"+score);
		//关闭考试界面
		examFrame.setVisible(false);
		//显示菜单界面
		menuFrame.setVisible(true);
	}
	//添加分数按钮方法
	public void getScore(){
		try {
			int score=examService.getScore();
			JOptionPane.showMessageDialog(menuFrame, "分数："+score);
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(menuFrame, e.getMessage());
		}
	}
	//添加显示规则方法
	public void rule(){
		JOptionPane.showMessageDialog(menuFrame,examService.getRule()+"");
	}
	//添加考试界面关闭方法
	public void exit(){
		send();
	}
	//添加计时器属性
	private Timer timer;
	//计时器方法
	public void timer(){
		//获得考试总时间
		final SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
		final Date date=new Date();
		final long end=examInfo.getTimeLimit()*60*1000+System.currentTimeMillis();;
		timer=new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				long now =System.currentTimeMillis();//获得当前时间
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
