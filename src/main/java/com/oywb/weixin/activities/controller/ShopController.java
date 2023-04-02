package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ShopRequestDto;
import com.oywb.weixin.activities.dto.response.ShopResponseDto;
import com.oywb.weixin.activities.service.ShopService;
import org.simpleframework.xml.core.Validate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping
    public CommonResponse createShop(@ModelAttribute ShopRequestDto shopRequestDto, @RequestParam(value = "files", required = false) MultipartFile[] files) throws Exception {
        return shopService.createShop(shopRequestDto, Arrays.asList(files));
    }

 /*   @GetMapping
    public CommonResponse<List<ShopResponseDto>> getShops (@RequestParam int userId) {
        return new CommonResponse<List<ShopResponseDto>>();
    }

    @PatchMapping()
    public CommonResponse updateShop(@RequestBody ShopRequestDto shopRequestDto) {
        return new CommonResponse();
    }*/
}
