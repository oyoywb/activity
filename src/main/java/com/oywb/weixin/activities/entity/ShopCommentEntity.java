package com.oywb.weixin.activities.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "shop_comment", schema = "oywb_test", catalog = "")
public class ShopCommentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "shop_id")
    private long shopId;
    @Basic
    @Column(name = "user_id")
    private long userId;
    @Basic
    @Column(name = "score")
    private int score;
    @Basic
    @Column(name = "ts")
    private Timestamp ts;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "seller_id")
    private long sellerId;
    @Basic
    @Column(name = "picture")
    private String picture;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Timestamp getTs() {
        return ts;
    }

    public void setTs(Timestamp ts) {
        this.ts = ts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopCommentEntity that = (ShopCommentEntity) o;
        return id == that.id && shopId == that.shopId && userId == that.userId && score == that.score && sellerId == that.sellerId && Objects.equals(ts, that.ts) && Objects.equals(content, that.content) && Objects.equals(picture, that.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shopId, userId, score, ts, content, sellerId, picture);
    }
}
