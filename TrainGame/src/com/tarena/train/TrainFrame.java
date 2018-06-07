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
		//�ƵĴ�С����
		public static final int CARD_WIDTH=69;
		public static final int CARD_HEIGHT=94;
		//�����
		private Container contentPane;
		//ϵͳ���װ����
		private JPanel honeyPanel;
		//���װ����
		private JPanel myPanel;
		//��װ����
		private JPanel trainPanel;
		//�ƶ���
		private JPanel tokenPanel;
		//�˵�
		private JMenuBar mBar;
		private JMenu mGame;
		private JMenuItem mExit;
		//�˿��Ƶ��߼������������ĸ��������˿���
		private TrainGame game;
		//����һ�����������˿��Ƶ����𣬱��������
		private List<CardView> cardViews=new ArrayList<CardView>();
		
		
		
		//������¼�????????????????????????????????????????????????????????????????????????????
		private MouseListener mouse=new MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				//ȷ���������һ���˿���
				//�������������˿��ƣ���������˿��Ƶ����귶Χ����������꣬�Ǿ�������
				int x=e.getX();//��ȡ���������?????????????????????????????????????????????????????????????????
				int y=e.getY();
				for(int i=0;i<cardViews.size();i++){
					CardView cv=cardViews.get(i);
					if(x>cv.getX()&&x<cv.getX()+CARD_WIDTH&&y>cv.getY()&&y<cv.getY()+CARD_HEIGHT){
						if(game.getSelectedCard()!=null){
							game.getSelectedCard().setSelected(false);//������ǰ��Ϊ��ѡ��
						}
						
						cv.getCard().setSelected(true);//��Ϊѡ��
						game.setSelectedCard(cv.getCard());//����ѡ�е���
					}
				}
				//�������˿�����Ϊѡ���˿���
				//�������˿��Ƹ�selectedCard

		}
			};
		
		//������
		public TrainFrame(TrainGame game){
			this.game=game;
			
			//��ʼ���ؼ�
			init();
			updateview();
		}
		//��װ��ʼ���ؼ��ʹ��ڵķ���
		private void init(){
			//��ʼ������
			this.setSize(800, 650);
			this.setTitle("��ţ�ϴ�");
			this.setLocationRelativeTo(null);//����
			
			//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رհ�ť����
			
			//��ô��������
			contentPane=this.getContentPane();//Ĭ�ϸ�Frame���һ�������??????
			contentPane.setLayout(null);//���ֹ���ر�
			contentPane.setBackground(Color.black);
			
			honeyPanel=new JPanel();
			honeyPanel.setSize(700,CARD_HEIGHT);
			honeyPanel.setLocation(95, 13);//���λ��
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
			trainPanel.addMouseListener(mouse);//������������¼�
			contentPane.add(trainPanel);
			
			tokenPanel=new JPanel();
			tokenPanel.setSize(CARD_WIDTH,600);
			tokenPanel.setLocation(10, 20);
			tokenPanel.setBackground(Color.BLACK);
			contentPane.add(tokenPanel);
			//�˵�
			mBar=new JMenuBar();
			mGame=new JMenu("�˵�");
			mBar.setBackground(Color.red);
			mBar.setBorder(null);
			mExit=new JMenuItem("�˳�");
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
			this.setUndecorated(true);//���ñ߿򲻿ɼ�
			this.setVisible(true);
		}
		//���½���
		public void updateview(){
			honeyPanel.removeAll();
			myPanel.removeAll();
			trainPanel.removeAll();
			tokenPanel.removeAll();
			
			//��ȡ�ƶ�����Ҫ��ʾ����
			List<Card> cards=game.getCards();
			//System.out.println(cards);
			tokenPanel.setLayout(null);
			for(int i=0;i<cards.size();i++){
				CardView cv=new CardView(cards.get(i));
				cv.setLocation(0, i*10);
				tokenPanel.add(cv);
				
			}
			//��ȡϵͳ�����Ҫ��ʾ����
			honeyPanel.setLayout(null);
			List<Card> honeyCards=game.getPlayers()[0].getCards();
			//System.out.println(honeyCards);
			for(int i=0;i<honeyCards.size();i++){
				CardView cv=new CardView(honeyCards.get(i));
				cv.setLocation(i*10, 0);
				honeyPanel.add(cv,0);
			}
			//��ȡ�����Ҫ��ʾ����
			myPanel.setLayout(null);
			List<Card> myCards=game.getPlayers()[1].getCards();
			//System.out.println(myCards);
			for(int i=0;i<myCards.size();i++){
				CardView cv=new CardView(myCards.get(i));
				cv.setLocation(i*10, 0);
				myPanel.add(cv,0);
			}
			
			//���»��������
			cardViews.clear();
			trainPanel.setLayout(null);
			List<Card> trainCards=game.getTrain();
			//System.out.println(trainCards);
			for(int i=0;i<trainCards.size();i++){
				CardView cv=new CardView(trainCards.get(i));
				cardViews.add(cv);//��cardViews��ֵ
				cv.setLocation(i*20, 0);
				trainPanel.add(cv,0);
			}
			repaint();//����ϵͳ��paint������ʹ��������
		}
		//��Ϣ��  ��ʾ��
		public void showMessage(String msg){
			JOptionPane.showMessageDialog(null, msg, "��ʾ��", JOptionPane.WARNING_MESSAGE);//???????????//
		}
		//�Ի��� ȷ�Ͽ�
		public boolean showConfirm(String msg){
			int res=JOptionPane.showConfirmDialog(null, msg, "��ȷ��", JOptionPane.YES_NO_OPTION);//??????????
			if(res==JOptionPane.YES_OPTION){
				return true;
			}
			return false;
		}
		
	}
		
		
		
		
		
		
