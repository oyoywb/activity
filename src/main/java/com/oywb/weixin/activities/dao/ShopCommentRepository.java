package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.ShopCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopCommentRepository extends JpaRepository<ShopCommentEntity, Long> {

    @Query(value = "select * from shop_comment where shop_id =:shopId", nativeQuery = true)
    public List<ShopCommentEntity> getShopCommentEntitiesByShopId(@Param("shopId") Long shopId);
}
