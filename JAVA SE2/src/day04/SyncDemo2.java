package day04;
/**
 * ͬ����
 * ��Ч����Сͬ����Χ������߳��򲢷����е�Ч��
 * @author asus
 *
 */
public class SyncDemo2 {
	public static void main(String[] args) {
		final Shop shop=new Shop();
		Runnable runnable=new Runnable(){
			public void run(){
				shop.buyGoods();
			}
		};
		//���������̣߳�ͬʱʹ��buyGoods()����
		Thread t1=new Thread(runnable);
		Thread t2=new Thread(runnable);
		Thread t3=new Thread(runnable);
		t1.start();
		t2.start();
		t3.start();
	}
}
class Shop{
	/**
	 * �����·���������
	 * 1.��ѡ�·�
	 * 2.���·�
	 * 3.����
	 */
	public  void buyGoods(){
		try {
			System.out.println("��ѡ�·�");
			Thread.sleep(5000);
			/**
			 * ����Ҫ�ر�ע�⣬Ҫ��֤��Ҫͬ���Ĵ���������Ķ���
			 * ����̶߳��ܹ��������ſ��Ա�֤ͬ��
			 */
			synchronized(this){//�Բ��ִ������
				System.out.println("��ѡ����,�����·�...");
				Thread.sleep(5000);
			}
			System.out.println("�Ժ��ˣ���������");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}