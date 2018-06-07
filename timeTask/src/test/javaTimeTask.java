package test;
/*
 * spring利用java本身的定时任务，来实现定时任务。
 * 缺点功能只有每隔多长时间运行，不能指定什么时间执行或每天什么时间 执行。
 */
public class javaTimeTask {
	private  String name;
	public  void doTask(){
		System.out.println(name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
