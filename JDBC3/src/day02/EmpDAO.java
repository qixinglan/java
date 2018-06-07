package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class EmpDAO {
	private static final String UPDATE=
	"update emp set salary=? where name=?";
	private static final String INSERT=
	"insert into emp (id,name,salary) values(seq1.nextval,?,?)";
	public static void main(String[] args) {
		try{
			//update(4000,"rose");
			insert(2000,"����");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void update(int salary,String name) throws Exception{
		//�������
		Connection con=DBUtils.getConnection();
		//����sql���
		PreparedStatement stmt=con.prepareStatement(UPDATE);
		stmt.setInt(1, salary);
		stmt.setString(2, name);
		//��ȡִ�н��
		int n=stmt.executeUpdate();
		System.out.println(n+"���Ѹ���");
		//�ر�����
		DBUtils.closeConnection(con);
	}
	public static void insert(int salary,String name) throws Exception{
		Connection con=DBUtils.getConnection();
		System.out.println(con);
		PreparedStatement stmt=con.prepareStatement(INSERT);
		stmt.setInt(2,salary);
		stmt.setString(1, name);
		//��ȡִ�н��
		int n=stmt.executeUpdate();
		//�ر�����
		DBUtils.closeConnection(con);
		System.out.println(n+"���Ѳ���");
	}
}
