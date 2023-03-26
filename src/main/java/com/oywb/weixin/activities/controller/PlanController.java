package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.PlanRequestDto;
import com.oywb.weixin.activities.dto.response.PlanResponseDto;
import lombok.Data;
import org.bouncycastle.pqc.crypto.newhope.NHOtherInfoGenerator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @PostMapping
    public CommonResponse createPlan(@RequestBody PlanRequestDto planRequestDto) {
        return new CommonResponse();
    }

    @PatchMapping
    public CommonResponse updatePlan(@RequestBody PlanRequestDto planRequestDto) {
        return new CommonResponse();
    }

    @GetMapping
    public CommonResponse<List<PlanResponseDto>> getPlan(@RequestParam int userId) {
        return new CommonResponse();
    }

    @DeleteMapping
    public CommonResponse deletePlan(@RequestParam int id) {
        return new CommonResponse();
    }

    @GetMapping("/top")
    public CommonResponse topPlan(@RequestParam int userId, @RequestParam int planId) {
        return new CommonResponse();
    }
}
