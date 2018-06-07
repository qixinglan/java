package com.tarena.entity;

import java.util.List;
import java.util.Map;

/*
 * Mapper�ӿ�������Dao�������﷨���޶�
 * ����������
 * Mapper�ӿ���������ʵ������CUUD(���ݿ����)����
 * �ӿڻ��Զ�ʵ��
 */
public interface WorkerMapper {
	void addWorker(Worker worker);
	void deleteWorker(Worker worker);
	void updateWorker(Worker Worker);
	Worker findByName(String name);
	Worker findById(int id);
	List<Worker> findAll();
	List<Worker> findByPwd(String pwd);
	List<Map<String,Object>>  findAllName();
}
