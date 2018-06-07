package model;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class applyServiceTest {

	@Test
	public void test() {
		applyService as=new applyService();
		try {
			as.apply("guliang",100000);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库异常");
		}catch(AccountNotExitException e){
			System.out.println("账号不存在");
		}catch(AccountLimtException e){
			System.out.println("余额不足");
		}catch(Exception e){
			System.out.println("未知异常");
			e.printStackTrace();
		}
	}

}
