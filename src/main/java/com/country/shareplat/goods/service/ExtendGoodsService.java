package com.country.shareplat.goods.service;

import cn.gfire.base.jpa.service.BaseService;
import com.country.common.goods.domain.ExtendGoods;
import com.country.common.goods.domain.SaleGoodsInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wust
 * @date 2017/6/26
 * @vsrsion
 * @desc
 */
@Service
public class ExtendGoodsService extends BaseService<ExtendGoods,Long> {


    @Resource
    private SaleGoodsService saleGoodsService;

    public String save(Long goodId,ExtendGoods extendGoods){
        SaleGoodsInfo saleGoodsInfo = saleGoodsService.findOne(goodId).get();
        extendGoods.setSaleGoodsInfo(saleGoodsInfo);
        save(extendGoods);
        return "SUCCESS";
    }
}
