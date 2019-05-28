package com.dcm.bjserver.Dao;

import Model.Vehicle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VehicleMapper {

    int insert(Vehicle record);

    int insertSelective(Vehicle record);

    List<Vehicle> selectByUserId(String userId);

    Vehicle selectByVehicleId(String vehicleId);
}