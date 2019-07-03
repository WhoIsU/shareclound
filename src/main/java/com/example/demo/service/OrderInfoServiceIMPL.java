package com.example.demo.service;

import com.example.demo.entity.GoodsDetail;
import com.example.demo.entity.OrderInfo;
import com.example.demo.entity.OrderInfoExample;
import com.example.demo.entity.ShopCart;
import com.example.demo.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.math.BigDecimal;
//question:可能不应该声明private...
@Service
public class OrderInfoServiceIMPL {
    @Autowired
    private OrderInfoMapper orderInfoMapper;




    //直接更新orderInfo
    public void updateOrderInfo(OrderInfo orderInfo){
        orderInfoMapper.updateByPrimaryKey(orderInfo);
    }
    //直接删除
    public void deleteOrderInfo(Integer id){
        orderInfoMapper.deleteByPrimaryKey(id);
    }
    //下订单：计算会员优惠--删除购物车里的订单-写入数据库 -------true为成功，false为没有库存或出错
    public boolean addOrderInfo(OrderInfo orderInfo){
        try{
            //检测是否有库存
            GoodsDetail goodsDetail=new GoodsDetail();
            goodsDetail.setGoodsDetaiId(orderInfo.getGoodsDetailId());
            GoodsDetailServiceIMPL goodsDetailServiceIMPL=new GoodsDetailServiceIMPL();
            List<GoodsDetail> rs= goodsDetailServiceIMPL.findWhateverYouWant(goodsDetail);
            GoodsDetail tmp=rs.get(0);
            if(tmp.getStock()<=0){
                return false;
            }

            //计算会员优惠--- 1级0-200 9折，2级200-400 8折，3级400+
            UserInfoServiceIMPL userInfoServiceIMPL=new UserInfoServiceIMPL();
            int level=userInfoServiceIMPL.selectUserInfo(orderInfo.getUserId()).getUserLevel();
            double discount=1;
            if(level<200){discount=0.9;}
            else if(level<400){discount=0.8;}
            else {discount=0.7;}
            double price=orderInfo.getOrderPrice().doubleValue();
            orderInfo.setOrderPrice(BigDecimal.valueOf(price));
            //更新积分--不做了

            //删除购物车里的同id商品
            ShopCart shopCart=new ShopCart();
            shopCart.setUserId(orderInfo.getUserId());
            shopCart.setGoodsDetailId(orderInfo.getGoodsDetailId());
            ShopCartServiceIMPL shopCartServiceIMPL=new ShopCartServiceIMPL();
            shopCartServiceIMPL.deleteShopCardByBothID(shopCart);;

            //添加订单
            orderInfoMapper.insert(orderInfo);

        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    //按用户id查找订单
    public List<OrderInfo> findOrderByUserID(Integer id){
        OrderInfoExample orderInfoExample=new OrderInfoExample();
        orderInfoExample.or().andUserIdEqualTo(id);
        return orderInfoMapper.selectByExample(orderInfoExample);
    }

    //按照gooddetailid查找
    public List<OrderInfo> findOrderByGoodsDetailID(Integer id){
        OrderInfoExample orderInfoExample=new OrderInfoExample();
        orderInfoExample.or().andGoodsDetailIdEqualTo(id);
        return orderInfoMapper.selectByExample(orderInfoExample);

    }

    //按照orderid查找
    public List<OrderInfo> findOrderByOrderId(Integer id){
        OrderInfoExample orderInfoExample=new OrderInfoExample();
        orderInfoExample.or().andOrderIdEqualTo(id);
        return orderInfoMapper.selectByExample(orderInfoExample);
    }
}
