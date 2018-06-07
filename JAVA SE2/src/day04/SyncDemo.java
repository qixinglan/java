package day04;
/**
 * 线程安全问题
 * 出现的情形
 * 当多线程访问同一段数据时，就会引发线程安全问题
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
	public synchronized int getBean(){//加同步锁，该方法就叫同步方法
		if(bean==0){
			throw new RuntimeException("没豆子了!");
		}
		Thread.yield();//目的
		return bean--;
		
	}
	//内部类
	class Person extends Thread{
		public void run(){
			while(true){
				int bean=getBean();//从桌子上取一个豆子
				/**
				 * String getName()
				 * Thread提供的方法
				 * 用于获取	当前线程的名字,这个名字是系统自动分配的
				 * 当然也可 自己定义，一般不
				 */
				System.out.println(getName()+":"+bean);
				Thread.yield();
			}
		}
	}
}