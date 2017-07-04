package com.country.shareplat.goods;

import com.country.common.goods.domain.SaleGoodsInfo;
import com.country.shareplat.Application;
import com.country.shareplat.goods.service.SaleGoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author wust
 * @date 2017/6/26
 * @vsrsion
 * @desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@Transactional
@Rollback(value = false)
public class TestGoodsService {

    @Resource
    private SaleGoodsService saleGoodsService;

    @Test
    public void testAdd(){
        SaleGoodsInfo saleGoodsInfo = new SaleGoodsInfo();
        saleGoodsInfo.setSaleDesc("测试数据");
        saleGoodsInfo.setSalePrice(11.0);
//        saleGoodsInfo.setStrore(20);
        saleGoodsInfo.setTitle("测试标题");

        saleGoodsService.save(saleGoodsInfo);

    }

}
