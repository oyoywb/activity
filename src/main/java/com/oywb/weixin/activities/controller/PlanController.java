package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.PlanRequestDto;
import com.oywb.weixin.activities.dto.response.PlanResponseDto;
import com.oywb.weixin.activities.service.PlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public CommonResponse createPlan(@RequestBody PlanRequestDto planRequestDto) throws Exception {
        return planService.createPlan(planRequestDto);
    }

    @PatchMapping
    public CommonResponse updatePlan(@RequestBody PlanRequestDto planRequestDto) throws Exception {
        return planService.updatePlan(planRequestDto);
    }

    @GetMapping
    public CommonResponse<List<PlanResponseDto>> getPlans(@RequestParam long userId) throws Exception {
        return planService.getPlan(userId);
    }

    @DeleteMapping
    public CommonResponse deletePlan(@RequestParam long id, @RequestParam long userId) throws Exception {
        return planService.deletePlan(id, userId);
    }

    @GetMapping("/top")
    public CommonResponse topPlan(@RequestParam long userId, @RequestParam long id, @RequestParam boolean isTop) throws Exception {
        return planService.topPlan(id, userId, isTop);
    }
}
