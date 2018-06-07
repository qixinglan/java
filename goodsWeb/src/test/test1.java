package test;

import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
public class test1 {
	@Test
	public void testDateBase(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		SqlSessionFactory ssf=ac.getBean("sqlSessionFactroy",SqlSessionFactory.class);
		SqlSession session=ssf.openSession();
		System.out.println(session.getConnection());
		session.close();
	}
}
