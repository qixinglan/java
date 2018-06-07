package com.tarena.train;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * ��Ϸ���߼���
 * @author asus
 *
 */
public class TrainGame {
	//
	public static final int BASE_SCORE=10;
	//һ���˿���
	private List<Card> cards=new LinkedList<Card>();//LinkedListɾ����
	//��Ż�������
	private List<Card> train=new LinkedList<Card>();
	//��Ҫ��� 
	private Player[] players=new Player[2];
	//
	private Player win;//Ӯ����
	//
	private TrainFrame frame;
	//����һ������Ϊѡ������
	private Card selectedCard;
	public TrainGame(){
		setPlayers();
		frame=new TrainFrame(this);
		
	}
	
	// �������
	public void setPlayers(){
		players[0]=new Player("ϵͳ");
		players[1]=new Player("���1");
	}
    // �����˿��ƣ�ϴ��
	public void shuffle(){
		//�����һ�������
		players[0].getCards().clear();
		players[1].getCards().clear();
		train.clear();
		//�����˿���
		for(int i=Card.SUIT_SPANDED;i<Card.SUIT_DIAMOND;i++){
			for(int j=Card.RANK_A;j<Card.RANK_K;j++){
				cards.add(new Card(i,j));
			}
		}
		cards.add(new Card(Card.SUIT_JOKER,Card.RANK_BLACK));
		cards.add(new Card(Card.SUIT_JOKER,Card.RANK_COLOR));
		//ϴ��
		Collections.shuffle(cards);
	}
    // ����
	public void deal(){
		while(true){
			if(cards.size()<=0){
				//���ƽ���
				break;
			}
			//����ҷ���ͬʱɾ��ϴ��������
			players[0].catchcard(cards.remove(cards.size()-1));
			players[1].catchcard(cards.remove(cards.size()-1));
			//���½���
			frame.updateview();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
    // ����   ��һ���˳��ƣ�
	public Card play(Player player){
		//��ҳ�һ����
		Card card=player.throwcard();
		if(card==null){
			return null;
		}
		//���Ʒ���
		card.setFaced(true);
		//�ŵ�����
		train.add(card);
		//����������
		return card;
	}
	//�ж��Ƿ�Ӯ��
	//�ж�ϵͳӮ�Ʒ�ʽ���Ǳ����������е���
	public boolean isWin(Card card){
		win=players[0];
		if(isJoker(card)){
			
			return true;
		}
		for(int i=0;i<train.size()-1;i++){
			if(train.get(i).equals(card)){
				//����ͬ��
				//ȡ����ͬ��֮���������
				List<Card> list=train.subList(i, train.size());
				//��Ӯ���Ƹ�Ӯ��
				win=players[0];//ϵͳ���
				reap(list);
				//�ѻ��������ߵ���ɾ��
				//train.removeAll(list);
				list.clear();//sublist���ԣ�ǳ�㸴�ƣ�Ҳ�ܴﵽ��Ŀ��
				frame.updateview();
				return true;
			}
		}
		return false;
	}
	//�ж����Ӯ�Ʒ�ʽ���Ǵӻ�������һ��ѡ�����ƣ���ѡ�����ƺͳ��������
	public boolean isWin(Card card,Card selecteCard){
		win=players[1];
		if(isJoker(card)){
			
			return true;
		}
		if(card.equals(selecteCard)){
			//����ͬ��
			//ȡ����ͬ��֮���������
			List<Card> list=train.subList(train.indexOf(selecteCard), train.size());
			//��Ӯ���Ƹ�Ӯ��
			win=players[1];//ϵͳ���
			reap(list);
			//�ѻ��������ߵ���ɾ��
			//train.removeAll(list);
			list.clear();//sublist���ԣ�ǳ�㸴�ƣ�Ҳ�ܴﵽ��Ŀ��
			frame.updateview();
			return true;
		}
		return false;
	}
	//���Ƹ�Ӯ���߼��ϵķ���
	public void reap(List<Card> list){
		win.setScore(win.getScore()+(BASE_SCORE)*list.size());//����
		//�����ٿ���
		for(Card c:list){
			c.setFaced(false);
			c.setSelected(false);//��ѡ��ȥ����ѡ״̬
		}
		win.wincards(list);
	}
	//�ж��ǲ�����
	private boolean isJoker(Card card){
		if(card.getSuit()==Card.SUIT_JOKER){
			reap(train);//�ѻ������е��ƶ���Ӯ��
			train.clear();
			return true;
		}
		return false;
	}
	//��ʼ������Ϸ
	public void start(){
		setPlayers();//�������
		shuffle();//�����Ʋ�ϴ��
		deal();//����
		//��ʼ����
		int turn=0;
		while(true){
			Player player=players[turn++%players.length];
			//����
			Card card=play(player);
			if(card==null){
				//��Ϸ����
				frame.showMessage("Ӯ����"+win);
				int times=0;
				while(!frame.showConfirm("����һ����")){
					if(times++>=3){
						frame.showMessage("����ˣ�����ɣ�Ҳ���´ξ�����");
					}
					frame.showMessage("����һ�ְ�");
				}
				//���¿�ʼ��Ϸ
				restart();
				break;
			}
			if(player.equals(players[0])){
				//����
				if(isWin(card)){
					//frame.showMessage("��û�������ð�");
				}
				//���������һ����������
				if(players[1].getLastCard()!=null)
				players[1].getLastCard().setFaced(true);
			}
			else{
			if(isWin(card,selectedCard)){
				//frame.showMessage("�ܺã���������");
			}
			if(players[0].getLastCard()!=null)
				players[0].getLastCard().setFaced(true);
			}
			//���½���
			frame.updateview();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//���¿�ʼ
	public void restart(){
		start();
	}
	

	public List<Card> getCards() {
		return cards;
	}

	public List<Card> getTrain() {
		return train;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setSelectedCard(Card selectedCard) {
		this.selectedCard = selectedCard;
	}

	public Card getSelectedCard() {
		return selectedCard;
	}

	
}
