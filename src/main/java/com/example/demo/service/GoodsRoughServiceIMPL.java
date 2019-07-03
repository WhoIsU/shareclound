package com.example.demo.service;

import com.example.demo.entity.GoodsRough;
import com.example.demo.entity.GoodsRoughExample;
import com.example.demo.mapper.GoodsRoughMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.math.BigDecimal;
@Service
public class GoodsRoughServiceIMPL {
    @Autowired
    private GoodsRoughMapper goodsRoughMapper;

    public List<GoodsRough> findWhateverYouWant(GoodsRough goodsRough){
        GoodsRoughExample goodsRoughExample=new GoodsRoughExample();
        GoodsRoughExample.Criteria criteria=goodsRoughExample.createCriteria();
        //构建查询语句--不包括picurl和price
        if(goodsRough.getGoodsId()!=null){
            criteria.andGoodsIdEqualTo(goodsRough.getGoodsId());
        }
        if(goodsRough.getGoodsName()!=null){
            criteria.andGoodsNameEqualTo(goodsRough.getGoodsName());
        }
        if(goodsRough.getBrand()!=null){
            criteria.andBrandEqualTo(goodsRough.getBrand());
        }
        if (goodsRough.getLable()!=null){
            criteria.andLableEqualTo(goodsRough.getLable());
        }
        return goodsRoughMapper.selectByExample(goodsRoughExample);
    }

    public List<GoodsRough> findPriceBetween(int min,int max){
        GoodsRoughExample goodsRoughExample=new GoodsRoughExample();
        GoodsRoughExample.Criteria criteria=goodsRoughExample.createCriteria();

        criteria.andGoodsPriceBetween(BigDecimal.valueOf(min),BigDecimal.valueOf(max));

        return goodsRoughMapper.selectByExample(goodsRoughExample);

    }

    public void deleteGoodRughByID(int id){

       goodsRoughMapper.deleteByPrimaryKey(id);

    }

    public void updateGoodRough(GoodsRough goodsRough){
        goodsRoughMapper.updateByPrimaryKey(goodsRough);
    }

    public void addGoodRough(GoodsRough goodsRough){
        goodsRoughMapper.insert(goodsRough);
    }


}
