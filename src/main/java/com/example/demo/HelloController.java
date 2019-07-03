package com.example.demo;

import com.example.demo.entity.UserLogin;
import com.example.demo.mapper.UserLoginMapper;
import io.swagger.models.auth.In;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HelloController {
    @Autowired
    private UserLoginMapper userLoginMapper;
    @PostMapping("/hello")
    public String hello(Integer id,String password){
        UserLogin u=null;
        u.setUserId(id);
        u.setUserPassword(password);


        Integer j=10;
//        us.login(u );
        userLoginMapper.selectByPrimaryKey(id);
        return "hello";
    }


}
