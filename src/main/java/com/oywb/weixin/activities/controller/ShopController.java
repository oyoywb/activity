package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ShopRequestDto;
import com.oywb.weixin.activities.dto.response.ShopResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @PostMapping
    public CommonResponse createShop(@RequestBody ShopRequestDto shopRequestDto) {
        return new CommonResponse();
    }

    @GetMapping
    public CommonResponse<List<ShopResponseDto>> getShops (@RequestParam int userId) {
        return new CommonResponse<List<ShopResponseDto>>();
    }

    @PatchMapping()
    public CommonResponse updateShop(@RequestBody ShopRequestDto shopRequestDto) {
        return new CommonResponse();
    }
}
