package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.dto.response.ShopCommentResDto;
import com.oywb.weixin.activities.dto.response.ShopCommentResponseDto;
import com.oywb.weixin.activities.entity.ShopCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopCommentRepository extends JpaRepository<ShopCommentEntity, Long> {

    @Query(value = "select s.*, u.name, u.profile from shop_comment s, user u where shop_id =:shopId and s.user_id = u.id order by ts desc", nativeQuery = true)
    public List<ShopCommentResDto> getShopCommentEntitiesByShopId(@Param("shopId") Long shopId);
}
