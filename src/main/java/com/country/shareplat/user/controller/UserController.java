package com.country.shareplat.user.controller;

import cn.gfire.base.mvc.controller.CrudController;
import cn.gfire.base.mvc.form.BaseDataResponse;
import cn.gfire.base.mvc.form.BaseForm;
import com.country.common.user.domain.User;
import com.country.shareplat.user.form.UserForm;
import com.country.shareplat.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wust
 * @date 2017/6/13
 * @vsrsion
 * @desc
 */
@Controller
@RequestMapping("user")
public class UserController extends CrudController<User, Long> {

    private final String  LOGIN_URL="/user/login";
    private final String  USER_CENTER_URL="/user/usercenter";
    private final String  INDEX_URL="/index/index";
    private final  String REGISTER_URL="/user/register_1";
    private final  String REGISTER2_URL="/user/register_2";

    private String UNKONW_ACCOUNT ="";
    private String ERROR_PASSEORD ="";

    private String OPERATE="PERSON_CENTER";

    @Resource
    private UserService userService;

    @RequestMapping("userCenter")
    public String userCenter(Model model){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //未登录直接登录
        if(user==null){
           model.addAttribute("operate",OPERATE);
           return LOGIN_URL;
        }
        model.addAttribute("user",service.findOne(user.getId()).get());
        return USER_CENTER_URL;
    }


    @PostMapping("login")
    public String login(Model model, UserForm form, BindingResult bindingResult ){

        //使用shiro的登录认证
        //1  获取Subject
        Subject currentUser = SecurityUtils.getSubject();
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        //2 判断当前用户是否登录
        if (!currentUser.isAuthenticated()) {
            // 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(form.getPhoneNo(), form.getPassword());
            // rememberme
            token.setRememberMe(true);
            //执行登录操作
            try {
                // 执行登录调用到realm
                currentUser.login(token);
            } catch (UnknownAccountException ue) { //账户不存在
                model.addAttribute("ACCOUNTINFO",UNKONW_ACCOUNT);
                return "forward:" + LOGIN_URL;

            } catch (IncorrectCredentialsException ice) {  //登录密码不对
                model.addAttribute("PASSWORDINFO",ERROR_PASSEORD);
                return "forward:" + LOGIN_URL;
            } catch (AuthenticationException ae) {
                model.addAttribute("logInfo", ae.getMessage());
                return "forward:" + LOGIN_URL;

            }
            model.addAttribute("session", currentUser.getSession());
        }
        return "redirect:" ;
    }

    @GetMapping("register")
    public String register(Model model){
        return REGISTER_URL;
    }

    @GetMapping("register2/{type}")
    public String register2(Model model,@PathVariable String type){
        model.addAttribute("type",type);
        return REGISTER2_URL;
    }

    @PostMapping("register")
    @ResponseBody
    public BaseDataResponse register(Model model, UserForm userForm) {
        User byPhone = userService.findByPhone(userForm.getPhoneNo());
        if (byPhone != null) {
            return BaseDataResponse.fail().data("手机号码已被使用!");
        }
        String result = userService.register(userForm.as());
        return "success".equals(result)?BaseDataResponse.ok().data("恭喜，注册成功").jumpUrl("/country/index"):BaseDataResponse.fail().data("注册失败"+result);
    }

    @RequestMapping("checkPhoneNo/{fileId}")
    @ResponseBody
    public BaseDataResponse checkPhoneNo(@PathVariable String phoneNo) {
        User byPhone = userService.findByPhone(phoneNo);
        return byPhone == null ? BaseDataResponse.ok() : BaseDataResponse.fail();
    }

    @Override
    protected BaseForm<User, Long> getBlankForm() {
        return null;
    }

    @Override
    protected BaseForm<User, Long> getForm(User user) {
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
