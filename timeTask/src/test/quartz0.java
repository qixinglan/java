package test;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
/**
 * quatz
 * ��Ҫ�̳�QuartzJobBean
 */
public class quartz0  extends QuartzJobBean{
	private int timeout;
	private static int i;
	/** 
	Ҫ���ȵľ������� 
	*/  
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
			System.out.println("��ʱ����");		
			System.out.println(timeout);
			System.out.println(i++);//i�ǹ̶��ģ���Ϊÿ��һ����������������಻���������
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
