package com.tarena.entity;

import java.util.List;
import java.util.Map;

/*
 * Mapper�ӿ�������Dao�������﷨���޶�
 * ����������
 * Mapper�ӿ���������ʵ������CUUD(���ݿ����)����
 * �ӿڻ��Զ�ʵ��
 */
@MapperBean
public interface WorkerMapper {
	List<Worker> findAll();
}
