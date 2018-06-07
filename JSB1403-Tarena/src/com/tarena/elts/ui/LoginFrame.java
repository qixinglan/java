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
 * ��½����
 * @author asus
 *
 */

public class LoginFrame extends JFrame {
	JButton login;
	public LoginFrame(){
		init();//��ʼ������
	}
	//��ʼ�����淽��
	private void init(){
		this.setTitle("��½ϵͳ");
		this.setSize(270, 188);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//�����⻹����ô����������д�˰ɣ���
		setLocationRelativeTo(null);
		setContentPane(createContentPane());
		this.getRootPane().setDefaultButton(login);//�ѵ�½��ΪĬ�ϰ�ť
		//�����ڹرհ�ť����¼�
		this.addWindowListener(new WindowAdapter() {//???????????????????????????????????????
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				clientContext.exit(LoginFrame.this);
			}
//			@Override
//			public void windowClosed(WindowEvent e) {
//				// TODO Auto-generated method stub
//				new JFrame("���").setVisible(true);
//			}
//		});
//		
		});
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
		JLabel message=new JLabel("��½��ʾ",JLabel.CENTER);
		//���ֲ�������ֵ����ȫ�ֱ���
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
		final JTextField idField=new JTextField();
		//���ֲ�������ֵ����ȫ�ֱ���
		this.idField=idField;
		idField.setText("1001");
		//��������
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
		JPanel p=new JPanel(new BorderLayout(6,0));//�вι�����������  �б߾� �ݱ߾�
		p.add(BorderLayout.WEST,new JLabel("���룺"));//û����
		final JPasswordField pwdField=new JPasswordField();
		//���ֲ�������ֵ����ȫ�ֱ���
		this.pwdField=pwdField;
		pwdField.setText("1234");
		//��������
		pwdField.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
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
		//����½��Ϊȫ�ֱ���
		JButton login=new JButton("��½");
		this.login=login;
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
				//���ÿ�������login���������߼�
				clientContext.login();
			}
		});
		p.add(login);
		p.add(cancel);
		return p;
	}
	private ClientContext clientContext;
	//���������set�����������ã������Խ��ж���ע�루IOC������֤��Ŀֻ��һ��clientContext
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}
	private JTextField idField;
	//��ȡ�ı�������
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
	 * �����ʾ������Ϣ�ķ���
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
