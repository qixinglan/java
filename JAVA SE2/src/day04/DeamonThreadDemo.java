package day04;
/**
 * 后台线程与前台线程
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
				System.out.println("噗通！");
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
		jack.setDaemon(true);//必须在start前先设置，否则无效，默认false——————前台前程
		jack.start();
		System.out.println("main执行完了");
	}
}
