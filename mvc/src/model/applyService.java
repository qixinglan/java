package model;

import java.sql.SQLException;
import java.util.Random;

import dao.AccountDAO;
import entity.Account;

public class applyService {
	public String apply(String username,double money) throws SQLException,AccountNotExitException,AccountLimtException,Exception{
		String number;
		AccountDAO dao=new AccountDAO();
		Account account=dao.findByUsername(username);
		System.out.println(account);
		if(account==null){
			throw new AccountNotExitException();//�Զ����쳣
		}
		if(account.getBalance()*10<money){
			throw new AccountLimtException();//�Զ����쳣
		}
		number=new Random().nextInt(10000)+"";
		System.out.println(number);
		return number;
	}
}
