package com.tarena.train.test;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestLayout {
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setSize(500,500);
		
		JPanel panel=new JPanel();
		//panel.setBackground(Color.red);
		panel.setSize(500,500);
		frame.add(panel);
		JButton bu1=new JButton("1");
		JButton bu2=new JButton("2");
		JButton bu3=new JButton("3");
		JButton bu4=new JButton("4");
		JButton bu5=new JButton("5");
//		panel.setLayout(new GridLayout(2,2));
		bu1.setLocation(100, 100);
		panel.add(bu1);
		panel.add(bu2);
		panel.add(bu3);
		panel.add(bu4);
		panel.add(bu5);
		panel.setLayout(null);//停止使用布局管理器
		
		frame.setVisible(true);
	}
}
