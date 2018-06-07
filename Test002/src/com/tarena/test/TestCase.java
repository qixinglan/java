package com.tarena.test;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.tarena.dao.WorkerMapper;
import com.tarena.entity.Worker;

public class TestCase {
	@Test
	public void f1() throws IOException, SQLException{
		String conf="SqlMapConfig.xml";
		Reader reader=Resources.getResourceAsReader(conf);
		SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
		SqlSessionFactory factory=builder.build(reader);
		SqlSession session=factory.openSession();
//		Connection con=session.getConnection();
//		System.out.println(con);
//		con.close();
		WorkerMapper workerMapper=session.getMapper(WorkerMapper.class);
		List<Worker> list1=workerMapper.findAll();
		for(Worker e:list1){
			System.out.println(e);
		}
		workerMapper.deleteWorker(new Worker("Tom1", "123", "12345"));
		workerMapper.deleteWorker(new Worker("Tom2", "123", "12345"));
		List<Worker> list2=workerMapper.findAll();
		for(Worker e:list2){
			System.out.println(e);
		}
		workerMapper.addWorker(new Worker("qqq","qqq","qqq"));
		//Worker worker=workerMapper.findByName("qqq");
		//System.out.println(worker);
		List<Map<String,Object>> list3=workerMapper.findAllName();
		for(Map<String,Object> e:list3){
			System.out.println(e.get("NAME"));
		}
		Worker worker=new Worker("Tom","1234","1234");
		session.update("updateWorker",worker);
		session.commit();
		session.close();
	}
}
