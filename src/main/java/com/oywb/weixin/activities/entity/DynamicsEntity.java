package com.oywb.weixin.activities.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dynamics", schema = "oywb_test", catalog = "")
public class DynamicsEntity {
    private long id;
    private String content;
    private String picture;
    private String keyword;
    private byte anonymous;
    private byte provided;
    private byte pass;
    private long likes;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content", nullable = false, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "picture", nullable = false, length = -1)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "keyword", nullable = false, length = -1)
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Basic
    @Column(name = "anonymous", nullable = false)
    public byte getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(byte anonymous) {
        this.anonymous = anonymous;
    }

    @Basic
    @Column(name = "provided", nullable = false)
    public byte getProvided() {
        return provided;
    }

    public void setProvided(byte provided) {
        this.provided = provided;
    }

    @Basic
    @Column(name = "pass", nullable = false)
    public byte getPass() {
        return pass;
    }

    public void setPass(byte pass) {
        this.pass = pass;
    }

    @Basic
    @Column(name = "likes", nullable = false)
    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DynamicsEntity that = (DynamicsEntity) o;
        return id == that.id &&
                anonymous == that.anonymous &&
                provided == that.provided &&
                pass == that.pass &&
                likes == that.likes &&
                Objects.equals(content, that.content) &&
                Objects.equals(picture, that.picture) &&
                Objects.equals(keyword, that.keyword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, picture, keyword, anonymous, provided, pass, likes);
    }
}
