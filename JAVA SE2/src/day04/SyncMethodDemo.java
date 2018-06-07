package day04;
/**
 * 方法互斥
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
 * 定义两个互斥方法
 * @author asus
 *
 */
class Bank{
	int money=100000;
	public synchronized void getMoney(int money){//取钱
		System.out.println("准备取钱..");
			int temp=this.money-money;
			Thread.yield();
			this.money=temp;
			// TODO Auto-generated catch block
			System.out.println("取钱成功"+"余额为"+this.money);
	}
	public synchronized void addMoney(int money){//存钱
		System.out.println("准备存钱..");
		this.money=this.money+money;
		System.out.println("存钱成功"+"余额为"+this.money);
	}
}