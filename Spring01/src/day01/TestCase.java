package day01;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCase {
	public static void main(String[] args) {
		String conf="applicationContext.xml";//获取配置文件
		//Spring容器实例化,默认路径src
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		Calendar cal1=(Calendar)ac.getBean("calobj1");//创建对象,返回Object对象
		List list=ac.getBean("list",List.class);//另一种对象类型转换方法
		cal1.set(2015, 9, 1);
		System.out.println(cal1);
		System.out.println(list);
	}
	public String date(Calendar cal){
		Date date=cal.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	@Test
	public void  testCal1(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		Calendar cal1=ac.getBean("calobj1",Calendar.class);
		cal1.set(2015, 9, 2);
		System.out.println(date(cal1));
		Calendar cal2=ac.getBean("calobj1",Calendar.class);
		System.out.println(date(cal2));
	}
	@Test 
	public void tsetcal2(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		Calendar cal2=ac.getBean("calobj2",Calendar.class);
		System.out.println(cal2);
	}
	@Test
	public void testdate1(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		Date date=ac.getBean("dateobj1",Date.class);
		System.out.println(date.getTime());
	}
	@Test
	public void date1(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		Date date1=ac.getBean("date1",Date.class);
		Date date2=ac.getBean("date1",Date.class);
		Date date3=ac.getBean("date2",Date.class);
		Date date4=ac.getBean("date2",Date.class);
		System.out.println((date1==date2));
		System.out.println((date3==date4));
		System.out.println(date1==date3);
	}
	//@Test
	public void testExampleBean(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		ExampleBean examplebean1=ac.getBean("ExampleBean1",ExampleBean.class);
		ExampleBean examplebean11=ac.getBean("ExampleBean1",ExampleBean.class);
		ExampleBean examplebean2=ac.getBean("ExampleBean2",ExampleBean.class);
		ExampleBean examplebean22=ac.getBean("ExampleBean2",ExampleBean.class);
		AbstractApplicationContext c=(AbstractApplicationContext)ac;
		c.close();
	}
	@Test
	public void testzhuru(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		Food food=ac.getBean("food",Food.class);
		System.out.println(food);
	}
	@Test
	public void testCode() throws FileNotFoundException, IOException{
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		Code code1=ac.getBean("code",Code.class);
		System.out.println(code1.code);
		code1.save(new FileOutputStream("a.png"));
		Code code2=ac.getBean("code",Code.class);
		System.out.println(code2.code);
		code2.save(new FileOutputStream("b.png"));
   	}
}
