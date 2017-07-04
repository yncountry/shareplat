package com.country.shareplat.user.service;

import cn.gfire.base.jpa.service.BaseService;
import com.country.common.user.dao.UserDao;
import com.country.common.user.domain.User;
import com.country.shareplat.user.form.UserForm;
import com.country.utils.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author wust
 * @date 2017/6/13
 * @vsrsion
 * @desc
 */
@Service
public class UserService extends BaseService<User,Long> {

    private String SUCCESS="success";


    public User findByPhone(String phoneNo){
        if(StringUtils.isNullData(phoneNo)){
            return null;
        }
        return ((UserDao)dao).findByPhoneNo(phoneNo);
    }


    public String register(User user) {
        save(user);
        return SUCCESS;
    }
}
