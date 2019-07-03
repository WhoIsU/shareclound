package com.example.demo.service;

import com.example.demo.entity.UserCollectionExample;
import com.example.demo.entity.UserCollectionKey;
import com.example.demo.mapper.UserCollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCollectionServiceIMPL {
    @Autowired
    private UserCollectionMapper userCollectionMapper;

    public void addCollection(UserCollectionKey userCollectionKey){
        userCollectionMapper.insert(userCollectionKey);
    }

    public void deleteCollect(UserCollectionKey userCollectionKey){
        userCollectionMapper.deleteByPrimaryKey(userCollectionKey);
    }

    public List<UserCollectionKey> findCollectionByUserId(Integer id){
        UserCollectionExample userCollectionExample=new UserCollectionExample();
        UserCollectionExample.Criteria criteria=userCollectionExample.createCriteria();

        criteria.andUserIdEqualTo(id);

        return userCollectionMapper.selectByExample(userCollectionExample);
    }


}
