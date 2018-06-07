package day04;
/**
 * 将线程与要运行的任务分离
 * @author asus
 *
 */
public class RunnableDemo {
	public static void main(String[] args) {
		Person3 p1=new Person3();
		Runnable p2=new Person4();
		Thread t1=new Thread(p1);
		Thread t2=new Thread(p2);
		t1.start();
		t2.start();
		//采用匿名类创建线程两种
		Thread t3=new Thread(){
			public void run(){
				System.out.println("匿名类方式");
			}
		};
		Thread t4=new Thread(new Runnable(){
			public void run(){
				System.out.println("");
			}
		});
	}
}
/**
 * 定义一个类并实现Runnable接口，并重写run（）方法
 * run方法中定义线程中要执行的逻辑代码
 * @author asus
 *
 */
class Person3 implements Runnable{
	public void run(){
		for(int i=1;i<=1000;i++){
			System.out.println("你是谁啊？"+i+"次");
		}
		
	}
}
class Person4 implements Runnable{
	public void run(){
		for(int i=1;i<=1000;i++){
			System.out.println("我是修水管的"+i+"次");
		}
	}
}