import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import vo.Student;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;


public class test {
	public static void main(String[] args) throws IOException, SQLException {
		Reader reader=Resources.getResourceAsReader("config/SqlMapConfig.xml");
		SqlMapClient client=SqlMapClientBuilder.buildSqlMapClient(reader);
//		Student stu=new Student();
//		stu.setAddress("ʯ��ׯ");
//		stu.setAge(25);
//		stu.setSex("��");
//		stu.setSid(37);
//		stu.setSname("����");
		//client.insert("saveStu",stu);
		//client.delete("delStu", 37);
		//client.update("updateStu");
//		Student stu1=new Student();
//		stu1.setAge(26);
//		stu1.setSex("��");
//		client.update("updateStuBySexAndAge",stu1);
//		
		List<Student> list=client.queryForList("findStu");
		for(Student s:list){
			System.out.println(s.getSname()+'\t'+s.getSex());
		}
		
	}
}
