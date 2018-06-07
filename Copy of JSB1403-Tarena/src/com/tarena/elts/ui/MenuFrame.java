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
 * �˵�����
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
		init();//��ʼ������
	}
	private void init(){
		this.setSize(600, 400);
		setTitle("���ڿƼ���������");
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
		JLabel label=new  JLabel("���ڿƼ�--��Ȩ����, ����ؾ�",JLabel.RIGHT);
		label.setFont(new Font("����",Font.BOLD,16));
		label.setForeground(Color.red);
		p.add(BorderLayout.SOUTH,label);
		return p;
	}
	private Component createCenterPane() {
		// TODO Auto-generated method stub
		JPanel p=new JPanel(new BorderLayout());
		//????
		JLabel info=new JLabel("��ӭ����",JLabel.CENTER);
		this.info=info;
		p.add(info,BorderLayout.NORTH);
		p.add(BorderLayout.CENTER,createBtnPane());
		return p;
	}
	private Component createBtnPane() {
		// TODO Auto-generated method stub
		JPanel p=new JPanel();
		JButton start=createImgButton("source/begin.png","��ʼ");
		this.start=start;
		this.getRootPane().setDefaultButton(start);//����Ĭ�ϰ�ť
		JButton score=createImgButton("source/score.png","����");
		JButton rule=createImgButton("source/rule.png","����");
		JButton exit=createImgButton("source/exit.png","�˳�");
		p.add(start);
		p.add(score);
		p.add(rule);
		p.add(exit);
		//��ʼ��ť����¼�
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clientContext.start();
			}
		});
		//������ť����¼�
		score.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clientContext.getScore();
			}
		});
		//����ť����¼�
		rule.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clientContext.getRule();
			}
		});
		//�˳���ť����¼�
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clientContext.exit(MenuFrame.this);
			}
		});
		return p;
	}
	//ͼƬ��ť��װ
	private JButton createImgButton(String icondir, String msg) {
		// TODO Auto-generated method stub
		ImageIcon icon=new ImageIcon(icondir);
		JButton button=new JButton(msg,icon);
		//���ı���ֱ����ײ�����
		button.setVerticalTextPosition(JButton.BOTTOM);
		//���ı�ˮƽ������ж���
		button.setHorizontalTextPosition(JButton.CENTER);
		return button;
	}
	//���²˵�����
	public void updateView(User user){
		info.setText(user.toString());
	}
}
