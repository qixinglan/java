package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.Connections;

public abstract class BaseDAO {
	//参数：1、SQl语句2、SQl语句中的参数,参数类型和数目不确定，所以用Object[]
	//返回值：list<Object>
	protected List query(String querySQL,Object[]params) throws Exception{//List默认Object,不过加了到不好
			//获取连接
			Connection con=null;
			try{
			con=Connections.openConnection();
			//创建PrepareStatement
			PreparedStatement stmt=con.prepareStatement(querySQL);
			//设置SQL语句参数
			if(params!=null){
				for(int i=1;i<=params.length;i++){
					//JDBC来自动判断具体的参数类型
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
	//不同在的子类用不同的方式把结果集中的数据转换成实体对象
	public abstract Object toEntity(ResultSet rs)throws Exception;
	
}
