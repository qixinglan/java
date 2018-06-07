package day04;
/**
 * 线程的优先级
 * @author asus
 *
 */
public class PriorityDemo {
	public static void main(String[] args) {
			// TODO Auto-generated method stub
			Thread max=new Thread(new Runnable(){
				public void run(){
					for(int i=0;i<100;i++){
						System.out.println("max");
					}
				}
			});
			Thread normal=new Thread(new Runnable(){
				public void run(){
					for(int i=0;i<100;i++){
						System.out.println("normal");
					}
				}
			});
			Thread min=new Thread(new Runnable(){
				public void run(){
					for(int i=0;i<100;i++){
						System.out.println("min");
					}
				}
			});
			max.setPriority(Thread.MAX_PRIORITY);
			normal.setPriority(Thread.NORM_PRIORITY);
			min.setPriority(Thread.MIN_PRIORITY);
			max.start();
			normal.start();
			min.start();
			
			//并不能绝对控制
		
	}
}
