package com.country.shareplat.goods.controller;

import cn.gfire.base.mvc.controller.CrudController;
import cn.gfire.base.mvc.form.BaseDataResponse;
import cn.gfire.base.mvc.form.BaseForm;
import com.country.common.goods.domain.SaleGoodsInfo;
import com.country.common.user.domain.User;
import com.country.shareplat.goods.form.SaleGoodsForm;
import com.country.shareplat.goods.query.SaleGoodsQuery;
import com.country.shareplat.goods.service.SaleGoodsService;
import org.apache.shiro.SecurityUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wust
 * @date 2017/6/15
 * @vsrsion
 * @desc
 */
@Controller
@RequestMapping("saleGoods")
@ConfigurationProperties(prefix = "com.country.goods")
public class SaleGoodsController extends CrudController<SaleGoodsInfo, Long> {


    private Integer showCount;

    private String GOODDETAIL_URL = "goods/detail";

    private String INDEX = "index";

    private String LOGIN_URL = "admin/user/login";

    private String AGENT_SUCCESS = "";
    private String AGENT_FAIL = "";


    @GetMapping("goodsDetail/{saleGoodsId}")
    public String goodsDetail(Model model, @PathVariable Long saleGoodsId) {
        model.addAttribute("form", service.findOne(saleGoodsId).get());
        return GOODDETAIL_URL;
    }

    @RequestMapping("index")
    public String list(Model model, SaleGoodsQuery query) {
        query.setSize(24);
        Pageable pageable = query.getPageable();
        Page<SaleGoodsInfo> all = service.findAll(pageable);
        List<SaleGoodsInfo> content = all.getContent();
        model.addAttribute("showData", handShowData(content));

        return super.list(model, query);
    }

    private List<List<SaleGoodsInfo>> handShowData(List<SaleGoodsInfo> content) {

        List<List<SaleGoodsInfo>> list = new ArrayList<>();
        List<SaleGoodsInfo> createList = new ArrayList<>();
        for (int i = 0; i < content.size(); i++) {
            SaleGoodsInfo goodsInfo = content.get(i);
            if (i != 0 && i % showCount == 0) {
                list.add(createList);
                createList=new ArrayList<>();
            }
            createList.add(goodsInfo);
        }
        if(createList.size()>0){
            list.add(createList);
        }
        return list;

    }

    @RequestMapping("agent/{goodId}")
    public String agentGoods(@PathVariable Long saleGoodId) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //未登录 先登录
        if (user == null) {
            return LOGIN_URL;
        }
        String result = ((SaleGoodsService) service).agentGoods(saleGoodId, user);
        if ("success".equals(result)) {
            return AGENT_SUCCESS;
        } else {
            return AGENT_FAIL;
        }
    }

    @Override
    protected BaseForm<SaleGoodsInfo, Long> getBlankForm() {
        return null;
    }

    @Override
    protected BaseForm<SaleGoodsInfo, Long> getForm(SaleGoodsInfo saleGoodsInfo) {
        return null;
    }

    @Override
    protected String getListUrl() {
        return INDEX;
    }

    @Override
    protected String getFormUrl() {
        return null;
    }

    public Integer getShowCount() {
        return showCount;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }

    //
}
