package dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountDAOTest {

	@Test
	public void test() throws Exception {
		AccountDAO dao=new AccountDAO();
		System.out.println(dao.findByUsername("guliang"));
	}

}
