package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connect.connections;

import entity.user;

public class userdao {
	
	public user findByname(String name) throws Exception{
		Connection con;
		user user1=new user();
		
			con = connections.openConnection1();
			PreparedStatement pre=con.prepareStatement("select * from users where name=?");
			pre.setString(1, name);
			ResultSet res=pre.executeQuery();
			if(res.next()){
				user1.setAge(res.getInt("age"));
				user1.setName(res.getString("name"));
				user1.setId(res.getInt("id"));
				user1.setPwd(res.getString("pwd"));
			}
			con.close();
		
		
		return user1;
	}
	public void insert(user user1) throws Exception{
		Connection con=connections.openConnection1();
		PreparedStatement pre=con.prepareStatement("insert into users values(xuhao.nextval,?,?,?)");
		pre.setString(1, user1.getName());
		pre.setString(2, user1.getPwd());
		pre.setInt(3, user1.getAge());
		pre.executeUpdate();
		con.close();
	}
	public boolean newuser(String name) throws Exception{
		Connection con = connections.openConnection1();
		PreparedStatement pre=con.prepareStatement("select * from users ");
		ResultSet res=pre.executeQuery();
		while(res.next()){
			if(name.equals(res.getString("name"))){
				return false;
			}
		}
		return true;
	}
}
