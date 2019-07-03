package com.example.demo.service;

import com.example.demo.entity.UserInfo;
import com.example.demo.entity.UserInfoExample;
import com.example.demo.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserInfoServiceIMPL  {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public boolean deleteUserInfoByID(int id){
        boolean flag=false;
        UserInfoExample userInfoExample=new UserInfoExample();
        UserInfoExample.Criteria criteria=userInfoExample.createCriteria();

        criteria.andUserIdEqualTo(id);

        userInfoMapper.deleteByExample(userInfoExample);
        //int result=userInfoMapper.deleteByPrimaryKey(1);
        System.out.println("删除完成");
        try{

            flag=true;
            return flag;
        }
        catch (Exception e)
        {
            return flag;
        }
    };
    public UserInfo updateUserInfo(UserInfo userInfo){

        try{
            int id=userInfo.getUserId();
            userInfoMapper.updateByPrimaryKey(userInfo);
            return userInfoMapper.selectByPrimaryKey(id);
        }
        catch (Exception e)
        {
            return null;
        }
    };
    public UserInfo selectUserInfo(Integer id){
        return  userInfoMapper.selectByPrimaryKey(id);
    };
    public List<UserInfo> findAllUserInfo(){
        UserInfoExample userInfoExample=new UserInfoExample();
        userInfoExample.or().andUserIdGreaterThan(0);
        return userInfoMapper.selectByExample(userInfoExample);

    };


    public boolean addUserInfo(UserInfo userInfo){
        boolean flag=false;
        try{
            userInfoMapper.deleteByPrimaryKey(userInfo.getUserId());
            flag=true;
            return flag;
        }
        catch (Exception e)
        {
            return flag;
        }
    };

}
