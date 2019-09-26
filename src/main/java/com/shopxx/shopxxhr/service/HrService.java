package com.shopxx.shopxxhr.service;

import com.shopxx.shopxxhr.entity.Hr;
import com.shopxx.shopxxhr.repository.HrRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HrService implements UserDetailsService {
    @Autowired
    HrRespository hrRespository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Hr hr = hrRespository.findByUsername(s);
        if (hr == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return hr;
    }
}
