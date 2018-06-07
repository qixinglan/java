package com.tarena.dao;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface ServiceMapper {
	List<Map<String,Object>>  findService(ServicePage page);
	int findTotalRows(ServicePage page);
	void updateToOpen(int id);
	void updateToPause(int id);
	void updateToDelete(int id);
	Service findById(int id);
	void updateToPauseByAccount_id(int account_id);
	void updateToDeleteByAccount_id(int account_id);
	void insertService(Service service);
	void updateService(Service service);
}
