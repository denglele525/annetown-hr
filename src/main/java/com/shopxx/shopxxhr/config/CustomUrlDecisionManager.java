package com.shopxx.shopxxhr.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        ConfigAttribute configAttribute1 = configAttributes.stream().filter(
                one -> "ROLE_LOGIN".equals(one.getAttribute())
        ).findAny().orElse(null);
        if (configAttribute1 != null) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                throw new AccessDeniedException("尚未登录，请登录！");
            } else {
                return;
            }
        }
        for (ConfigAttribute configAttribute : configAttributes) {
            String needRole = configAttribute.getAttribute();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            GrantedAuthority grantedAuthority = authorities.stream().filter(
                    authority -> authority.getAuthority().equals(needRole)
            ).findAny().orElse(null);
            if (grantedAuthority != null) {
                return;
            }
        }
//        configAttributes.stream().filter(
//                configAttribute -> {
//                    String needRole = configAttribute.getAttribute();
//                    if ("ROLE_LOGIN".equals(needRole)) {
//                        if (authentication instanceof AnonymousAuthenticationToken) {
//                            throw new AccessDeniedException("尚未登录，请登录！");
//                        } else {
//                            return;
//                        }
//                    }
//                    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//                    GrantedAuthority grantedAuthority = authorities.stream().filter(
//                            authority -> authority.getAuthority().equals(needRole)
//                    ).findAny().orElse(null);
//                    if (grantedAuthority != null) {
//                        return;
//                    }
//                }
//        );
        throw new AccessDeniedException("权限不足，请联系管理员！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
