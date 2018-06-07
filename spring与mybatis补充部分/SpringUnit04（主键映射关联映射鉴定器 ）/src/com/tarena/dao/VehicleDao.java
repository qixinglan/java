package com.tarena.dao;

import java.util.List;

import com.tarena.annotation.MyBatisRepository;
import com.tarena.entity.Vehicle;

@MyBatisRepository
public interface VehicleDao {

	List<Vehicle> findAll();

}
