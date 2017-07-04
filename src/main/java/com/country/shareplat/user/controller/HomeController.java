package com.country.shareplat.user.controller;

import cn.gfire.base.mvc.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wust
 * @date 2017/6/13
 * @vsrsion
 * @desc
 */
@Controller
@RequestMapping("country")
public class HomeController extends BaseController {

    private String INDEX_URL="index";

    @RequestMapping("index")
    public String index(Model model){
        return "forward:/saleGoods/index";
    }

}
