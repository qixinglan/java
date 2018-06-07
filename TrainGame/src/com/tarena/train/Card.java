package com.tarena.train;
/**
 * һ���˿��ƣ�ֻ�����߼���û����ʾ��
 * @author asus
 *
 */
public class Card {
	public static final int SUIT_SPANDED=1;//
	public static final int SUIT_HEART=2;//
	public static final int SUIT_CLUB=3;//
	public static final int SUIT_DIAMOND=4;//
	public static final int SUIT_JOKER=5;//
	public static final int RANK_A=1;
	public static final int RANK_2=2;
	public static final int RANK_3=3;
	public static final int RANK_4=4;
	public static final int RANK_5=5;
	public static final int RANK_6=6;
	public static final int RANK_7=7;
	public static final int RANK_8=8;
	public static final int RANK_9=9;
	public static final int RANK_10=10;
	public static final int RANK_J=11;
	public static final int RANK_Q=12;
	public static final int RANK_K=13;
	public static final int RANK_BLACK=14;
	public static final int RANK_COLOR=15;
	
	private int suit;//��ɫ
	private int rank;//����
	private boolean faced;//Ĭ��false����
	private boolean selected;//Ĭ��false����ѡ��
	public Card(){
		
	}
	
	public Card(int suit,int rank){
		this.suit=suit;
		this.rank=rank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rank;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (rank != other.rank)
			return false;
		return true;
	}
	String[] SUIT_NAMES={"","����","����","÷��","����",""};
	String[] RANK_NAMES={"","A","2","3","4","5","6","7","8","9","10","J","Q","K","С��","����"};
	public String toString() {
		// TODO Auto-generated method stub
		return SUIT_NAMES[suit]+RANK_NAMES[rank];
	}

	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public boolean isFaced() {
		return faced;
	}

	public void setFaced(boolean faced) {
		this.faced = faced;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
