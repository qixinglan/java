package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.Connections;

public abstract class BaseDAO {
	//������1��SQl���2��SQl����еĲ���,�������ͺ���Ŀ��ȷ����������Object[]
	//����ֵ��list<Object>
	protected List query(String querySQL,Object[]params) throws Exception{//ListĬ��Object,�������˵�����
			//��ȡ����
			Connection con=null;
			try{
			con=Connections.openConnection();
			//����PrepareStatement
			PreparedStatement stmt=con.prepareStatement(querySQL);
			//����SQL������
			if(params!=null){
				for(int i=1;i<=params.length;i++){
					//JDBC���Զ��жϾ���Ĳ�������
					stmt.setObject(i, params[i-1]);
				}
			}
			ResultSet rs=stmt.executeQuery();
			List list=new ArrayList();
			while(rs.next()){
				list.add(toEntity(rs));
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			throw  e;
		}finally{
			if(con!=null){
				con.close();
			}
		}
	}
	public void update(String updateSQL, Object[] params) throws Exception{
		Connection con=null;
		try{
			con=Connections.openConnection();
			PreparedStatement stmt=con.prepareStatement(updateSQL);
			for(int i=1;i<=params.length;i++){
				stmt.setObject(i, params[i-1]);
			}
			stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(con!=null){
				con.close();
			}
		}
	}
	//��ͬ�ڵ������ò�ͬ�ķ�ʽ�ѽ�����е�����ת����ʵ�����
	public abstract Object toEntity(ResultSet rs)throws Exception;
	
}
