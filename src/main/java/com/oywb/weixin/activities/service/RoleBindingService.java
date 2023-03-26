package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dao.RoleBindingRepository;
import com.oywb.weixin.activities.entity.RoleBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleBindingService {
    @Autowired
    private RoleBindingRepository roleBindingRepository;

    public RoleBinding findByOpenidAndUserRole (String openid, String userRole) {
        return roleBindingRepository.findByOpenidAndUserRole(openid, userRole);
    }
}
