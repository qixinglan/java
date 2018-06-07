package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJDBC {
	public static void main(String[] args) throws Exception{
		//װ��JDBC��ʵ��
//		Driver driver =new OracleDriver();
//		DriverManager.registerDriver(driver);
		Class.forName("oracle.jdbc.OracleDriver");
		//�������װ�ص��ڴ�,��̬����������䣬�����ע��
		//������������
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
		//url="jdbc:mysql://ip:3306/test";
		Connection con=DriverManager.getConnection(url,"jsd1403","jsd1403");
		
		/*url�ñ�ʾ���ݿ��������ϢIP,�˿ںţ����ݿ�����,��ͬ�ĳ����ض���url��ʽ���ʶ��
		�����ͬʱע���˶�����ݿ⳧����Ϣ��DriverManager����ݲ�ͬ��ʶѡȡ��ͬ��ȥ����Ϣ*/
		//�û���
		//����;
		//�÷������ص����ݿ⳧�̶�Connection�ӿڵ�ʵ����Ķ�����ΪDriverManage�Ѿ�ע�������ݿ⳧�̵�Driver��Ϣ��
		//System.out.println(con);
		//ִ��SQL
		//connection��createStatement()����Statement��ʵ�������
		Statement stmt=con.createStatement();
		//ֻ��ִ��DQL���,����ResultSet����������ݿ�ִ��
		//��ȡ���ݿ⴫��ص����ݣ����Ұ�������ݷ�װ��ResultSet����
		ResultSet rs=stmt.executeQuery("select ename, rownum,empno,sal from emp");
		//��ȡ���
		while(rs.next()){
			System.out.println(rs.getString("rownum")+"\t"+rs.getString("ename")+"\t"+rs.getString("empno"));//�����ַ���
		}
		//�ر�����
		con.close();
	}
}
