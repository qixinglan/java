package com.tarena.elts.ui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * ª∂”≠ΩÁ√Ê…¡∆¡
 * @author asus
 *
 */
public class WelcomeFrame extends JFrame{
	public static void main(String[] args) {
		new WelcomeFrame().setVisible(true);
	}
	public WelcomeFrame(){
		init();
	}
	private void init(){
		setSize(430, 300);
		this.setLocationRelativeTo(null);
		setUndecorated(true);//…Ë÷√»•≥˝±ﬂøÚ?????
		this.setContentPane(creatContentPane());
	}
	private Container creatContentPane() {
		// TODO Auto-generated method stub
		JPanel p=new JPanel(new BorderLayout());
		p.add(BorderLayout.CENTER,new JLabel(new ImageIcon("source/welcome.png")));
		return p;
	}
}
