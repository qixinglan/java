package com.tarena.train;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TrainFrame extends JFrame {
		//牌的大小常量
		public static final int CARD_WIDTH=69;
		public static final int CARD_HEIGHT=94;
		//主面板
		private Container contentPane;
		//系统玩家装牌区
		private JPanel honeyPanel;
		//玩家装牌区
		private JPanel myPanel;
		//火车装牌区
		private JPanel trainPanel;
		//牌堆区
		private JPanel tokenPanel;
		//菜单
		private JMenuBar mBar;
		private JMenu mGame;
		private JMenuItem mExit;
		//扑克牌的逻辑对象，里面有四个区所有扑克牌
		private TrainGame game;
		//创建一个火车区所有扑克牌的区别，便于鼠标点击
		private List<CardView> cardViews=new ArrayList<CardView>();
		
		
		
		//鼠标点击事件????????????????????????????????????????????????????????????????????????????
		private MouseListener mouse=new MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				//确定鼠标点的是一张扑克牌
				//遍历火车区所有扑克牌，如果有张扑克牌的坐标范围包含鼠标坐标，那就是这张
				int x=e.getX();//获取鼠标点击坐标?????????????????????????????????????????????????????????????????
				int y=e.getY();
				for(int i=0;i<cardViews.size();i++){
					CardView cv=cardViews.get(i);
					if(x>cv.getX()&&x<cv.getX()+CARD_WIDTH&&y>cv.getY()&&y<cv.getY()+CARD_HEIGHT){
						if(game.getSelectedCard()!=null){
							game.getSelectedCard().setSelected(false);//设置以前的为不选中
						}
						
						cv.getCard().setSelected(true);//设为选中
						game.setSelectedCard(cv.getCard());//设置选中的牌
					}
				}
				//把这张扑克牌设为选中扑克牌
				//把这张扑克牌给selectedCard

		}
			};
		
		//构造器
		public TrainFrame(TrainGame game){
			this.game=game;
			
			//初始化控件
			init();
			updateview();
		}
		//封装初始化控件和窗口的方法
		private void init(){
			//初始化窗口
			this.setSize(800, 650);
			this.setTitle("老牛赶大车");
			this.setLocationRelativeTo(null);//居中
			
			//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭按钮可用
			
			//获得窗口主面板
			contentPane=this.getContentPane();//默认给Frame里加一个主面板??????
			contentPane.setLayout(null);//布局管理关闭
			contentPane.setBackground(Color.black);
			
			honeyPanel=new JPanel();
			honeyPanel.setSize(700,CARD_HEIGHT);
			honeyPanel.setLocation(95, 13);//相对位置
			honeyPanel.setBackground(Color.blue);
			contentPane.add(honeyPanel);
			
			myPanel=new JPanel();
			myPanel.setSize(700,CARD_HEIGHT);
			myPanel.setLocation(95, 450);
			myPanel.setBackground(Color.red);
			contentPane.add(myPanel);
			
			trainPanel=new JPanel();
			trainPanel.setSize(700,CARD_HEIGHT);
			trainPanel.setLocation(95, 255);
			trainPanel.setBackground(Color.yellow);
			trainPanel.addMouseListener(mouse);//给面板添加鼠标事件
			contentPane.add(trainPanel);
			
			tokenPanel=new JPanel();
			tokenPanel.setSize(CARD_WIDTH,600);
			tokenPanel.setLocation(10, 20);
			tokenPanel.setBackground(Color.BLACK);
			contentPane.add(tokenPanel);
			//菜单
			mBar=new JMenuBar();
			mGame=new JMenu("菜单");
			mBar.setBackground(Color.red);
			mBar.setBorder(null);
			mExit=new JMenuItem("退出");
			mExit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.exit(0);
				}
			});
			mExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			mGame.add(mExit);
			mBar.add(mGame);
			this.setJMenuBar(mBar);
			this.setUndecorated(true);//设置边框不可见
			this.setVisible(true);
		}
		//更新界面
		public void updateview(){
			honeyPanel.removeAll();
			myPanel.removeAll();
			trainPanel.removeAll();
			tokenPanel.removeAll();
			
			//获取牌堆区域要显示的牌
			List<Card> cards=game.getCards();
			//System.out.println(cards);
			tokenPanel.setLayout(null);
			for(int i=0;i<cards.size();i++){
				CardView cv=new CardView(cards.get(i));
				cv.setLocation(0, i*10);
				tokenPanel.add(cv);
				
			}
			//获取系统玩家区要显示的牌
			honeyPanel.setLayout(null);
			List<Card> honeyCards=game.getPlayers()[0].getCards();
			//System.out.println(honeyCards);
			for(int i=0;i<honeyCards.size();i++){
				CardView cv=new CardView(honeyCards.get(i));
				cv.setLocation(i*10, 0);
				honeyPanel.add(cv,0);
			}
			//获取玩家区要显示的牌
			myPanel.setLayout(null);
			List<Card> myCards=game.getPlayers()[1].getCards();
			//System.out.println(myCards);
			for(int i=0;i<myCards.size();i++){
				CardView cv=new CardView(myCards.get(i));
				cv.setLocation(i*10, 0);
				myPanel.add(cv,0);
			}
			
			//更新火车区域的牌
			cardViews.clear();
			trainPanel.setLayout(null);
			List<Card> trainCards=game.getTrain();
			//System.out.println(trainCards);
			for(int i=0;i<trainCards.size();i++){
				CardView cv=new CardView(trainCards.get(i));
				cardViews.add(cv);//给cardViews赋值
				cv.setLocation(i*20, 0);
				trainPanel.add(cv,0);
			}
			repaint();//调用系统的paint方法，使界面连贯
		}
		//消息框  提示框
		public void showMessage(String msg){
			JOptionPane.showMessageDialog(null, msg, "提示框", JOptionPane.WARNING_MESSAGE);//???????????//
		}
		//对话框 确认框
		public boolean showConfirm(String msg){
			int res=JOptionPane.showConfirmDialog(null, msg, "请确认", JOptionPane.YES_NO_OPTION);//??????????
			if(res==JOptionPane.YES_OPTION){
				return true;
			}
			return false;
		}
		
	}
		
		
		
		
		
		
