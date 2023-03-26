package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.DynamicsRequestDto;
import com.oywb.weixin.activities.dto.response.DynamicsResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dynamics")
public class DynamicsController {

    @PostMapping
    public CommonResponse createDynamics(@RequestBody DynamicsRequestDto dynamicsRequestDto) {
        return new CommonResponse();
    }

    @GetMapping
    public CommonResponse<List<DynamicsResponseDto>> getDynamics(int type) {
        return new CommonResponse<>();
    }
}
