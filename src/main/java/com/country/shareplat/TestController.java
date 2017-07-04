package com.country.shareplat;

import cn.gfire.base.mvc.controller.BaseController;
import cn.gfire.base.mvc.form.BaseDataResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author XuHui(xh@gfire.cn)
 * @date 2017/5/16
 * @desc
 */
@RequestMapping("test")
@Controller
public class TestController extends BaseController{
    @GetMapping("test")
    @ResponseBody
    public BaseDataResponse test(){
        return BaseDataResponse.ok().data("hello");
    }
}
