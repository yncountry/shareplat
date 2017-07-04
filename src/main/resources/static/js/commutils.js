//此js 主要完成相关的的form 表单提交的ajax 方法封装
//@author 吴仕涛 2017-2-23

/**
 *
 * @param pageFromId from 表单的add
 * @param url ajax 请求的url
 * @param subType  提交的类型  POST 或者GET
 * @returns 返回处理的结果
 */
var retCode; //定义执行返回的码值 0000表示执行成功 获取数据返回
var retShow; //定义执行结果
var retData; //返回参数
var title ="温馨提示";
var detial ="操作成功";
function fromSubmit(data,url) {
    if(data==null||data==''||url==null||url==''){
        retCode = "0001";
        retShow = "请求参数不完整";
        return {'retCode':retCode,'retShow':retShow};
    }
    //构建ajax 发起请求
    $.ajax({
        cache: true,
        type: "POST",
        url:url,
        data:data,// 完成
        async: false,
        beforeSend:function(){},
        error: function(request) {
            retCode = "9999";
            retShow = "请求结果错误";
            retData = request;
        },
        success: function(data) {
            retCode = "0000";
            retShow = "请求成功获取结果";
            retData = data;
        }
    });
    //把最终的结果返回 调用者解析json 获取对应结果处理
    return {'retCode':retCode,'retShow':retShow,'retData':retData};
}


/**
 * 单独封装的简单ajax 请求操作 这类操作涉及简单form表单或者单独id请求操作
 * 主要针对删除之类的操作
 * 可以自定义提示信息
 * @param obj 当前页面对象
 * @param page 当前页
 * @param args  请求参数
 * @param url  请求url
 */
function ajaxReq(page,args,url,tipinfo) {
    Lobibox.confirm({
        title: "温馨提示",
        msg: tipinfo,
        callback: function ($this, type) {
            if (type === 'yes') {
                $.post(url,args,function(data){
                    Lobibox.alert('success',{
                        msg : detial
                    });
                    setTimeout(function(){
                        requestUrl(handUrl(data.jumpUrl)+"?index="+page);
                    },1000);
                });
            }
        }
    });
}

/**
 * 完成请求之后自动跳转到自定义的url
 * 可以自定义提示信息
 * @param obj 当前页面对象
 * @param page 当前页
 * @param args  请求参数
 * @param url  请求url
 */
function ajaxReq2(page,args,url,tipinfo,requUrl) {
    Lobibox.confirm({
        title: "温馨提示",
        msg: tipinfo,
        callback: function ($this, type) {
            if (type === 'yes') {
                $.post(url,args,function(data){
                    Lobibox.alert('success',{
                        msg : detial
                    });
                    setTimeout(function(){
                        requestUrl(requUrl);
                    },1000);
                });
            }
        }
    });
}


/**
 * 此方法完成操作前提示，不做页面跳转
 * @param page
 * @param args
 * @param url
 * @param tipinfo
 */
function ajaxReq3(args,url,tipinfo) {
    Lobibox.confirm({
        title: "温馨提示",
        msg: tipinfo,
        callback: function ($this, type) {
            if (type === 'yes') {
                $.post(url,args,function(data){
                    Lobibox.alert('success',{
                        msg : detial
                    });
                    window.location.reload(true);
                });
            }
        }
    });
}


/**
 * 封装带文件上传的ajax 请求操作 文件上传的处理
 * 对表单数据统一formdata  非文件的则为序列化
 * @param formData 请求参数
 * @param url 请求url 地址
 * @param isRetData 是否返回数据
 */
function uploadAjax(formData,url,isRetData){
    $.ajax({
        url: url,
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
            retCode = "0000";
            retShow = "请求成功获取结果";
            retData = returndata;
        },
        error: function (returndata) {
            retCode = "9999";
            retShow = "请求结果错误";
            retData = returndata;
        }

    });
    if(isRetData){
        return retData;
    }
    handReturnData({'retCode':retCode,'retShow':retShow,'retData':retData});
}

/**
 * 对ajax请求返回的数据处理 主要功能
 * 弹出提示和url跳转
 * @param returnData
 */
function handReturnData(returnData) {
    if(returnData!=''){
        retCode = returnData.retCode;
        if(retCode!='0000'){ //当ajax 请求返回错误信息的时
            alert(returnData.retShow);
            return;
        }
        //处理后端返回的数据
        var info = returnData.retData;
        if(info!=''){
            checkRetInfoShow(info);
            setTimeout(function(){requestUrl(handUrl(info.jumpUrl))},1000);
            if(info.code=='0'){
                Lobibox.alert('success', {
                    title: title,
                    position:"top center",
                    msg: detial
                });
                return;
            }
        }
        // 否则添加失败，提示用户
        Lobibox.alert('error', {
            title:title,
            position:"top center",
            msg: detial
        });
        return;
    }else {
        Lobibox.alert('error', {
            title: title,
            position:"top center",
            msg: '系统未知错误'
        });
    }
}

/**
 * 对ajax请求返回的数据处理 主要功能
 * 弹出提示和url跳转
 * @param returnData
 */
function handReturnDataRels(returnData) {
    if(returnData!=''){
        retCode = returnData.retCode;
        if(retCode!='0000'){ //当ajax 请求返回错误信息的时
            alert(returnData.retShow);
            return;
        }
        //处理后端返回的数据
        var info = returnData.retData;
        if(info!=''){
            checkRetInfoShow(info);
            setTimeout(function(){requestUrl(info.jumpUrl)},1000);
            if(info.code=='0'){
                Lobibox.alert('success', {
                    title: title,
                    position:"top center",
                    msg: detial
                });
                return;
            }
        }
        // 否则添加失败，提示用户
        Lobibox.alert('error', {
            title:title,
            position:"top center",
            msg: detial
        });
        return;
    }else {
        Lobibox.alert('error', {
            title: title,
            position:"top center",
            msg: '系统未知错误'
        });
    }
}

/**
 * 根据指定的url 完成请求跳转
 * @param url
 */
function requestUrl(url){
    // alert("req=="+url);
    window.location.href=url;
}
/**
 *
 * @param freurl
 * @returns {*}
 */
function handUrl(freurl){
    var url =freurl.split("/");
    return  url[url.length-1];
}

/**
 * 处理的多文件和单个文件上传的验证 支持文件大小验证 文件类型
 * @param fileUpId 文件 inpu的 id
 * @param allowmaxSize 允许上传文件大小 为字节
 * @param typeArr  支持类型的数组
 * @param isAllow 与typeArr 连用，true 时表示允许类型为typeArr 中的类型
 *        false 时表示 不允许的类型为typeArr 中的类型
 */
function cheUploadFile(fileUpId,allowmaxSize,typeArr,isAllow) {
    var f = document.getElementById(fileUpId).files;
    var checkResult = true;
    if(f.length != 0){
        for(var i=0;i<f.length;i++){
            if(!checkAloneFile(f[i],allowmaxSize,typeArr,isAllow)){
                checkResult =false;
                break;
            }
        }
    }
    return checkResult;
}

/**
 *
 * @param file 验证的单个文件
 * @param allowmaxSize 允许上传的大小
 * @param typeArr 类型数组
 * @param isAllow 与typeArr 连用，true 时表示允许类型为typeArr 中的类型
 *        false 时表示 不允许的类型为typeArr 中的类型
 */
function checkAloneFile(file,allowmaxSize,typeArr,isAllow) {
    var type=(file.name).split(".")[(file.name).split(".").length-1];
    var checkType =true;
    //对文件类型进行验证
    if(typeArr!=null&&isAllow!=null){
        //取出所有的类型 jpg txt zip  zip
        for(var i=0;i<typeArr.length;i++){
            if(typeArr[i]==type){
                checkType =false;
            }
        }

        //if true 表示没有相等的类型 isAlllow 表示允许类型
        if((checkType &&isAllow)||(!isAllow&&!checkType)){
            Lobibox.alert('error', {
                title: title,
                position:"top center",
                msg: type+"为不允许上传的文件类型"
            });
            return false;
        }

    }

}

//验证文件类型
function checkFileType(file,typeArr,isAllow) {
    var split = file.split(".");
    var type=split[split.length-1];
    var checkType =true;
    //对文件类型进行验证
    if(typeArr!=null&&isAllow!=null){
        //取出所有的类型 jpg txt zip  zip
        for(var i=0;i<typeArr.length;i++){
            if(typeArr[i]==type){
                checkType =false;
            }
        }

        //if true 表示没有相等的类型 isAlllow 表示允许类型
        if((checkType &&isAllow)||(!isAllow&&!checkType)){
            Lobibox.alert('error', {
                title: title,
                position:"top center",
                msg: type+"为不允许上传的文件类型"
            });
            return false;
        }

    }

    return true;

}

/**
 * 验证文件大小
 * @param file
 * @param allowmaxSize
 * @returns {boolean}
 */
function checkFileSize(file,allowmaxSize) {
    //大小 字节
    var allM = Math.ceil(allowmaxSize/(1024*1024)); //计算单位为M
    var size=file.size
    if(allowmaxSize<size){
        Lobibox.alert('error', {
            title: title,
            position:"top center",
            msg: '上传文件必须小于'+allM+"M"
        });
        return false;
    }
    return true;
}

/**
 * 获取上传的文件允许的类型数组
 * @param typeNum 数组编码
 */
function getFileType(typeNum) {
    var type0 = new Array("txt","zip","docx","xls","pdf");
    var type1 = new Array("jpg","gif","bmp","png","jpeg");
    var fileType = new Array("csv","xls","xlsx");
    //后续需要的类型直接往里面添加就行
    var types = new Array(type0,type1,fileType);
    if(typeNum!=null&& typeNum<types.length){
        return types[typeNum];
    }
    //否则默认返回第一个
    return types[0];
}

//对提示的信息进行处理
function checkRetInfoShow(info){
    if(info.msg!=null&& info.msg!=''){
        title= info.msg;
    }
    if(info.detail!=null&&info.detail!=''){
        detial = info.detail;
    }
}

/**
 * 提交form
 */
function submitform(form,url){
    $().click(function (){
        var options = {
            url: url,
            target: "",
            success: function () {
                Lobibox.notify('success', {
                    title: "提示信息",
                    position:"top center",
                    msg: 'Ajax请求成功'
                });
            }
        };
        $(form).ajaxForm(options);
    })
}

/**
 * 把form 表单转为vue js 绑定的数据
 * @param formdata
 */
function formVueJson(formdata) {
    var v_modata={};
    var data = formdata;
    var split = data.split("=&");
    for(var i=0;i<split.length;i++){
        var temp =split[i].split("=");
        var name =temp[0];
        var  value =temp[1];
        v_modata[name]="";
    }
    return v_modata;
}

//处理 $("#summernote") 赋值提交表单的
function setSummernote(summernote,input) {
    var jbnrVali = summernote.code();
    input.val(jbnrVali);
}


/**
 * 处理对于后台传到前台的类似1491705929860 这样的时间格式转为正确的时间
 * @param jsondate 处理的数据串
 * @returns {string}
 */
function getLocalTime(jsondate) {
    jsondate=""+jsondate+"";//因为jsonDate是number型的indexOf会报错
    if (jsondate.indexOf("+") > 0) {
        jsondate = jsondate.substring(0, jsondate.indexOf("+"));
    }
    else if (jsondate.indexOf("-") > 0) {
        jsondate = jsondate.substring(0, jsondate.indexOf("-"));
    }
    var date = new Date(parseInt(jsondate, 10));
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
    var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
    var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
    return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
}
/**
 * 重置按钮,清除验证状态
 */
function btnReset(form){
    $(form)[0].reset();//jquery下的重置
    $(form).bootstrapValidator('resetForm', true);
    $("#summernote,#chuliyijian").code("");
    $("#summernote,#chuliyijian").code("<p><br></p>");
    if($(".informered-name")){
        var allName = $(".informered-name");
        allName.parent().removeClass("has-error").removeClass("has-success");
        allName.next().css("display","none");
        $(".name-informered").css("display","none");
    }
    $(form+" input").val("");
    $(form+" select option:first-child").attr("selected","selected");
    $(".select2-selection__rendered").text($($(".select2-hidden-accessible option:first-child")[0]).text());
}
(function($){
    $.fn.reSet = function(formName){
        $(this).click(function(){
            $(formName)[0].reset();

        })
    }
})(jQuery)


