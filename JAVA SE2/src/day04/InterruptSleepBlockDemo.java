package day04;
/**
 * �ж�˯������
 * @author asus
 *
 */
public class InterruptSleepBlockDemo {
	public static void main(String[] args) {
		//��������˯����������
		final Thread lin=new Thread(new Runnable(){
			public void run(){
				System.out.println("�֣��������ݣ�˯��ȥ��");
				try {
					Thread.sleep(1000000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("�֣������أ������أ������أ����������ˣ�");
				}
			}
		});
		// �ƺ��߳������ж�������˯������
		Thread huang=new Thread(new Runnable(){
			public void run(){
				System.out.println("��ʼ��ǽ!!");
				for(int i=0;i<5;i++){
					System.out.println("�ƣ�80");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("�۵�");
				System.out.println("�ƣ��㶨��");
				//�ж�����������������
				lin.interrupt();
				/**
				 * һ�������ľֲ��ڲ�������Ҫ���������ֲ��������ñ���������final
				 */
			}
		});
		
		huang.start();lin.start();
	}
}
