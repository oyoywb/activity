package com.oywb.weixin.activities.entity;

import com.oywb.weixin.activities.dto.response.SellerResponseDto;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "seller")
@DynamicInsert
@DynamicUpdate
public class SellerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "price")
    private BigDecimal price;
    @Basic
    @Column(name = "discription")
    private String discription;
    @Basic
    @Column(name = "picture")
    private String picture;
    @Basic
    @Column(name = "shop_id")
    private long shopId;
    @Basic
    @Column(name = "create_ts")
    private Timestamp createTs;
    @Basic
    @Column(name = "update_ts")
    private Timestamp updateTs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public Timestamp getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Timestamp createTs) {
        this.createTs = createTs;
    }

    public Timestamp getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Timestamp updateTs) {
        this.updateTs = updateTs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellerEntity that = (SellerEntity) o;
        return id == that.id && shopId == that.shopId && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(discription, that.discription) && Objects.equals(picture, that.picture) && Objects.equals(createTs, that.createTs) && Objects.equals(updateTs, that.updateTs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, discription, picture, shopId, createTs, updateTs);
    }

    public SellerResponseDto toSellerResponseDto() {
        SellerResponseDto sellerResponseDto = new SellerResponseDto();
        sellerResponseDto.setId(this.id);
        sellerResponseDto.setName(this.name);
        sellerResponseDto.setPrice(this.price);
        sellerResponseDto.setDescription(this.discription);
        sellerResponseDto.setPicture(Arrays.asList(this.picture.split(",")));

        return sellerResponseDto;
    }
}
