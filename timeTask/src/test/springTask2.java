package test;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
 * spring�Դ���ʱ����spring-task
 * ע��ʽ
 */

//ͨ��ע��
@Component
public class springTask2 {
	//�����񷽷�ǰ��@Scheduled
	@Scheduled(cron="0 08 13 * * ?")//ÿ��13:08ִ��һ��   (cron="0/5 * *  * * ? ")   //ÿ5��ִ��һ��  
	public void doSpringTask(){
		System.out.println("springTask2");
	}
}
