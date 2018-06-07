package com.tarena.train;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WelcomFrame extends JFrame {
	private JPanel panel;
	public WelcomFrame(){
		this.setSize(500,400);
		setLocationRelativeTo(null);
		this.setUndecorated(true);
		panel=new MyPanel();
		this.setContentPane(panel);
	}
	class MyPanel extends JPanel{
		private int width=500;
		private int height=400;
		private Image image;
		public MyPanel(){
			image=new ImageIcon("source/20.jpg").getImage();
			setSize(500,400);
		}
		public void paint(Graphics g){
			g.drawImage(image,0,0,width,height,null);
			g.setFont(new Font("宋体",Font.BOLD,30));
			g.setColor(Color.red);
			g.drawString("精品游戏", 100, 100);
		}
	}
	}




