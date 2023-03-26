package com.oywb.weixin.activities.service;

import com.oywb.weixin.activities.dao.UserRepository;
import com.oywb.weixin.activities.entity.User;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User checkAndCreate (String openid) {
        User user = userRepository.findByOpenid(openid);
        if (user == null) {
            user = new User();
            user.setOpenid(openid);
            userRepository.save(user);
        }
        return user;
    }

    public User findUserByOpenId (String openId) {
        return userRepository.findByOpenid(openId);
    }
}
