package com.mucfc.dao;

import com.mucfc.entity.TestUser;

public interface TestUserDao {
    int deleteByPrimaryKey(Integer userId);

    int insert(TestUser record);

    int insertSelective(TestUser record);

    TestUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(TestUser record);

    int updateByPrimaryKey(TestUser record);
}