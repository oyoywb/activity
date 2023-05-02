package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.PlanRequestDto;
import com.oywb.weixin.activities.dto.response.PlanResponseDto;
import com.oywb.weixin.activities.service.PlanService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plan")
@PreAuthorize("@roleEvaluator.isRegistered(authentication)")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    //tested
    @PostMapping
    public void createPlan(@RequestBody PlanRequestDto planRequestDto, Authentication authentication) throws Exception {
        planService.createPlan(planRequestDto, authentication.getName());
    }

    //tested
    @PatchMapping
    public void updatePlan(@RequestBody PlanRequestDto planRequestDto, Authentication authentication) throws Exception {
        planService.updatePlan(planRequestDto, authentication.getName());
    }

    //tested
    @GetMapping
    public List<PlanResponseDto> getPlans(Authentication authentication) throws Exception {
        return planService.getPlan(authentication.getName());
    }

    //tested
    @PreAuthorize("@roleEvaluator.planBelongToUser(authentication, #id)")
    @DeleteMapping
    public void deletePlan(@RequestParam long id) throws Exception {
       planService.deletePlan(id);
    }

    //tested
    @PreAuthorize("@roleEvaluator.planBelongToUser(authentication, #id)")
    @GetMapping("/top")
    public void topPlan(@RequestParam long id, @RequestParam boolean isTop) throws Exception {
        planService.topPlan(id, isTop);
    }
}
