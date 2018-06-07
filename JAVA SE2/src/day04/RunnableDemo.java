package day04;
/**
 * ���߳���Ҫ���е��������
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
		//���������ഴ���߳�����
		Thread t3=new Thread(){
			public void run(){
				System.out.println("�����෽ʽ");
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
 * ����һ���ಢʵ��Runnable�ӿڣ�����дrun��������
 * run�����ж����߳���Ҫִ�е��߼�����
 * @author asus
 *
 */
class Person3 implements Runnable{
	public void run(){
		for(int i=1;i<=1000;i++){
			System.out.println("����˭����"+i+"��");
		}
		
	}
}
class Person4 implements Runnable{
	public void run(){
		for(int i=1;i<=1000;i++){
			System.out.println("������ˮ�ܵ�"+i+"��");
		}
	}
}