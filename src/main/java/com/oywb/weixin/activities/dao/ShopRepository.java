package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.dto.response.ShopSimpleDto;
import com.oywb.weixin.activities.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.NamedNativeQuery;
import java.util.List;


public interface ShopRepository extends JpaRepository<ShopEntity, Long> {

    boolean existsByIdAndUserId(Long id, Long userId);

    @Modifying
    @Query(nativeQuery = true, value = "update shop set pass = ?2 where id in (?1)")
    void passShop(List<Long> ids, byte pass);

    @Modifying
    @Query(nativeQuery = true, value = "update shop set status = ?2 where id = ?1")
    void updataStatus(Long shopId, int status);
}
