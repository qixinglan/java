package com.tarena.elts.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.tarena.elts.entity.User;
/**
 * 菜单界面
 * @author asus
 *
 */
public class MenuFrame extends JFrame{
	JButton start;
	JLabel info;
	ClientContext clientContext;
	
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}
	public static void main(String[] args) {
		new MenuFrame().setVisible(true);
	}
	public MenuFrame(){
		init();//初始化界面
	}
	private void init(){
		this.setSize(600, 400);
		setTitle("达内科技在线评测");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				clientContext.exit(MenuFrame.this);
			}
		});
		setContentPane(createContentPane());
	}
	private Container createContentPane() {
		// TODO Auto-generated method stub
		JPanel p=new JPanel(new BorderLayout());
		p.add(BorderLayout.NORTH,new JLabel(new ImageIcon("source/title.png")));
		p.add(BorderLayout.CENTER,createCenterPane());
		JLabel label=new  JLabel("达内科技--版权所有, 盗版必究",JLabel.RIGHT);
		label.setFont(new Font("楷体",Font.BOLD,16));
		label.setForeground(Color.red);
		p.add(BorderLayout.SOUTH,label);
		return p;
	}
	private Component createCenterPane() {
		// TODO Auto-generated method stub
		JPanel p=new JPanel(new BorderLayout());
		//????
		JLabel info=new JLabel("欢迎测试",JLabel.CENTER);
		this.info=info;
		p.add(info,BorderLayout.NORTH);
		p.add(BorderLayout.CENTER,createBtnPane());
		return p;
	}
	private Component createBtnPane() {
		// TODO Auto-generated method stub
		JPanel p=new JPanel();
		JButton start=createImgButton("source/begin.png","开始");
		this.start=start;
		this.getRootPane().setDefaultButton(start);//设置默认按钮
		JButton score=createImgButton("source/score.png","分数");
		JButton rule=createImgButton("source/rule.png","规则");
		JButton exit=createImgButton("source/exit.png","退出");
		p.add(start);
		p.add(score);
		p.add(rule);
		p.add(exit);
		//开始按钮添加事件
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clientContext.start();
			}
		});
		//分数按钮添加事件
		score.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clientContext.getScore();
			}
		});
		//规则按钮添加事件
		rule.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clientContext.getRule();
			}
		});
		//退出按钮添加事件
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clientContext.exit(MenuFrame.this);
			}
		});
		return p;
	}
	//图片按钮封装
	private JButton createImgButton(String icondir, String msg) {
		// TODO Auto-generated method stub
		ImageIcon icon=new ImageIcon(icondir);
		JButton button=new JButton(msg,icon);
		//将文本垂直方向底部对齐
		button.setVerticalTextPosition(JButton.BOTTOM);
		//将文本水平方向居中对齐
		button.setHorizontalTextPosition(JButton.CENTER);
		return button;
	}
	//更新菜单界面
	public void updateView(User user){
		info.setText(user.toString());
	}
}
