package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ShopCommentRequestDto;
import com.oywb.weixin.activities.dto.request.ShopRequestDto;
import com.oywb.weixin.activities.dto.response.ShopResponseDto;
import com.oywb.weixin.activities.service.ShopService;
import org.simpleframework.xml.core.Validate;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/shop")
@PreAuthorize("@roleEvaluator.isRegistered(authentication)")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping
    public CommonResponse createShop(@ModelAttribute ShopRequestDto shopRequestDto, @RequestParam(value = "files", required = false) MultipartFile[] files) throws Exception {
        return shopService.createShop(shopRequestDto, Arrays.asList(files));
    }

    @PreAuthorize("@roleEvaluator.shopBelongToUser(authentication, #shopRequestDto.id, #shopRequestDto.userId)")
    @PatchMapping
    public CommonResponse updateShop(@ModelAttribute ShopRequestDto shopRequestDto, @RequestParam(value = "files", required = false) MultipartFile[] files) throws Exception {
        return shopService.updateShop(shopRequestDto, Arrays.asList(files));
    }

    @PostMapping("/comment")
    public CommonResponse shopComment(@ModelAttribute ShopCommentRequestDto shopCommentRequestDto, @RequestParam(value = "files", required = false) MultipartFile[] files) throws Exception {
        return shopService.createComment(shopCommentRequestDto, Arrays.asList(files));

    }

    @GetMapping
    public CommonResponse getShops(@RequestParam (required = false) Long userId, @RequestParam (required = false) String school, @RequestParam (required = false) String zone
            , @RequestParam (required = false) String type, @RequestParam(required = false) Pageable pageable) throws Exception {
        return shopService.getShopSimple(userId, school, zone, type, pageable);
    }

    @GetMapping("/seller")
    public CommonResponse getSellers(@RequestParam Long shopId) throws Exception {
        return shopService.getSeller(shopId);
    }

    @GetMapping("/comment")
    public CommonResponse getComment(@RequestParam Long shopId) throws Exception {
        return shopService.getComments(shopId);
    }
}
