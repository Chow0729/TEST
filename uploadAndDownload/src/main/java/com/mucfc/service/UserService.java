package com.mucfc.service;

import java.util.Map;

import com.mucfc.entity.TestUser;

public interface UserService {

    public Map<String, Object> register(TestUser user) throws Exception;
}
