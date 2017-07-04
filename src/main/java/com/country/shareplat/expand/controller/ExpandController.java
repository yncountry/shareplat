package com.country.shareplat.expand.controller;

import cn.gfire.base.mvc.controller.CrudController;
import cn.gfire.base.mvc.form.BaseForm;
import com.country.common.expand.domain.Expand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wust
 * @date 2017/6/16
 * @vsrsion
 * @desc
 */
@Controller
@RequestMapping("expand")
public class ExpandController extends CrudController<Expand,Long> {




    @Override
    protected BaseForm<Expand, Long> getBlankForm() {
        return null;
    }

    @Override
    protected BaseForm<Expand, Long> getForm(Expand expand) {
        return null;
    }

    @Override
    protected String getListUrl() {
        return null;
    }

    @Override
    protected String getFormUrl() {
        return null;
    }
}
