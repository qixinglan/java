package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbutil.connections;
import entity.Account;

public class AccountDAO {
	public Account findByUsername(String username) throws Exception{
		Connection con=connections.openConnection();
		PreparedStatement pre=con.prepareStatement("select * from t_account where username=?");
		pre.setString(1, username);
		ResultSet res=pre.executeQuery();
		Account account=null;
		while(res.next()){
			account=new Account();
			account.setId(res.getInt("id"));
			account.setBalance(res.getDouble("balance"));
			account.setPwd(res.getString("pwd"));
			account.setUsername(username);
		}
		return account;
			
	}
}
