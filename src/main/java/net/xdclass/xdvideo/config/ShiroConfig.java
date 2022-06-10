package net.xdclass.xdvideo.config;

import net.xdclass.xdvideo.interceoter.JwtFilter;
import net.xdclass.xdvideo.utils.CommonUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiro(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 自定义的 登录接口
        shiroFilterFactoryBean.setLoginUrl("/notLogin");

        // 自定义的 登录成功接口
        // shiroFilterFactoryBean.setSuccessUrl("/");

        // 自定义的 无权限接口
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");

        // 不使用linked hash map 会出现顺序错乱
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JwtFilter()); // 加载自定义的JWT过滤器
        shiroFilterFactoryBean.setFilters(filterMap);

        // 过滤链从上到下执行，从上向下
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/notLogin", "anon");
        filterChainDefinitionMap.put("/notRole", "anon");
        // pub/xxx 不需要登录
        filterChainDefinitionMap.put("/pub/**", "anon");
        // 其他的都需要登录
        filterChainDefinitionMap.put("/**", "jwt");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(serviceRealm());
        // 关闭shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    @Bean
    public ServiceRealm serviceRealm() {
        ServiceRealm realm =  new ServiceRealm();
        return realm;
    }
}
