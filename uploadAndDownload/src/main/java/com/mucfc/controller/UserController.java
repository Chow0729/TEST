package com.mucfc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mucfc.entity.TestUser;
import com.mucfc.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @RequestMapping("/tologin")
    public String toLogin() {
        return "user/register";
    }
    
    @RequestMapping("register")
    @ResponseBody
    public Map<String, Object> register(TestUser user, HttpServletRequest request) {
        Map<String, Object> map = null;
        try {
            map = userService.register(user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }
}
