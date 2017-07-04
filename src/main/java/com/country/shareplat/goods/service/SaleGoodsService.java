package com.country.shareplat.goods.service;

import cn.gfire.base.jpa.service.BaseService;
import com.country.common.goods.domain.ExtendGoods;
import com.country.common.goods.domain.SaleGoodsInfo;
import com.country.common.user.dao.UserDao;
import com.country.common.user.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wust
 * @date 2017/6/14
 * @vsrsion
 * @desc
 */
@Service
public class SaleGoodsService extends BaseService<SaleGoodsInfo,Long> {

    private String SUCCESS="success";
    private String ERROR="error";

    @Resource
    private UserDao userDao;


    public String agentGoods(Long goodId, User user) {
        SaleGoodsInfo saleGoodsInfo = findOne(goodId).get();
        User newUser = userDao.findOne(user.getId());
        if(newUser ==null){
            return ERROR;
        }
        List<SaleGoodsInfo> saleGoodsInfos = newUser.getSaleGoodsInfos();
        saleGoodsInfos.add(saleGoodsInfo);
        newUser.setSaleGoodsInfos(saleGoodsInfos);
        userDao.save(newUser);
        return SUCCESS;
    }

}
