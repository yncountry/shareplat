package com.country.shareplat;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.country.shareplat.user.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author XuHui(xh@gfire.cn)
 * @date 2017/3/3
 * @desc
 */
@Configuration
public class ShiroConfiguration {
    private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap();

    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

    @Bean(name = "getUserRealm")
    public UserRealm getUserRealm() {
        //设置加密方式
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        return new UserRealm();
    }



    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean(name ="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(){
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRememberMeManager(null);
        dwsm.setRealm(getUserRealm());
        return dwsm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(getDefaultWebSecurityManager());
        return new AuthorizationAttributeSourceAdvisor();
    }

    @Bean(name= "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean =new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
        shiroFilterFactoryBean.setLoginUrl("/country/index");
        shiroFilterFactoryBean.setSuccessUrl("/country/index");

//        filterChainDefinitionMap.put("/admin/user/userLogin","anon");//请求登录放行 add by wust
//        filterChainDefinitionMap.put("/admin/user/register","anon");//注册放行
//        filterChainDefinitionMap.put("/templates/util/**","anon");
//        filterChainDefinitionMap.put("/static/css/**", "anon");
//        filterChainDefinitionMap.put("/static/js/**", "anon");
//        filterChainDefinitionMap.put("/static/images/**", "anon");
//        filterChainDefinitionMap.put("/static/images/goods/**", "anon");
//        filterChainDefinitionMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}
