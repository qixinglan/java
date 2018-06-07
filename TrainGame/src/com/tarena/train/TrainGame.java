package com.tarena.train;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 游戏的逻辑类
 * @author asus
 *
 */
public class TrainGame {
	//
	public static final int BASE_SCORE=10;
	//一副扑克牌
	private List<Card> cards=new LinkedList<Card>();//LinkedList删除快
	//存放火车区的牌
	private List<Card> train=new LinkedList<Card>();
	//需要玩家 
	private Player[] players=new Player[2];
	//
	private Player win;//赢牌者
	//
	private TrainFrame frame;
	//定义一张牌作为选定的牌
	private Card selectedCard;
	public TrainGame(){
		setPlayers();
		frame=new TrainFrame(this);
		
	}
	
	// 设置玩家
	public void setPlayers(){
		players[0]=new Player("系统");
		players[1]=new Player("玩家1");
	}
    // 创建扑克牌，洗牌
	public void shuffle(){
		//清空玩家火车区的牌
		players[0].getCards().clear();
		players[1].getCards().clear();
		train.clear();
		//创建扑克牌
		for(int i=Card.SUIT_SPANDED;i<Card.SUIT_DIAMOND;i++){
			for(int j=Card.RANK_A;j<Card.RANK_K;j++){
				cards.add(new Card(i,j));
			}
		}
		cards.add(new Card(Card.SUIT_JOKER,Card.RANK_BLACK));
		cards.add(new Card(Card.SUIT_JOKER,Card.RANK_COLOR));
		//洗牌
		Collections.shuffle(cards);
	}
    // 发牌
	public void deal(){
		while(true){
			if(cards.size()<=0){
				//发牌结束
				break;
			}
			//给玩家发牌同时删除洗牌区的牌
			players[0].catchcard(cards.remove(cards.size()-1));
			players[1].catchcard(cards.remove(cards.size()-1));
			//更新界面
			frame.updateview();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
    // 出牌   （一个人出牌）
	public Card play(Player player){
		//玩家出一张牌
		Card card=player.throwcard();
		if(card==null){
			return null;
		}
		//将牌翻开
		card.setFaced(true);
		//放到火车区
		train.add(card);
		//返回这张牌
		return card;
	}
	//判断是否赢牌
	//判断系统赢牌方式，是遍历火车区所有的牌
	public boolean isWin(Card card){
		win=players[0];
		if(isJoker(card)){
			
			return true;
		}
		for(int i=0;i<train.size()-1;i++){
			if(train.get(i).equals(card)){
				//有相同的
				//取出相同牌之间的所有牌
				List<Card> list=train.subList(i, train.size());
				//把赢得牌给赢者
				win=players[0];//系统玩家
				reap(list);
				//把火车区被拿走的牌删掉
				//train.removeAll(list);
				list.clear();//sublist特性，浅层复制，也能达到该目的
				frame.updateview();
				return true;
			}
		}
		return false;
	}
	//判断玩家赢牌方式，是从火车区给定一张选定的牌，让选定的牌和出的牌相比
	public boolean isWin(Card card,Card selecteCard){
		win=players[1];
		if(isJoker(card)){
			
			return true;
		}
		if(card.equals(selecteCard)){
			//有相同的
			//取出相同牌之间的所有牌
			List<Card> list=train.subList(train.indexOf(selecteCard), train.size());
			//把赢得牌给赢者
			win=players[1];//系统玩家
			reap(list);
			//把火车区被拿走的牌删掉
			//train.removeAll(list);
			list.clear();//sublist特性，浅层复制，也能达到该目的
			frame.updateview();
			return true;
		}
		return false;
	}
	//把牌给赢牌者集合的方法
	public void reap(List<Card> list){
		win.setScore(win.getScore()+(BASE_SCORE)*list.size());//分数
		//把牌再扣上
		for(Card c:list){
			c.setFaced(false);
			c.setSelected(false);//被选的去除被选状态
		}
		win.wincards(list);
	}
	//判断是不是王
	private boolean isJoker(Card card){
		if(card.getSuit()==Card.SUIT_JOKER){
			reap(train);//把火车区所有的牌都给赢着
			train.clear();
			return true;
		}
		return false;
	}
	//开始进行游戏
	public void start(){
		setPlayers();//创建玩家
		shuffle();//创建牌并洗牌
		deal();//发牌
		//开始玩牌
		int turn=0;
		while(true){
			Player player=players[turn++%players.length];
			//出牌
			Card card=play(player);
			if(card==null){
				//游戏结束
				frame.showMessage("赢家是"+win);
				int times=0;
				while(!frame.showConfirm("重玩一局吗")){
					if(times++>=3){
						frame.showMessage("别点了，就玩吧，也许下次就行了");
					}
					frame.showMessage("再玩一局吧");
				}
				//重新开始游戏
				restart();
				break;
			}
			if(player.equals(players[0])){
				//判牌
				if(isWin(card)){
					//frame.showMessage("你没我运气好吧");
				}
				//把玩家最后的一张牌亮出来
				if(players[1].getLastCard()!=null)
				players[1].getLastCard().setFaced(true);
			}
			else{
			if(isWin(card,selectedCard)){
				//frame.showMessage("很好，继续加油");
			}
			if(players[0].getLastCard()!=null)
				players[0].getLastCard().setFaced(true);
			}
			//更新界面
			frame.updateview();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//重新开始
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
