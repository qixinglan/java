package com.tarena.elts.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * ��½����
 * @author asus
 *
 */

public class LoginFrame extends JFrame {
	//���������set�����������ã������Խ��ж���ע�루IOC������֤��Ŀֻ��һ��clientContext
	private ClientContext clientContext;
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}
	private JTextField idField;
	private JPasswordField pwdField;
	JLabel message;
	JButton login;
	public LoginFrame(){
		init();//��ʼ������
	}
	//��ʼ�����淽��
	private void init(){
		this.setTitle("��½ϵͳ");
		this.setSize(270, 188);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		this.getRootPane().setDefaultButton(login);//�ѵ�½��ΪĬ�ϰ�ť
		//�����ڹر�����¼�
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				clientContext.exit(LoginFrame.this);//??????????????????????????????????????????????????????????????????????
			}
		});
		setContentPane(createContentPane());
		setVisible(true);
	}
	//�����
	private Container createContentPane() {
		JPanel p=new JPanel(new BorderLayout());//boderLayout���޲ι�����
		p.setBorder(new EmptyBorder(8,15,8,15));//��������ڱ߾�(�������ң�
		p.add(new JLabel("��½ϵͳ",JLabel.CENTER),BorderLayout.NORTH);//�ὫJLabel���������north���棬ÿ������ֻ�ܷ�һ�����
		p.add(createCenterPane());//Ĭ�Ϸ����м�����
		p.add(BorderLayout.SOUTH,createBtnPane());
		return p;
	}
	//
	private Component createCenterPane() {
		JPanel p=new JPanel(new BorderLayout());
		p.add(BorderLayout.NORTH,createIdPwdPane());
		//???
		JLabel message=new JLabel("��½��ʾ",JLabel.CENTER);
		this.message=message;
		p.add(BorderLayout.CENTER,message);
		return p;
	}
	//
	private Component createIdPwdPane() {
		JPanel p=new JPanel(new GridLayout(2, 1, 0, 10));//����  ���� �м��  �м��
		p.add(createIdPane());
		p.add(createPwdPane());
		return p;
	}
	//
	private Component createIdPane() {
		JPanel p=new JPanel(new BorderLayout(6,0));
		p.add(BorderLayout.WEST,new JLabel("��ţ�"));//û����
		//???
		final JTextField idField=new JTextField();
		this.idField=idField;
		idField.setText("1001");
		//��������
		idField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				idField.setText("");
				
			}
		});
		p.add(BorderLayout.CENTER,idField);
		return p;
	}
	//
	private Component createPwdPane() {
		JPanel p=new JPanel(new BorderLayout(6,0));//�вι�����������  �б߾� �ݱ߾�
		p.add(BorderLayout.WEST,new JLabel("���룺"));//û����
		//???
		final JPasswordField pwdField=new JPasswordField();
		this.pwdField=pwdField;
		pwdField.setText("1234");
		//��������
		pwdField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pwdField.setText("");
			}
		});
		pwdField.enableInputMethods(true);//����Ϊ����ʶ�����뷨
		p.add(BorderLayout.CENTER,pwdField);
		return p;
	}
	//
	private Component createBtnPane() {
		JPanel p=new JPanel(new FlowLayout());//��ʵĬ�Ͼ���
		//????
		JButton login=new JButton("��½");
		JButton cancel=new JButton("ȡ��");
		//��ȡ����ť����¼�
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clientContext.exit(LoginFrame.this);
			}
		});
		//����½��ť����¼�
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clientContext.login();
			}
		});
		p.add(login);
		p.add(cancel);
		return p;
	}
	//��ȡ�û�����ID����
	public String getUserId(){
		return idField.getText();
	}
	//��ȡ�û�����Password����
	public String getPassWord(){
		char[] temp=pwdField.getPassword();
		return new String(temp);
	}
	//�����ʾ������Ϣ��������ʧ�ķ���
	public void showMessage(String msg){
		final Timer timer=new Timer();
		final Color c=message.getForeground();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int alpha=c.getAlpha();
				alpha=alpha==0?0:(int)(alpha*80.0/100);
				//System.out.println();
				message.setForeground(new Color(c.getRed(),c.getGreen(),c.getBlue(),alpha));
			}
		}, 1000, 50);
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				timer.cancel();
				message.setText("");
				message.setForeground(new Color(c.getRed(), c.getGreen(), c.getBlue(), 100));
			}
		}, 5000);
		message.setText(msg);
	}
}
