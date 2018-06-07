package day04;
/**
 * 多线程
 * @author asus
 *
 */
public class ThreadDemo {
	public static void main(String[] args) {
		Thread p1=new Person();
		Thread p2=new Person2();
		//注意启动线程要用多线程的start（）方法
		p1.start();
		p2.start();
	}
}
class Person extends Thread{
	//重写run方法，其中定义需要并发运行的代码
	public void run(){
		for(int i=0;i<1000;i++){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("你是干嘛的？？？"+(i+1)+"次");
		}
	}
}
class Person2 extends Thread{
	public void run(){
		for(int i=0;i<1000;i++){
			System.out.println("我是修水管的"+(i+1)+"次");
		}
		int i=0;
		int a=1000;
	}
}