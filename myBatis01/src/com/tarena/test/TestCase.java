package com.tarena.test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.tarena.entity.Worker;
import com.tarena.entity.WorkerMapper;

public class TestCase {
	//链接测试
	@Test
	public void testSqlSession() throws IOException{
		String conf="SqlMapConfig.xml";
		Reader reader=Resources.getResourceAsReader(conf);
		SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
		SqlSessionFactory factroy=builder.build(reader);
		SqlSession session=factroy.openSession();
		Connection con=session.getConnection();
		System.out.println(con);
		session.close();
	}
	//测试添加
	@Test
	public void test1() throws IOException{
		String conf="SqlMapConfig.xml";
		Reader reader=Resources.getResourceAsReader(conf);
		SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
		SqlSessionFactory factroy=builder.build(reader);
		SqlSession session=factroy.openSession();
		WorkerMapper mapper=session.getMapper(WorkerMapper.class);
		Worker worker=new Worker("Tom1", "110210326", "18231088827");
		mapper.addWorker(worker);
		System.out.println(worker);
		session.commit();
		session.close();
	}
	//删除测试
	@Test
	public void test2() throws IOException{
		String conf="SqlMapConfig.xml";
		Reader reader=Resources.getResourceAsReader(conf);
		SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
		SqlSessionFactory factroy=builder.build(reader);
		SqlSession session=factroy.openSession();
		WorkerMapper mapper=session.getMapper(WorkerMapper.class);
		Worker worker=new Worker("Tom1", "110210326", "18231088827");
		mapper.deleteWorker(worker);
		session.commit();
		session.close();
	}
	//查询Byname测试
		@Test
		public void test3() throws IOException{
			String conf="SqlMapConfig.xml";
			Reader reader=Resources.getResourceAsReader(conf);
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			SqlSessionFactory factroy=builder.build(reader);
			SqlSession session=factroy.openSession();
			WorkerMapper mapper=session.getMapper(WorkerMapper.class);
			Worker worker=mapper.findByName("Tom");
			System.out.println(worker);
			session.commit();
			session.close();
		}
	//查询ById测试
			@Test
			public void test4() throws IOException{
				String conf="SqlMapConfig.xml";
				Reader reader=Resources.getResourceAsReader(conf);
				SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
				SqlSessionFactory factroy=builder.build(reader);
				SqlSession session=factroy.openSession();
				WorkerMapper mapper=session.getMapper(WorkerMapper.class);
				Worker worker=mapper.findByName("Tom");
				System.out.println(worker.getId());
				Worker worker1=mapper.findById(worker.getId());
				System.out.println(worker1);
				session.commit();
				session.close();
			}
		//查询findAll测试
		@Test
		public void test5() throws IOException{
			String conf="SqlMapConfig.xml";
			Reader reader=Resources.getResourceAsReader(conf);
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			SqlSessionFactory factroy=builder.build(reader);
			SqlSession session=factroy.openSession();
			WorkerMapper mapper=session.getMapper(WorkerMapper.class);
			List<Worker> list=mapper.findAll();
			for(Worker e:list){
				System.out.println(e);
			}
			session.commit();
			session.close();
		}	
	//更新测试
			@Test
			public void test6() throws IOException{
				String conf="SqlMapConfig.xml";
				Reader reader=Resources.getResourceAsReader(conf);
				SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
				SqlSessionFactory factroy=builder.build(reader);
				SqlSession session=factroy.openSession();
				WorkerMapper mapper=session.getMapper(WorkerMapper.class);
				Worker worker=mapper.findByName("Tom");
				worker.setPhone("182");
				worker.setPwd("182");
				mapper.updateWorker(worker);
				session.commit();
				session.close();
			}	
		//MAP（解决只需要部分数据问题，非值对象 ）测试
		@Test
		public void test7() throws IOException{
			String conf="SqlMapConfig.xml";
			Reader reader=Resources.getResourceAsReader(conf);
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			SqlSessionFactory factroy=builder.build(reader);
			SqlSession session=factroy.openSession();
			WorkerMapper mapper=session.getMapper(WorkerMapper.class);
			List<Map<String,Object>> list= mapper.findAllName();
			//注意大写
			for(Map p:list){
				System.out.println(p.get("NAME"));
			}
			session.commit();
			session.close();
		}
		//直接执行SQl语句
		@Test
		public void test8() throws IOException{
			String conf="SqlMapConfig.xml";
			Reader reader=Resources.getResourceAsReader(conf);
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			SqlSessionFactory factroy=builder.build(reader);
			SqlSession session=factroy.openSession();
			Worker worker=new Worker("Tom2", "110210326", "18231088827");
			System.out.println(worker);
			session.insert("addWorker", worker);
			System.out.println(worker);//没有变化，因为数据库workerId没有用。。。。
			session.commit();
			session.close();
		}			
}
