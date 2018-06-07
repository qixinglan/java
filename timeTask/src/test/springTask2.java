package test;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
 * spring自带定时任务spring-task
 * 注解式
 */

//通用注解
@Component
public class springTask2 {
	//在任务方法前加@Scheduled
	@Scheduled(cron="0 08 13 * * ?")//每天13:08执行一次   (cron="0/5 * *  * * ? ")   //每5秒执行一次  
	public void doSpringTask(){
		System.out.println("springTask2");
	}
}
