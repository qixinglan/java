package day04;
/**
 * �̰߳�ȫ����
 * ���ֵ�����
 * �����̷߳���ͬһ������ʱ���ͻ������̰߳�ȫ����
 * @author asus
 *
 */
public class SyncDemo {
	public static void main(String[] args) {
		Table table=new Table();
		Table.Person p1=table.new Person();
		Table.Person p2=table.new Person();
		
		p1.start();
		p2.start();
	}
}
class Table{
	int bean=20;
	public synchronized int getBean(){//��ͬ�������÷����ͽ�ͬ������
		if(bean==0){
			throw new RuntimeException("û������!");
		}
		Thread.yield();//Ŀ��
		return bean--;
		
	}
	//�ڲ���
	class Person extends Thread{
		public void run(){
			while(true){
				int bean=getBean();//��������ȡһ������
				/**
				 * String getName()
				 * Thread�ṩ�ķ���
				 * ���ڻ�ȡ	��ǰ�̵߳�����,���������ϵͳ�Զ������
				 * ��ȻҲ�� �Լ����壬һ�㲻
				 */
				System.out.println(getName()+":"+bean);
				Thread.yield();
			}
		}
	}
}