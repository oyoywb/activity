package com.oywb.weixin.activities.security;

import com.oywb.weixin.activities.Enum.Role;
import com.oywb.weixin.activities.entity.RoleBinding;
import com.oywb.weixin.activities.service.RoleBindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("roleEvaluator")
public class RoleEvaluator {

    @Autowired
    private RoleBindingService roleBindingService;

    public boolean isAdmin(Authentication authentication) {
        String openid = authentication.getName();
        RoleBinding roleBinding = roleBindingService.findByOpenidAndUserRole(openid, Role.admin.name());
        return roleBinding == null ? false : true;
    }
}
