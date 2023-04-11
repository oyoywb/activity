package com.oywb.weixin.activities.security;

import com.oywb.weixin.activities.Enum.Role;
import com.oywb.weixin.activities.dao.PlanRepository;
import com.oywb.weixin.activities.dao.UserRepository;
import com.oywb.weixin.activities.entity.RoleBinding;
import com.oywb.weixin.activities.entity.UserEntity;
import com.oywb.weixin.activities.service.RoleBindingService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("roleEvaluator")
public class RoleEvaluator {

    private final RoleBindingService roleBindingService;

    private final UserRepository userRepository;

    private final PlanRepository planRepository;

    public RoleEvaluator(RoleBindingService roleBindingService, UserRepository userRepository, PlanRepository planRepository) {
        this.roleBindingService = roleBindingService;
        this.userRepository = userRepository;
        this.planRepository = planRepository;
    }

    public boolean isAdmin(Authentication authentication) {
        String openid = authentication.getName();
        RoleBinding roleBinding = roleBindingService.findByOpenidAndUserRole(openid, Role.admin.name());
        return roleBinding == null ? false : true;
    }

    public boolean isRegisted(Authentication authentication) {
        UserEntity userEntity = userRepository.findByOpenid(authentication.getName());

        return userEntity == null ? false : (userEntity.getRegisted() == 1 ? true : false );
    }

    public boolean sameUser(Authentication authentication, long userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

        if (!userEntityOptional.isPresent()) {
            return false;
        }
        UserEntity userEntity = userEntityOptional.get();

        return userEntity.getId() == userId;
    }

    public boolean planBelongToUser(Authentication authentication, long id, long userId) {
        boolean sameUser = this.sameUser(authentication, userId);

        boolean exist = planRepository.existsByIdAndUserId(id, userId);

        return sameUser && exist;

    }
}
