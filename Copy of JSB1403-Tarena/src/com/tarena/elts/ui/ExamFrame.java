package com.tarena.elts.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
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
 * 考试界面
 * @author asus
 *
 */

public class ExamFrame extends JFrame{
	//添加Option类型list集合，供getUserAnswer遍历，在创建选项面板的时候赋值
	private List<Option> options=new ArrayList();
	private JTextArea questionArea;
	private JLabel examInfo;
	private JLabel questionCount;
	JButton next;
	JButton prev;
	JLabel timer=new JLabel();
	ClientContext clientContext;
	
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}
	EntityContext entityContext=new EntityContext(new Config("client.properties"));
	
	public static void main(String[] args) {
		new ExamFrame().setVisible(true);
	}
	public ExamFrame(){
		init();//
	}
	private void init(){
		setTitle("达内科技在线测试");
		setSize(600, 380);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.getRootPane().setDefaultButton(next);//设置默认按钮
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				clientContext.exit();
			}
		});
		setContentPane(createContentPane());
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
		JLabel examInfo=new JLabel("考生信息",JLabel.CENTER);
		this.examInfo=examInfo;
		p.add(BorderLayout.NORTH,examInfo);
		p.add(BorderLayout.CENTER,createQuessPane());
		p.add(BorderLayout.SOUTH,createOptionPane());
		return p;
	}
	
	//创建设置文本区域
	private Component createQuessPane() {
		// TODO Auto-generated method stub
		JScrollPane p=new JScrollPane();//带滚动条的面板
		p.setBorder(new TitledBorder("题目"));//带标题和标线的边框
		JTextArea questionArea=new JTextArea();//文本区域
		this.questionArea=questionArea;
		questionArea.setLineWrap(true);//设置自动换行
		questionArea.setEditable(false);//设置不能编辑
		p.getViewport().add(questionArea);//在面板的可视区添加文本区域？？？
		return p;
	}
	//选项面板
	private Component createOptionPane() {
		// TODO Auto-generated method stub
		JPanel p=new JPanel();
		Option a=new Option(0,"A");
		Option b=new Option(1,"B");
		Option c=new Option(2,"C");
		Option d=new Option(3,"D");
		//给选项集合赋值
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
	//最下面的工具面板
	private Component createToolsPane() {
		// TODO Auto-generated method stub
		JPanel p=new JPanel(new BorderLayout());
		p.setBorder(new EmptyBorder(0,10,0,10 ));
		//
		JLabel questionCount=new JLabel("题号");
		this.questionCount=questionCount;
		p.add(BorderLayout.WEST,questionCount);
		p.add(BorderLayout.CENTER,creatBtnPane());
		//??
		JLabel timer=new JLabel("剩余时间");
		this.timer=timer;
		p.add(BorderLayout.EAST,timer);
		return p;
	}
	//三个按钮上一题下一题交卷
	private Component creatBtnPane() {
		// TODO Auto-generated method stub
		JPanel p=new JPanel();
		JButton prev=new JButton("上一题");
		JButton next=new JButton("下一题");
		JButton send=new JButton("交卷");
		this.prev=prev;
		this.next=next;
		//下一题按扭添加事件
		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clientContext.next();
			}
		});
		//交卷按扭添加事件
		send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clientContext.send();
			}
		});
		//上一题按钮添加事件
		prev.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clientContext.last();
			}
		});
		p.add(prev);
		p.add(next);
		p.add(send);
		return p;
	}
	//扩展API的多选框，有int类型的value属性，代表答案的值传给服务器
	class Option extends JCheckBox{
		int value;
		public Option(int value,String text){
			super(text);
			this.value=value;
		}
	}
	//添加更新考生信息和考题信息的方法
	public void upDataView(ExamInfo examInfo,QuestionInfo questionInfo){
		this.examInfo.setText(examInfo.toString());
		questionArea.setText(questionInfo.toString());
		questionCount.setText(questionInfo.getQuestionIndex()+"/"+examInfo.getQuesstionCount()+"  题");
		//调用更新选项功能
		updataOptions(questionInfo);
		//调用更新按钮方法
		updataButton(questionInfo);
	}
	//更新选项方法
	private void updataOptions(QuestionInfo questionInfo){
		List<Integer>ans=questionInfo.getUserAnswer();
		for(Option o:options){
			if(ans.contains(o.value)){
				o.setSelected(true);
			}else{
				o.setSelected(false);
			}
		}
	}
	//更新按钮方法
	private void updataButton(QuestionInfo questionInfo){
		prev.setEnabled(questionInfo.getState()!=QuestionInfo.STATE_FIRST);
		next.setEnabled(questionInfo.getState()!=QuestionInfo.STATE_LAST);
	}
	// 添加获得被选中的valure值，作为用户答案集合
	public List<Integer> getUserAnswer(){
		List<Integer>ans=new ArrayList<Integer>();
		for(Option o:options){
			if(o.isSelected()){
				ans.add(o.value);
			}
		}
		return ans;
	}
	//获得考试时间的方法
	public void updateTime(String time){
		timer.setText(time);
	}
}





