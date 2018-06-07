package com.tarena.train;

import java.util.LinkedList;
import java.util.List;

/**
 * �����
 * @author asus
 *
 */
public class Player {
	private int score;
	private int id;
	private String name;
	//������
	private List<Card> cards=new LinkedList<Card>();
	public Player(){
		
	}
	public Player(String name){
		id++;
		this.name=name;
	}
	//������
	public void catchcard(Card card){
		cards.add(card);
	}
	//����
	public Card throwcard(){
		if(cards.size()<=0){
			return null;
		}
		return cards.remove(cards.size()-1);//�����һ����
	}
	//Ӯ��
	public void wincards(List<Card> listcards){
		cards.addAll(0, listcards);
	}
	//������һ����
	public Card getLastCard(){
		if(cards.size()<=0){
			return null;
		}
		return cards.get(cards.size()-1);
	}
	public List<Card> getCards() {
		return cards;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Player [name=" + name + ", score=" + score + "]";
	}
	
}
