package com.mucfc.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mucfc.dao.TestUserDao;
import com.mucfc.entity.TestUser;
import com.mucfc.service.UserService;
import com.mucfc.utils.MailUtil;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TestUserDao userDao;
    
    
    @Override
    public Map<String, Object> register(TestUser user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        int result = userDao.insert(user);
        //发送邮件
        MailUtil.sendMail("18257130417@163.com", "aaa");
        
        
        map.put("result", result);
        if(result > 0) {
            map.put("message", "注册成功");
        } else {
            map.put("message", "注册失败");
        }
        return map;
    }

}
