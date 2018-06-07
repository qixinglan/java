package day04;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Test02 {
	public static void main(String[] args) {
		Thread t1=new Thread(){
			
			public void run(){
				System.out.println("最高优先级");
			}
		};
		Thread t2=new Thread(){
			public void run(){
				System.out.println("中等优先级");
			}
		};
		Thread t3=new Thread(){
			public void run(){
				System.out.println("最低优先级");
			}
		};
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.NORM_PRIORITY);
		t3.setPriority(Thread.MIN_PRIORITY);
	
		t1.start();
		t2.start();
		t3.start();
	}
}
