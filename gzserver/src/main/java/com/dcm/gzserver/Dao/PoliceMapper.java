package com.dcm.gzserver.Dao;


import Model.Police;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PoliceMapper {

    int deleteByPrimaryKey(String policeId);

    int insert(Police record);

    int insertSelective(Police record);


    Police selectByPrimaryKey(String policeId);

    Police selectByPhone(String phone);

    int updateByPrimaryKeySelective(Police record);


    int updateByPrimaryKey(Police record);
}