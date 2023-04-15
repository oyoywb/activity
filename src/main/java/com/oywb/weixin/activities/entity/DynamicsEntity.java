package com.oywb.weixin.activities.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "dynamics", schema = "oywb_test", catalog = "")
public class DynamicsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "picture")
    private String picture;
    @Basic
    @Column(name = "keyword")
    private String keyword;
    @Basic
    @Column(name = "anonymous")
    private byte anonymous;
    @Basic
    @Column(name = "provided")
    private byte provided;
    @Basic
    @Column(name = "pass")
    private byte pass;
    @Basic
    @Column(name = "likes")
    private long likes;
    @Basic
    @Column(name = "create_ts")
    private Timestamp createTs;
    @Basic
    @Column(name = "user_id")
    private Long userId;

    @Basic
    @Column(name = "contact")
    private String contact;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public byte getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(byte anonymous) {
        this.anonymous = anonymous;
    }

    public byte getProvided() {
        return provided;
    }

    public void setProvided(byte provided) {
        this.provided = provided;
    }

    public byte getPass() {
        return pass;
    }

    public void setPass(byte pass) {
        this.pass = pass;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public Timestamp getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Timestamp createTs) {
        this.createTs = createTs;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DynamicsEntity that = (DynamicsEntity) o;
        return id == that.id && anonymous == that.anonymous && provided == that.provided && pass == that.pass && likes == that.likes && Objects.equals(content, that.content) && Objects.equals(picture, that.picture) && Objects.equals(keyword, that.keyword) && Objects.equals(createTs, that.createTs) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, picture, keyword, anonymous, provided, pass, likes, createTs, userId);
    }
}
