package com.example.demo.service;

import com.example.demo.entity.OrderInfo;
import com.example.demo.entity.ShopCart;
import com.example.demo.entity.ShopCartExample;
import com.example.demo.entity.ShopCartKey;
import com.example.demo.mapper.ShopCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCartServiceIMPL {
    @Autowired
    private ShopCartMapper shopCartMapper;
    @Autowired
    private OrderInfoServiceIMPL orderInfoServiceIMPL;

    public void addShopCart(ShopCart shopCart){
        shopCartMapper.insert(shopCart);
    }

    public void deleteShopCardByBothID(ShopCart shopCart) {

        ShopCartKey shopCartKey=new ShopCartKey();
        shopCartKey.setGoodsDetailId(shopCart.getGoodsDetailId());
        shopCartKey.setUserId(shopCart.getUserId());

        shopCartMapper.deleteByPrimaryKey(shopCartKey);
    }

    public List<ShopCart> findShopCardByUserID(int id){
        ShopCartExample shopCartExample=new ShopCartExample();
        shopCartExample.or().andUserIdEqualTo(id);
        return  shopCartMapper.selectByExample(shopCartExample);
    }

    public boolean buyFromShopCart(OrderInfo newOrder){
        boolean flag=false;

        flag=orderInfoServiceIMPL.addOrderInfo(newOrder);

        return flag;
    }
}
