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
			System.out.println("���ݿ��쳣");
		}catch(AccountNotExitException e){
			System.out.println("�˺Ų�����");
		}catch(AccountLimtException e){
			System.out.println("����");
		}catch(Exception e){
			System.out.println("δ֪�쳣");
			e.printStackTrace();
		}
	}

}
