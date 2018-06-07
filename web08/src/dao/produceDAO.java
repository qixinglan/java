package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.connections;

import entity.produce;

public class produceDAO {
	public List<produce> findall() throws Exception{
		Connection con=connections.openConnection1();
		PreparedStatement pre=con.prepareStatement("select * from produce");
		ResultSet re=pre.executeQuery();
		List<produce> list=new ArrayList<produce>();
		while(re.next()){
			produce pro=new produce();
			pro.setId(re.getInt("id"));
			pro.setModel(re.getString("model"));
			pro.setImagedes(re.getString("imagedes"));
			pro.setProducedes(re.getString("producedes"));
			pro.setPrice(re.getDouble("price"));
			list.add(pro);
		}
		return list;
	}
	public produce findByid(int id) throws Exception{
		Connection con=connections.openConnection1();
		PreparedStatement pre=con.prepareStatement("select * from produce where id=?");
		pre.setInt(1, id);
		ResultSet re=pre.executeQuery();
		produce pro=new produce();
		while(re.next()){
			pro.setId(re.getInt("id"));
			pro.setModel(re.getString("model"));
			pro.setImagedes(re.getString("imagedes"));
			pro.setProducedes(re.getString("producedes"));
			pro.setPrice(re.getDouble("price"));
		}
		return pro;
	}
}
