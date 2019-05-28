package com.dcm.bjserver.Dao;

import Model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper {

    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPhone(String phone);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);


    int updateByPrimaryKey(User record);
}