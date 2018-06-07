package com.tarena.elts.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.tarena.elts.entity.EntityContext;
import com.tarena.elts.entity.ExamInfo;
import com.tarena.elts.entity.QuestionInfo;
import com.tarena.elts.util.Config;
/**
 * ���Խ���
 * @author asus
 *
 */

public class ExamFrame extends JFrame{
	JLabel timer=new JLabel();
	EntityContext entityContext=new EntityContext(new Config("client.properties"));
	public ExamFrame(){
		init();//
	}
	private void init(){
		setTitle("���ڿƼ����߲���");
		setSize(600, 380);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setContentPane(createContentPane());
		getRootPane().setDefaultButton(next);//����Ĭ�ϰ�ť
		//��Ӵ��ڹر��¼�
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				clientContext.exit();
			}
		});
	}
	private Container createContentPane() {
		// TODO Auto-generated method stub
		JPanel p=new JPanel(new BorderLayout());
		p.setBorder(new EmptyBorder(8, 8, 8, 8));
		p.add(BorderLayout.NORTH,new JLabel(new ImageIcon("source/exam_title.png")));
		p.add(BorderLayout.CENTER,createCentertPane());
		p.add(BorderLayout.SOUTH,createToolsPane());
		return p;
	}
	private Component createCentertPane() {
		// TODO Auto-generated method stub
		JPanel p=new JPanel(new BorderLayout());
		JLabel examInfo=new JLabel("������Ϣ",JLabel.CENTER);
		this.examInfo=examInfo;
		p.add(BorderLayout.NORTH,examInfo);
		p.add(BorderLayout.CENTER,createQuessPane());
		p.add(BorderLayout.SOUTH,createOptionPane());
		return p;
	}
	
	//���������ı�����
	private Component createQuessPane() {
		// TODO Auto-generated method stub
		JScrollPane p=new JScrollPane();//�������������
		p.setBorder(new TitledBorder("��Ŀ"));//������ͱ��ߵı߿�
		JTextArea questionArea=new JTextArea();//�ı�����
		this.questionArea=questionArea;
		questionArea.setLineWrap(true);//�����Զ�����
		questionArea.setEditable(false);//���ò��ܱ༭
		p.getViewport().add(questionArea);//�����Ŀ���������ı����򣿣���
		return p;
	}
	//ѡ�����
	private Component createOptionPane() {
		// TODO Auto-generated method stub
		JPanel p=new JPanel();
		Option a=new Option(0,"A");
		Option b=new Option(1,"B");
		Option c=new Option(2,"C");
		Option d=new Option(3,"D");
		//��ѡ��ϸ�ֵ
		options.add(a);
		options.add(b);
		options.add(c);
		options.add(d);
		p.add(a);
		p.add(b);
		p.add(c);
		p.add(d);
		return p;
	}
	//������Ĺ������
	private Component createToolsPane() {
		// TODO Auto-generated method stub
		JPanel p=new JPanel(new BorderLayout());
		p.setBorder(new EmptyBorder(0,10,0,10 ));
		//
		JLabel questionCount=new JLabel("���");
		this.questionCount=questionCount;
		p.add(BorderLayout.WEST,questionCount);
		p.add(BorderLayout.CENTER,creatBtnPane());
		//??
		//��ʣ��ʱ���Ϊȫ�ֱ���
		JLabel timer=new JLabel();
		this.timer=timer;
		p.add(BorderLayout.EAST,timer);
		return p;
		
	}
	//������ť��һ����һ�⽻��
	private Component creatBtnPane() {
		// TODO Auto-generated method stub
		JPanel p=new JPanel();
		JButton prev=new JButton("��һ��");
		JButton next=new JButton("��һ��");
		JButton send=new JButton("����");
		//��һ����һ���ȫ������
		this.prev=prev;
		this.next=next;
		
		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clientContext.next();
			}
		});
		prev.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clientContext.last();
			}
		});
		send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clientContext.send();
			}
		});
		p.add(prev);
		p.add(next);
		p.add(send);
		return p;
	}
	//��չAPI�Ķ�ѡ����int���͵�value���ԣ�����𰸵�ֵ����������
	class Option extends JCheckBox{
		int value;
		public Option(int value,String text){
			super(text);
			this.value=value;
		}
	}
	//���upDateView��ExamInfo��QuestionInfo������
	public void upDataView(ExamInfo examInfo,QuestionInfo questionInfo){
		this.examInfo.setText(examInfo.toString());
		questionArea.setText(questionInfo.toString());
		questionCount.setText(questionInfo.getQuestionIndex()+"/"+examInfo.getQuessionCount()+"  ��");
		
		//���ø��°�ť����
		updataButton(questionInfo);
		//���½������ѡ��
		updataOptions(questionInfo);
	}
//	//upDateView���صķ���������Ҫ����examInfo��Ϣ
//	public void upDataView(QuestionInfo questionInfo){
//		questionArea.setText(questionInfo.toString());
//		questionCount.setText("��"+questionInfo.getQuestionIndex()+"��");
//		//���½������ѡ��
//		updataOptions(questionInfo);
//		//���ø��°�ť����
//		updataButton(questionInfo);
//	}
	//���˽�е�updataOption���������ڸ��½���ѡ��
	//��һ����һ�ⶼ������û�������ѡ��
	//
	//
	private void updataOptions(QuestionInfo questionInfo){
		List<Integer>ans=questionInfo.getUserAnswers();
//		for(int i:ans){
//			options.get(i).setSelected(true);
//		}
		for(Option o:options){
			if(ans.contains(o.value)){//����Ǵ𰸣�true
				o.setSelected(true);
			}else{
				o.setSelected(false);//���Ǵ𰸣���false
			}
		}
	}
	//�ѿ�����Ϣ�������е�examInfo
	//��QuestionInfo�����൱�е�questionArea
	private JTextArea questionArea;
	private JLabel examInfo;
	private JLabel questionCount;
	
	private ClientContext clientContext;
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}
	//���Option����list���ϣ���getUserAnswer�������ڴ���ѡ������ʱ��ֵ
	private List<Option> options=new ArrayList();
	//��ӻ�ñ�ѡ�ж�ѡ���valueֵ����Ϊ�û��𰸵ļ���
	public List<Integer> getUserAnswers(){
		List<Integer>ans=new ArrayList<Integer>();
		for(Option o:options){
			if(o.isSelected()){
				ans.add(o.value);
			}
		}
		return ans;
	}
	//���°�ť����
	JButton next;
	JButton prev;
	private void updataButton(QuestionInfo questionInfo){
			prev.setEnabled(questionInfo.getState()!=QuestionInfo.STATE_FIRST);
			next.setEnabled(questionInfo.getState()!=QuestionInfo.STATE_LAST);
	}
	//��Ӹ��¿���ʱ��ķ���
	public void updateTime(String time){
		timer.setText(time);
	}
}
