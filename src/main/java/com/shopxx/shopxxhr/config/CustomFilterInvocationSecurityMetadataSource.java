package com.shopxx.shopxxhr.config;

import com.shopxx.shopxxhr.entity.Menu;
import com.shopxx.shopxxhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
 * 根据用户传来的请求地址分析出需要的角色。
 *
 * */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuService menuService;
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Menu> menus = menuService.getAllMenusWithRole();
        Menu menu = menus.stream().filter(
                one -> antPathMatcher.match(one.getUrl(), requestUrl)
        ).findAny().orElse(null);
        if (menu != null) {
            List<String> roleNameList = menu.getRoles().stream().map(
                    role -> role.getName()
            ).collect(Collectors.toList());
            String[] roleNames = roleNameList.toArray(new String[roleNameList.size()]);
            return SecurityConfig.createList(roleNames);
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
