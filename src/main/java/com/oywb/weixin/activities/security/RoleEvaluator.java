package com.oywb.weixin.activities.security;

import com.oywb.weixin.activities.Enum.Role;
import com.oywb.weixin.activities.dao.*;
import com.oywb.weixin.activities.entity.*;
import com.oywb.weixin.activities.service.RoleBindingService;
import com.oywb.weixin.activities.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("roleEvaluator")
public class RoleEvaluator {

    private final RoleBindingService roleBindingService;

    private final UserService userService;

    private final PlanRepository planRepository;
    private final ShopRepository shopRepository;
    private final ActivityRepository activityRepository;
    private final ProjectRepository projectRepository;
    private final DynamicsRepository dynamicsRepository;
    private final DynamicsCommentRepository dynamicsCommentRepository;

    public RoleEvaluator(RoleBindingService roleBindingService, UserService userService, PlanRepository planRepository, ShopRepository shopRepository, ActivityRepository activityRepository, ProjectRepository projectRepository, DynamicsRepository dynamicsRepository, DynamicsCommentRepository dynamicsCommentRepository) {
        this.roleBindingService = roleBindingService;
        this.userService = userService;
        this.planRepository = planRepository;
        this.shopRepository = shopRepository;
        this.activityRepository = activityRepository;
        this.projectRepository = projectRepository;
        this.dynamicsRepository = dynamicsRepository;
        this.dynamicsCommentRepository = dynamicsCommentRepository;
    }

    public boolean isAdmin(Authentication authentication) {
        String openid = authentication.getName();
        RoleBinding roleBinding = roleBindingService.findByOpenidAndUserRole(openid, Role.admin.name());
        return roleBinding == null ? false : true;
    }

    public boolean isRegistered(Authentication authentication) {
        UserEntity userEntity = userService.findByOpenid(authentication.getName());

        return userEntity == null ? false : (userEntity.getRegisted() == 1);
    }

    public boolean sameUser(Authentication authentication, long userId) {
        long authUserId = userService.getUserId(authentication.getName());

        return authUserId == userId;
    }

    public boolean planBelongToUser(Authentication authentication, long id) {
        long userId = userService.getUserId(authentication.getName());

        boolean exist = planRepository.existsByIdAndUserId(id, userId);

        return exist;

    }

    public boolean canGetNoPass(Authentication authentication, byte pass) {
        return pass == 1 || isAdmin(authentication);
    }

    public boolean shopBelongToUser(Authentication authentication, long id, long userId) {
        boolean sameUser = this.sameUser(authentication, userId);

        boolean exist = shopRepository.existsByIdAndUserId(id, userId);

        return sameUser && exist;
    }

    public boolean activityBelongToUser(Authentication authentication, long activityId) {
        long userId = userService.getUserId(authentication.getName());

        Optional<ActivityEntity> activityEntity = activityRepository.findById(activityId);

        if (!activityEntity.isPresent()) {
            return false;
        }

        return activityEntity.get().getUserId() == userId;
    }

    public boolean projectBelongToUser(Authentication authentication, long projectId) {
        long userId = userService.getUserId(authentication.getName());

        Optional<ProjectEntity> projectEntity = projectRepository.findById(projectId);

        if (!projectEntity.isPresent()) {
            return false;
        }

        return projectEntity.get().getUserId() == userId;
    }

    public boolean dyCommentBelongToUser(Authentication authentication, long dyId, long dyCommentId) {
        long userId = userService.getUserId(authentication.getName());

        DynamicsEntity dynamicsEntity = dynamicsRepository.findByIdAndUserId(dyId, userId);

        DynamicsCommentEntity dynamicsCommentEntity = dynamicsCommentRepository.getDynamicsCommentEntitiesByIdAndUserId(dyCommentId, userId);

        return dynamicsEntity != null || dynamicsCommentEntity !=null;
    }
}
