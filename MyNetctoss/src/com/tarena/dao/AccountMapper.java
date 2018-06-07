package com.tarena.dao;

import java.util.List;

@MyBatisRepository
public interface AccountMapper {
	List<Account> findByPage(accountPage page);
	int findTotalRows(accountPage page);
	void updateToPause(int id);
	void updateToOpen(int id);
	void deleteAccount(int id);
	void toAddAccount(Account account);
	Account findById(int id);
	void fixAccount(Account account);
	Account findByIdcard_no(String idcard_no);
}
