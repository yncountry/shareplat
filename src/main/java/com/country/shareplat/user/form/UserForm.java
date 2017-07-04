package com.country.shareplat.user.form;

import cn.gfire.base.mvc.form.BaseForm;
import com.country.common.emmun.RegType;
import com.country.common.user.domain.User;

/**
 * @author wust
 * @date 2017/6/13
 * @vsrsion
 * @desc
 */

public class UserForm extends BaseForm<User,Long> {

    private String userName;
    private String phoneNo;
    private String password;
    private String confirmPass;
    private String netName;
    private String wexNum;
    private String address;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNetName() {
        return netName;
    }

    public void setNetName(String netName) {
        this.netName = netName;
    }

    public String getWexNum() {
        return wexNum;
    }

    public void setWexNum(String wexNum) {
        this.wexNum = wexNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getName() {
        return "人员信息";
    }


}
