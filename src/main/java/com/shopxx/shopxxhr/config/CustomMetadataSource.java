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

@Component
public class CustomMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<Menu> allMenu = menuService.getAllMenu();
        Menu menu = allMenu.stream().filter(one -> antPathMatcher.match(one.getUrl(), requestUrl) && one.getRoles().size() > 0)
                .findAny().orElse(null);
        if (menu != null) {
            List<String> roleNames = menu.getRoles()
                    .stream()
                    .map(role -> role.getName())
                    .collect(Collectors.toList());
            String[] values = roleNames.toArray(new String[roleNames.size()]);
            return SecurityConfig.createList(values);
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
