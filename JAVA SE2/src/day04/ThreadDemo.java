package day04;
/**
 * ���߳�
 * @author asus
 *
 */
public class ThreadDemo {
	public static void main(String[] args) {
		Thread p1=new Person();
		Thread p2=new Person2();
		//ע�������߳�Ҫ�ö��̵߳�start��������
		p1.start();
		p2.start();
	}
}
class Person extends Thread{
	//��дrun���������ж�����Ҫ�������еĴ���
	public void run(){
		for(int i=0;i<1000;i++){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("���Ǹ���ģ�����"+(i+1)+"��");
		}
	}
}
class Person2 extends Thread{
	public void run(){
		for(int i=0;i<1000;i++){
			System.out.println("������ˮ�ܵ�"+(i+1)+"��");
		}
		int i=0;
		int a=1000;
	}
}