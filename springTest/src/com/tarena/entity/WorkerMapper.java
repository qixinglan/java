package com.tarena.entity;

import java.util.List;
import java.util.Map;

/*
 * Mapper接口类似于Dao，但是语法有限定
 * 不能有重载
 * Mapper接口用来定义实体对象的CUUD(数据库操作)操作
 * 接口会自动实现
 */
@MapperBean
public interface WorkerMapper {
	List<Worker> findAll();
}
