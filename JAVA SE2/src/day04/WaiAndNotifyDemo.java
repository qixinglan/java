package day04;


/**
 * wait��������   notify��������
 * @author asus
 *
 */
public class WaiAndNotifyDemo {
	//ͼƬ�Ƿ��������
	public static boolean finish=false;
	public static void main(String[] args){
		/**
		 * �����̲߳�������
		 * һ���߳���������ͼƬ
		 * ��һ���߳�������ʾͼƬ
		 * ����ͳ���һ�����⣬��ʾͼƬ���߳�Ӧ���ȴ�
		 * ����ͼƬ�Ľ��̽�ͼƬ���غ��ٽ�����ʾ
		 */
	
		//����ͼƬ����
		final Thread downLoadThread=new Thread(new Runnable(){
			public void run(){
				System.out.println("��������ͼƬ...");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("ͼƬ�������");
				finish=true;
				/**
				 * ֪ͨ��ǰ�����ϵȴ����̻߳ص�runnable״̬
				 * �����this����downLoadThread
				 * �������displayThread�����ڵ�ǰ�����ϵȴ���
				 * ���Ե���this notify���������ὫdisplayThread
				 * ����ȴ�������ʹ����Լ�������
				 */
				synchronized (this) {
					this.notify();
					//this.notifyAll();??
				}
				
			}
		});
		//չʾͼƬ����
		Thread displayThread=new Thread(new Runnable(){
			public void run(){
				System.out.println("�ȴ�����ͼƬ���߳�֪ͨ����ʾ...");
				try {
					synchronized (downLoadThread) {//Ҫ��������
						/**
						 * ��ǰ�߳����ĸ������Ͻ��еȴ�������Ҫ��ȡ�ĸ��������
						 */
						downLoadThread.wait();
					}
					//����ȴ�����״̬����sleep״̬��ͬ���ǣ����뻽�ѣ�ʹ��notify�����������ѣ����ܽ���runnable״̬
					//wait sleep ���벶���쳣
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!finish){
					throw new RuntimeException("ͼƬû��������ɣ��޷���ʾ");
				}
				System.out.println("��ʾͼƬ");
			}
		});
		downLoadThread.start();
		displayThread.start();
	}
}
