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
 * 考试界面
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
		setTitle("达内科技在线测试");
		setSize(600, 380);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setContentPane(createContentPane());
		getRootPane().setDefaultButton(next);//设置默认按钮
		//添加窗口关闭事件
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
		//将剩余时间变为全局变量
		JLabel timer=new JLabel();
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
		//上一题下一题变全局属性
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
	//扩展API的多选框，有int类型的value属性，代表答案的值传给服务器
	class Option extends JCheckBox{
		int value;
		public Option(int value,String text){
			super(text);
			this.value=value;
		}
	}
	//添加upDateView（ExamInfo，QuestionInfo）方法
	public void upDataView(ExamInfo examInfo,QuestionInfo questionInfo){
		this.examInfo.setText(examInfo.toString());
		questionArea.setText(questionInfo.toString());
		questionCount.setText(questionInfo.getQuestionIndex()+"/"+examInfo.getQuessionCount()+"  题");
		
		//调用更新按钮方法
		updataButton(questionInfo);
		//更新界面里的选项
		updataOptions(questionInfo);
	}
//	//upDateView重载的方法，不需要更新examInfo信息
//	public void upDataView(QuestionInfo questionInfo){
//		questionArea.setText(questionInfo.toString());
//		questionCount.setText("第"+questionInfo.getQuestionIndex()+"题");
//		//更新界面里的选项
//		updataOptions(questionInfo);
//		//调用更新按钮方法
//		updataButton(questionInfo);
//	}
	//添加私有的updataOption方法，用于更新界面选项
	//上一题下一题都会根据用户答案设置选项
	//
	//
	private void updataOptions(QuestionInfo questionInfo){
		List<Integer>ans=questionInfo.getUserAnswers();
//		for(int i:ans){
//			options.get(i).setSelected(true);
//		}
		for(Option o:options){
			if(ans.contains(o.value)){//如果是答案，true
				o.setSelected(true);
			}else{
				o.setSelected(false);//不是答案，就false
			}
		}
	}
	//把考试信息给本类中的examInfo
	//把QuestionInfo给本类当中的questionArea
	private JTextArea questionArea;
	private JLabel examInfo;
	private JLabel questionCount;
	
	private ClientContext clientContext;
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}
	//添加Option类型list集合，供getUserAnswer遍历，在创建选项面板的时候赋值
	private List<Option> options=new ArrayList();
	//添加获得被选中多选框的value值，作为用户答案的集合
	public List<Integer> getUserAnswers(){
		List<Integer>ans=new ArrayList<Integer>();
		for(Option o:options){
			if(o.isSelected()){
				ans.add(o.value);
			}
		}
		return ans;
	}
	//更新按钮方法
	JButton next;
	JButton prev;
	private void updataButton(QuestionInfo questionInfo){
			prev.setEnabled(questionInfo.getState()!=QuestionInfo.STATE_FIRST);
			next.setEnabled(questionInfo.getState()!=QuestionInfo.STATE_LAST);
	}
	//添加更新考试时间的方法
	public void updateTime(String time){
		timer.setText(time);
	}
}
