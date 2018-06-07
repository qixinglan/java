package com.qixing.dao;

import java.util.List;

import com.qixing.entity.goods;
@MyBatisRepository
public interface goodsMapper {
	void insertGoods(goods goods);
	goods findById(long id);
	List<goods> findByPage(goodsPage goodsPage);
	int findTotalRows(goodsPage goodsPage);
}
