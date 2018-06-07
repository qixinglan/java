package test03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
	List select(String sql,Object[] o) throws SQLException{
		Connection con=null;
		try {
			con=Util.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			for(int i=1;i<=o.length;i++){
				ps.setObject(i, o[i-1]);
			}
			ResultSet rs=ps.executeQuery();
			List list=toEntity(rs);
			con.commit();
			return  list;
		} catch (SQLException e) {
				con.rollback();
			e.printStackTrace();
		}finally{
			con.close();
		}
		return null;
	}
	void fix(String sql,Object[] o) throws SQLException{
		Connection con=null;
		try {
			con=Util.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			for(int i=1;i<=o.length;i++){
				ps.setObject(i, o[i-1]);
			}
			ResultSet rs=ps.executeQuery();
			con.commit();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			con.rollback();
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
		abstract List<Object>  toEntity(ResultSet rs) throws SQLException;
}
