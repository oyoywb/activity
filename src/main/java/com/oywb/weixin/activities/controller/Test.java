package com.oywb.weixin.activities.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("@roleEvaluator.isAdmin(authentication)")
@Slf4j
public class Test {
    @GetMapping("/service-a")
    public String test(Authentication authentication) {
        log.error("test");
        return "try";
    }
}
