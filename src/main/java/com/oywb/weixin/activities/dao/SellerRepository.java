package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SellerRepository extends JpaRepository<SellerEntity, Long> {

    @Query(value = "select * from seller where shop_id = ?1", nativeQuery = true)
    public List<SellerEntity> findAllByShopId(long shopId);
}
