package com.country.shareplat.base.controller;

import cn.gfire.base.mvc.controller.BaseController;
import com.country.utils.FileOpUtils;
import com.country.utils.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author wust
 * @date 2017/5/28
 * @vsrsion
 * @desc
 */
@Controller
@RequestMapping("common")
@ConfigurationProperties(prefix = "com.country")
public class CommonController extends BaseController {

    private String goodImages;
    private String imgPath="static/images/main/noimg.jpg";
    private String goodsDetailPath;

    @RequestMapping("uploadImage")
    public void uploadImage(HttpServletRequest request, HttpServletResponse response,String fileName) throws Exception{
        if(!StringUtils.isNullData(fileName)){
            File  file = new File(getGoodsDetailPath()+"/"+fileName);
            FileOpUtils.downFile(response, file, "demo1");
            return;
        }
        List<MultipartFile> h5File = ((StandardMultipartHttpServletRequest) request).getMultiFileMap().get("wangEditorH5File");
        File file =FileOpUtils.uplaodFile(h5File.get(0), getGoodsDetailPath());
        response.getWriter().write("/common/uploadImage?fileName="+file.getName());
    }

    @RequestMapping("showPic")
    public void showPic(HttpServletResponse response,String filePath)throws Exception{
        if(!StringUtils.isNullData(filePath)){
            FileOpUtils.downFile(response,new File(filePath),"demo");
        }else {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(imgPath);
            FileOpUtils.downFile(response,inputStream,"demo");
        }
    }




    public String getGoodImages() {
        return goodImages+"/detial";
    }

    public void setGoodImages(String goodImages) {
        this.goodImages = goodImages;
    }

    public String getGoodsDetailPath() {
        return goodsDetailPath;
    }

    public void setGoodsDetailPath(String goodsDetailPath) {
        this.goodsDetailPath = goodsDetailPath;
    }
}
