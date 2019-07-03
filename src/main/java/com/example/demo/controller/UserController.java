package com.example.demo.controller;

import com.example.demo.entity.UserInfo;
import com.example.demo.entity.UserLogin;
import com.example.demo.entity.UserLoginExample;
import com.example.demo.mapper.UserLoginMapper;
import com.example.demo.service.UserInfoServiceIMPL;
import com.example.demo.service.UserLoginServiceIMPL;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserLoginServiceIMPL userLoginServiceIMPL;
    @Autowired
    private UserInfoServiceIMPL userInfoServiceIMPL;

    @RequestMapping("/login")//false为假，true为真
    public boolean login(Integer id, String password) {
        UserLogin userLogin = new UserLogin();
        userLogin.setUserId(id);
        userLogin.setUserPassword(password);
        System.out.println(userLogin.getUserId());
        System.out.println(userLogin.getUserId());

        return userLoginServiceIMPL.login(userLogin);
    }

    @RequestMapping("/findall/userinfo")
    public List<UserInfo> findall(){
        return userInfoServiceIMPL.findAllUserInfo();
    }


    @RequestMapping("/del")
    public List<UserInfo> del(int id){

        UserInfoServiceIMPL userInfoServiceIMPL=new UserInfoServiceIMPL();
        //System.out.println(id);
        userInfoServiceIMPL.deleteUserInfoByID(id);
        return null;


    }

}