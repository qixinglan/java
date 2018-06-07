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
	//���Ӳ���
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
	//�������
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
	//ɾ������
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
	//��ѯByname����
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
	//��ѯById����
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
		//��ѯfindAll����
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
	//���²���
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
		//MAP�����ֻ��Ҫ�����������⣬��ֵ���� ������
		@Test
		public void test7() throws IOException{
			String conf="SqlMapConfig.xml";
			Reader reader=Resources.getResourceAsReader(conf);
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			SqlSessionFactory factroy=builder.build(reader);
			SqlSession session=factroy.openSession();
			WorkerMapper mapper=session.getMapper(WorkerMapper.class);
			List<Map<String,Object>> list= mapper.findAllName();
			//ע���д
			for(Map p:list){
				System.out.println(p.get("NAME"));
			}
			session.commit();
			session.close();
		}
		//ֱ��ִ��SQl���
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
			System.out.println(worker);//û�б仯����Ϊ���ݿ�workerIdû���á�������
			session.commit();
			session.close();
		}			
}
