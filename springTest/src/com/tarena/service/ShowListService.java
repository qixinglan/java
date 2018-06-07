package com.tarena.service;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.tarena.entity.Worker;
import com.tarena.entity.WorkerMapper;

@Service
public class ShowListService {
	@Resource
	WorkerMapper workerMapper;
	public List<Worker> showList(){
		return workerMapper.findAll();
	}
}
