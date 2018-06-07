package test;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
/**
 * quatz
 * 需要继承QuartzJobBean
 */
public class quartz0  extends QuartzJobBean{
	private int timeout;
	private static int i;
	/** 
	要调度的具体任务 
	*/  
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
			System.out.println("定时任务");		
			System.out.println(timeout);
			System.out.println(i++);//i是固定的，因为每隔一段世间是运行这个类不是这个方法
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public static int getI() {
		return i;
	}
	public static void setI(int i) {
		quartz0.i = i;
	}
	
}
