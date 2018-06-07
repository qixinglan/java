package com.tarena.train;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 画好的扑克牌，有图有真相
 * @author asus
 *
 */
public class CardView extends JPanel{
	private Card card ;
	private ImageIcon face;
	private ImageIcon back;
	public CardView(Card card) {
		super();
		this.card = card;
		//设置面板的大小
		this.setSize(TrainFrame.CARD_WIDTH, TrainFrame.CARD_HEIGHT);
		
		String fileName="source/扑克牌图片/"+card.getSuit()+"-"+card.getRank()+".gif";
		face=new ImageIcon(fileName);//默认路径是项目
		back=new ImageIcon("source/扑克牌图片/rear.gif");
		
	}
	public void paint(Graphics g2){
		Graphics2D g=(Graphics2D)g2;
		ImageIcon temp=card.isFaced()?face:back;
		g.drawImage(temp.getImage(), 0, 0, TrainFrame.CARD_WIDTH,TrainFrame.CARD_HEIGHT,null);
		if(card.isSelected()){//画一个框
			g.setColor(Color.GREEN);
			g.setStroke(new BasicStroke(5));
			g.drawRect(0, 0, this.getWidth(), this.getHeight());
		}
	}
	public Card getCard() {
		return card;
	}
	
}
