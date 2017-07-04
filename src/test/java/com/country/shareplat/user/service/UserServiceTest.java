package com.country.shareplat.user.service;

import com.country.common.user.domain.User;
import com.country.shareplat.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @author wust
 * @date 2017/6/28
 * @vsrsion
 * @desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@Transactional
@Rollback(value = false)
public class UserServiceTest {

    @Test
    public  void testReg(){
        User user= new User();
        user.setPhoneNo("18298353356");
        user.setPassword("1233443");
    }

}