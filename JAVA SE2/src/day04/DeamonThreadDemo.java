package day04;
/**
 * ��̨�߳���ǰ̨�߳�
 * @author asus
 *
 */
public class DeamonThreadDemo {
	public static void main(String[] args) {
		Thread rose=new Thread(new Runnable(){
			public void run(){
				for(int i=0;i<10;i++){
					System.out.println("rose:let me go!");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				System.out.println("rose:AAAAAAAAAAaaaaaaaaaaaaa..........");
				System.out.println("��ͨ��");
			}
		});
		Thread jack=new Thread(new Runnable(){
			public void run(){
				while(true){
					System.out.println("jack:you jump! I jump!");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		rose.start();
		jack.setDaemon(true);//������startǰ�����ã�������Ч��Ĭ��false������������ǰ̨ǰ��
		jack.start();
		System.out.println("mainִ������");
	}
}
