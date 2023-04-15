package com.oywb.weixin.activities.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "likes", schema = "oywb_test", catalog = "")
public class LikesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "user_id")
    private long userId;
    @Basic
    @Column(name = "dy_id")
    private long dyId;
    @Basic
    @Column(name = "likes")
    private byte likes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getDyId() {
        return dyId;
    }

    public void setDyId(long dyId) {
        this.dyId = dyId;
    }

    public byte getLikes() {
        return likes;
    }

    public void setLikes(byte likes) {
        this.likes = likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikesEntity that = (LikesEntity) o;
        return id == that.id && userId == that.userId && dyId == that.dyId && likes == that.likes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, dyId, likes);
    }
}
