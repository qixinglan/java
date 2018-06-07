package day04;
/**
 * ��������
 * @author asus
 *
 */
public class SyncMethodDemo {
	public static void main(String[] args) {
		final Bank bank=new Bank();
		Thread t1=new Thread(){
			public void run(){
				bank.getMoney(10000);
			}
		};
		Thread t2=new Thread(){
			public void run(){
				bank.addMoney(10000);
			}
		};
		t1.start();
		t2.start();
	}
}
/**
 * �����������ⷽ��
 * @author asus
 *
 */
class Bank{
	int money=100000;
	public synchronized void getMoney(int money){//ȡǮ
		System.out.println("׼��ȡǮ..");
			int temp=this.money-money;
			Thread.yield();
			this.money=temp;
			// TODO Auto-generated catch block
			System.out.println("ȡǮ�ɹ�"+"���Ϊ"+this.money);
	}
	public synchronized void addMoney(int money){//��Ǯ
		System.out.println("׼����Ǯ..");
		this.money=this.money+money;
		System.out.println("��Ǯ�ɹ�"+"���Ϊ"+this.money);
	}
}