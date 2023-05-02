package com.oywb.weixin.activities.controller;

import com.oywb.weixin.activities.dto.CommonResponse;
import com.oywb.weixin.activities.dto.request.ShopCommentRequestDto;
import com.oywb.weixin.activities.dto.request.ShopRequestDto;
import com.oywb.weixin.activities.dto.response.SellerResponseDto;
import com.oywb.weixin.activities.dto.response.ShopCommentResponseDto;
import com.oywb.weixin.activities.dto.response.ShopResponseDto;
import com.oywb.weixin.activities.dto.response.ShopSimpleDto;
import com.oywb.weixin.activities.service.ShopService;
import org.simpleframework.xml.core.Validate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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

    //tested
    @PostMapping
    public void createShop(@ModelAttribute ShopRequestDto shopRequestDto, @RequestParam(value = "files", required = false) MultipartFile[] files, Authentication authentication) throws Exception {
        shopService.createShop(shopRequestDto, Arrays.asList(files), authentication.getName());
    }

    //tested
    @PreAuthorize("@roleEvaluator.shopBelongToUser(authentication, #shopRequestDto.id, #shopRequestDto.userId)")
    @PatchMapping
    public void updateShop(@ModelAttribute ShopRequestDto shopRequestDto, @RequestParam(value = "files", required = false) MultipartFile[] files) throws Exception {
        shopService.updateShop(shopRequestDto, Arrays.asList(files));
    }

    //tested
    @PostMapping("/comment")
    public void shopComment(@ModelAttribute ShopCommentRequestDto shopCommentRequestDto, @RequestParam(value = "files", required = false) MultipartFile[] files, Authentication authentication) throws Exception {
        shopService.createComment(shopCommentRequestDto, Arrays.asList(files), authentication.getName());

    }

    //tested
    @GetMapping
    public Page<ShopSimpleDto> getShops(Authentication authentication, @RequestParam (required = false) String school, @RequestParam (required = false) String zone
            , @RequestParam (required = false) String type, Pageable pageable, int flag) throws Exception {
        return shopService.getShopSimple(authentication.getName(), school, zone, type, pageable, flag);
    }

    //tested
    @GetMapping("/seller")
    public List<SellerResponseDto> getSellers(@RequestParam Long shopId) throws Exception {
        return shopService.getSeller(shopId);
    }

    //tested
    @GetMapping("/comment")
    public List<ShopCommentResponseDto> getComment(@RequestParam Long shopId) throws Exception {
        return shopService.getComments(shopId);
    }
}
