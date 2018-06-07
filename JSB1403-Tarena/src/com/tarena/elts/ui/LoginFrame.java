package com.tarena.elts.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
/**
 * 登陆界面
 * @author asus
 *
 */

public class LoginFrame extends JFrame {
	JButton login;
	public LoginFrame(){
		init();//初始化界面
	}
	//初始化界面方法
	private void init(){
		this.setTitle("登陆系统");
		this.setSize(270, 188);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//？？这还有用么？？？不用写了吧？？
		setLocationRelativeTo(null);
		setContentPane(createContentPane());
		this.getRootPane().setDefaultButton(login);//把登陆设为默认按钮
		//给窗口关闭按钮添加事件
		this.addWindowListener(new WindowAdapter() {//???????????????????????????????????????
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				clientContext.exit(LoginFrame.this);
			}
//			@Override
//			public void windowClosed(WindowEvent e) {
//				// TODO Auto-generated method stub
//				new JFrame("你好").setVisible(true);
//			}
//		});
//		
		});
	}
	//主面板
	private Container createContentPane() {
		JPanel p=new JPanel(new BorderLayout());//boderLayout的无参构造器
		p.setBorder(new EmptyBorder(8,15,8,15));//设置面板内边距(上左下右）
		p.add(new JLabel("登陆系统",JLabel.CENTER),BorderLayout.NORTH);//会将JLabel会充满整个north界面，每个区域只能放一个组件
		p.add(createCenterPane());//默认放在中间区域
		p.add(BorderLayout.SOUTH,createBtnPane());
		return p;
	}
	//
	private Component createCenterPane() {
		JPanel p=new JPanel(new BorderLayout());
		p.add(BorderLayout.NORTH,createIdPwdPane());
		JLabel message=new JLabel("登陆提示",JLabel.CENTER);
		//将局部变量的值传给全局变量
		this.message=message;
		p.add(BorderLayout.CENTER,message);
		return p;
	}
	//
	private Component createIdPwdPane() {
		JPanel p=new JPanel(new GridLayout(2, 1, 0, 10));//行数  列数 行间距  列间距
		p.add(createIdPane());
		p.add(createPwdPane());
		return p;
	}
	//
	private Component createIdPane() {
		JPanel p=new JPanel(new BorderLayout(6,0));
		p.add(BorderLayout.WEST,new JLabel("编号："));//没居中
		final JTextField idField=new JTextField();
		//将局部变量的值传给全局变量
		this.idField=idField;
		idField.setText("1001");
		//鼠标点击清空
		idField.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				idField.setText("");
			}
		});
		p.add(BorderLayout.CENTER,idField);
		return p;
	}
	//
	private Component createPwdPane() {
		JPanel p=new JPanel(new BorderLayout(6,0));//有参构造器区域间距  行边距 纵边距
		p.add(BorderLayout.WEST,new JLabel("密码："));//没居中
		final JPasswordField pwdField=new JPasswordField();
		//将局部变量的值传给全局变量
		this.pwdField=pwdField;
		pwdField.setText("1234");
		//鼠标点击清空
		pwdField.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				pwdField.setText("");
			}
		});
		pwdField.enableInputMethods(true);//设置为可以识别输入法
		p.add(BorderLayout.CENTER,pwdField);
		return p;
	}
	//
	private Component createBtnPane() {
		JPanel p=new JPanel(new FlowLayout());//其实默认就是
		//将登陆变为全局变量
		JButton login=new JButton("登陆");
		this.login=login;
		JButton cancel=new JButton("取消");
		//给取消按钮添加事件
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clientContext.exit(LoginFrame.this);
			}
		});
		//给登陆按钮添加事件
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//调用控制器的login方法处理逻辑
				clientContext.login();
			}
		});
		p.add(login);
		p.add(cancel);
		return p;
	}
	private ClientContext clientContext;
	//给属性添加set方法供外界调用，对属性进行对象注入（IOC），保证项目只有一个clientContext
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}
	private JTextField idField;
	//获取文本框内容
	public String  getUserId() {
		return idField.getText();
	}
	private JPasswordField pwdField;
	public String getPassWord(){
		char[] temp=pwdField.getPassword();
		return new String(temp);
	}
	private JLabel message;
	/**
	 * 添加显示错误信息的方法
	 */
	public  void showMessage(String msg){
		final Timer timer=new Timer();final Color c=message.getForeground();;
		timer.schedule(new TimerTask() {
			public void run() {
				// TODO Auto-generated method stub
				int alpha=c.getAlpha();
				alpha=alpha==0?0:(int)(alpha*80.0/100);
				//System.out.println("ihcuwbvuu");
				message.setForeground(new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha));
			}
		}, 1000, 20);
		timer.schedule(new TimerTask() {
			public void run() {
				timer.cancel();
				message.setText("");
				message.setForeground(new Color(c.getRed(), c.getGreen(), c.getBlue(), 100));
			}
		},  6000);
		message.setText(msg);
	}
	
	
}
