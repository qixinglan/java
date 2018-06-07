package day04;

public class Test {
	public static void main(String[] args) {
		Thread t1=new Thread(){
			public void run(){
				for(int i=0;i<5;i++){
					System.out.println("³Ô·¹");
				}
			}
		};
		Thread t2=new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					System.out.println("³Ô±¥ÁË");
				}
				
			}
		});
		t2.setDaemon(true);
		t2.start();
		t1.start();
	}
}
